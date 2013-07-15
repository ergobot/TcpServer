import java.io.*;
import java.net.*;

/*
* Simpson College
* CMSC340_DM     
* Homework #8 - Part 1 (The server)
* Sean O'Bryan
* 
* Notes: It's an automated question and answer session between the
* 		 client and server.  Possible improvements - Place all logic
* 		 for server in a separate class. Improve the question/answer
* 		 session of the loop so it's not so rigid and procedural-like.
* 		 Look at using threads in the driver to handle clients.
*/



public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			// Create a ServerSocket on port 8189
			ServerSocket serverSocket = new ServerSocket(8189);
			// Wait for the client to accept at port 8189
			Socket clientSocket = serverSocket.accept();

			// BufferedReader fromClient gets text 
			// coming into the server, from the client 
			BufferedReader fromClient = new BufferedReader(
	                	new InputStreamReader(clientSocket.getInputStream()));
	        
			// PrintWriter toClient sends text to the client
			PrintWriter toClient = 
				new PrintWriter(clientSocket.getOutputStream(), true);
	        
			// Give a start message
	        toClient.println("Adding two numbers demo");
	        	
	        // variables
	        boolean continueFlag = true;
	        double firstNumber = 0;
	        double secondNumber = 0;
	        String errorMessage = "";
	        String quitAnswer = "";
	        	
	        // Looping until client input tells me to exit
			while (continueFlag) 
			{
				//reset values
				firstNumber = 0;
				secondNumber = 0;
				errorMessage = "";
				
				
				// Message to start entry
				toClient.println("Please enter the first number");
				// Deal with first number
				try{
					// get the first number
					firstNumber  = Double.parseDouble(fromClient.readLine());
					
					// no troubles parsing, ask for the second number
					toClient.println("Please enter the second number");
					try
					{
						// get the second number
						secondNumber = Double.parseDouble(fromClient.readLine());
						
						// no troubles parsing, give the output
						toClient.println("The sum of the two numbers is: " + 
								 (firstNumber + secondNumber));
					}
					catch(NumberFormatException ex2)
					{		
						// The number wasn't a number
						// There was a problem, tell somebody
						errorMessage = "Invalid input: Please enter a number";
						toClient.println(errorMessage);
					}
				}
				catch(NumberFormatException ex)
				{	
					// The number wasn't a number
					// There was a problem, tell somebody
					errorMessage = "Invalid input: Please enter a number";
					toClient.println(errorMessage);
				}
				
				// Ask to exit
				toClient.println("Would you like to exit? (Yes or No)");
				
				// get the response
				quitAnswer = fromClient.readLine();
				
				// Overkill the checks for lazy
				// people who just enter 'y' or 'Y'
				if(quitAnswer.charAt(0) == 'y' ||
				   quitAnswer.charAt(0) == 'Y' ||
				   quitAnswer.compareToIgnoreCase("Yes")== 0){
					
					// User wants to exit, set the flag to not continue
					continueFlag = false;
					// Tell the client of impending exit
					toClient.println("Exiting");		
				}
				else
				{
					// Not exiting... and probably not needed.
					toClient.println("Repeating");
				}
			}
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
}