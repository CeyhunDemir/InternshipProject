import React, {useEffect, useState} from 'react';
import useAxios from '../interceptors/AxiosInstance.tsx';

import "../styles/pages/allpage.css"
import {useNavigate} from "react-router-dom";
import {Transaction} from "../models/Transaction.tsx";
import TransactionsFilterMenu from "../components/TransactionsFilterMenu.tsx";


const AllTransactionsPage = () => {

    const [productName, setProductName] = useState<string | null>(null);
    const [minQuantity, setMinQuantity] = useState<number | null>(null);
    const [maxQuantity, setMaxQuantity] = useState<number | null>(null);
    const [minPrice, setMinPrice] = useState<number | null>(null);
    const [maxPrice, setMaxPrice] = useState<number | null>(null);
    const [afterDate, setAfterDate] = useState<string | null>(null);
    const [beforeDate, setBeforeDate] = useState<string | null>(null);
    const [transactionType, setTransactionType] = useState<string | null>(null);
    const [orderDirection, setOrderDirection] = useState<string | null>(null);
    const [orderType, setOrderType] = useState<string | null>(null);
    const [applyButtonValue, setApplyButtonValue] = useState<boolean>(false);


    const axiosInstance = useAxios();
    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const navigate = useNavigate();
    function nullAll(){
        setProductName(null);
        setMinQuantity(null);
        setMaxQuantity(null);
        setMinPrice(null);
        setMaxPrice(null);
        setAfterDate(null);
        setBeforeDate(null);
        setTransactionType(null);
        setOrderDirection(null);
        setOrderType(null);
    }

    useEffect(() => {
        const fetchTransactions = async () => {
            try {
                const requestBody = {
                    productName:productName,
                    minQuantity:minQuantity,
                    maxQuantity:maxQuantity,
                    minPrice:minPrice,
                    maxPrice:maxPrice,
                    afterDate:afterDate,
                    beforeDate:beforeDate,
                    transactionType:transactionType,
                    order:orderDirection,
                    orderType:orderType,
                };
                const response = await axiosInstance.post('/v1/transaction/allWithFilters',
                    requestBody); // Interceptor adds Authorization
                setTransactions(response.data?.map((transaction:any) => ({
                    id: transaction.id,
                    productName: transaction.productName,
                    customerName: transaction.customerName,
                    transactionDate: transaction.transactionDate,
                    transactionType: transaction.transactionType,
                    totalPrice: transaction.totalPrice,
                    quantity: transaction.quantity,
                })));

            } catch (error) {
                console.error('Error fetching transactions:', error);
            }
        };
        fetchTransactions();

    }, [applyButtonValue]);


    return (
        <>
            <TransactionsFilterMenu setProductName={setProductName}
                                    setMinQuantity={setMinQuantity}
                                    setMaxQuantity={setMaxQuantity}
                                    setMinPrice={setMinPrice}
                                    setMaxPrice={setMaxPrice}
                                    setAfterDate={setAfterDate}
                                    setBeforeDate={setBeforeDate}
                                    setTransactionType={setTransactionType}
                                    setOrderDirection={setOrderDirection}
                                    setOrderType={setOrderType}
                                    setApplyButtonValue={setApplyButtonValue}
                                    applyButtonValue={applyButtonValue}/>
            <div className="container_header" style={{minWidth: "920px"}}>
                <div>
                    <h1 className="table_title">All Transactions</h1>
                </div>
                <div className="table_responsive">
                    <button className="table_add"
                            onClick={() => navigate("/addTransaction")}>Add Transaction
                    </button>
                    {/*<button className="table_search"
                            onClick={()=>navigate("/searchTransaction")}>Search</button>*/}
                </div>
            </div>
            <div className = "table_table">
                <table className="table">
                    <thead className="table_head">
                    <tr key="header">
                        <td className="cell">Product Name</td>
                        <td className="cell">Quantity</td>
                        <td className="cell">Transaction Type</td>
                        <td className="cell">TotalPrice</td>
                        <td className="cell">CustomerName</td>
                        <td className="cell">Transaction Date</td>
                        <td className="cell_edit" style={{border: "0px"}}></td>
                    </tr>
                    </thead>
                    <tbody className="table_body">
                    {transactions.map((transaction: any) => (<tr className="table_row" key={transaction.id}>
                        <td className="cell">{transaction.productName}</td>
                        <td className="cell">{transaction.quantity}</td>
                        <td className="cell">{transaction.transactionType}</td>
                        <td className="cell">{transaction.totalPrice} </td>
                        <td className="cell">{transaction.customerName}</td>
                        <td className="cell">{transaction.transactionDate}</td>

                        <td className="cell_edit">
                            <button className="table_edit"
                                    style={{border: "0px"}}
                                    onClick={() => navigate(`/editTransaction/${transaction.id}`)}>Edit
                            </button>
                        </td>
                    </tr>))}
                    </tbody>

                </table>
            </div>
        </>
    );
};
export default AllTransactionsPage;