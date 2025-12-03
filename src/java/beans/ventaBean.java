package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "ventaBean")
@RequestScoped
public class ventaBean {

    private int cantProducto;
    private String metodoEnvio;
    private double totalVenta;
    private String metodoPago;

    // Getters y setters
    public int getCantProducto() {
        return cantProducto;
    }

    public void setCantProducto(int cantProducto) {
        this.cantProducto = cantProducto;
    }

    public String getMetodoEnvio() {
        return metodoEnvio;
    }

    public void setMetodoEnvio(String metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String guardarVenta() {
        // Aquí va la lógica para guardar en BD
        System.out.println("Venta guardada:");
        System.out.println("Cantidad: " + cantProducto);
        System.out.println("Envío: " + metodoEnvio);
        System.out.println("Total: " + totalVenta);
        System.out.println("Pago: " + metodoPago);

        return "detalle-venta.xhtml?faces-redirect=true";
    }
}
