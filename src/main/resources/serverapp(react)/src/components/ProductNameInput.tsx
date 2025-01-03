import React, { useState, useEffect } from "react";
import useAxios from "../interceptors/AxiosInstance.tsx";

type Props = {
    productName?: string;
    setProductName: React.Dispatch<React.SetStateAction<string | null>>
    setId?: React.Dispatch<React.SetStateAction<number>>
}

const ProductNameInput = (props:Props)=> {
    const [query, setQuery] = useState("");
    const [products, setProducts] = useState([]);
    const [showDropdown, setShowDropdown] = useState(false);
    const [debounceTimer, setDebounceTimer] = useState<NodeJS.Timeout | null>(null);
    const axiosInstance = useAxios();

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setQuery(value);

        // Clear the previous timer
        if (debounceTimer) {
            clearTimeout(debounceTimer);
        }

        // Set a new timer
        const newTimer = setTimeout(async () => {
            if (value.length > 0) {
                try {
                    const response = await axiosInstance.get(`/v1/product/search`, {
                        params: {query: value}
                    });
                    setProducts(response.data);
                    setShowDropdown(true);
                } catch (error) {
                    console.error("Error fetching products:", error);
                }
            } else {
                setShowDropdown(false);
            }
        }, 1000); // 1000ms = 1 second delay

        setDebounceTimer(newTimer);
        props.setProductName(e.target.value.toLowerCase());
        if (e.target.value === "")
            props.setProductName(null);

    };

    const handleSelectProduct = (productName: string, productId:number) => {
        setQuery(productName);
        setShowDropdown(false);
        props.setProductName(productName.toLowerCase());
        if (props.setId) {
            props.setId(productId)
        }
    };

    return (
        <div className = "input_bar_withDropdown">
            <input
                className="input_bar_new"
                type="text"
                value={query}
                onChange={handleInputChange}
                placeholder={props.productName}
            />
            {showDropdown && products.length > 0 && (
                <ul
                    style={{
                        position: "absolute",
                        width: "120px",
                        marginTop: "0px",
                        marginBottom: "0px",
                        marginLeft: "10px",
                        backgroundColor: "#027c8c",
                        color: "white",
                        borderRadius: "10px",
                    }}
                >
                    {products.map((product: any) => (
                        <li
                            key={product.id}
                            style={{ padding: "8px", cursor: "pointer" }}
                            onClick={() => handleSelectProduct(product.name, product.id)}
                        >
                            {product.name}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default ProductNameInput;