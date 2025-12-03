package beans;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import dao.UsuarioDAO;

@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String correo;
    private String password;

    private Usuario adminLogueado;

    // ------------------ LOGIN ------------------
    public String login() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            adminLogueado = dao.login(correo, password);

            if (adminLogueado != null) {
                // Redirecciona al panel de administración
                return "/admin/admin?faces-redirect=true";
            } else {
                // Mensaje de error si usuario o contraseña incorrectos
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Datos incorrectos", "Usuario o contraseña inválidos"));
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "No se pudo iniciar sesión"));
            return null;
        }
    }

    // ------------------ VERIFICAR SESIÓN ------------------
    public void verificarSesion() {
        try {
            if (adminLogueado == null) {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("../login.xhtml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------ LOGOUT ------------------
    public String logout() {
        adminLogueado = null;
        correo = null;
        password = null;
        // Redirige al login y finaliza la sesión
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    // ------------------ GETTERS & SETTERS ------------------
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getAdminLogueado() {
        return adminLogueado;
    }

    public void setAdminLogueado(Usuario adminLogueado) {
        this.adminLogueado = adminLogueado;
    }
}
