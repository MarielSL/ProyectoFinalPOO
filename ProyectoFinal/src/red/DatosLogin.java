package red;

import java.io.Serializable;

public class DatosLogin implements Serializable {

    private String username;
    private String password;

    public DatosLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { 
    	return username; 
    }
    
    public String getPassword() { 
    	return password; 
    }
}