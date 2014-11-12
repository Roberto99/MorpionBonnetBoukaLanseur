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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/*Nous avons ici repris la classe server du profeseur M.Pfister et nous l'avons adaptée au morpion.
*/

public class Server0 {

	//Le tableau ci dessous est le tableau de flux de sortie contenant les flux de sortie texte vers les deux joueurs(vers les deux clients)
	private ArrayList<PrintStream> ArrayOutput =new ArrayList<PrintStream>(2); 
	private static final int PORT = 8051;
	private ServerSocket socketEcoute; 
	private boolean mustStop;
	int idClient=0;// id du client, le premier joueur qui se connecte obtient l'id 0 et le second joueur qui se connecte l'id 1
	
	//Arraybtn est une arrayList qui permet de contenir des String.
	//Ces string représentent les boutons du morpion qui ont été enclenchés.
	//Par exemple si le bouton B121 est contenu dans Arraybtn cela signifie que
	//le bouton à la position (1,2) du client numéro 1 à été enclenché.
	private ArrayList<String> Arraybtn =new ArrayList<String>(18); 

	boolean tour0 = true; // ces boolean permettent de définir a qui le tour de jouer 
	boolean tour1 = false;// ici le joueur ayant l'id 0 commence

	boolean btn11=true; // ces booleans permettent de déterminer si le bouton est enclenché ou pas.
	boolean btn21=true; // true siginifie que le bouton n'est pas enclenché
	boolean btn31=true;
	boolean btn12=true;
	boolean btn22=true;
	boolean btn32=true;
	boolean btn13=true;
	boolean btn23=true;
	boolean btn33=true;

	public static void main(String[] args) {
		try {
			new Server0().exec();
		} catch (Exception e) {
			System.out.println(e.toString());
			// e.printStackTrace();
		}
	}

	public void exec() throws Exception {
		socketEcoute = new ServerSocket(PORT);
		System.out.println("[serveur multiclient multisession] démarré sur :"
				+ socketEcoute.getInetAddress().getLocalHost().getHostAddress()
				+ ":" + socketEcoute.getLocalPort());
		while (!mustStop) {
			System.out.println("en attente d'une connexion");
			Socket socketService = socketEcoute.accept(); // bloquant ici
			PrintStream output = new PrintStream(socketService.getOutputStream(), true); // ***
			output.println(idClient);//***
			idClient+=1;//***
			if (!mustStop)
				new ServiceThread(socketService).start();
		}
		stopServer();
	}

	public void stopServer() throws IOException {
		System.out.println("arrêt du serveur");
		socketEcoute.close();  //débloque socketEcoute.accept() avec une esception et force l'arrêt immédiat
	}

	class ServiceThread extends Thread {

		private Socket socketService;
		private PrintStream output;
		private BufferedReader networkIn;
		private boolean sessionEnd;

		public ServiceThread(Socket socketService) {
			super();
			this.socketService = socketService;
		}

		@Override
		public void run() {
			System.out.println("le client "
					+ socketService.getRemoteSocketAddress()
					+ " s'est connecté");
			try {
				output = new PrintStream(socketService.getOutputStream(), true);
				ArrayOutput.add(output); // On ajoute la sortie au tableau pour pouvoir par la suite communiquer avec le joueur concerné
				// autoflush
				networkIn = new BufferedReader(new InputStreamReader(
						socketService.getInputStream()));
				traiteClient();
				System.out.println("arrêt du service pour le client "
						+ socketService.getRemoteSocketAddress());
				networkIn.close();
				output.close();
				socketService.close();
				if (mustStop)
					stopServer();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		private void traiteClient() throws IOException {
			while (!mustStop && !sessionEnd) {
				String requeteclient = networkIn.readLine();
				if (requeteclient == null) {
					sessionEnd = true;
					System.out
					.println("le client "+socketService.getRemoteSocketAddress()+" s'est déconnecté, fin de la session");

				}
				System.out.println("le client "+socketService.getRemoteSocketAddress()+" demande: " + requeteclient);

				if (requeteclient.equals("stop-server")) {//requeteclient.toLowerCase().contains("stop-server1")
					// requête attendue: stop-server
					output.println("stopping server");
					mustStop = true;
					sessionEnd = true;
					break;}
				
				String[] req = requeteclient.split(" ");
				if (req[0].toLowerCase().equals("joueur")) {					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println(requeteclient);}
				}
				

				if (requeteclient.equals("B110") && tour0 && btn11) { //si le client clique sur le bouton en position(1,1) et que c'est son tour  
																	  //(i.e le client numéro 0) et que le bouton(1,1) n'est pas déjà enclenché (i.e. btn est true)
																	  // alors on execute les action suivantes.
					for(int i = 0; i < ArrayOutput.size(); i++){  //on envoie aux clients le string "B110"
						ArrayOutput.get(i).println("B110");}
					Arraybtn.add("B110"); // on ajoute le bouton B110 à Arraybtn (ce dernier contient les String des boutons enclenchés.) 
					btn11=false; // le bouton (1,1) étant enclenché btn est false
					tour0=!tour0;// on inverse les tours
					tour1=!tour1;
					
					//si une combinaison gagnante est détectée tout les boutons sont désenclenchés et on affiche le joueur gagnant.
					if (VerificationJoueur0(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("0gagne");ArrayOutput.get(1).println("0gagne");}
				}

				if (requeteclient.equals("B111")&& tour1 && btn11) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B111");}
					Arraybtn.add("B111");
					btn11=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur1(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("1gagne");ArrayOutput.get(1).println("1gagne");}
				} 

				if (requeteclient.equals("B210")&& tour0 && btn21) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B210");}
					Arraybtn.add("B210");
					btn21=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur0(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("0gagne");ArrayOutput.get(1).println("0gagne");}
				} 

				if (requeteclient.equals("B211")&& tour1 && btn21) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B211");}
					Arraybtn.add("B211");
					btn21=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur1(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("1gagne");ArrayOutput.get(1).println("1gagne");}
				} 

				if (requeteclient.equals("B310")&& tour0 && btn31) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B310");}
					Arraybtn.add("B310");
					btn31=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur0(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("0gagne");ArrayOutput.get(1).println("0gagne");}

				} 

				if (requeteclient.equals("B311")&& tour1 && btn31) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B311");}
					Arraybtn.add("B311");
					btn31=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur1(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("1gagne");ArrayOutput.get(1).println("1gagne");}
				} 

				if (requeteclient.equals("B120")&& tour0 && btn12) {
				
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B120");}
					Arraybtn.add("B120");
					btn12=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur0(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("0gagne");ArrayOutput.get(1).println("0gagne");}
				} 

				if (requeteclient.equals("B121")&& tour1 && btn12) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B121");}
					Arraybtn.add("B121");
					btn12=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur1(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("1gagne");ArrayOutput.get(1).println("1gagne");}
				} 

				if (requeteclient.equals("B220")&& tour0 && btn22) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B220");}
					Arraybtn.add("B220");
					btn22=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur0(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("0gagne");ArrayOutput.get(1).println("0gagne");}
				} 

				if (requeteclient.equals("B221")&& tour1 && btn22) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B221");}
					Arraybtn.add("B221");
					btn22=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur1(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("1gagne");ArrayOutput.get(1).println("1gagne");}
				} 

				if (requeteclient.equals("B320")&& tour0 && btn32) {
				
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B320");}
					Arraybtn.add("B320");
					btn32=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur0(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("0gagne");ArrayOutput.get(1).println("0gagne");}
				} 

				if (requeteclient.equals("B321")&& tour1 && btn32) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B321");}
					Arraybtn.add("B321");
					btn32=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur1(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("1gagne");ArrayOutput.get(1).println("1gagne");}
				} 

				if (requeteclient.equals("B130")&& tour0 && btn13) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B130");}
					Arraybtn.add("B130");
					btn13=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur0(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("0gagne");ArrayOutput.get(1).println("0gagne");}
				} 

				if (requeteclient.equals("B131")&& tour1 && btn13) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B131");}
					Arraybtn.add("B131");
					btn13=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur1(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("1gagne");ArrayOutput.get(1).println("1gagne");};
				} 

				if (requeteclient.equals("B230")&& tour0 && btn23) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B230");}
					Arraybtn.add("B230");
					btn23=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur0(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("0gagne");ArrayOutput.get(1).println("0gagne");}
				} 

				if (requeteclient.equals("B231")&& tour1 && btn23) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B231");}
					Arraybtn.add("B231");
					btn23=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur1(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("1gagne");ArrayOutput.get(1).println("1gagne");}
				} 

				if (requeteclient.equals("B330")&& tour0 && btn33){
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B330");}
					Arraybtn.add("B330");
					btn33=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur0(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("0gagne");ArrayOutput.get(1).println("0gagne");}
				}

				if (requeteclient.equals("B331")&& tour1 && btn33) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("B331");}
					Arraybtn.add("B331");
					btn33=false;
					tour0=!tour0;
					tour1=!tour1;
					if (VerificationJoueur1(Arraybtn)){setbtn(false);ArrayOutput.get(0).println("1gagne");ArrayOutput.get(1).println("1gagne");}
				}

				if (requeteclient.equals("clear")) {
					
					for(int i = 0; i < ArrayOutput.size(); i++){
						ArrayOutput.get(i).println("clear");}
					setbtn(true);
					Arraybtn.clear();
					
				}
			} 
				
	}
		public boolean VerificationJoueur1(ArrayList<String> Arraybtn){ // vérifie que le joueur 1 n'a pas joué de combinaison gagnante

			return (   (Arraybtn.contains("B111")
					&& Arraybtn.contains("B211")
					&& Arraybtn.contains("B311"))
					||
					(Arraybtn.contains("B121")
					&& Arraybtn.contains("B221")
					&& Arraybtn.contains("B321"))
					||
					(Arraybtn.contains("B131")
					&& Arraybtn.contains("B231")
					&& Arraybtn.contains("B331"))
					||
					(Arraybtn.contains("B111")
					&& Arraybtn.contains("B121")
					&& Arraybtn.contains("B131"))
					||
					(Arraybtn.contains("B211")
					&& Arraybtn.contains("B221")
					&& Arraybtn.contains("B231"))
					||
					(Arraybtn.contains("B311")
					&& Arraybtn.contains("B321")
					&& Arraybtn.contains("B331"))
					||
					(Arraybtn.contains("B111")
					&& Arraybtn.contains("B221")
					&& Arraybtn.contains("B331"))
					||
					(Arraybtn.contains("B131")
					&& Arraybtn.contains("B221")
					&& Arraybtn.contains("B311"))
					);

		}

	public boolean VerificationJoueur0(ArrayList<String> Arraybtn){ // vérifie que le joueur 0 n'a pas joué de combinaison gagnante

		return (   (Arraybtn.contains("B110")
				&& Arraybtn.contains("B210")
				&& Arraybtn.contains("B310"))
				||
				(Arraybtn.contains("B120")
				&& Arraybtn.contains("B220")
				&& Arraybtn.contains("B320"))
				||
				(Arraybtn.contains("B130")
				&& Arraybtn.contains("B230")
				&& Arraybtn.contains("B330"))
				||
				(Arraybtn.contains("B110")
				&& Arraybtn.contains("B120")
				&& Arraybtn.contains("B130"))
				||
				(Arraybtn.contains("B210")
				&& Arraybtn.contains("B220")
				&& Arraybtn.contains("B230"))
				||
				(Arraybtn.contains("B310")
				&& Arraybtn.contains("B320")
				&& Arraybtn.contains("B330"))
				||
				(Arraybtn.contains("B110")
				&& Arraybtn.contains("B220")
				&& Arraybtn.contains("B330"))
				||
				(Arraybtn.contains("B130")
				&& Arraybtn.contains("B220")
				&& Arraybtn.contains("B310"))
				);

	}

	public void setbtn (boolean b)
	{
		btn11 = b;
		btn21 = b;
		btn31 = b;
		btn12 = b;
		btn22 = b;
		btn32 = b;
		btn13 = b;
		btn23 = b;
		btn33 = b;
	}

	}
				
}
