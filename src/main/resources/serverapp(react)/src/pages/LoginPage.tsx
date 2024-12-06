import axios from "axios";
import "../styles/pages/loginpage.css"
import React, {useState} from "react";
import {useAuth} from "../context/AuthContext.tsx";
import {Link, useNavigate} from "react-router-dom";
import useAxios from "../interceptors/AxiosInstance.tsx";
const api = "http://localhost:8080/api"


export const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const axiosInstance = useAxios();
    const handleLogin = async () => {

        try {
            const response = await axiosInstance.post("/v1/auth/authenticate", {email, password})
            if (response.status === 200) {
                localStorage.setItem("token", response.data.token);
                navigate("/products");
            }
        }
        catch (error) {
            console.log(error);
        }
    };
    return (
        <div className="login">
            <h1 className="title">LOGIN</h1>
            <input
                className="input_bar"
                type="text"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
            />
            <input
                className="input_bar"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Password"
            />
            <button className="login_button" onClick={handleLogin}>Login</button>
        </div>
    );
}