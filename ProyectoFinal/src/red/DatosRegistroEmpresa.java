package red;

import java.io.Serializable;
import logico.TipoEmpresa;

public class DatosRegistroEmpresa implements Serializable {
    private String rnc;
    private String nombre;
    private String telefono;
    private String direccion;
    private TipoEmpresa tipo;
    private String correo;
    private String username;
    private String password;
    private String fotoPerfil;

    public DatosRegistroEmpresa(String rnc, String nombre, String telefono, String direccion,
            TipoEmpresa tipo, String correo, String username, String password, String fotoPerfil) {
        this.rnc = rnc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tipo = tipo;
        this.correo = correo;
        this.username = username;
        this.password = password;
        this.fotoPerfil = fotoPerfil;
    }

    public String getRnc() { 
    	return rnc; 
    }
    
    public String getNombre() { 
    	return nombre; 
    }
    
    public String getTelefono() { 
    	return telefono; 
    }
    
    public String getDireccion() { 
    	return direccion; 
    }
    
    public TipoEmpresa getTipo() { 
    	return tipo; 
    }
    
    public String getCorreo() { 
    	return correo; 
    }
    
    public String getUsername() { 
    	return username; 
    }
    
    public String getPassword() { 
    	return password; 
    }
    
    public String getFotoPerfil() { 
    	return fotoPerfil;
    }
    
}