import { Component, Inject, inject } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-editar-producto',
  imports: [FormsModule],
  templateUrl: './editar-producto.component.html',
})
export class EditarProductoComponent {
  producto: Producto = new Producto();

  idProducto!: number;

  private productoServicio = inject(ProductoService);
  private ruta = inject(ActivatedRoute);
  private enrutador = inject(Router);

  ngOnInit(){
    this.idProducto = this.ruta.snapshot.params['idProducto'];
    this.productoServicio.obtenerProductoPorId(this.idProducto).subscribe({
      next: (datos) => this.producto = datos,
      error: (errores: any) => console.log(errores)
    });
  }

  onSubmit(){
    //editar producto
    this.guardarProducto();
  }

  guardarProducto(){
    this.productoServicio.editarProducto(this.idProducto, this.producto).subscribe({
      next: (datos) => this.irProductoLista(),
      error: (errores) => console.log(errores)
    });
  }

  irProductoLista(){
    this.enrutador.navigate(['/productos']);
  }

}
