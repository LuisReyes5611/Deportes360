package beans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import dao.UsuarioDAO;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {

    private String correo;
    private String clave;
    private Usuario usuarioLogueado;

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public Usuario getUsuarioLogueado() { return usuarioLogueado; }

    public String iniciarSesion() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            usuarioLogueado = dao.login(correo, clave);

            if (usuarioLogueado != null) {

                HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(true);

                session.setAttribute("usuario", usuarioLogueado);

                return "admin.xhtml?faces-redirect=true";  
            } else {
                FacesMessage msg = new FacesMessage("Datos incorrectos");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String cerrarSesion() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        if (session != null) session.invalidate();

        return "login.xhtml?faces-redirect=true";
    }
}
