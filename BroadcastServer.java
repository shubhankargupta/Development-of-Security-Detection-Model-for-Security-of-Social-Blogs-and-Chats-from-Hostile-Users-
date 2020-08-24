// BroadcastServer.java

import java.util.*;
import java.net.*;
import java.io.*;

class BroadcastServer
{
	public static void main(String s[])
	{
		try
		{
		System.out.println("server started");
		ServerSocket ss=new ServerSocket(1500);
	
			ArrayList sockets=new ArrayList();
	// collection object to store the Socket objects
				
			while(true)
			{
				Socket sk=ss.accept();

		System.out.println("client connected");

				sockets.add(sk);
		// adding the socket into collection object

			new ClientThread(sk , sockets);
			// new client thread started

			}			
		}
		catch(Exception e)
		{	e.printStackTrace();
		}
	}
}
class ClientThread extends Thread
{
	ArrayList al;
	Socket sk;
	ClientThread(Socket sk , ArrayList  al)
	{
		this.sk=sk;
		this.al=al;
		start();
	}
	public void run()
	{
		try
		{
	DataInputStream din=new DataInputStream(sk.getInputStream());
			while(true)
			{
				String msg=din.readUTF();
			
			 String  str[]=msg.split("#");
			
				if(str[0].equals("login"))
				{
			msg=str[1]+" has loggedin";				System.out.println(str[1]+" connected");
				}
			else if(str[0].equals("logout"))
				{
			msg=str[1]+" has loggedout";
				}
				else
				{
				msg = str[1];	
				}

				broadcast(msg);
	
				if(str[0].equals("logout"))
				{
					al.remove(sk);
	// to remove the current socket from collection
					break;
				}
			}// while close
		}// try close
		catch(Exception e)
		{	e.printStackTrace();
		}		
	}
	void broadcast(String msg)
	{
		try
		{
			for(Object ob:al)  //for each loop
			{
			Socket sk=(Socket)ob;

			DataOutputStream dout=new DataOutputStream(sk.getOutputStream());
				dout.writeUTF(msg);
				dout.flush();
			}
		}
		catch(Exception e)
		{	e.printStackTrace();
		}
	}
}




