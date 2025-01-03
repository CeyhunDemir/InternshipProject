import React, {useEffect, useState} from 'react';
import useAxios from '../interceptors/AxiosInstance.tsx';

import "../styles/pages/allpage.css"
import {useNavigate} from "react-router-dom";
import {Customer} from "../models/Customer.tsx";
import AddCustomerPage from "./AddCustomerPage.tsx";

const AllCustomersPage = () => {

    const axiosInstance = useAxios();
    const [customers, setCustomers] = useState<Customer[]>([]);
    const navigate = useNavigate();
    useEffect(() => {
        const fetchCustomers = async () => {
            try {
                const response = await axiosInstance.get('/v1/customer/all'); // Interceptor adds Authorization
                setCustomers(response.data?.map((customer:any) => ({
                    id: customer.id,
                    name: customer.name,
                    address: customer.address,
                    email: customer.email,
                    phone: customer.phone,
                })));

            } catch (error) {
                console.error('Error fetching customers:', error);
            }
        };
        fetchCustomers();

    }, []);


    return (
        <>
            <div className="container_header" style={{minWidth: "920px"}}>
                <div>
                    <h1 className="table_title">All Customers</h1>
                </div>
                <div className="table_responsive">
                    <button className="table_add"
                            onClick={() => navigate("/addCustomer")}>Add Customer
                    </button>
                </div>
            </div>
            <div className = "table_table">
                <table className="table">
                    <thead className="table_head">
                    <tr >
                        <td className="cell_id">ID</td>
                        <td className="cell">Name</td>
                        <td className="cell">Address</td>
                        <td className="cell">Email</td>
                        <td className="cell">Phone</td>
                        <td className="cell_edit" style={{border: "0px"}}></td>
                    </tr>
                    </thead>
                    <tbody className="table_body">
                    {customers.map((customer:any) => (<tr className="table_row" key={customer.id}>
                        <td className="cell_id">{customer.id}</td>
                        <td className="cell">{customer.name}</td>
                        <td className="cell">{customer.address}</td>
                        <td className="cell">{customer.email}</td>
                        <td className="cell">{customer.phone}</td>

                        <td className="cell_edit">
                            <button className="table_edit"
                                    style={{border: "0px"}}
                                    onClick={() => navigate(`/editCustomer/${customer.id}`)}>Edit
                            </button>
                        </td>
                    </tr>))}
                    </tbody>

                </table>
            </div>
        </>
    );
};
export default AllCustomersPage;
