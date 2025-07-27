import { Routes } from '@angular/router';
import { ProductoListaComponent } from './producto-lista/producto-lista.component';
import { AgregarProductosComponent } from './agregar-productos/agregar-productos.component';
import { EditarProductoComponent } from './editar-producto/editar-producto.component';

//http://localhost:4200/productos
export const routes: Routes = [
    {path: 'productos', component: ProductoListaComponent},
    {path: '', redirectTo: 'productos', pathMatch: 'full'},
    {path: 'agregar-producto', component: AgregarProductosComponent},
    {path: 'editar-producto/:idProducto', component: EditarProductoComponent}

];
