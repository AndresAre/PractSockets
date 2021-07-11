/*
 * La aplicacion servidor debe atender a multiples clientes y el cliente se conectará al servidor.
 * Por cada cliente que se conecte, el servidor creara un hilo para el mismo.
 *  https://www.youtube.com/watch?v=VuqhED5zbfg
 * 1. Almacenar un numero en un archivo, se almacenarán en un archivo de texto 'numeros.txt' donde el contenido será
 * el nombre del cliente y el número, separados por dos puntos (Ejemplo C3:2)
 * 2. Devolver cuántas números se han almacenado hasta el momento.
 * 3. Devolver la lista de números almacenados
 * 4. Devuelve el número de números almacenados por el cliente.
 * 5. Recibir un archivo, sólo, con sus numeros.
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andres Arenas
 * https://www.youtube.com/watch?v=JNeCSElXURM
 * Ejercicio:
 * La aplicacion servidor debe atender a multiples clientes y el cliente se conectara al servidor.
 * Por cada cliente que se conecte, el servidor creara un hilo para el mismo.
 * Las opciones que se mostraran al cliente son los siguientes:
 * -Almacenar unnumero en un archivo, se almacenara en un archivo de texto 'numeros.txt' donde el contenido sera el nombre del cliente y el numero, separados por dos puntos (Ejemplo: C3:4)
 * -Devolver cuantos numeros se han almacenado hasta el momento
 * -Devolver la lista de numeros almacenados
 * -Devolver el numero de numeros almacenados por el cliente.
 * -Recibir un archivo, solo, con sus numeros.
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(5000);
            Socket sc;
            
            System.out.println("Servidor iniciado");
            while(true){
                sc= server.accept();
                
                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            
                out.writeUTF("Indica tu nombre");
                String nombreCliente = in.readUTF();
            
                ServidorHilo hilo = new ServidorHilo(sc, in, out, nombreCliente);
                hilo.start();
                
                System.out.println("Creada la conexion con el cliente: "+nombreCliente);
            }
        }catch (IOException ex){
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
