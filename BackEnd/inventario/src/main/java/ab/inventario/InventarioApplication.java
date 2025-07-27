package ab.inventario;

import ab.inventario.modelo.Producto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	//prueba de lombok
		Producto producto = new Producto();
		producto.setDescripcion("calzas");
		producto.setPrecio(5000.00);
		producto.setStock(35);

		//imprimimos
		System.out.println(producto);
	}

}
