package com.company;

// Java implementation for a client
// Save file as Client.java

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client class
public class Client
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            System.out.println("Calculadora \n" + "Digite sair para sair do código");

            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");
            InetAddress ip2 = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);

            // establish the connection with server port 5066
            Socket s2 = new Socket(ip2, 5066);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            DataInputStream dis2 = new DataInputStream(s2.getInputStream());
            DataOutputStream dos2 = new DataOutputStream(s2.getOutputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true)
            {
                String tosend = scn.nextLine();
                String received = null;

                if(tosend.equals("sair"))
                {
                    System.out.println("Closing this connections : " + s + s2);
                    s.close();
                    s2.close();
                    System.out.println("Connection closed");
                    break;
                }
                var aux = tosend.split(" ");

                if(aux[1].trim().equals("^") || aux[1].trim().equals("V") || aux[1].trim().equals("%")){
                    dos2.writeUTF(tosend);
                    received = dis2.readUTF();
                }else if(aux[1].trim().equals("+") || aux[1].trim().equals("-") || aux[1].trim().equals("*") || aux[1].trim().equals("/")){
                    dos.writeUTF(tosend);
                    received = dis.readUTF();
                }

                System.out.println(received);
            }

            // closing resources
            scn.close();
            dis.close();
            dos.close();
            dis2.close();
            dos2.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}