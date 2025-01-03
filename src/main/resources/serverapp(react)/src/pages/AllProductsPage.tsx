import React, {useEffect, useState} from 'react';
import useAxios from '../interceptors/AxiosInstance.tsx';
import {Product} from "../models/Product.tsx";
import "../styles/pages/allpage.css"
import {useNavigate} from "react-router-dom";
import TransactionsFilterMenu from "../components/TransactionsFilterMenu.tsx";

const AllProductsPage = () => {

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
            <div className="container_header" style={{minWidth: "920px", justifyContent: "space-between"}}>
                <div>
                    <h1 className="table_title">All Products</h1>
                </div>
                <div className="table_responsive">
                    <button className="table_add"
                            onClick={() => navigate("/addProduct")}>Add Product
                    </button>
                </div>
                </div>
                <div className = "table_table">

                    <table className="table">
                        <thead className="table_head">
                        <tr >
                            <td className="cell_id">ID</td>
                            <td className="cell_name">Name</td>
                            <td className="cell_quantity">Quantity</td>
                            <td className="cell_unitType">Unit Type</td>
                            <td className="cell_price">Price</td>
                            {/*                            <td className="cell_expirable">Expire</td>*/}
                            <td className="cell_edit" style={{border: "0px"}}></td>
                        </tr>
                        </thead>
                        <tbody className="table_body">
                        {products.map((product: Product) => (<tr className="table_row" key={product.id}>
                            <td className="cell_id">{product.id}</td>
                            <td className="cell_name">{product.name}</td>
                            <td className="cell_quantity">{product.quantity}</td>
                            <td className="cell_unitType">{product.unitType}</td>
                            <td className="cell_price">{product.price}</td>
                            {/*                                <td className="cell_expirable">{
                                    (product.expirable === "EXPIRABLE") ? "YES" : "NO"}</td>*/}
                            <td className="cell_edit" style={{border: "0px"}}>
                                    <button className="table_edit" onClick={()=>navigate(`/editProduct/${product.id}`)}>Edit</button>
                                </td>
                            </tr>))}
                        </tbody>

                    </table>
                </div>
        </>
    );
};

export default AllProductsPage;
