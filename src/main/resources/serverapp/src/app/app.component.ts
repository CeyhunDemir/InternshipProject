import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Product } from './interface/product-interface';
import { ProductService } from './service/product.service';
import { error } from 'console';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { NgFor } from '@angular/common';
import { HeaderComponent } from "./components/header/header.component";
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  // public products: Product[];
  title: string = "serverapp";
  authService = inject(AuthService)
  constructor (){
    this.authService.login(
      {
        email: "suleymanceyhundemir@gmail.com",
        password: "123123123123"
      }
    ).subscribe((r) => {
      console.log(r)
    })
  }

  ngOnInit(): void {
    // this.getAllProducts();
  }

















  // public getAllProducts(): void {
  //   this.productService.getAllProducts().subscribe(
  //     {
  //       next: (response: Product[]) => {
  //         this.products = response;
  //       },
  //       error:
  //       (error: HttpErrorResponse) => {
  //         alert(error.message);
  //       }
  //     }
  //   )
  // }
}
