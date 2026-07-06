import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "../pages/Login/Login";
import Dashboard from "../pages/Dashboard/Dashboard";

import Clientes from "../pages/Clientes/Clientes";
import Barberos from "../pages/Barberos/Barberos";
import Servicios from "../pages/Servicios/Servicios";
import Reservas from "../pages/Reservas/Reservas";
import Pagos from "../pages/Pagos/Pagos";
import Reportes from "../pages/Reportes/Reportes";

function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Login />} />

        <Route path="/dashboard" element={<Dashboard />} />

        <Route path="/clientes" element={<Clientes />} />

        <Route path="/barberos" element={<Barberos />} />

        <Route path="/servicios" element={<Servicios />} />

        <Route path="/reservas" element={<Reservas />} />

        <Route path="/pagos" element={<Pagos />} />

        <Route path="/reportes" element={<Reportes />} />

        <Route path="*" element={<Navigate to="/" replace />} />

      </Routes>
    </BrowserRouter>
  );
}

export default AppRouter;