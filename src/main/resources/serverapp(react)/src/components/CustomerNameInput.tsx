import React, { useState, useEffect } from "react";
import useAxios from "../interceptors/AxiosInstance.tsx";

type Props = {
    customerName: string;
    setCustomerName: React.Dispatch<React.SetStateAction<string>>
}

const CustomerNameInput = (props:Props)=> {
    const [query, setQuery] = useState("");
    const [customers, setCustomers] = useState([]);
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
                    const response = await axiosInstance.get(`/v1/customer/search`, {
                        params: {query: value}
                    });
                    setCustomers(response.data);
                    setShowDropdown(true);
                } catch (error) {
                    console.error("Error fetching customers:", error);
                }
            } else {
                setShowDropdown(false);
            }
        }, 1000); // 1000ms = 1 second delay

        setDebounceTimer(newTimer);
    };

    const handleSelectProduct = (customerName: string) => {
        setQuery(customerName);
        setShowDropdown(false);
        props.setCustomerName(customerName)
    };

    return (
        <div>
            <input
                className="input_bar_new"
                type="text"
                value={query}
                onChange={handleInputChange}
                placeholder={props.customerName}
            />
            {showDropdown && customers.length > 0 && (
                <ul
                    style={{
                        position: "relative",
                        width: "120px",
                        marginTop: "0px",
                        marginBottom: "0px",
                        marginLeft: "10px",
                        backgroundColor: "#027c8c",
                        color: "white",
                        borderRadius: "10px",
                        zIndex: 9999,
                    }}
                >
                    {customers.map((customer: any) => (
                        <li
                            key={customer.id}
                            style={{ padding: "8px", cursor: "pointer" }}
                            onClick={() => handleSelectProduct(customer.name)}
                        >
                            {customer.name}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default CustomerNameInput;