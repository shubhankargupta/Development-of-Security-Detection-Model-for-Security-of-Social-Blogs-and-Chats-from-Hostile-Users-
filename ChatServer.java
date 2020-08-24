import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;

public class ChatServer 
{

static Socket sk1,sk2;

public static void main(String[] args)
{

try{



ServerSocket ss=new ServerSocket(3000);

System.out.println("Server Started");

System.out.println("Waiting for the first client...");
sk1=ss.accept();

System.out.println("First Client Connected..");

System.out.println("Waiting for the second client...");
sk2=ss.accept();

System.out.println("Second Client Connected..");

new ClientThread(sk1);
new ClientThread(sk2);

}

catch(Exception e)
{
e.printStackTrace();
}

}

}


class ClientThread extends Thread
{

static int count;
Socket current,other;

public ClientThread(Socket sk)
{
current=sk;

if(count==0)
{
other=ChatServer.sk2;
count++;
}

else{
other=ChatServer.sk1;
}

start();

}

public void run()
{

try{

ArrayList a=new ArrayList();

a.add("FUCK");
a.add("F***");
a.add("SHIT");
a.add("S***");
a.add("SUCK");
a.add("S***");


DataInputStream din=new DataInputStream(current.getInputStream());
DataOutputStream dout=new DataOutputStream(other.getOutputStream());

while(true)
{

String msg=din.readUTF();

msg=msg.toUpperCase();

if(a.contains(msg))
{
System.out.println("Sorry...can't send the message");
}

else{
dout.writeUTF(msg);

dout.flush();
}

}

}

catch(Exception e)
{

e.printStackTrace();
}

}



}



