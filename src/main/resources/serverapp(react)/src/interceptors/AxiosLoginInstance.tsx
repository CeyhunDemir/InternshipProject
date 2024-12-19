import axios from "axios";

const useAxiosLogin = () => {
    const token = localStorage.getItem("token");

    const axiosInstance = axios.create({
        baseURL: 'http://localhost:8080/api/',
    });

    // Attach the token to requests
    axiosInstance.interceptors.request.use((config) => {
        if (token) {
            config.headers.Authorization = `Bearer `;
        }
        return config;
    });

    return axiosInstance;
};

export default useAxiosLogin;