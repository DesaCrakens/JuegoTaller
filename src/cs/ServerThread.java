package cs;		
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import peticiones.CodigoPeticion;
import peticiones.PeticionLogueo;
import peticiones.PeticionRegistro;

public class ServerThread extends Thread{
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Server sv;	

	public ServerThread(Socket s, Server sv) {
		super();
		this.sv = sv;
		this.s = s;
		try {
			this.oos = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
			System.out.println("Problema generando el OOS");
		}
		try {
			this.ois = new ObjectInputStream(s.getInputStream());
		} catch (Exception e) {
			System.out.println("Problema generando el OIS");
		}
	}

	@Override
	public void run() {
		while(true){
			Mensaje mjeIn = null;
			try {
				mjeIn = (Mensaje) ois.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("El thread del sv no encontr� la clase Mensaje al hacer readObject");
			} catch (IOException e) {
				System.out.println("IOException en el thread del server al recibir un mensaje");
				e.printStackTrace();
			}
			switch (mjeIn.getCodigo()){
			case CodigoPeticion.LOGEO:
				PeticionLogueo petLog = (PeticionLogueo) mjeIn.getObj();
				try {
					oos.writeObject(new Mensaje(sv.getConexionBD().login(petLog.getUsuario(), new String(petLog.getPassword())),null));		//manda mje con el c�digo que devuelva el intento de login en la BD
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case CodigoPeticion.REGISTRO:
				PeticionRegistro petReg = (PeticionRegistro) mjeIn.getObj();
				try {
					oos.writeObject(new Mensaje(sv.getConexionBD().registro(petReg.getUsuario(), new String(petReg.getPassword()), petReg.getEmail()),null));
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
		
}