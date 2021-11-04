package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.lang.Math;

import static java.lang.Integer.parseInt;

// ClientHandler1 class
class ClientHandler2 extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    // Constructor
    public ClientHandler2(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }



    @Override
    public void run()
    {

        String received;

        float n1, n2;

        while (true)
        {
            try {
                // receive the answer from client
                received = dis.readUTF();

                if(received.equals("sair"))
                {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }
                // write on output stream based on the
                // answer from the client
                var aux = received.split(" ");
                n1 = parseInt(aux[0].trim());
                n2 = parseInt(aux[2].trim());
                System.out.println(received);
                switch (aux[1].trim()) {
                    case "^":
                        dos.writeUTF(""+ (Math.pow(n1,n2)));
                        break;
                    case "V":
                        if(n1 == 2) {
                            dos.writeUTF("" + (Math.sqrt(n2)));
                        }else{
                            dos.writeUTF("" + (Math.cbrt(n2)));
                        }
                        break;
                    case "%":
                        dos.writeUTF(""+((n1*n2)/100));
                        break;

                    default:
                        dos.writeUTF("Operação Invalida");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
