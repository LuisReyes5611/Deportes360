package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "carritoBean")
@SessionScoped
public class CarritoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Producto> productos = new ArrayList<>();
    private String tallaSeleccionada;

    // ---------- CLASE PRODUCTO ----------
    public static class Producto implements Serializable {
        private static final long serialVersionUID = 1L;

        private String nombre;
        private int precio;
        private String imagen;
        private String talla;

        public Producto(String nombre, int precio, String imagen, String talla) {
            this.nombre = nombre;
            this.precio = precio;
            this.imagen = imagen;
            this.talla = talla;
        }

        public String getNombre() { return nombre; }
        public int getPrecio() { return precio; }
        public String getImagen() { return imagen; }
        public String getTalla() { return talla; }
    }

    public void agregarProducto(String nombre, int precio, String imagen) {
        if (tallaSeleccionada == null || tallaSeleccionada.isEmpty()) {
            // Podrías lanzar una FacesMessage aquí si quieres notificar al usuario
            System.out.println("ERROR: Debe seleccionar una talla antes de comprar");
            return;
        }
        productos.add(new Producto(nombre, precio, imagen, tallaSeleccionada));
        System.out.println("Producto agregado al carrito: " + nombre + " - talla " + tallaSeleccionada);
    }

    public void eliminar(int index) {
        if (index >= 0 && index < productos.size()) {
            Producto p = productos.remove(index);
            System.out.println("Producto eliminado: " + p.getNombre());
        }
    }

    public void vaciar() {
        productos.clear();
        System.out.println("Carrito vaciado");
    }

    public int getTotal() {
        int total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String getTallaSeleccionada() {
        return tallaSeleccionada;
    }

    public void setTallaSeleccionada(String tallaSeleccionada) {
        this.tallaSeleccionada = tallaSeleccionada;
    }
}
