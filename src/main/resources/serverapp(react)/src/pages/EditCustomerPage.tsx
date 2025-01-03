
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import useAxios from "../interceptors/AxiosInstance.tsx";
import Popup from "reactjs-popup";
import {Customer} from "../models/Customer.tsx";

const EditCustomerPage = () => {
    const {customerId} = useParams();
    const id:number = Number(customerId);
    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [submitSuccess, setSubmitSuccess] = useState(false);
    const [deleteSuccess, setDeleteSuccess] = useState(false);
    const axiosInstance = useAxios();
    const navigate = useNavigate();

    const handleDelete = async () => {
        const response = await axiosInstance.delete(`/v1/customer/${customerId}`);
        if (response.status === 200) {
            setDeleteSuccess(true);
        }
    }

    const handleSubmit = async() => {
        const response = await axiosInstance.put("/v1/customer", {id, name, address, email ,phone});
        if (response.status === 200) {
            setSubmitSuccess(true);
        }
    }

    useEffect(()=>{
        const getCustomers = async () => {
            const response = await axiosInstance.get(`/v1/customer/${customerId}`);
            if (response.status === 200) {
                setName(response.data.name);
                setAddress(response.data.address);
                setEmail(response.data.email);
                setPhone(response.data.phone);
            }
            else{
                navigate("/customers");
            }
        }
        getCustomers();
    },[]);

    return(
        <div className="addProductPage">
            <h1>Edit Customer</h1>
            <div className="titles">
                <h1 style={{  fontSize: "15px", marginRight: "110px", marginLeft: "5px"   }}>
                    Customer Name
                </h1>
                <h1 style={{  fontSize: "15px", marginRight: "75px"  }}>
                    Customer Address
                </h1>
                <h1 style={{  fontSize: "15px"   }}>
                    Customer Email
                </h1>
                <h1 style={{  fontSize: "15px" ,marginLeft: "10px"  }}>
                    Customer Phone
                </h1>
            </div>
            <div className="container">
                <input
                    className="input_bar"
                    type="string"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder={name}/>
                <input
                    className="input_bar"
                    type="string"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}/>
                <input
                    className="input_bar"
                    type="string"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}/>
                <input
                    className="input_bar"
                    type="string"
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}/>

                <Popup
                    trigger=
                        {<button
                            className="addButton"
                        >Update
                        </button>}
                    onOpen={handleSubmit}
                    position="bottom center">
                    {submitSuccess ? (<div>Successfully updated the customer!</div>): <div>Customer could not be updated!</div>}
                </Popup>

                <Popup
                    trigger=
                        {<button
                            className="deleteButton"
                        >Delete
                        </button>}
                    onOpen={handleDelete}
                    position="bottom center">
                    {deleteSuccess ? (<div>Successfully deleted the customer!</div>): <div>Customer could not be deleted!</div>}
                </Popup>
            </div>
        </div>
    )
}
export default EditCustomerPage;