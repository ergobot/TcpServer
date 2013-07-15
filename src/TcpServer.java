import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class TcpServer {
	
	int mySocket;
	ServerSocket server;
	/*
	 * Constructor
	 */
	public TcpServer()
	{
		try
		{
			mySocket = 8189;
			server = new ServerSocket(mySocket);
			//System.out.println("Socket " + mySocket + " is open.");
		}
		catch(Exception ex)
		{
			System.out.println("There was an error: " + ex.getMessage());
		}
		
	}
	
	public void connection()
	{
		try{
			
			//System.out.println("Starting the connection");
			
			System.out.println("Socket " + mySocket + " is open.");
			
		
			
			// wait for client connection
			Socket connection = server.accept();
			//System.out.println("Waiting....");
			
			
			
			try{
				
				
				
				System.out.println("The connection has been set");
				
				
				String message = "Hello from the Server";//input.readUTF();
				//char tryAgain = 0;
				PrintWriter output = new PrintWriter(connection.getOutputStream(), true);
				//output.reset();
				System.out.println("Hello from the Server");
				output.print(message);
				
				//ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
				InputStream input = connection.getInputStream();
				//Scanner input = new Scanner(connection.getInputStream());
				Console c = System.console();
				
				message = c.readLine();
				//output.flush();
				
				//int numberOne = input.nex
				System.out.println(message);
				output.print(message);
				
				/*char tryAgain = 0;
				do
				{
					System.out.println("First line of do");
					output.writeChars("This is your message");
					
					//output.writeChars(message);
					output.writeChars("Would you like to go again?");
					
					tryAgain = input.nextLine().charAt(0);
					System.out.println(tryAgain);
				}while(tryAgain != 'y' || tryAgain == 'Y');*/
				
				server.close();
				connection.close();
				input.close();
				output.close();
				
				}
			catch(IOException ex)
				{
					System.out.println("There was a problem: " + ex.toString());
					server.close();
					connection.close();
					
				}
			}
			catch(Exception ex)
			{
				System.out.println("First try had problem: " + ex.toString());
			}
			
		
	}
	
	
}