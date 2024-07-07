import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server   {
	

	static ServerSocket serversocket;
	static Socket clientsocket;
	static PrintWriter out;
	static BufferedReader in;
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			serversocket = new ServerSocket(7777);
			System.out.println("server is ready..........");
			clientsocket = serversocket.accept();
			out = new PrintWriter(clientsocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			
		
			String servermessages = "C:/Users/aitav/OneDrive/Desktop/server.txt/";
			FileOutputStream msgout = new FileOutputStream(servermessages);
			
			
			Thread sender = new Thread(new Runnable() {
				String msg;
				public void run() {
					byte[] msgbytes = null;
					while(true) {
						msg = sc.nextLine();
						msgbytes = msg.getBytes();
						try {
							msgout.write(msgbytes);
						} catch (IOException e) {
							e.printStackTrace();
						}
						out.println(msg);
						out.flush();
					}
				}
			});
			
			sender.start();
			
			String clientmessages = "C:/Users/aitav/OneDrive/Desktop/client.txt";
			FileOutputStream clientmsg = new FileOutputStream(clientmessages);
			
			Thread reciever = new Thread(new Runnable() {
				String msg;
				byte[] msgbytes = null;
				public void run() {
					try {
						msg = in.readLine();
						while(msg!=null) {
							msgbytes = msg.getBytes();
							clientmsg.write(msgbytes);
							
							System.out.println("client :"+msg);
							msg = in.readLine();
						}
						System.out.println("server out of service");
						out.close();
						clientsocket.close();
						
					}catch(Exception e) {
						System.out.println(e.toString());
					}
					
				}
			});
			reciever.start();
			
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}//catch

	}

}
