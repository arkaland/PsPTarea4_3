package t4pspcliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class T4PsPCliente extends Thread{

    public static void main(String args[]) {

        // Comprueba los argumentos
        if (args.length != 3) {
            System.err.println("Uso: java -jar T4PsPCliente MAQUINA PUERTO numero");
        } else {
            try {


                // Construye la dirección del socket del receptor 
                InetAddress maquina = InetAddress.getByName(args[0]);
                int Puerto = Integer.parseInt(args[1]);
                
                // Crea el socket 
                DatagramSocket sSocket = new DatagramSocket(Puerto);
                
                // Crea el mensaje
                byte[] cadena = args[2].getBytes();
                DatagramPacket mensaje = new DatagramPacket(cadena, args[2].length(), maquina, Puerto);
                    
                    // Envía el mensaje 
                sSocket.send(mensaje);
                
                // Cierra el socket 
                sSocket.close();
                
                
            } catch (UnknownHostException e) {
                System.err.println("Desconocido: " + e.getMessage());
            } catch (SocketException e) {
                System.err.println("Socket: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("E/S: " + e.getMessage());

            }
        }
    }
}