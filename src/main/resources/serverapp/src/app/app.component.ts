import { afterNextRender, Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Product } from './interface/product-interface';
import { ProductService } from './service/product.service';
import { error } from 'console';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { NgFor } from '@angular/common';
import { HeaderComponent } from "./components/header/header.component";
import { AuthService } from './service/auth.service';
import { CommonModule, DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HeaderComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  // public products: Product[];
  title: string = "serverapp";
  authService = inject(AuthService)
  productService = inject(ProductService)
  constructor (){

  }

  ngOnInit(): void {
    this.authService.login(
      {
        email: "suleymanceyhundemir@gmail.com",
        password: "123123123123"
      }
    ).subscribe((r) => {
      console.log(r)
    })
    this.productService.getAllProducts().subscribe((r) => {
      console.log(r)
    })
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
