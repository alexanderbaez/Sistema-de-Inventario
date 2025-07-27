package ab.inventario.servicio;

import ab.inventario.modelo.Producto;

import java.util.List;

public interface IProductoServicio {
    List<Producto> listarProducto();
    Producto buscarProductoPorId(Integer idproducto);
    Producto guardarProducto (Producto producto);
    void eliminarProductoPorId (Integer idProducto);
}
