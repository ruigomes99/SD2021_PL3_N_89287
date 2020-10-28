import java.util.*;
import java.net.*;
import java.io.*;

public class presencesClient {

	static final int DEFAULT_PORT = 2000;
	static final String DEFAULT_HOST = "127.0.0.1";

	public static void main(String[] args) throws Exception {
		String servidor = DEFAULT_HOST;
		int port = DEFAULT_PORT;

		if (args.length != 1) {
			System.out.println("Erro: use java presencesClient <ip>");
			System.exit(-1);
		}
		// Create a representation of the IP address of the Server: API
		// java.net.InetAddress
		//
		InetAddress serverAddress = InetAddress.getByName(servidor);
		//
		Socket ligacao = null;
		// Create a client sockets (also called just "sockets"). A socket is an endpoint
		// for communication between two machines: API java.net.Socket
		//
		ligacao = new Socket(serverAddress, port);
		//
		try {
			// Create a java.io.BufferedReader for the Socket; Use
			// java.io.Socket.getInputStream() to obtain the Socket input stream
			//
			BufferedReader in = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));
			//
			// Create a java.io. PrintWriter for the Socket; Use
			// java.io.Socket.etOutputStream() to obtain the Socket output stream
			//
			PrintWriter out = new PrintWriter(ligacao.getOutputStream(), true);
			//
			String request = "get" + " " + args[0];
			// write the request into the Socket
			//
			out.println(request);
			out.flush();
			//
			String msg;
			// Read the server response - read the data until null
			while ((msg = in.readLine()) != null){
				System.out.println(msg);
			};
			// Close teh Socket
			ligacao.close();
			//
			System.out.println("Terminou a ligacao!");
		} catch (IOException e) {
			System.out.println("Erro ao comunicar com o servidor: " + e);
			System.exit(1);
		}
	}
}

