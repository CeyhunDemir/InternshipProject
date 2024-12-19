import "./App.css"
import "./styles/theme.css"
import {HashRouter, Routes, Route} from "react-router-dom";
import {Login} from "./pages/LoginPage.tsx";
import {AllProductsPage} from "./pages/AllProductsPage";
import {Layout} from "./components/Layout.tsx";
import React from "react";
import ProtectedRoute from "./routes/ProtectedRoute.tsx";
import {HomePage} from "./pages/Home.tsx";
import {AddProductPage} from "./pages/AddProductPage.tsx";
import {EditProductPage} from "./pages/EditProductPage.tsx";
import {EditCustomerPage} from "./pages/EditCustomerPage.tsx";
import {AddCustomerPage} from "./pages/AddCustomerPage.tsx";
import {AllCustomersPage} from "./pages/AllCustomersPage.tsx";
import {AllTransactionsPage} from "./pages/AllTransactionsPage.tsx";
import {AddTransactionsPage} from "./pages/AddTransactionsPage.tsx";
import {SearchProductPage} from "./pages/SearchProductPage.tsx";
import { useParams } from 'react-router-dom';

function App() {

  return (
      <HashRouter>
          <Routes>
                <Route element={<Layout/>}>

                    <Route path="/"
                           element={<HomePage/>}/>
                    <Route path="/login"
                           element={<Login/>}/>
                    <Route path="/products"
                           element={
                        <ProtectedRoute>
                        <AllProductsPage/>
                        </ProtectedRoute>
                    }/>
                    <Route path="/addProduct"
                           element={
                        <ProtectedRoute>
                            <AddProductPage/>
                        </ProtectedRoute>
                    }/>
                    <Route path="/editProduct/:productId"
                           element={
                        <ProtectedRoute>
                            <EditProductPage/>
                        </ProtectedRoute>
                    }/>
                    <Route path="/searchProduct"
                           element={
                        <ProtectedRoute>
                            <SearchProductPage/>
                        </ProtectedRoute>
                           }/>
                    <Route path="/customers"
                           element={
                        <ProtectedRoute>
                            <AllCustomersPage/>
                        </ProtectedRoute>
                    }/>
                    <Route path="/addCustomer"
                           element={
                        <ProtectedRoute>
                            <AddCustomerPage/>
                        </ProtectedRoute>
                    }/>
                    <Route path="/editCustomer/:customerId"
                           element={
                               <ProtectedRoute>
                                   <EditCustomerPage/>
                               </ProtectedRoute>
                           }/>
                    <Route path="/transactions"
                           element={
                               <ProtectedRoute>
                                   <AllTransactionsPage/>
                               </ProtectedRoute>
                           }/>
                    <Route path="/addTransaction"
                           element={
                        <ProtectedRoute>
                            <AddTransactionsPage/>
                        </ProtectedRoute>
                    }/>

                </Route>
          </Routes>
      </HashRouter>
)
}

export default App
