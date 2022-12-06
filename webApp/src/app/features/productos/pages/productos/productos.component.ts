import { Component, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { ProductosService } from 'src/app/core/services/productos/productos.service';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.scss']
})
export class ProductosComponent implements OnInit {

  unsubscribe$ = new Subject<void>()
  productos :any[] =[]
  
  constructor(private productosServicce:ProductosService) { }

  ngOnInit(): void {

    this.obtenerProductos();
  }

  obtenerProductos(){

    this.productosServicce.obtenerProductos()
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response)=>{this.productos = response.products
        console.log('Response okr',response) 
  },
      (error)=>{console.log('Sali por el error',error)})
    
  }

}
