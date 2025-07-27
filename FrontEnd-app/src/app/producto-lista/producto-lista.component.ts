import { Component, inject } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-producto-lista',
  imports: [],
  templateUrl: './producto-lista.component.html',
})
export class ProductoListaComponent {

  productos!: Producto[];
  
  private productoServicio = inject(ProductoService);
  private enrutador = inject(Router)

  ngOnInit(){
    //Cargar los productos
    this.obtenerProductos();
  }

  private obtenerProductos(): void {
    this.productoServicio.obtenerProductosLista().subscribe(
      {
        next: (datos) => {
          this.productos = datos;
        },
        error: (error) => {
          console.error("Error al obtener los productos", error);
        }
      }
    );
  }

  editarProducto(idProducto: number){
    this.enrutador.navigate(['editar-producto', idProducto])
  }

  eliminarProducto(idProducto:number){
    this.productoServicio.eliminarProducto(idProducto).subscribe({
      next: (datos) => this.obtenerProductos(),
      error: (errores) => console.log(errores)
    });
  }
}
