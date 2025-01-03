import "./App.css"
import "./styles/theme.css"
import {HashRouter, Routes, Route} from "react-router-dom";
import {Login} from "./pages/LoginPage.tsx";
import React, {lazy} from "react";
import {Layout} from "./components/Layout.tsx";
import ProtectedRoute from "./routes/ProtectedRoute.tsx";
import {HomePage} from "./pages/Home.tsx";

const AllProductsPage = lazy(() => import("./pages/AllProductsPage"));
const AddProductPage = lazy(() => import("./pages/AddProductPage.tsx"));
const EditProductPage = lazy(() => import("./pages/EditProductPage.tsx"));
const EditCustomerPage = lazy(() => import("./pages/EditCustomerPage.tsx"));
const AddCustomerPage = lazy(() => import("./pages/AddCustomerPage.tsx"));
const AllCustomersPage = lazy(() => import("./pages/AllCustomersPage.tsx"));
const AllTransactionsPage = lazy(() => import("./pages/AllTransactionsPage.tsx"));
const AddTransactionsPage = lazy(() => import("./pages/AddTransactionsPage.tsx"));
/*const SearchProductPage = lazy(() => import("./pages/SearchProductPage.tsx"));*/
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
                    {/*<Route path="/searchProduct"
                           element={
                        <ProtectedRoute>
                            <SearchProductPage/>
                        </ProtectedRoute>
                           }/>*/}
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
