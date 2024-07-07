import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class client {

	public static void main(String[] args) {
		Socket clientsocket;
		BufferedReader in;
		PrintWriter out;
		Scanner sc = new Scanner(System.in);
		try {
			clientsocket = new Socket("127.0.0.1",7777);
			System.out.println("chat is ready..........");
			out = new PrintWriter(clientsocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			
			Thread sender = new Thread(new Runnable() {
				String msg;
				public void run() {
					while(true) {
						msg = sc.nextLine();
						out.println(msg);
						out.flush();
					}
				}
			});
			sender.start();
			
	Thread reciever = new Thread(new Runnable() {
				
				String msg;
				public void run() {
					try {
						msg = in.readLine();
						while(msg!=null) {
							System.out.println("server :"+msg);
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
		}
	}

}
