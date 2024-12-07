import protectedRoute from "../routes/ProtectedRoute.tsx";
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import useAxios from "../interceptors/AxiosInstance.tsx";
import Popup from "reactjs-popup";
import toString from "axios";
import {Product} from "../models/Product.tsx";
import axios from "axios";

export const EditProductPage = () => {
    const {productId} = useParams();
    const id:number = Number(productId);
    const [name, setName] = useState("");
    const [quantity, setQuantity] = useState(0);
    const [unitType, setUnitType] = useState("COUNT");
    const [price, setPrice] = useState(1);
    const [submitSuccess, setSubmitSuccess] = useState(false);
    const [deleteSuccess, setDeleteSuccess] = useState(false);
    const [product, setProduct] = useState<Product>();
    const axiosInstance = useAxios();
    const navigate = useNavigate();

    const handleDelete = async () => {
        const response = await axiosInstance.delete(`/v1/product/${productId}`);
        if (response.status === 200) {
            setDeleteSuccess(true);
        }
    }

    const handleSubmit = async() => {
        const response = await axiosInstance.put("/v1/product", {id, name, unitType, quantity ,price});
        if (response.status === 200) {
            setSubmitSuccess(true);
        }
    }

    useEffect(()=>{
        const getProducts = async () => {
            const response = await axiosInstance.get(`/v1/product/${productId}`);
            if (response.status === 200) {
                setProduct(response.data);
                setName(response.data.name);
                setQuantity(response.data.quantity);
                setUnitType(response.data.unitType);
                setPrice(response.data.price);
            }
            else{
                navigate("/products");
            }
        }
        getProducts();
    },[]);

    return(
        <div className="addProductPage">
            <h1>Edit Product</h1>
            <div className="titles">
                <h1 style={{  fontSize: "15px", marginRight: "110px", marginLeft: "5px"   }}>
                    Product Name
                </h1>
                <h1 style={{  fontSize: "15px", marginRight: "75px"  }}>
                    Product Quantity
                </h1>
                <h1 style={{  fontSize: "15px"   }}>
                    Product Unit Type
                </h1>
                <h1 style={{  fontSize: "15px" ,marginLeft: "10px"  }}>
                    Product Price
                </h1>
            </div>
            <div className="container">
                <input
                    className="input_bar"
                    type="string"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder={product?.name}/>
                <input
                    className="input_bar"
                    type="number"
                    value={quantity}
                    onChange={(e) => setQuantity(Number(e.target.value))}/>
                <select
                    className="select_bar"
                    value={unitType}
                    onChange={(e) => setUnitType(e.target.value)}>

                    <option value="COUNT">COUNT</option>
                    <option value="KILOGRAMS">KILOGRAMS</option>
                    <option value="LITERS">LITERS</option>

                </select>
                <input
                    className="input_bar"
                    type="number"
                    value={price}
                    onChange={(e) => setPrice(Number(e.target.value))}/>

                <Popup
                    trigger=
                        {<button
                            className="addButton"
                        >Update
                        </button>}
                    onOpen={handleSubmit}
                    position="bottom center">
                    {submitSuccess ? (<div>Successfully added the product!</div>): <div>Product could not be added!</div>}
                </Popup>

                <Popup
                    trigger=
                        {<button
                            className="deleteButton"
                        >Delete
                        </button>}
                    onOpen={handleDelete}
                    position="bottom center">
                    {deleteSuccess ? (<div>Successfully deleted the product!</div>): <div>Product could not be deleted!</div>}
                </Popup>
            </div>
        </div>
    )
}