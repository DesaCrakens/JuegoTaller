package principal.interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JComboBox;

import principal.entes.personajes.Atacable;
import principal.ElementosPrincipales;
import principal.entes.enemigos.Enemigo;
import principal.entes.enemigos.Goblin;
import principal.entes.personajes.Engorgio;
import principal.entes.personajes.Especialidad;
import principal.entes.personajes.Guerrero;
import principal.entes.personajes.Hechicero;
import principal.entes.personajes.Humano;
import principal.entes.personajes.Personaje;

import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Combate extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnAtacar;
	private JComboBox comboBox;
	private JButton btnSeleccionar;
	private JButton btnHuir;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JLabel label_1;
	private JProgressBar progressBar_2;
	private JLabel lblMagia;
	private JProgressBar progressBar;
	private JProgressBar progressBar_1;
	private JLabel lblSalud;
	private boolean turno1;
	private boolean turno2;
	private boolean combatiendo;
	private boolean huir;
	private Personaje per;
	private Enemigo ene;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personaje p=new Humano();
					Especialidad c=new Hechicero();
					c.getHechicero().agregarHechizo("Engorgio", new Engorgio());
					p.setCasta(c);
					p.bonificacionDeCasta();
					p.setNombrePersonaje("Java");
					Enemigo e=new Goblin();
					Combate combate = new Combate(p,e);
					
					combate.setVisible(true);
					
					combate.combatir();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Combate(Personaje p, Enemigo e) {
		
		this.per = p;
		this.ene = e;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 360);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textInactiveText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 197, 624, 125);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnAtacar = new JButton("Atacar");
		btnAtacar.setForeground(Color.BLUE);
		btnAtacar.setFont(new Font("Harrington", Font.PLAIN, 15));
		btnAtacar.setBackground(Color.WHITE);
		btnAtacar.setBounds(10, 11, 89, 23);
		panel.add(btnAtacar);
		
		comboBox = new JComboBox();
		comboBox.setForeground(Color.RED);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Harrington", Font.PLAIN, 15));
		comboBox.setBounds(10, 61, 219, 20);
		panel.add(comboBox);
		
		btnSeleccionar = new JButton("");
		btnSeleccionar.setForeground(Color.BLUE);
		btnSeleccionar.setFont(new Font("Harrington", Font.PLAIN, 15));
		btnSeleccionar.setBounds(10, 92, 219, 23);
		panel.add(btnSeleccionar);
		
		if (p.getCasta().getNombre() != "Hechicero") 
			btnSeleccionar.setText("Seleccionar Habilidad");
		else
			btnSeleccionar.setText("Seleccionar Hechizo");
		
		
		btnHuir = new JButton("Huir");
		btnHuir.setForeground(Color.BLUE);
		btnHuir.setFont(new Font("Harrington", Font.PLAIN, 15));
		btnHuir.setBounds(525, 91, 89, 23);
		panel.add(btnHuir);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(348, 11, 192, 14);
		panel.add(progressBar);
		progressBar.setMaximum(p.getSaludTotal());
		progressBar.setMinimum(0);
		progressBar.setValue(p.getSalud());
		
		progressBar_1 = new JProgressBar();
		progressBar_1.setForeground(Color.YELLOW);
		progressBar_1.setBounds(348, 36, 192, 14);
		panel.add(progressBar_1);
		progressBar_1.setMaximum(p.getEnergiaTotal());
		progressBar_1.setMinimum(0);
		progressBar_1.setValue(p.getEnergia());
	
		lblSalud = new JLabel("Salud");
		lblSalud.setFont(new Font("Harrington", Font.BOLD, 15));
		lblSalud.setForeground(Color.RED);
		lblSalud.setBounds(287, 11, 51, 14);
		panel.add(lblSalud);
		
		lblNewLabel = new JLabel("Energia");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Harrington", Font.BOLD, 15));
		lblNewLabel.setBounds(271, 30, 67, 20);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel( p.getSaludTotal() + "/" + p.getSalud());
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Harrington", Font.BOLD, 15));
		lblNewLabel_1.setBounds(550, 11, 46, 14);
		panel.add(lblNewLabel_1);
		
		label = new JLabel(p.getEnergiaTotal() + "/" + p.getEnergia());
		label.setForeground(Color.RED);
		label.setFont(new Font("Harrington", Font.BOLD, 15));
		label.setBounds(550, 36, 46, 14);
		panel.add(label);
		
		if (p.getCasta().getNombre() == "Hechicero") {
			
			progressBar_2 = new JProgressBar();
			progressBar_2.setForeground(Color.BLUE);
			progressBar_2.setBounds(348, 61, 192, 14);
			panel.add(progressBar_2);
			progressBar_2.setMaximum(p.getCasta().getMagiaTot());
			progressBar_2.setMinimum(0);
			progressBar_2.setValue(p.getCasta().getMagia());
			
			lblMagia = new JLabel("Magia");
			lblMagia.setForeground(Color.RED);
			lblMagia.setFont(new Font("Harrington", Font.BOLD, 15));
			lblMagia.setBounds(281, 58, 51, 20);
			panel.add(lblMagia);
			
			label_1 = new JLabel(p.getCasta().getMagiaTot() + "/" + p.getCasta().getMagia());
			label_1.setForeground(Color.RED);
			label_1.setFont(new Font("Harrington", Font.BOLD, 15));
			label_1.setBounds(550, 64, 46, 14);
			panel.add(label_1);
		}
		
		for (int i = 0; i < p.getCasta().getHabilidades().size(); i++) {
			comboBox.addItem(p.getCasta().getHabilidades().get(i));
		}
		
		//metodo de los botones//
		
		//presionar atacar//
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				combatiendo=per.atacar(ene);
				ene.serEnergizado();
				turno2 = true;
				turno1 = false;
			}
		});
		
		//presionar seleccionar habilidad//
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combatiendo=habilidadesDeCasta();
				ene.serEnergizado();
				turno2 = true;
				turno1 = false;
			}
		});
		
		//presionar huir//
		btnHuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combatiendo=false;
				huir=true;
				per.perderItemMasValioso(per);
			}
		});
	}
	
	//personaje contra enemigo
	public void combatir(){ // personaje contra enemigo //
			
			this.combatiendo = true;
			this.huir= false;
			
			definirTurnos();
			
			while(combatiendo && !huir){ //mientras ambos esten vivos el combate perdura//
				
				if(this.turno1){
					this.btnAtacar.setEnabled(true);
					this.btnSeleccionar.setEnabled(true);
					this.btnHuir.setEnabled(true);
					
				}	
				
				if(ene.estaVivo() && huir!=true){
					if(this.turno2){
						
						combatiendo=ene.atacar(per); //si el enemigo tiene varios ataques tiras un random y un switch//
						per.serEnergizado();
						this.turno1 = true;
						this.turno2 = false;
						
						this.btnAtacar.setEnabled(false);
						this.btnSeleccionar.setEnabled(false);
						this.btnHuir.setEnabled(false);
					}
				}
				else
					combatiendo=false;
			}
	}
			

	public void definirTurnos(){
		
		if (per.getAgilidad() > ene.getAgilidad()){
			turno1= true;
		}
		else{
			turno2=true;
		}
	}

	private boolean habilidadesDeCasta() {
		
		
		switch (per.getCasta().getNombre()) {
		
		case "Hechicero":
		{
			if(per.getCasta().getHechicero().getCantidadDeHechizos() != 0){
				per.getCasta().getHechicero().hechizar((String)comboBox.getSelectedItem(), ene); //Por ahora solo sabe engorgio//	
				return true;
			}
		}			
				
		case "Guerrero":
		{
			switch((String)comboBox.getSelectedItem()){
			
				case "Posicion Normal":
				{
					per.getCasta().getGuerrero().posicionNormal();break;
				}
					
				case "Posicion Agresiva":
				{
					per.getCasta().getGuerrero().posicionAgresiva();break;
				}
			
				case "Posicion Defensiva":
				{
					per.getCasta().getGuerrero().posicionDefensiva();break;
				}
			
			}
			
			return per.atacar(ene);
		}
		
		case "Ladron":
		{	
			if((String)comboBox.getSelectedItem() == "Daño Critico")
				per.getCasta().getLadron().dañoCritico(per.getDefensa());
			else
				per.getCasta().getLadron().salirDañoCritico();
			
			return per.atacar(ene);
			
		}
		
		
		}
		
		return true;
	}

	public boolean getHuir() {
		
		return this.huir;
	}

	public boolean getTurno1() {
		
		return this.turno1;
	}
	
	public boolean getTurno2() {
		
		return this.turno2;
	}
}
