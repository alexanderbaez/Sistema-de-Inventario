package ab.inventario.controlador;


import ab.inventario.excepcion.RecursoNoEncontradoExcepcion;
import ab.inventario.modelo.Producto;
import ab.inventario.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")// puerto por default de angular
public class ProductoControlador {

    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);
    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/productos")
    @CrossOrigin
    public List<Producto> obtenerProductos(){
        List<Producto> productoList = this.productoServicio.listarProducto();
        logger.info("Productos Obtenidos:");
        productoList.forEach(producto -> logger.info(producto.toString()));
        return productoList;
    }

    @PostMapping("/productos")
    @CrossOrigin
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("Producto a agregar:" + producto);
        return this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/{idProducto}")
    @CrossOrigin
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int idProducto){
        Producto producto = this.productoServicio.buscarProductoPorId(idProducto);
        if (producto != null){
            return ResponseEntity.ok(producto);
        }
        else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: "+idProducto);
        }
    }

    @PutMapping("/productos/{idProducto}")
    @CrossOrigin
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int idProducto, @RequestBody Producto productoRecibido){

        if (productoRecibido != null){
            Producto producto = this.productoServicio.buscarProductoPorId(idProducto);
            producto.setDescripcion(productoRecibido.getDescripcion());
            producto.setPrecio(productoRecibido.getPrecio());
            producto.setStock(productoRecibido.getStock());

            //guardamos la informacion
            this.productoServicio.guardarProducto(producto);
            return ResponseEntity.ok(producto);
        }
        else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: "+idProducto);
        }
    }

    @DeleteMapping("/productos/{idProducto}")
    @CrossOrigin
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable int idProducto){
        Producto producto = this.productoServicio.buscarProductoPorId(idProducto);
        if (producto == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: "+ idProducto);
        }
        this.productoServicio.eliminarProductoPorId(producto.getIdProducto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

//    @DeleteMapping("/productos/{idProducto}")
//    @CrossOrigin
//    public ResponseEntity<Void> eliminarProducto(@PathVariable int idProducto) {
//        Producto producto = this.productoServicio.buscarProductoPorId(idProducto);
//        if (producto == null) {
//            throw new RecursoNoEncontradoExcepcion("No se encontró el ID: " + idProducto);
//        }
//        this.productoServicio.eliminarProductoPorId(idProducto);
//        return ResponseEntity.noContent().build(); // 204 No Content
//    }
}
