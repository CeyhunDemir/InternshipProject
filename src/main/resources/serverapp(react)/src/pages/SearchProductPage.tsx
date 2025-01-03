/*
import {useState} from "react";
import useAxios from "../interceptors/AxiosInstance.tsx";
import "../styles/pages/addpage.css"
import ProductNameInput from "../components/ProductNameInput.tsx";
import {useNavigate} from "react-router-dom";

const SearchProductPage = () => {
    const [name, setName] = useState<string | null>(null);
    const [id, setId] = useState(0);
    const axiosInstance = useAxios();
    const navigate = useNavigate();

    const handleSearch = async() => {
        const response = await axiosInstance.get(`/v1/product/${id}`);
        if (response.status === 200) {
            navigate(`../editProduct/${id}`);
        }
    }
    return(
        <div className="addProductPage">
            <h1>Search Product</h1>
            <div className="container" style={{justifyContent:"left",alignItems:"left"}}>
                <ProductNameInput setProductName={setName} setId={setId}></ProductNameInput>
                <button className="table_search" onClick={handleSearch}>Search</button>
            </div>
        </div>
    )
}
export default SearchProductPage;*/
