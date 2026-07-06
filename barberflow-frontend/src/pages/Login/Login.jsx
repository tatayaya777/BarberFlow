import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const iniciarSesion = (e) => {

        e.preventDefault();

        if (email === "" || password === "") {

            alert("Debe completar todos los campos.");

            return;

        }

        navigate("/dashboard");

    };

    return (

        <div
            className="container-fluid bg-light"
            style={{ minHeight: "100vh" }}
        >

            <div className="row justify-content-center align-items-center vh-100">

                <div className="col-md-5 col-lg-4">

                    <div className="card shadow-lg border-0">

                        <div className="card-body p-5">

                            <h1
                                className="text-center text-warning mb-4"
                            >

                                💈 BarberFlow

                            </h1>

                            <h5
                                className="text-center text-secondary mb-4"
                            >

                                Iniciar Sesión

                            </h5>

                            <form onSubmit={iniciarSesion}>

                                <div className="mb-3">

                                    <label>

                                        Correo

                                    </label>

                                    <input

                                        type="email"

                                        className="form-control"

                                        value={email}

                                        onChange={(e) => setEmail(e.target.value)}

                                    />

                                </div>

                                <div className="mb-4">

                                    <label>

                                        Contraseña

                                    </label>

                                    <input

                                        type="password"

                                        className="form-control"

                                        value={password}

                                        onChange={(e) => setPassword(e.target.value)}

                                    />

                                </div>

                                <button

                                    className="btn btn-warning w-100"

                                >

                                    Ingresar

                                </button>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default Login;