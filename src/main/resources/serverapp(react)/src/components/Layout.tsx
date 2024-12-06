import Navigationbar from "./Navigationbar.tsx";
import {Outlet} from "react-router-dom";
import "../styles/layout.css"

export const Layout = () => {
    return (
        <>
            <Navigationbar />
            <main className="main">
                <Outlet/>
            </main>
        </>
    )
}