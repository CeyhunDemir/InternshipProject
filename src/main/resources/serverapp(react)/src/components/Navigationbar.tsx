import "../styles/components/navbar.css"
import {useNavigate} from "react-router-dom";
import {useAuth} from "../context/AuthContext.tsx";
const Navigationbar = () =>{
    const navigate = useNavigate();
    const {isAuthenticated, logout} = useAuth();
    return (
        <nav className="navbar">
            <div className="navbar-title">
                <h1>
                    Stock Management System
                </h1>
            </div>
            <div className="navbar-body">
                <button className="navbar-button" onClick={() => {navigate("/products")} }>Products</button>
                <button className="navbar-button" onClick={() => {navigate("/customers")} }>Customers</button>
                <button className="navbar-button" onClick={() => {navigate("/sales")} }>Sales</button>
                {isAuthenticated ?
                    (<button className="navbar-button" onClick={() => {logout(); navigate("/login");} }>Logout</button>):
                    (<button className="navbar-button" onClick={() => {navigate("/login")} }>Login</button>)
                }
            </div>
        </nav>
    );
}
export default Navigationbar;