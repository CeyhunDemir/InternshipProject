import React, {ChangeEvent, useState} from "react";
import ProductNameInput from "./ProductNameInput.tsx";
import "../styles/components/transactionFilterMenu.css"

type Props = {
    setProductName: React.Dispatch<React.SetStateAction<string | null>>;
    setMinQuantity: React.Dispatch<React.SetStateAction<number | null>>;
    setMaxQuantity: React.Dispatch<React.SetStateAction<number | null>>;
    setMinPrice: React.Dispatch<React.SetStateAction<number | null>>;
    setMaxPrice: React.Dispatch<React.SetStateAction<number | null>>;
    setAfterDate: React.Dispatch<React.SetStateAction<string | null>>;
    setBeforeDate: React.Dispatch<React.SetStateAction<string | null>>;
    setTransactionType: React.Dispatch<React.SetStateAction<string | null>>;
    setOrderDirection: React.Dispatch<React.SetStateAction<string | null>>;
    setOrderType: React.Dispatch<React.SetStateAction<string | null>>;

    applyButtonValue: boolean;
    setApplyButtonValue: React.Dispatch<React.SetStateAction<boolean>>;

}

const TransactionsFilterMenu = (props:Props) => {
    const [productName, setProductName] = useState("");

    return (
        <div className="filter_menu">
            <div className="filter_menu_header">
                <h1 style={{marginTop:"0px"}}>Filters</h1>
            </div>
            <div className="filter_menu_productNameFilter">
                <div className="filter_menu_container">
                    
                    <h4>Product Name</h4>
                    <ProductNameInput productName={"Enter product name"}
                                      setProductName={props.setProductName}></ProductNameInput>
                </div>
            </div>
            <div className="filter_menu_quantityFilter">
                <div className="filter_menu_container">
                    
                    <h4>Quantity</h4>
                    <div className="filter_body">
                        <div>
                            <p>Min</p>
                            <input
                                type="number"
                                onChange={(e) => {
                                    if (e.target.value === "")
                                        props.setMinQuantity(null)
                                    else
                                        props.setMinQuantity(Number(e.target.value))}}/>
                        </div>
                        <div>
                            <p>Max</p>
                            <input
                                type="number"
                                onChange={(e) => {
                                    if (e.target.value === "")
                                        props.setMaxQuantity(null)
                                    else
                                        props.setMaxQuantity(Number(e.target.value))}}/>
                        </div>

                    </div>
                </div>
            </div>
            <div className="filter_menu_totalPriceFilter">
                <div className="filter_menu_container">
                    
                    <h4>Total Price</h4>
                    <div className="filter_body">
                        <div>
                            <p>Min</p>
                            <input
                                type="number"
                                onChange={(e) => {
                                    if (e.target.value === "")
                                        props.setMinPrice(null)
                                    else
                                        props.setMinPrice(Number(e.target.value))}}/>
                        </div>
                        <div>
                            <p>Max</p>
                            <input
                                type="number"
                                onChange={(e) => {
                                    if (e.target.value === "")
                                        props.setMaxPrice(null)
                                    else
                                        props.setMaxPrice(Number(e.target.value))}}/>
                        </div>

                    </div>
                </div>
            </div>
            <div className="filter_menu_dateFilter">
                <div className="filter_menu_container">
                    
                    <h4>Date</h4>
                    <div className="filter_body">
                        <div>
                            <p>After</p>
                            <input
                                type={"date"}
                                onChange={(e) => {
                                    if (e.target.value === "")
                                        props.setAfterDate(null)
                                    else
                                        props.setAfterDate(e.target.value)}}/>
                        </div>
                        <div>
                            <p>Before</p>
                            <input
                                type={"date"}
                                onChange={(e) => {
                                    if (e.target.value === "")
                                        props.setBeforeDate(null)
                                    else
                                        props.setBeforeDate(e.target.value)}}/>
                        </div>

                    </div>
                </div>
            </div>
            <div className="filter_menu_transactionTypeFilter">
                <div className="filter_menu_container">
                    
                    <h4>Transaction Type</h4>
                    <div className="filter_body">
                        <label>
                            <input
                                type={"radio"}
                                name="transactionType"
                                value={"SELL"}
                                onChange={(e) => {props.setTransactionType(e.target.value)}}/>
                            SELL
                        </label>
                        <label>
                            <input
                                type={"radio"}
                                name="transactionType"
                                value={"BUY"}
                                onChange={(e) => {props.setTransactionType(e.target.value)}}/>
                            BUY
                        </label>
                        <label>
                            <input
                                type={"radio"}
                                name="transactionType"
                                value={""}
                                onChange={(e) => {props.setTransactionType(null)}}/>
                            ALL
                        </label>
                    </div>
                </div>
            </div>
            <div className="filter_menu_orderFilter">
                <div className="filter_menu_container">
                    <h4>Order Type</h4>
                    <div className="radio_inputs">
                        <select
                            onChange={(e) => {
                                if (e.target.value === "")
                                    props.setOrderType(null)
                                else
                                    props.setOrderType(e.target.value)}}>
                            <option value={""}></option>
                            <option value={"DATE"}>Date</option>
                            <option value={"QUANTITY"}>Quantity</option>
                            <option value={"PRICE"}>Price</option>
                        </select>
                        <div className="radio_inputs">
                            <label>
                                <input
                                    name={"orderDirection"}
                                    type={"radio"}
                                    value="ASCENDING"
                                    onChange={(e) => {
                                        props.setOrderDirection(e.target.value)
                                    }}/>
                                ASC
                            </label>
                            <label>
                                <input
                                    name={"orderDirection"}
                                    type={"radio"}
                                    value=""
                                    onChange={(e) => {
                                        props.setOrderDirection(null)
                                    }}/>
                                -
                            </label>
                            <label>
                                <input
                                    name={"orderDirection"}
                                    type={"radio"}
                                    value="DESCENDING"
                                    onChange={(e) => {
                                        props.setOrderDirection(e.target.value)
                                    }}/>
                                DSC
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <button onClick={() => {
                props.setApplyButtonValue(!props.applyButtonValue)
            }}>Apply
            </button>
        </div>

    );
}

export default TransactionsFilterMenu;