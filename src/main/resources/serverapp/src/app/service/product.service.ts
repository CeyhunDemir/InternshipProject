import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AppState } from "../interface/app-state";
import { Product } from "../interface/product-interface";
import { environment } from "../components/environment/environment.dev";

@Injectable({providedIn: "root"})
export class ProductService {
    constructor(private http: HttpClient) {}

    addProduct(Product: Product) : Observable<any> {
        return this.http.post<any>(environment.apiUrl + "api/v1/product", Product)
    }

    getAllProducts(): Observable<Product[]> {
        return this.http.get<Product[]>(environment.apiUrl +"api/v1/product/all")
    }
}