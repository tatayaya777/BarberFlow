import { useEffect, useState } from "react";
import * as bootstrap from "bootstrap";
import Layout from "../../components/layout/Layout";
import api from "../../services/api";


function Servicios() {

    const servicioVacio = {
        nombre: "",
        precio: "",
        duracion: ""
    };

    const [servicios, setServicios] = useState([]);

    const [servicio, setServicio] = useState(servicioVacio);

    const [editando, setEditando] = useState(false);

    const [buscar, setBuscar] = useState("");

    useEffect(() => {

        cargarServicios();

    }, []);

    const cargarServicios = async () => {

        try {

            const response = await api.get("/servicios");

            setServicios(response.data);

        } catch (error) {

            console.error("Error al cargar servicios", error);

        }

    };

    const guardarServicio = async () => {

        try {

            if (editando) {

                await api.put(`/servicios/${servicio.id}`, servicio);

            } else {

                await api.post("/servicios", servicio);

            }

            cerrarModal();

            cargarServicios();

        } catch (error) {

            console.error(error);

            alert("Error al guardar el servicio.");

        }

    };

    const eliminarServicio = async (id) => {

        const confirmar = window.confirm(
            "¿Desea eliminar este servicio?"
        );

        if (!confirmar) return;

        try {

            await api.delete(`/servicios/${id}`);

            cargarServicios();

        } catch (error) {

            console.error(error);

            alert("No se pudo eliminar.");

        }

    };

    const editarServicio = (servicioSeleccionado) => {

        setServicio(servicioSeleccionado);

        setEditando(true);

        const modal = new bootstrap.Modal(
            document.getElementById("modalServicio")
        );

        modal.show();

    };

    const nuevoServicio = () => {

        setServicio(servicioVacio);

        setEditando(false);

        const modal = new bootstrap.Modal(
            document.getElementById("modalServicio")
        );

        modal.show();

    };

    const cerrarModal = () => {

        const modal = bootstrap.Modal.getInstance(
            document.getElementById("modalServicio")
        );

        modal.hide();

        setServicio(servicioVacio);

        setEditando(false);

    };

    const serviciosFiltrados = servicios.filter((s) =>

        s.nombre.toLowerCase().includes(buscar.toLowerCase()) ||

        s.precio.toString().includes(buscar) ||

        s.duracion.toString().includes(buscar)

    );
        return (

        <Layout>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>✂️ Servicios</h2>

                <button
                    className="btn btn-success"
                    onClick={nuevoServicio}
                >
                    + Agregar Servicio
                </button>

            </div>

            <div className="card shadow border-0">

                <div className="card-body">

                    <div className="row mb-3">

                        <div className="col-md-4">

                            <input

                                type="text"

                                className="form-control"

                                placeholder="Buscar servicio..."

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

                                <th>Precio</th>

                                <th>Duración</th>

                                <th width="180">

                                    Acciones

                                </th>

                            </tr>

                        </thead>

                        <tbody>

                            {

                                serviciosFiltrados.map((servicio) => (

                                    <tr key={servicio.id}>

                                        <td>{servicio.id}</td>

                                        <td>{servicio.nombre}</td>

                                        <td>${servicio.precio}</td>

                                        <td>{servicio.duracion} min</td>

                                        <td>

                                            <button

                                                className="btn btn-warning btn-sm me-2"

                                                onClick={() => editarServicio(servicio)}

                                            >

                                                ✏️ Editar

                                            </button>

                                            <button

                                                className="btn btn-danger btn-sm"

                                                onClick={() => eliminarServicio(servicio.id)}

                                            >

                                                🗑️ Eliminar

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
                id="modalServicio"
                tabIndex="-1"
            >

                <div className="modal-dialog">

                    <div className="modal-content">

                        <div className="modal-header">

                            <h5>

                                {

                                    editando

                                        ? "Editar Servicio"

                                        : "Nuevo Servicio"

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

                                    value={servicio.nombre}

                                    onChange={(e) =>
                                        setServicio({
                                            ...servicio,
                                            nombre: e.target.value
                                        })
                                    }

                                />

                            </div>

                            <div className="mb-3">

                                <label>

                                    Precio

                                </label>

                                <input

                                    type="number"

                                    className="form-control"

                                    value={servicio.precio}

                                    onChange={(e) =>
                                        setServicio({
                                            ...servicio,
                                            precio: e.target.value
                                        })
                                    }

                                />

                            </div>

                            <div className="mb-3">

                                <label>

                                    Duración (minutos)

                                </label>

                                <input

                                    type="number"

                                    className="form-control"

                                    value={servicio.duracion}

                                    onChange={(e) =>
                                        setServicio({
                                            ...servicio,
                                            duracion: e.target.value
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

                                onClick={guardarServicio}

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

export default Servicios;