import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Leitura{

    public static String entDados(String label) {

        System.out.print(label);
        String resp = "";

        InputStreamReader teclado = new InputStreamReader(System.in);
        BufferedReader buff = new BufferedReader(teclado);

        try{
                resp = buff.readLine();
        }
        catch(IOException ioe){
                System.out.println("\nERRO");
        }
        return resp;

    }
}