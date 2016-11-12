package peticiones;

import cs.ServerTh;

public class PeticionCrearJugador {
	private String respuesta;
	
	public String getRespuesta() {
		return respuesta;
	}

	public PeticionCrearJugador(String[] datosPeticion, ServerTh serverTh) {
		String 	usuario = datosPeticion[1];
		int 	minJ = Integer.parseInt(datosPeticion[2]),
				maxJ = Integer.parseInt(datosPeticion[3]);
		boolean resultado=serverTh.getServer().getConexionBD().agregarJugador(usuario, minJ, maxJ);
		if(resultado)
			respuesta = CodigoPeticion.CREAR_JUGADOR_CORRECTO+"";
		else
			respuesta = CodigoPeticion.CREAR_JUGADOR_INCORRECTO+"";
		
	}
}
