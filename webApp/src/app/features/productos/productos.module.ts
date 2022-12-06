import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductosRoutingModule } from './productos-routing.module';
import { ProductosComponent } from './pages/productos/productos.component';
import { DemoComponent } from './pages/demo/demo.component';
import { FormularioComponent } from './pages/formulario/formulario.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    ProductosComponent,
    DemoComponent,
    FormularioComponent
  ],
  imports: [
    CommonModule,
    ProductosRoutingModule,
   //  SharedModule
  ]
})
export class ProductosModule { }
