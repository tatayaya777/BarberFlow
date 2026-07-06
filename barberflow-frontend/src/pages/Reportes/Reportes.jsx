import { useEffect, useState } from "react";
import * as bootstrap from "bootstrap";
import Layout from "../../components/layout/Layout";
import api from "../../services/api";


function Reportes() {

    const reporteVacio = {
        fecha: "",
        ingresos: "",
        reservas: ""
    };

    const [reportes, setReportes] = useState([]);

    const [reporte, setReporte] = useState(reporteVacio);

    const [editando, setEditando] = useState(false);

    const [buscar, setBuscar] = useState("");

    useEffect(() => {

        cargarReportes();

    }, []);

    const cargarReportes = async () => {

        try {

            const response = await api.get("/reportes");

            setReportes(response.data);

        } catch (error) {

            console.error("Error al cargar reportes", error);

        }

    };

    const guardarReporte = async () => {

        try {

            if (editando) {

                await api.put(`/reportes/${reporte.id}`, reporte);

            } else {

                await api.post("/reportes", reporte);

            }

            cerrarModal();

            cargarReportes();

        } catch (error) {

            console.error(error);

            alert("Error al guardar el reporte.");

        }

    };

    const eliminarReporte = async (id) => {

        const confirmar = window.confirm(
            "¿Desea eliminar este reporte?"
        );

        if (!confirmar) return;

        try {

            await api.delete(`/reportes/${id}`);

            cargarReportes();

        } catch (error) {

            console.error(error);

            alert("No se pudo eliminar.");

        }

    };

    const editarReporte = (reporteSeleccionado) => {

        setReporte(reporteSeleccionado);

        setEditando(true);

        const modal = new bootstrap.Modal(
            document.getElementById("modalReporte")
        );

        modal.show();

    };

    const nuevoReporte = () => {

        setReporte(reporteVacio);

        setEditando(false);

        const modal = new bootstrap.Modal(
            document.getElementById("modalReporte")
        );

        modal.show();

    };

    const cerrarModal = () => {

        const modal = bootstrap.Modal.getInstance(
            document.getElementById("modalReporte")
        );

        modal.hide();

        setReporte(reporteVacio);

        setEditando(false);

    };

    const reportesFiltrados = reportes.filter((r) =>

        r.fecha.toLowerCase().includes(buscar.toLowerCase()) ||

        r.ingresos.toString().includes(buscar) ||

        r.reservas.toString().includes(buscar)

    );
        return (

        <Layout>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>📊 Reportes</h2>

                <button
                    className="btn btn-success"
                    onClick={nuevoReporte}
                >
                    + Agregar Reporte
                </button>

            </div>

            <div className="card shadow border-0">

                <div className="card-body">

                    <div className="row mb-3">

                        <div className="col-md-4">

                            <input
                                type="text"
                                className="form-control"
                                placeholder="Buscar reporte..."
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

                                <th>Ingresos</th>

                                <th>Reservas</th>

                                <th width="180">

                                    Acciones

                                </th>

                            </tr>

                        </thead>

                        <tbody>

                            {

                                reportesFiltrados.map((reporte) => (

                                    <tr key={reporte.id}>

                                        <td>{reporte.id}</td>

                                        <td>{reporte.fecha}</td>

                                        <td>${reporte.ingresos}</td>

                                        <td>{reporte.reservas}</td>

                                        <td>

                                            <button

                                                className="btn btn-warning btn-sm me-2"

                                                onClick={() => editarReporte(reporte)}

                                            >

                                                ✏️ Editar

                                            </button>

                                            <button

                                                className="btn btn-danger btn-sm"

                                                onClick={() => eliminarReporte(reporte.id)}

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
                id="modalReporte"
                tabIndex="-1"
            >

                <div className="modal-dialog">

                    <div className="modal-content">

                        <div className="modal-header">

                            <h5>

                                {

                                    editando

                                        ? "Editar Reporte"

                                        : "Nuevo Reporte"

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

                                    value={reporte.fecha}

                                    onChange={(e) =>
                                        setReporte({
                                            ...reporte,
                                            fecha: e.target.value
                                        })
                                    }

                                />

                            </div>

                            <div className="mb-3">

                                <label>

                                    Ingresos

                                </label>

                                <input

                                    type="number"

                                    className="form-control"

                                    value={reporte.ingresos}

                                    onChange={(e) =>
                                        setReporte({
                                            ...reporte,
                                            ingresos: e.target.value
                                        })
                                    }

                                />

                            </div>

                            <div className="mb-3">

                                <label>

                                    Reservas

                                </label>

                                <input

                                    type="number"

                                    className="form-control"

                                    value={reporte.reservas}

                                    onChange={(e) =>
                                        setReporte({
                                            ...reporte,
                                            reservas: e.target.value
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

                                onClick={guardarReporte}

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

export default Reportes;