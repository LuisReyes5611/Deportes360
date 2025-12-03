package beans; 

import dao.UsuarioDAO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;

@ManagedBean(name = "registroBean") 
@RequestScoped
public class RegistroBean {

    private String nombre;
    private String direccion;
    private String fechaNacimiento;
    private String barrio;
    private String usuCorreo;
    private String usuario;
    private String clave;
    private String confirmar;
    private String usutelefono;

    // ------------------ MÉTODO REGISTRAR ------------------
    public String registrar() {
        // Validación de contraseñas
        if (!clave.equals(confirmar)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", null));
            return null;
        }

        try {
            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setDireccion(direccion);
            u.setFechaNacimiento(fechaNacimiento);
            u.setBarrio(barrio);
            u.setUsuCorreo(usuCorreo);
            u.setUsuario(usuario);
            u.setClave(clave);
            u.setUsuTelefono(usutelefono);
            u.setRol("cliente"); // rol por defecto

            UsuarioDAO dao = new UsuarioDAO();

            if (dao.registrar(u)) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro exitoso"));
                return "login.xhtml?faces-redirect=true"; // redirige al login
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar", null));
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el registro", null));
        }

        return null;
    }

    // ------------------ GETTERS & SETTERS ------------------
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getBarrio() { return barrio; }
    public void setBarrio(String barrio) { this.barrio = barrio; }

    public String getUsuCorreo() { return usuCorreo; }
    public void setUsuCorreo(String usuCorreo) { this.usuCorreo = usuCorreo; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getConfirmar() { return confirmar; }
    public void setConfirmar(String confirmar) { this.confirmar = confirmar; }

    public String getUsutelefono() { return usutelefono; }
    public void setUsuTelefono(String usutelefono) { this.usutelefono = usutelefono; }
}
