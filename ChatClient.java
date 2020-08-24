import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class ChatClient
{

public static void main(String[] args)
{

try{

ArrayList a=new ArrayList();

a.add("FUCK");
a.add("F***");
a.add("SHIT");
a.add("S***");
a.add("SUCK");
a.add("S***");




Socket sk=new Socket("localhost",3000);

BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

System.out.println("Enter your name");

String name=br.readLine();

System.out.println("Welcome "+name);

System.out.println("Connection request sent");

new ReaderThread(sk);

DataOutputStream dout=new DataOutputStream(sk.getOutputStream());

System.out.println("Enter \"BYE\" to exit the chat");

while(true)
{

//Enter your message
String message=br.readLine();
System.out.println(name+":"+message);

message=message.toUpperCase();

if(a.contains(message))
{
//System.out.println("Sorry!! Can't send the message");
dout.writeUTF(message);
dout.flush();

}

else if(message.equalsIgnoreCase("BYE"))
{
break;
}


else{

dout.writeUTF(message);
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



class ReaderThread extends Thread
{
Socket sk;

public ReaderThread(Socket sk)
{
this.sk=sk;
setDaemon(true);
start();
}


public void run()
{

try{
DataInputStream din=new DataInputStream(sk.getInputStream());

while(true)
{
String msg=din.readUTF();

System.out.println(msg);
}


}


catch(Exception e)
{
e.printStackTrace();
}

}

}














