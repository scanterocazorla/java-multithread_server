import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultiThreadServer {

    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(7777);
            System.out.println("Servidor escuchando en el puerto 7777");
            while(true){
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress());
                Hilo h = new Hilo(client);
                h.start();
            }
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }
}

class Hilo extends Thread {
    private Socket client;

    public Hilo(Socket socket){
        this.client = socket;
    }

    public void run(){
        try{
            Scanner input = new Scanner(client.getInputStream());
            PrintWriter output = new PrintWriter(client.getOutputStream());
            String message;
            while(input.hasNextLine()){
                message = input.nextLine();
                System.out.println(client.getInetAddress() + ": " + message);
                output.println(message);
                output.flush(); //forzar envío
                if (message.equalsIgnoreCase("FIN")) {
                    System.out.println("Cliente desconectado");
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println("Error");
        }
        finally{
            try{
                client.close();
            }
            catch(Exception e){
                System.out.println("Error");

            }
        }
    }
}

