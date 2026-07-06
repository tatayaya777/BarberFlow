import { useEffect, useState } from "react";
import Layout from "../../components/layout/Layout";
import api from "../../services/api";
import * as bootstrap from "bootstrap";


function Clientes() {

    const clienteVacio = {
        nombre: "",
        telefono: "",
        email: ""
    };

    const [clientes, setClientes] = useState([]);

    const [cliente, setCliente] = useState(clienteVacio);

    const [editando, setEditando] = useState(false);

    const [buscar, setBuscar] = useState("");

    useEffect(() => {
        cargarClientes();
    }, []);

    const cargarClientes = async () => {

        try {

            const response = await api.get("/clientes");

            setClientes(response.data);

        } catch (error) {

            console.error("Error al cargar clientes", error);

        }

    };

    const guardarCliente = async () => {

        try {

            if (editando) {

                await api.put(`/clientes/${cliente.id}`, cliente);

            } else {

                await api.post("/clientes", cliente);

            }

            cerrarModal();

            cargarClientes();

        } catch (error) {

            console.error(error);

            alert("Error al guardar el cliente.");

        }

    };

    const eliminarCliente = async (id) => {

        const confirmar = window.confirm(
            "¿Desea eliminar este cliente?"
        );

        if (!confirmar) return;

        try {

            await api.delete(`/clientes/${id}`);

            cargarClientes();

        } catch (error) {

            console.error(error);

            alert("No se pudo eliminar.");

        }

    };

    const editarCliente = (clienteSeleccionado) => {

        setCliente(clienteSeleccionado);

        setEditando(true);

        const modal = new bootstrap.Modal(
            document.getElementById("modalCliente")
        );

        modal.show();

    };

    const nuevoCliente = () => {

        setCliente(clienteVacio);

        setEditando(false);

        const modal = new bootstrap.Modal(
            document.getElementById("modalCliente")
        );

        modal.show();

    };

    const cerrarModal = () => {

        const modal = bootstrap.Modal.getInstance(
            document.getElementById("modalCliente")
        );

        modal.hide();

        setCliente(clienteVacio);

        setEditando(false);

    };

    const clientesFiltrados = clientes.filter((c) =>

        c.nombre.toLowerCase().includes(buscar.toLowerCase()) ||

        c.email.toLowerCase().includes(buscar.toLowerCase()) ||

        c.telefono.toLowerCase().includes(buscar.toLowerCase())

    );
      return (

        <Layout>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>👤 Clientes</h2>

                <button
                    className="btn btn-success"
                    onClick={nuevoCliente}
                >
                    + Agregar Cliente
                </button>

            </div>

            <div className="card shadow border-0">

                <div className="card-body">

                    <div className="row mb-3">

                        <div className="col-md-4">

                            <input

                                type="text"

                                className="form-control"

                                placeholder="Buscar cliente..."

                                value={buscar}

                                onChange={(e) => setBuscar(e.target.value)}

                            />

                        </div>

                    </div>

                    <table className="table table-hover table-striped align-middle">

                        <thead className="table-dark">

                            <tr>

                                <th>ID</th>

                                <th>Nombre</th>

                                <th>Teléfono</th>

                                <th>Email</th>

                                <th width="170">

                                    Acciones

                                </th>

                            </tr>

                        </thead>

                        <tbody>

                            {

                                clientesFiltrados.map((cliente) => (

                                    <tr key={cliente.id}>

                                        <td>

                                            {cliente.id}

                                        </td>

                                        <td>

                                            {cliente.nombre}

                                        </td>

                                        <td>

                                            {cliente.telefono}

                                        </td>

                                        <td>

                                            {cliente.email}

                                        </td>

                                        <td>

                                            <button

                                                className="btn btn-warning btn-sm me-2"

                                                onClick={() => editarCliente(cliente)}

                                            >

                                                ✏️

                                            </button>

                                            <button

                                                className="btn btn-danger btn-sm"

                                                onClick={() => eliminarCliente(cliente.id)}

                                            >

                                                🗑️

                                            </button>

                                        </td>

                                    </tr>

                                ))

                            }

                        </tbody>

                    </table>

                </div>

            </div>

            <div
                className="modal fade"
                id="modalCliente"
                tabIndex="-1"
            >

                <div className="modal-dialog">

                    <div className="modal-content">

                        <div className="modal-header">

                            <h5>

                                {

                                    editando

                                        ? "Editar Cliente"

                                        : "Nuevo Cliente"

                                }

                            </h5>

                            <button

                                className="btn-close"

                                data-bs-dismiss="modal"

                            ></button>

                        </div>

                        <div className="modal-body">

                            <div className="mb-3">

                                <label>

                                    Nombre

                                </label>

                                <input

                                    className="form-control"

                                    value={cliente.nombre}

                                    onChange={(e) =>
                                        setCliente({
                                            ...cliente,
                                            nombre: e.target.value
                                        })
                                    }

                                />

                            </div>

                            <div className="mb-3">

                                <label>

                                    Teléfono

                                </label>

                                <input

                                    className="form-control"

                                    value={cliente.telefono}

                                    onChange={(e) =>
                                        setCliente({
                                            ...cliente,
                                            telefono: e.target.value
                                        })
                                    }

                                />

                            </div>

                            <div className="mb-3">

                                <label>

                                    Email

                                </label>

                                <input

                                    className="form-control"

                                    value={cliente.email}

                                    onChange={(e) =>
                                        setCliente({
                                            ...cliente,
                                            email: e.target.value
                                        })
                                    }

                                />

                            </div>

                        </div>

                        <div className="modal-footer">

                            <button

                                className="btn btn-secondary"

                                data-bs-dismiss="modal"

                            >

                                Cancelar

                            </button>

                            <button

                                className="btn btn-success"

                                onClick={guardarCliente}

                            >

                                Guardar

                            </button>

                        </div>

                    </div>

                </div>

            </div>
    </Layout>

    );

}

export default Clientes;