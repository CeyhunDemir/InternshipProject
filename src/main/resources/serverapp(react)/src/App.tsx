import "./App.css"
import "./styles/theme.css"
import {HashRouter, Routes, Route} from "react-router-dom";
import {Login} from "./pages/LoginPage.tsx";
import {AllProductsPage} from "./pages/AllProductsPage";
import {Layout} from "./components/Layout.tsx";
import React from "react";

function App() {

  return (
      <HashRouter>
          <Routes>
                <Route element={<Layout/>}>

                  <Route path="/" element={<Login/>}/>
                  <Route path="/products" element={<AllProductsPage/>}/>

                </Route>
          </Routes>
      </HashRouter>
)
}

export default App
