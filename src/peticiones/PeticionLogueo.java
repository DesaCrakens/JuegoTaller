package peticiones;

import java.io.Serializable;

import cs.*;

public class PeticionLogueo implements Serializable{
	
	private String usuario,
					password;
	
	public PeticionLogueo(String u, String p) {
		this.usuario = u;
		this.password = p;
	}
	
	public String getUsuario(){
		return this.usuario;
	}
	
	public String getPassword(){
		return this.password;
	}
}