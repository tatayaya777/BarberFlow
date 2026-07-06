import { useEffect, useState } from "react";
import * as bootstrap from "bootstrap";
import Layout from "../../components/layout/Layout";
import api from "../../services/api";


function Barberos() {

    const barberoVacio = {
        nombre: "",
        especialidad: "",
        email: ""
    };

    const [barberos, setBarberos] = useState([]);

    const [barbero, setBarbero] = useState(barberoVacio);

    const [editando, setEditando] = useState(false);

    const [buscar, setBuscar] = useState("");

    useEffect(() => {

        cargarBarberos();

    }, []);

    const cargarBarberos = async () => {

        try {

            const response = await api.get("/barberos");

            setBarberos(response.data);

        } catch (error) {

            console.error("Error al cargar barberos", error);

        }

    };

    const guardarBarbero = async () => {

        try {

            if (editando) {

                await api.put(`/barberos/${barbero.id}`, barbero);

            } else {

                await api.post("/barberos", barbero);

            }

            cerrarModal();

            cargarBarberos();

        } catch (error) {

            console.error(error);

            alert("Error al guardar el barbero.");

        }

    };

    const eliminarBarbero = async (id) => {

        const confirmar = window.confirm(
            "¿Desea eliminar este barbero?"
        );

        if (!confirmar) return;

        try {

            await api.delete(`/barberos/${id}`);

            cargarBarberos();

        } catch (error) {

            console.error(error);

            alert("No se pudo eliminar.");

        }

    };

    const editarBarbero = (barberoSeleccionado) => {

        setBarbero(barberoSeleccionado);

        setEditando(true);

        const modal = new bootstrap.Modal(
            document.getElementById("modalBarbero")
        );

        modal.show();

    };

    const nuevoBarbero = () => {

        setBarbero(barberoVacio);

        setEditando(false);

        const modal = new bootstrap.Modal(
            document.getElementById("modalBarbero")
        );

        modal.show();

    };

    const cerrarModal = () => {

        const modal = bootstrap.Modal.getInstance(
            document.getElementById("modalBarbero")
        );

        modal.hide();

        setBarbero(barberoVacio);

        setEditando(false);

    };

    const barberosFiltrados = barberos.filter((b) =>

        b.nombre.toLowerCase().includes(buscar.toLowerCase()) ||

        b.especialidad.toLowerCase().includes(buscar.toLowerCase()) ||

        b.email.toLowerCase().includes(buscar.toLowerCase())

    );
        return (

        <Layout>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>💈 Barberos</h2>

                <button
                    className="btn btn-success"
                    onClick={nuevoBarbero}
                >
                    + Agregar Barbero
                </button>

            </div>

            <div className="card shadow border-0">

                <div className="card-body">

                    <div className="row mb-3">

                        <div className="col-md-4">

                            <input

                                type="text"

                                className="form-control"

                                placeholder="Buscar barbero..."

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

                                <th>Especialidad</th>

                                <th>Email</th>

                                <th width="180">

                                    Acciones

                                </th>

                            </tr>

                        </thead>

                        <tbody>

                            {

                                barberosFiltrados.map((barbero) => (

                                    <tr key={barbero.id}>

                                        <td>{barbero.id}</td>

                                        <td>{barbero.nombre}</td>

                                        <td>{barbero.especialidad}</td>

                                        <td>{barbero.email}</td>

                                        <td>

                                            <button

                                                className="btn btn-warning btn-sm me-2"

                                                onClick={() => editarBarbero(barbero)}

                                            >

                                                ✏️ Editar

                                            </button>

                                            <button

                                                className="btn btn-danger btn-sm"

                                                onClick={() => eliminarBarbero(barbero.id)}

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
                id="modalBarbero"
                tabIndex="-1"
            >

                <div className="modal-dialog">

                    <div className="modal-content">

                        <div className="modal-header">

                            <h5>

                                {

                                    editando

                                        ? "Editar Barbero"

                                        : "Nuevo Barbero"

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

                                    value={barbero.nombre}

                                    onChange={(e) =>
                                        setBarbero({
                                            ...barbero,
                                            nombre: e.target.value
                                        })
                                    }

                                />

                            </div>

                            <div className="mb-3">

                                <label>

                                    Especialidad

                                </label>

                                <input

                                    className="form-control"

                                    value={barbero.especialidad}

                                    onChange={(e) =>
                                        setBarbero({
                                            ...barbero,
                                            especialidad: e.target.value
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

                                    value={barbero.email}

                                    onChange={(e) =>
                                        setBarbero({
                                            ...barbero,
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

                                onClick={guardarBarbero}

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

export default Barberos;