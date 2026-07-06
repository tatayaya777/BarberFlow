import { useEffect, useState } from "react";
import * as bootstrap from "bootstrap";
import Layout from "../../components/layout/Layout";
import api from "../../services/api";


function Reservas() {

    const reservaVacia = {
        fecha: "",
        hora: "",
        estado: ""
    };

    const [reservas, setReservas] = useState([]);

    const [reserva, setReserva] = useState(reservaVacia);

    const [editando, setEditando] = useState(false);

    const [buscar, setBuscar] = useState("");

    useEffect(() => {

        cargarReservas();

    }, []);

    const cargarReservas = async () => {

        try {

            const response = await api.get("/reservas");

            setReservas(response.data);

        } catch (error) {

            console.error("Error al cargar reservas", error);

        }

    };

    const guardarReserva = async () => {

        try {

            if (editando) {

                await api.put(`/reservas/${reserva.id}`, reserva);

            } else {

                await api.post("/reservas", reserva);

            }

            cerrarModal();

            cargarReservas();

        } catch (error) {

            console.error(error);

            alert("Error al guardar la reserva.");

        }

    };

    const eliminarReserva = async (id) => {

        const confirmar = window.confirm(
            "¿Desea eliminar esta reserva?"
        );

        if (!confirmar) return;

        try {

            await api.delete(`/reservas/${id}`);

            cargarReservas();

        } catch (error) {

            console.error(error);

            alert("No se pudo eliminar.");

        }

    };

    const editarReserva = (reservaSeleccionada) => {

        setReserva(reservaSeleccionada);

        setEditando(true);

        const modal = new bootstrap.Modal(
            document.getElementById("modalReserva")
        );

        modal.show();

    };

    const nuevaReserva = () => {

        setReserva(reservaVacia);

        setEditando(false);

        const modal = new bootstrap.Modal(
            document.getElementById("modalReserva")
        );

        modal.show();

    };

    const cerrarModal = () => {

        const modal = bootstrap.Modal.getInstance(
            document.getElementById("modalReserva")
        );

        modal.hide();

        setReserva(reservaVacia);

        setEditando(false);

    };

    const reservasFiltradas = reservas.filter((r) =>

        r.fecha.toLowerCase().includes(buscar.toLowerCase()) ||

        r.hora.toLowerCase().includes(buscar.toLowerCase()) ||

        r.estado.toLowerCase().includes(buscar.toLowerCase())

    );
        return (

        <Layout>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>📅 Reservas</h2>

                <button
                    className="btn btn-success"
                    onClick={nuevaReserva}
                >
                    + Agregar Reserva
                </button>

            </div>

            <div className="card shadow border-0">

                <div className="card-body">

                    <div className="row mb-3">

                        <div className="col-md-4">

                            <input

                                type="text"

                                className="form-control"

                                placeholder="Buscar reserva..."

                                value={buscar}

                                onChange={(e) => setBuscar(e.target.value)}

                            />

                        </div>

                    </div>

                    <table className="table table-hover table-striped align-middle">

                        <thead className="table-dark">

                            <tr>

                                <th>ID</th>

                                <th>Fecha</th>

                                <th>Hora</th>

                                <th>Estado</th>

                                <th width="180">

                                    Acciones

                                </th>

                            </tr>

                        </thead>

                        <tbody>

                            {

                                reservasFiltradas.map((reserva) => (

                                    <tr key={reserva.id}>

                                        <td>{reserva.id}</td>

                                        <td>{reserva.fecha}</td>

                                        <td>{reserva.hora}</td>

                                        <td>{reserva.estado}</td>

                                        <td>

                                            <button

                                                className="btn btn-warning btn-sm me-2"

                                                onClick={() => editarReserva(reserva)}

                                            >

                                                ✏️ Editar

                                            </button>

                                            <button

                                                className="btn btn-danger btn-sm"

                                                onClick={() => eliminarReserva(reserva.id)}

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
                id="modalReserva"
                tabIndex="-1"
            >

                <div className="modal-dialog">

                    <div className="modal-content">

                        <div className="modal-header">

                            <h5>

                                {

                                    editando

                                        ? "Editar Reserva"

                                        : "Nueva Reserva"

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

                                    Fecha

                                </label>

                                <input

                                    type="date"

                                    className="form-control"

                                    value={reserva.fecha}

                                    onChange={(e) =>
                                        setReserva({
                                            ...reserva,
                                            fecha: e.target.value
                                        })
                                    }

                                />

                            </div>

                            <div className="mb-3">

                                <label>

                                    Hora

                                </label>

                                <input

                                    type="time"

                                    className="form-control"

                                    value={reserva.hora}

                                    onChange={(e) =>
                                        setReserva({
                                            ...reserva,
                                            hora: e.target.value
                                        })
                                    }

                                />

                            </div>

                            <div className="mb-3">

                                <label>

                                    Estado

                                </label>

                                <select

                                    className="form-select"

                                    value={reserva.estado}

                                    onChange={(e) =>
                                        setReserva({
                                            ...reserva,
                                            estado: e.target.value
                                        })
                                    }

                                >

                                    <option value="">
                                        Seleccione...
                                    </option>

                                    <option value="Pendiente">
                                        Pendiente
                                    </option>

                                    <option value="Confirmada">
                                        Confirmada
                                    </option>

                                    <option value="Finalizada">
                                        Finalizada
                                    </option>

                                    <option value="Cancelada">
                                        Cancelada
                                    </option>

                                </select>

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

                                onClick={guardarReserva}

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

export default Reservas;