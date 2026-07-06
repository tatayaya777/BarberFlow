import { useEffect, useState } from "react";
import * as bootstrap from "bootstrap";
import Layout from "../../components/layout/Layout";
import api from "../../services/api";


function Pagos() {

    const pagoVacio = {
        monto: "",
        metodo: "",
        estado: ""
    };

    const [pagos, setPagos] = useState([]);

    const [pago, setPago] = useState(pagoVacio);

    const [editando, setEditando] = useState(false);

    const [buscar, setBuscar] = useState("");

    useEffect(() => {

        cargarPagos();

    }, []);

    const cargarPagos = async () => {

        try {

            const response = await api.get("/pagos");

            setPagos(response.data);

        } catch (error) {

            console.error("Error al cargar pagos", error);

        }

    };

    const guardarPago = async () => {

        try {

            if (editando) {

                await api.put(`/pagos/${pago.id}`, pago);

            } else {

                await api.post("/pagos", pago);

            }

            cerrarModal();

            cargarPagos();

        } catch (error) {

            console.error(error);

            alert("Error al guardar el pago.");

        }

    };

    const eliminarPago = async (id) => {

        const confirmar = window.confirm(
            "¿Desea eliminar este pago?"
        );

        if (!confirmar) return;

        try {

            await api.delete(`/pagos/${id}`);

            cargarPagos();

        } catch (error) {

            console.error(error);

            alert("No se pudo eliminar.");

        }

    };

    const editarPago = (pagoSeleccionado) => {

        setPago(pagoSeleccionado);

        setEditando(true);

        const modal = new bootstrap.Modal(
            document.getElementById("modalPago")
        );

        modal.show();

    };

    const nuevoPago = () => {

        setPago(pagoVacio);

        setEditando(false);

        const modal = new bootstrap.Modal(
            document.getElementById("modalPago")
        );

        modal.show();

    };

    const cerrarModal = () => {

        const modal = bootstrap.Modal.getInstance(
            document.getElementById("modalPago")
        );

        modal.hide();

        setPago(pagoVacio);

        setEditando(false);

    };

    const pagosFiltrados = pagos.filter((p) =>

        p.metodo.toLowerCase().includes(buscar.toLowerCase()) ||

        p.estado.toLowerCase().includes(buscar.toLowerCase()) ||

        p.monto.toString().includes(buscar)

    );
        return (

        <Layout>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>💳 Pagos</h2>

                <button
                    className="btn btn-success"
                    onClick={nuevoPago}
                >
                    + Agregar Pago
                </button>

            </div>

            <div className="card shadow border-0">

                <div className="card-body">

                    <div className="row mb-3">

                        <div className="col-md-4">

                            <input

                                type="text"

                                className="form-control"

                                placeholder="Buscar pago..."

                                value={buscar}

                                onChange={(e) => setBuscar(e.target.value)}

                            />

                        </div>

                    </div>

                    <table className="table table-hover table-striped align-middle">

                        <thead className="table-dark">

                            <tr>

                                <th>ID</th>

                                <th>Monto</th>

                                <th>Método</th>

                                <th>Estado</th>

                                <th width="180">

                                    Acciones

                                </th>

                            </tr>

                        </thead>

                        <tbody>

                            {

                                pagosFiltrados.map((pago) => (

                                    <tr key={pago.id}>

                                        <td>{pago.id}</td>

                                        <td>${pago.monto}</td>

                                        <td>{pago.metodo}</td>

                                        <td>{pago.estado}</td>

                                        <td>

                                            <button

                                                className="btn btn-warning btn-sm me-2"

                                                onClick={() => editarPago(pago)}

                                            >

                                                ✏️ Editar

                                            </button>

                                            <button

                                                className="btn btn-danger btn-sm"

                                                onClick={() => eliminarPago(pago.id)}

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
                id="modalPago"
                tabIndex="-1"
            >

                <div className="modal-dialog">

                    <div className="modal-content">

                        <div className="modal-header">

                            <h5>

                                {

                                    editando

                                        ? "Editar Pago"

                                        : "Nuevo Pago"

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

                                    Monto

                                </label>

                                <input

                                    type="number"

                                    className="form-control"

                                    value={pago.monto}

                                    onChange={(e) =>
                                        setPago({
                                            ...pago,
                                            monto: e.target.value
                                        })
                                    }

                                />

                            </div>

                            <div className="mb-3">

                                <label>

                                    Método

                                </label>

                                <input

                                    className="form-control"

                                    value={pago.metodo}

                                    onChange={(e) =>
                                        setPago({
                                            ...pago,
                                            metodo: e.target.value
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

                                    value={pago.estado}

                                    onChange={(e) =>
                                        setPago({
                                            ...pago,
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

                                    <option value="Pagado">
                                        Pagado
                                    </option>

                                    <option value="Rechazado">
                                        Rechazado
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

                                onClick={guardarPago}

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

export default Pagos;