package impl;


/*
 * 
 * MORPION BONNET CELINE, BOUKRA IMANE, LANSEUR ZOUBIR 
 * MORPION BONNET CELINE, BOUKRA IMANE, LANSEUR ZOUBIR 
 * MORPION BONNET CELINE, BOUKRA IMANE, LANSEUR ZOUBIR 
 * MORPION BONNET CELINE, BOUKRA IMANE, LANSEUR ZOUBIR
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;

import api.ISocketControlerM;
import api.ISocketViewM;

public class ThreadedClientM implements ISocketControlerM {

	/**
	 * @param args
	 */
	private ISocketViewM socketViewM;
	private Socket clientSocket;
	//static String serverUri = "127.0.0.1";
	//static int port = 8052;
	BufferedReader entree;
	PrintStream sortie;
	Socket socket;
	String reponse;

	boolean disconnect;

	int idClient; // id du client vaut 0 si le client est le premier à se connecter il vaut 1 sinon.

	public ThreadedClientM(ISocketViewM socketViewM) { // on initialise socketViewM
		this.socketViewM = socketViewM;
	}
	public int getIdClient(){
		return idClient;  // un getter qui retourne l'id du Client
	};

	public void requeteId(String positionBouton) //permet d'envoyer au serveur le bouton enclenché sous le format B151 par exemple
	{											 // (si le bouton en position (1,5) est enclenché par le joueur 1)
		sortie.println(positionBouton+idClient);
	}

	public void requete(String requete)// permet d'envoyer au serveur une requete
	{
		sortie.println(requete);
	}

	@Override
	public boolean connect(String serverUri, int port) throws IOException{
		// TODO Auto-generated method stub

		socket = new Socket(serverUri, port);
		entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		sortie = new PrintStream(socket.getOutputStream());
		reponse = entree.readLine(); //***
		idClient= Integer.parseInt(reponse);//on initialise l'id du client.

		new miseAJour(serverUri, port).start();

		return true;
	}

	public void disconnect() throws IOException{ //se déconnecter

		requete("stop-server");
		entree.close();
		sortie.close();
		socket.close();
		disconnect= true;

	}

	class miseAJour extends Thread { 

		String serverUri;
		int port;

		public miseAJour(String serverUri, int port){this.serverUri=serverUri; this.port=port;}

		@Override
		public void run() {

			while(!disconnect){
				try {

					reponse = entree.readLine();
					
					if(reponse.toLowerCase().contains("joueur"))  {socketViewM.sendMessage(reponse);} // permet d'écrire le message reçu pour le tchat
					
					if(reponse.equals("B110"))	{socketViewM.setlibelle(socketViewM.getButton11()); }; // on met a jour la vue en signalant qu'un bouton est enclenché
					if(reponse.equals("B210"))	{socketViewM.setlibelle(socketViewM.getButton21()); };
					if(reponse.equals("B310"))	{socketViewM.setlibelle(socketViewM.getButton31()); };
					if(reponse.equals("B120"))	{socketViewM.setlibelle(socketViewM.getButton12()); };
					if(reponse.equals("B220"))	{socketViewM.setlibelle(socketViewM.getButton22()); };
					if(reponse.equals("B320"))	{socketViewM.setlibelle(socketViewM.getButton32()); };
					if(reponse.equals("B130"))	{socketViewM.setlibelle(socketViewM.getButton13()); };
					if(reponse.equals("B230"))	{socketViewM.setlibelle(socketViewM.getButton23()); };
					if(reponse.equals("B330"))	{socketViewM.setlibelle(socketViewM.getButton33()); };

					if(reponse.equals("B111"))	{socketViewM.setlibelle1(socketViewM.getButton11()); };
					if(reponse.equals("B211"))	{socketViewM.setlibelle1(socketViewM.getButton21()); };
					if(reponse.equals("B311"))	{socketViewM.setlibelle1(socketViewM.getButton31()); };
					if(reponse.equals("B121"))	{socketViewM.setlibelle1(socketViewM.getButton12()); };
					if(reponse.equals("B221"))	{socketViewM.setlibelle1(socketViewM.getButton22()); };
					if(reponse.equals("B321"))	{socketViewM.setlibelle1(socketViewM.getButton32()); };
					if(reponse.equals("B131"))	{socketViewM.setlibelle1(socketViewM.getButton13()); };
					if(reponse.equals("B231"))	{socketViewM.setlibelle1(socketViewM.getButton23()); };
					if(reponse.equals("B331"))	{socketViewM.setlibelle1(socketViewM.getButton33()); };
					
					if(reponse.equals("0gagne"))	{socketViewM.setTextVictoire("LE JOUEUR N°0 GAGNE !"); };// envoie la requete d'affichage du joueur gagnant 
					if(reponse.equals("1gagne"))	{socketViewM.setTextVictoire("LE JOUEUR N°1 GAGNE !"); };

					if(reponse.equals("clear"))	{Clear();};

					System.out.println(reponse);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void Clear() { 
		// TODO Auto-generated method stub

		socketViewM.effaceTextVictoire();
		socketViewM.setlibelle2(socketViewM.getButton11());
		socketViewM.setlibelle2(socketViewM.getButton21());
		socketViewM.setlibelle2(socketViewM.getButton31());
		socketViewM.setlibelle2(socketViewM.getButton12());
		socketViewM.setlibelle2(socketViewM.getButton22());
		socketViewM.setlibelle2(socketViewM.getButton32());
		socketViewM.setlibelle2(socketViewM.getButton13());
		socketViewM.setlibelle2(socketViewM.getButton23());
		socketViewM.setlibelle2(socketViewM.getButton33());
		System.out.println("Clear");


	}
}
