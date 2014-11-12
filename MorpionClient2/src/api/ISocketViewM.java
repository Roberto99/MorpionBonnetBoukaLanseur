package api;

import javax.swing.JButton;

public interface ISocketViewM {

	boolean isDisposed();

	void setlibelle(JButton btnNewButton);
	void setlibelle1(JButton btnNewButton);

	JButton getButton11();
	JButton getButton21();
	JButton getButton31();
	JButton getButton12();
	JButton getButton22();
	JButton getButton32();
	JButton getButton13();
	JButton getButton23();
	JButton getButton33();

	void setTextVictoire(String str);

	void effaceTextVictoire();

	void setlibelle2(JButton button11);

	void sendMessage(String reponse);
}
