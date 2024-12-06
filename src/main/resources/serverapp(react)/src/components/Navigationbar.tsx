import "../styles/components/navbar.css"
const Navigationbar = () =>{
    return (
        <nav className="navbar">
            <div className="navbar-title">
                <h1>
                    Stock Management System
                </h1>
            </div>
            <div className="navbar-body">
                <button className="navbar-button" >Products</button>
                <button className="navbar-button" >Customers</button>
                <button className="navbar-button" >Sales</button>
            </div>
        </nav>
    );
}
export default Navigationbar;