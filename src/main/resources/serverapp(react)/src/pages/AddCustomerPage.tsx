import {useState} from "react";
import useAxios from "../interceptors/AxiosInstance.tsx";
import "../styles/pages/addpage.css"
import Popup from 'reactjs-popup';
import EditProductPage from "./EditProductPage.tsx";

const AddCustomerPage = () => {
    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [success, setSuccess] = useState(false);
    const axiosInstance = useAxios();

    const handleSubmit = async() => {
        const response = await axiosInstance.post("/v1/customer", {name, address, email ,phone});
        if (response.status === 200) {
            setSuccess(true);
        }
    }
    return(
        <div className="addProductPage">
            <h1>Add Customer</h1>
            <div className="titles">
                <h1 style={{marginRight: "110px", marginLeft: "5px"}}>
                    Customer Name
                </h1>
                <h1 style={{marginRight: "75px"}}>
                    Customer Address
                </h1>
                <h1 style={{}}>
                    Customer Email
                </h1>
                <h1 style={{marginLeft: "10px"}}>
                    Customer Phone Number
                </h1>
            </div>
            <div className="container">
                <input
                    className="input_bar_new"
                    type="string"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder="Customer Name"/>
                <input
                    className="input_bar_new"
                    type="string"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                    placeholder="Customer Address"/>
                <input
                    className="input_bar_new"
                    type="string"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Customer Email"/>
                <input
                    className="input_bar_new"
                    type="string"
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}
                    placeholder="Customer Phone Number"/>

                <Popup
                    trigger=
                        {<button
                            className="addButton"
                        >Add
                        </button>}
                    onOpen={handleSubmit}
                    position="right center">
                    {success ? (<div>Successfully added the customer!</div>): <div>Customer could not added!</div>}
                </Popup>
            </div>
        </div>
    )
}
export default AddCustomerPage;