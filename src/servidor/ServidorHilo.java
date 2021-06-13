/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServidorHilo extends Thread{
    
    private DataInputStream in;
    private DataOutputStream out;
    private String nombreCliente;
    
    public ServidorHilo(DataInputStream in, DataOutputStream out, String nombreCliente) {
        this.in = in;
        this.out = out;
        this.nombreCliente = nombreCliente;
    }
    @Override
    public void run(){
        
        
        
        int opcion;
        File f = new File("numeros.txt");
        
        while(true){
            try{
                opcion = in.readInt();
                switch(opcion){
                 case 1:
                     int numeroAleatorio = in.readInt();
                     escribirNumeroAleatorio(f,numeroAleatorio);
                     System.out.println("Se escribio el numero en el cliente: "+nombreCliente);
                     out.writeUTF("Numero guardado correctamente");
                     break;
                 case 2:
                     int numLineas = numerosLineasFichero(f);
                     out.writeInt(numLineas);
                     break;
                 case 3:
                     ArrayList<Integer> numeros = listaNumeros(f);
                     out.writeInt(numeros.size());
                     for (int n:numeros){
                        out.writeInt(n);
                     }
                     break;
                 case 4:
                     break;
                 case 5:
                     break;
                 case 6:
                     break;
                 default:
                     out.writeUTF("Solo numero del 1 al 6");
                     
             }
            }catch(IOException ex){
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        
    }
    
    public void escribirNumeroAleatorio(File f,int numeroAleatorio)throws IOException{
        FileWriter fw = new FileWriter(f,true);
        fw.write(nombreCliente+":"+numeroAleatorio+"\r\n");
        fw.close();
    }
    public int numerosLineasFichero(File f) throws FileNotFoundException, IOException{
        int numLineas=0;
        BufferedReader br = new BufferedReader(new FileReader(f));
        
        String linea = "";
        
        while((linea=br.readLine())!=null){
            numLineas++;
        }
        
        br.close();
        return numLineas;
    }
    public ArrayList<Integer> listaNumeros(File f)throws FileNotFoundException, IOException{
        ArrayList<Integer> numeros = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(f));
        
        String linea = "";
        
        while((linea = br.readLine()) != null){
            String[] partes = linea.split(":");
            int numero = Integer.parseInt(partes[1]);
            numeros.add(numero);
        }
        br.close();
        return numeros;
    }
    
}
