import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost", 7777);
            System.out.println("Conectado");
            PrintWriter output = new PrintWriter(s.getOutputStream());
            Scanner input = new Scanner(s.getInputStream());
            Scanner kb = new Scanner(System.in);
            String message;
            while(true){
                message = kb.nextLine();
                output.println(message);
                output.flush(); //forzar envío
                System.out.println(input.nextLine());
                if(message.equals("FIN")) {break;}
            }
            s.close();
            kb.close();
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }
}
