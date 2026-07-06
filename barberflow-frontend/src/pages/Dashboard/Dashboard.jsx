import { useEffect, useState } from "react";
import Layout from "../../components/layout/Layout";
import api from "../../services/api";

function Dashboard() {
  const [totalClientes, setTotalClientes] = useState(0);
const [totalBarberos, setTotalBarberos] = useState(0);
const [totalReservas, setTotalReservas] = useState(0);
const [totalIngresos, setTotalIngresos] = useState(0);

useEffect(() => {
    cargarDashboard();
}, []);

const cargarDashboard = async () => {

    try {

        const clientes = await api.get("/clientes");
        const barberos = await api.get("/barberos");
        const reservas = await api.get("/reservas");
        const pagos = await api.get("/pagos");

        setTotalClientes(clientes.data.length);
        setTotalBarberos(barberos.data.length);
        setTotalReservas(reservas.data.length);

        const ingresos = pagos.data.reduce(
            (total, pago) => total + Number(pago.monto),
            0
        );

        setTotalIngresos(ingresos);

    } catch (error) {

        console.error(error);

    }

};
  return (
    <Layout>
      
      <div className="mb-4">

        <h1 className="fw-bold">
          <p className="text-muted">
            Última actualización: {new Date().toLocaleString("es-CL")}
            </p>
          Dashboard
        </h1>

        <p className="text-secondary">
          Bienvenido al sistema de gestión BarberFlow.
        </p>

      </div>

      <div className="row g-4">

        <div className="col-lg-3 col-md-6">

          <div className="card shadow border-0 text-bg-primary">

            <div className="card-body">

              <h5>👤 Clientes</h5>

              <h2>{totalClientes}</h2>

              <small>Total registrados</small>

            </div>

          </div>

        </div>

        <div className="col-lg-3 col-md-6">

          <div className="card shadow border-0 text-bg-success">

            <div className="card-body">

              <h5>💈 Barberos</h5>

              <h2>{totalBarberos}</h2>

              <small>Barberos activos</small>

            </div>

          </div>

        </div>

        <div className="col-lg-3 col-md-6">

          <div className="card shadow border-0 text-bg-warning">

            <div className="card-body">

              <h5>📅 Reservas</h5>

              <h2>{totalReservas}</h2>

              <small>Reservas registradas</small>

            </div>

          </div>

        </div>

        <div className="col-lg-3 col-md-6">

          <div className="card shadow border-0 text-bg-danger">

            <div className="card-body">

              <h5>💳 Pagos</h5>
              <h2>$
                {" "}
                {totalIngresos.toLocaleString("es-CL")}
                </h2>

              <small>Ingresos</small>

            </div>

          </div>

        </div>

      </div>

      <div className="card shadow border-0 mt-5">

        <div className="card-body">

          <h3>

            💈 BarberFlow

          </h3>

          <hr />

          <p>

            Sistema desarrollado con React, Spring Boot,
            API Gateway y arquitectura de Microservicios.

          </p>

          <p>

            Desde el menú lateral puedes administrar clientes,
            barberos, reservas, servicios, pagos y reportes.

          </p>

        </div>

      </div>
    
    </Layout>
  );
}

export default Dashboard;