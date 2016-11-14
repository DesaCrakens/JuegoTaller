package cs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import java.net.UnknownHostException;

import peticiones.CodigoPeticion;
import peticiones.PeticionLogueo;
import pojo.POJOLogin;


public class Cliente{
	
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private static String respuestaServer;
	private boolean estaConectado = false;
	private String nombreUsuario;

	
	
	public Cliente(String host){	
		try {
			s = new Socket(host, Server.PUERTO_POR_DEFECTO);
		} catch (UnknownHostException e) {
			System.out.println("Unknown host exception creando socket del lado cliente.");
		} catch (IOException e) {
			System.out.println("IOException creando socket del lado cliente");
		}
		try {
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			System.out.println("IOException creando OIS en el cliente");
		}
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
		} catch (IOException e) {
			System.out.println("IOException creando OOS en el cliente");
		}
		estaConectado = true;
	}
	
	public boolean isEstaConectado() {
		return estaConectado;
	}

	public String getNombre() {
		return this.nombreUsuario;
	}	
	

	public int loguearse(String nombre, String pass) {
		try {
			PeticionLogueo petLog = new PeticionLogueo(nombre, pass);
			oos.writeObject(new Mensaje(CodigoPeticion.LOGEO,petLog));		//manda mje de login
			oos.flush();
		} catch (IOException e1) {
			System.out.println("Error en el login al enviar petici�n por IOException");
		}
		try {
			Mensaje respuestaSv = (Mensaje) ois.readObject();
			return respuestaSv.getCodigo();
		} catch (ClassNotFoundException e) {
			System.out.println("Error en el login al recibir respuesta por ClassNotFound");
		} catch (IOException e) {
			System.out.println("Error en el login al recibir respuesta por IOException");
		}
		return 1234;
	}
	


	public void crearJugador(String nombrePartida, int minJ, int maxJ) {
		try {/*
			POJOCrearPartida partidaNueva = new POJOCrearPartida(nombrePartida, minJ, maxJ);
			out.writeUTF(partidaNueva.getDatosEnviable());*/
		} catch (Exception e) {
			
		}
	}
	
}
	
