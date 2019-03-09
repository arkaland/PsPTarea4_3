package t4pspservidor;

import java.io.*;
import java.net.*;
import java.util.Random;

public class T4PsPServidor {

    public static void main(String args[]) {

        // Sin argumentos 
        if (args.length != 0) {
            System.err.println("Uso: Recepción por udp");

        } else {
            try { 

                // Crea socket LIST
                DatagramSocket[] sSocket = new DatagramSocket[10];
                for (int i = 1; i < 10; i++) {
                    
                    sSocket[i] = new DatagramSocket(i+2000);

                }
                 
                
                // Crea el espacio para los mensajes 
                byte[] numero = new byte[100];
                DatagramPacket mensaje = new DatagramPacket(numero, numero.length);
                System.out.println("Esperando numero para calcular..\n");
                System.out.println("Puertos abiertos 2000 - 2009..\n");
                System.out.println("Lanzar cliente usando parametros maquina puerto NUMERO\n");
                
                //GENERAMOS NUMERO DE 0 A 100
                Random rand = new Random(); 
                int numerito;
                numerito = rand.nextInt(101); 

                while (true) {
                   // Recibe y muestra el mensaje 

                   for (int i=1;i<10;i++) {
                   sSocket[i].receive(mensaje);
                   
                    String datos = new String(mensaje.getData(), 0, mensaje.getLength());
                    System.out.println("\tNumero recibido: " + datos);
                   
                    //LOGICA PARA DEVOLVER MENSAJE
                    int numeroservidor = Integer.valueOf(datos);
     
                    if (numeroservidor < numerito) 
                        System.out.println("El número indicado es MENOR que el número del servidor");
                    if (numeroservidor > numerito) 
                        System.out.println("El número indicado es MAYOR que el número del servidor");
                    if (numeroservidor == numerito)
                        System.out.println("HAS ACERTADO EL NUMERO!");    
                   }
                   
                
        }
            } catch (SocketException e) {
                System.err.println("Socket: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("E/S: " + e.getMessage());
            }
        }

    }

}
