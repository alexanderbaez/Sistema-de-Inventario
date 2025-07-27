import { Component, Inject, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agregar-productos',
  imports: [FormsModule],
  templateUrl: './agregar-productos.component.html',
})
export class AgregarProductosComponent {
  
  producto: Producto = new Producto();

  private productoServicio = inject(ProductoService);
  private enrutador = inject(Router);

  onSubmit(){
    this.guardarProducto();
  }
  guardarProducto(){
    this.productoServicio.agregarProducto(this.producto).subscribe({
      next: (datos) => {
        this.irListaProducto();
      },
      error:(error: any) => {console.log(error)}
    });
  }

  irListaProducto(){
    this.enrutador.navigate(['/productos'])
  }
}
