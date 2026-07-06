import { Link, useLocation } from "react-router-dom";

function Sidebar() {

    const location = useLocation();

    const menus = [
        { nombre: "Dashboard", ruta: "/dashboard", icono: "🏠" },
        { nombre: "Clientes", ruta: "/clientes", icono: "👤" },
        { nombre: "Barberos", ruta: "/barberos", icono: "💈" },
        { nombre: "Reservas", ruta: "/reservas", icono: "📅" },
        { nombre: "Servicios", ruta: "/servicios", icono: "✂️" },
        { nombre: "Pagos", ruta: "/pagos", icono: "💳" },
        { nombre: "Reportes", ruta: "/reportes", icono: "📊" }
    ];

    return (
        <div
            className="bg-dark text-white shadow"
            style={{
                width: "260px",
                minHeight: "100vh"
            }}
        >

            <div className="p-4">

                <h2 className="text-warning text-center">

                    💈 BarberFlow

                </h2>

                <hr />

                {
                    menus.map((menu) => (

                        <Link

                            key={menu.ruta}

                            to={menu.ruta}

                            className={`btn w-100 mb-2 ${
                                location.pathname === menu.ruta
                                    ? "btn-warning"
                                    : "btn-outline-warning"
                            }`}

                        >

                            {menu.icono} {menu.nombre}

                        </Link>

                    ))
                }

            </div>

        </div>
    );
}

export default Sidebar;