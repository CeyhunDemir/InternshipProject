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
import { useParams } from 'react-router-dom';

function App() {

  return (
      <HashRouter>
          <Routes>
                <Route element={<Layout/>}>

                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/products" element={
                        <ProtectedRoute>
                        <AllProductsPage/>
                        </ProtectedRoute>
                    }/>
                    <Route path="/addProduct" element={
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

                </Route>
          </Routes>
      </HashRouter>
)
}

export default App
