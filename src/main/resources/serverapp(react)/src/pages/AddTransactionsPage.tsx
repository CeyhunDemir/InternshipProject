import {useState} from "react";
import useAxios from "../interceptors/AxiosInstance.tsx";
import Popup from "reactjs-popup";
import ProductNameInput from "../components/ProductNameInput.tsx";
import CustomerNameInput from "../components/CustomerNameInput.tsx";
import "../styles/pages/addpage.css"


const AddTransactionsPage = () => {
    const [productName, setProductName] = useState<string | null>("");
    const [customerName, setCustomerName] = useState("");
    const [quantity, setQuantity] = useState(0);
    const [transactionType, setTransactionType] = useState("SELL");
    const [totalPrice, setTotalPrice] = useState(1);
    const [submitSuccess, setSubmitSuccess] = useState(false);
    const axiosInstance = useAxios()

    const handleSubmit = async() => {
        const response = await axiosInstance.post("/v1/transaction", {product_name: productName, customer_name:customerName, quantity, transactionType, totalPrice});
        if (response.status === 200) {
            setSubmitSuccess(true);
        }
    }

    return (
        <div className="addProductPage">
            <h1>Add Transaction</h1>
            <div className="titles">
                <h1 style={{fontSize: "15px", marginRight: "90px", marginLeft: "5px"}}>
                    Product Name
                </h1>
                <h1 style={{fontSize: "15px", marginRight: "80px"}}>
                    Customer Name
                </h1>
                <h1 style={{fontSize: "15px", marginRight: "110px"}}>
                    Quantity
                </h1>
                <h1 style={{fontSize: "15px", marginLeft: "20px"}}>
                    Transac <br/>
                    -tion <br/>
                    Type

                </h1>
                <h1 style={{fontSize: "15px", marginLeft: "10px"}}>
                    Total Price
                </h1>

            </div>
            <div className="container">

                <ProductNameInput setProductName={setProductName}/>

                <CustomerNameInput customerName={customerName} setCustomerName={setCustomerName} />

                <input
                    className="input_bar_new"
                    type="number"
                    value={quantity}
                    onChange={(e) => setQuantity(Number(e.target.value))}/>
                <select
                    className="select_bar"
                    value={transactionType}
                    onChange={(e) => setTransactionType(e.target.value)}>

                    <option value="SELL">SELL</option>
                    <option value="BUY">BUY</option>

                </select>
                <input
                    className="input_bar_new"
                    type="number"
                    value={totalPrice}
                    onChange={(e) => setTotalPrice(Number(e.target.value))}/>
                <Popup
                    trigger=
                        {<button
                            className="addButton"
                        >Add
                        </button>}
                    onOpen={handleSubmit}
                    position="right center">
                    {submitSuccess ? (<div>Successfully added the transaction!</div>) : <div>Transaction could not be added!</div>}
                </Popup>
            </div>
        </div>
    )
}
export default AddTransactionsPage;