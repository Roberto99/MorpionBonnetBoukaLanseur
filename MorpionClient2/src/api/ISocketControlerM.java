package api;

import java.io.IOException;

public interface ISocketControlerM {

	boolean connect(String host, int port) throws IOException;

	void disconnect() throws IOException;

	void requeteId(String string);
	
	void requete(String string);

	int getIdClient();

	void Clear();
}
