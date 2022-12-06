import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { DemoComponent } from './pages/demo/demo.component';
import { FormularioComponent } from './pages/formulario/formulario.component';
import { ProductosComponent } from './pages/productos/productos.component';

const routes: Routes = [
  { path: '', component: ProductosComponent },
  {
    path: 'demo-ppal', component: DemoComponent,
  },
  {
    path: 'crear', component: FormularioComponent,
  },
  {
    path: 'editar', component: FormularioComponent,
  },
  {
    path: 'demo', //Demo con rutas hijas
    children: [
      { path: 'hijo', component: ProductosComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProductosRoutingModule {}
