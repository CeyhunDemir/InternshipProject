import {createContext, ReactNode, useContext, useEffect, useState} from "react";
import axios from "axios";




interface AuthContextType {
    token: string | null;
    setToken: (token: string | null) => void;
}
const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }:{children: ReactNode}) => {
    const [token, setToken] = useState<string | null>(null);
    return (
        <AuthContext.Provider value={{token, setToken}}>
            {children}
        </AuthContext.Provider>
    )

}

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error("useAuth must be used within the AuthProvider");
    }
    return context;
}

