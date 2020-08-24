// ChatClient2.java

import java.net.*;
import java.io.*;

class ChatClient2
{
	public static void main(String s[])
	{
		try
		{
		System.out.println("client started");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
		System.out.println("enter the user name");
		String name=br.readLine();

		Socket sk=new Socket("192.168.173.1",1500);
	System.out.println("connection established");
		
		ReaderThread rt=new ReaderThread(sk);
		rt.start();
	
		DataOutputStream dout=new DataOutputStream(sk.getOutputStream());
		dout.writeUTF("login#"+name);						dout.flush();
		
		System.out.println("START TO ENTER THE MESSAGES, ENTER 'STOP' TO TERMINATE");
			while(true)
			{
			
			String str = br.readLine();	
			if(str.equalsIgnoreCase("stop"))
			{
			dout.writeUTF("logout#"+name);						dout.flush();
				break;	
			}
			else
			{
		dout.writeUTF("message#"+name+": "+str);
				dout.flush();
			}				
			}// while close			
		}// try close
		catch(Exception e)
		{	e.printStackTrace();
		}
	}
}
class ReaderThread extends Thread
{
	Socket sk;
	ReaderThread(Socket sk)
	{
		this.sk=sk;
		this.setDaemon(true);
		
	}
	public void run()
	{
		try
		{
			DataInputStream din = new DataInputStream(sk.getInputStream());
			while(true)
			{

		String  msg=din.readUTF();
		System.out.println(msg);

			}			
		}
		catch(Exception e)
		{	e.printStackTrace();
		}
	}
}



