import {useState} from "react";
import useAxios from "../interceptors/AxiosInstance.tsx";
import "../styles/pages/addpage.css"
import Popup from 'reactjs-popup';

export const AddProductPage = () => {
    const [name, setName] = useState("");
    const [quantity, setQuantity] = useState(0);
    const [unitType, setUnitType] = useState("COUNT");
    const [price, setPrice] = useState(1);
    const [success, setSuccess] = useState(false);
    const axiosInstance = useAxios();

    const handleSubmit = async() => {
        const response = await axiosInstance.post("/v1/product", {name, unitType, quantity ,price});
        if (response.status === 200) {
            setSuccess(true);
        }
    }
    return(
    <div className="addProductPage">
        <h1>Add Product</h1>
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
                className="input_bar_new"
                type="string"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Product Name"/>
            <input
                className="input_bar_new"
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
                className="input_bar_new"
                type="number"
                value={price}
                onChange={(e) => setPrice(Number(e.target.value))}/>

            <Popup
                    trigger=
                       {<button
                           className="addButton"
                       >Add
                       </button>}
                   onOpen={handleSubmit}
                   position="right center">
                  {success ? (<div>Successfully added the product!</div>): <div>Product could not be added!</div>}
            </Popup>
        </div>
    </div>
)
}