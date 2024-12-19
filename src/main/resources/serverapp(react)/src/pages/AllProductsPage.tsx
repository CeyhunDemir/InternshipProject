import React, {useEffect, useState} from 'react';
import useAxios from '../interceptors/AxiosInstance.tsx';
import {Product} from "../models/Product.tsx";
import "../styles/pages/allpage.css"
import {useNavigate} from "react-router-dom";
import TransactionsFilterMenu from "../components/TransactionsFilterMenu.tsx";

export const AllProductsPage = () => {

    const axiosInstance = useAxios();
    const [products, setProducts] = useState<Product[]>([]);
    const navigate = useNavigate();
    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await axiosInstance.get('/v1/product/all'); // Interceptor adds Authorization
                setProducts(response.data?.map((product:any) => ({
                    id: product.id,
                    name: product.name,
                    quantity: product.quantity,
                    price: product.price,
                    unitType: product.unitType,
                })));

            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };
        fetchProducts();

    }, []);


    return (
        <>
            <div className="container" style={{minWidth: "920px"}}>
                <div>
                    <h1 className="table_title">All Products</h1>
                </div>
                <div className="table_responsive">
                    <button className="table_add"
                            onClick={()=>navigate("/addProduct")}>Add</button>
                    <button className="table_search"
                            onClick={()=>navigate("/searchProduct")}>Search</button>
                </div>
                </div>
                <div className = "table_table">

                    <table className="table">
                        <thead className="table_head">
                        <tr >
                            <td className="cell">ID</td>
                            <td className="cell">Name</td>
                            <td className="cell">Quantity</td>
                            <td className="cell">Unit Type</td>
                            <td className="cell">Price</td>
                            <td className="cell_edit"></td>
                        </tr>
                        </thead>
                        <tbody className="table_body">
                            {products.map((product:any) => (<tr className="table_row" key={product.id}>
                                <td className="cell">{product.id}</td>
                                <td className="cell">{product.name}</td>
                                <td className="cell">{product.quantity}</td>
                                <td className="cell">{product.unitType}</td>
                                <td className="cell">{product.price}</td>
                                <td className="cell_edit">
                                    <button className="table_edit" onClick={()=>navigate(`/editProduct/${product.id}`)}>Edit</button>
                                </td>
                            </tr>))}
                        </tbody>

                    </table>
                </div>
        </>
    );
};
