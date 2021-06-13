/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ClienteHilo extends Thread{
    private DataInputStream in;
    private DataOutputStream out;

    public ClienteHilo(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }
    @Override
    public void run(){
        Scanner scn = new Scanner(System.in);  
        
        String mensaje;
        int opcion = 0;
        boolean salir = false;
        
        while(!salir){
            try {
                System.out.println("1. Almacenar numero en el archivo");
                System.out.println("2. Numeros almacenados hasta el momento");
                System.out.println("3. Lista de numeros almacenados");
                System.out.println("4. El numero de numeros almacenados por el cliente");
                System.out.println("5. Archivo con numeros del cliente");
                System.out.println("6. Salir");
                opcion = scn.nextInt();
                out.writeInt(opcion);
                switch(opcion){
                    case 1:
                        int numeroaleatorio = generarNumeroAleatorio(1,100);
                        System.out.println("Numero generado: "+numeroaleatorio);
                        out.writeInt(numeroaleatorio);
                        mensaje = in.readUTF();
                        System.out.println(mensaje);
                        break;
                    case 2:
                        int numLineas = in.readInt();
                        System.out.println("Hay "+numLineas+"numeros");
                        break;
                    case 3:
                        int limite = in.readInt();
                        for(int i=0; i<limite; i++){
                            System.out.println(in.readInt());
                        }
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:
                        mensaje = in.readUTF();
                        System.out.println(mensaje);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        
    }
    
    public int generarNumeroAleatorio(int minimo, int maximo){
            
        int num=(int)Math.floor(Math.random()*(maximo-minimo+1)+(minimo));
        return num;
        }
}
