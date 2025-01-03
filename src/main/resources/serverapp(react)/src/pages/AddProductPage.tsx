import {useState} from "react";
import useAxios from "../interceptors/AxiosInstance.tsx";
import "../styles/pages/addpage.css"
import Popup from 'reactjs-popup';

const AddProductPage = () => {
    const [name, setName] = useState("");
    /*    const [quantity, setQuantity] = useState(0);*/
    const [unitType, setUnitType] = useState("COUNT");
    const [price, setPrice] = useState(1);
    const [success, setSuccess] = useState(false);
    const [attributes, setAttributes] = useState<string[]>([]);
    const [attributeValues, setAttributeValues] = useState<string[]>([]);
    const [attributeCount, setAttributeCount] = useState(0);
    const axiosInstance = useAxios();


    const handleAttributeInputChange = (index: number, value: string) => {
        const updatedAttributes = [...attributes];
        updatedAttributes[index] = value;
        setAttributes(updatedAttributes);
    }
    const handleAttributeValueInputChange = (index: number, value: string) => {
        const updatedAttributeValues = [...attributeValues];
        updatedAttributeValues[index] = value;
        setAttributeValues(updatedAttributeValues);

    }

    const preparePayload = () => {
        const attributesObject: { [key: string]: string } = {};

        attributes.forEach((attr, index) => {
            attributesObject[attributes[index]] = attributeValues[index];
        });

        const payload = {
            name: name,
            unitType: unitType,
            price: price,
            attributes: attributesObject,
        }

        return payload;
    };

    const handleSubmit = async() => {
        const payload = preparePayload();
        const response = await axiosInstance.post("/v1/product", payload);
        if (response.status === 200) {
            setSuccess(true);
        }
    }
    return(
    <div className="addProductPage">
        <h1>Add Product</h1>
        <div className="container">
            <div>
                <h1 style={{}}>
                    Product Name
                </h1>
                <input
                    className="input_bar_new"
                    type="string"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder="Product Name"/>
            </div>

            <div>
                <h1 style={{}}>
                    Product Unit <br/> Type
                </h1>
                <select
                    className="select_bar"
                    value={unitType}
                    onChange={(e) => setUnitType(e.target.value)}>

                    <option value="COUNT">COUNT</option>
                    <option value="KILOGRAMS">KILOGRAMS</option>
                    <option value="LITERS">LITERS</option>

                </select>
            </div>
            <div>
                <h1 style={{}}>
                    Product Price
                </h1>
                <input
                    className="input_bar_new"
                    type="number"
                    value={price}
                    onChange={(e) => setPrice(Number(e.target.value))}/>
            </div>
            <Popup
                trigger=
                    {<button
                        className="addButton"
                    >Add Product
                    </button>}
                onOpen={handleSubmit}
                position="right center">
                {success ? (<div>Successfully added the product!</div>) : <div>Product could not be added!</div>}
            </Popup>
        </div>
        <div style={{marginTop: "10px"}}>
            {Array.from({length: attributeCount}).map((_, index) => (
                <div className={"attribute"} key={index}>
                    <div style={{
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "left",
                        alignItems: "flex-start"
                    }}>
                        <label style={{fontSize: "12px"}}>Attribute {index + 1}:</label>
                        <input
                            className={"input_bar"}
                            style={{marginRight: "10px"}}
                            type="text"
                            value={attributes[index] || ""}
                            onChange={(e) => {
                                handleAttributeInputChange(index, e.target.value);
                            }}
                            placeholder={`Enter attribute ${index + 1}`}
                        />
                    </div>
                    <div style={{
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "left",
                        alignItems: "flex-start"
                    }}>
                        <label style={{fontSize: "12px"}}>Attribute Value {index + 1}:</label>
                        <input
                            className={"input_bar"}
                            type="text"
                            value={attributeValues[index] || ""}
                            onChange={(e) => {
                                handleAttributeValueInputChange(index, e.target.value);
                            }}
                            placeholder={`Enter attribute value ${index + 1}`}
                        />
                    </div>
                </div>
            ))}
        </div>
        <button className={"addButton"} style={{width: "40px"}} onClick={() => {
            setAttributeCount(attributeCount + 1)
        }}>+
        </button>
    </div>
    )
}
export default AddProductPage;