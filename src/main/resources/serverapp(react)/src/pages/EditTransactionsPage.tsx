
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import useAxios from "../interceptors/AxiosInstance.tsx";
import Popup from "reactjs-popup";
import ProductNameInput from "../components/ProductNameInput.tsx";


export const EditTransactionsPage = () => {
    const {transactionId} = useParams();
    const id:number = Number(transactionId);
    const [productName, setProductName] = useState("");
    const [customerName, setCustomerName] = useState("");
    const [quantity, setQuantity] = useState(0);
    const [transactionType, setTransactionType] = useState("SELL");
    const [totalPrice, setTotalPrice] = useState(1);
    const [submitSuccess, setSubmitSuccess] = useState(false);
    const [deleteSuccess, setDeleteSuccess] = useState(false);
    const axiosInstance = useAxios();
    const navigate = useNavigate();

    const handleDelete = async () => {
        const response = await axiosInstance.delete(`/v1/transaction/${transactionId}`);
        if (response.status === 200) {
            setDeleteSuccess(true);
        }
    }

    const handleSubmit = async() => {
        const response = await axiosInstance.put("/v1/transaction", {productName, customerName, quantity, transactionType, totalPrice});
        if (response.status === 200) {
            setSubmitSuccess(true);
        }
    }

    useEffect(()=>{
        const getProducts = async () => {
            const response = await axiosInstance.get(`/v1/transaction/${transactionId}`);
            if (response.status === 200) {
                setProductName(response.data.name);
                setCustomerName(response.data.custname);
                setQuantity(response.data.quantity);

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
                <ProductNameInput productName={productName}/>
                    <input
                    className="input_bar"
                    type="number"
                    value={quantity}
                    onChange={(e) => setQuantity(Number(e.target.value))}/>
                <select
                    className="select_bar"
                    value={transactionType}
                    onChange={(e) => setTransactionType(e.target.value)}>

                    <option value="COUNT">SELL</option>
                    <option value="KILOGRAMS">BUY</option>

                </select>
                <input
                    className="input_bar"
                    type="number"
                    value={totalPrice}
                    onChange={(e) => setTotalPrice(Number(e.target.value))}/>

                <Popup
                    trigger=
                        {<button
                            className="addButton"
                        >Update
                        </button>}
                    onOpen={handleSubmit}
                    position="bottom center">
                    {submitSuccess ? (<div>Successfully updated the product!</div>): <div>Product could not be updated!</div>}
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