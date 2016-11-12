package peticiones;

import cs.ServerTh;

public class PeticionCrearJugador {
	private String respuesta;
	
	public String getRespuesta() {
		return respuesta;
	}

	public PeticionCrearJugador(String[] datosPeticion, ServerTh serverTh) {
		String 	usuario = datosPeticion[1], 
				password = datosPeticion[2],
				mail = datosPeticion[3];
		boolean resultado=serverTh.getServer().getConexionBD().agregarJugador(usuario, password, mail);
		if(resultado)
			respuesta = CodigoPeticion.CREAR_JUGADOR_CORRECTO+"";
		else
			respuesta = CodigoPeticion.CREAR_JUGADOR_INCORRECTO+"";
		
	}
}
