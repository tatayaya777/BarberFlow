import Sidebar from "../sidebar/Sidebar";
import Navbar from "../navbar/Navbar";

function Layout({ children }) {

    return (

        <div className="d-flex">

            <Sidebar />

            <div className="flex-grow-1 bg-light">

                <Navbar />

                <div className="container-fluid p-4">

                    {children}

                </div>

            </div>

        </div>

    );

}

export default Layout;