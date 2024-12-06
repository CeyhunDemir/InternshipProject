import React, {useEffect, useState} from 'react';
import useAxios from '../interceptors/AxiosInstance.tsx';
import {Product} from "../models/Product.tsx";
import "../styles/pages/productpage.css"

export const AllProductsPage = () => {

    const axiosInstance = useAxios();
    const [products, setProducts] = useState<Product[]>([]);
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
            <div className="container">
            <h1 className="table_title">All Products</h1>
            <table className="table">
                <thead className="table_head">
                <tr >
                    <td className="cell">ID</td>
                    <td className="cell">Name</td>
                    <td className="cell">Quantity</td>
                    <td className="cell">Unit Type</td>
                    <td className="cell">Price</td>
                </tr>
                </thead>
                <tbody className="table_body">
                    {products.map((product:any) => (<tr className="table_row" key={product.id}>
                        <td className="cell">{product.id}</td>
                        <td className="cell">{product.name}</td>
                        <td className="cell">{product.quantity}</td>
                        <td className="cell">{product.unitType}</td>
                        <td className="cell">{product.price}</td>
                    </tr>))}
                </tbody>

            </table>
            </div>
        </>
    );
};
