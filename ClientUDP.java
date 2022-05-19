import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientUDP {

	public static void main(String[] args) {
		int port = 2000;
		InetAddress serverAddress;
		DatagramSocket dSocket;
		DatagramPacket outPacket;
		DatagramPacket inPacket;
		
		byte[] buffer;
		String message="RICHIESTA DATA E ORA";
		String response;
				
		try {
			serverAddress = InetAddress.getLocalHost();
			
			System.out.println("Indirizzo del server trovato!");
			dSocket = new DatagramSocket();
			
			outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);
		
			dSocket.send(outPacket);
		
			buffer = new byte[256];
			inPacket = new DatagramPacket(buffer, buffer.length);
		
			dSocket.receive(inPacket);
			
			response = new String(inPacket.getData(), 0, inPacket.getLength());
			
			System.out.println("Connessione stabilita!");
			System.out.println("Data e ora del server: " + response);
			System.out.println("Connessione chiusa!");
			
			dSocket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
}