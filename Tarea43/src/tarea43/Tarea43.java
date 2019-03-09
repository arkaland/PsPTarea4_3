/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea43;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.*;

public class Tarea43 {

    /**
     * DETECCION OS La variable SystemFlag se pone a 1 si es windows, 2 si es
     * unix/linux, 0 en cualquier otro caso.
     */
    public static void sistema() {

        int SystemFlag;

        String sistema = System.getProperty("os.name");
        if (sistema.contains("indow")) {
            System.out.println("Detectado un sistema operativo windows el cual está soportado por este servidor de ficheros\n");
            SystemFlag = 1;
        } else {
            if (sistema.contains("nix") | (sistema.contains("nux"))) {
                System.out.print("Se detecta SO unix/linux. Sistema operativo " + sistema + "solo soportado para la opcion 2");
                SystemFlag = 2;
            } else {
                System.out.print("No se reconoce este SO. Sistema operativo " + sistema + " no está soportado");
                SystemFlag = 0;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Indica tu usuario:");
        Scanner usuario = new Scanner(System.in);
        String usu = usuario.nextLine();

        System.out.println("Indica tu password:");
        Scanner contrasena = new Scanner(System.in);
        String pass = contrasena.nextLine();

        if (!("secreta".equals(pass)) && !("javier".equals(usu))) {
            System.out.println("CREDENCIALES INCORRECTAS, SALIENDO\n");

        } else {
            System.out.println("USUARIO Y CONTRASEÑA VALIDOS\n");
            // MAIN LOOP, MENU PRINCIPAL
            do {

                //CREDENCIALES
                System.out.println("\n*-------------------------------------------------------------------------*");
                System.out.println("|                     TAREA 4_3                                           |");
                System.out.println("*-------------------------------------------------------------------------*");
                System.out.println("|        1- Ver archivos del directorio ACTUAL                            |");
                System.out.println("|        2- Ver archivos de otra ruta                                     |");
                System.out.println("|        3- Ver contenido de un archivo (Dir actual)                      |");                
                System.out.println("|        4- Salir                                                         |");
                System.out.println("*-------------------------------------------------------------------------*");
                System.out.print("Escoge una opción: ");

                try {

                    Scanner pideopt = new Scanner(System.in);
                    int opcion = pideopt.nextInt();

                    if (opcion < 1 || opcion > 3) {
                        System.out.print("La opción " + opcion + " NO es válida\n\n");
                    }

                    // --------
                    // OPCION 1
                    // --------
                    if (opcion == 1) {
                        sistema();

                        try {

                            //SALIDA POR CMD           
                            //System.out.println(Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"dir\"")); 
                            System.out.println("Listando contenido de directorio actual de trabajo !");
                            String dirName = System.getProperty("user.dir");

                            Files.list(new File(dirName).toPath())
                                    .limit(10)
                                    .forEach(path -> {
                                        System.out.println(path);
                                    });
                        } catch (IOException e) {
                            System.err.println("Algo ha ido mal...");
                        }

                    } // END OPT 1

                    // --------
                    // OPCION 2
                    // --------
                    if (opcion == 2) {
                        sistema();

                        System.out.println("Dame una ruta para listar datos (Puede ser windows o linux)");
                        Scanner pideruta = new Scanner(System.in);
                        String ruta = pideruta.nextLine();

                        // Correccion de ruta para windows 
                        String sistema = System.getProperty("os.name");
                        if (sistema.contains("indow")) {
                            ruta = ruta.replace("\\", "\\\\");
                            ruta = ruta.replace("//", "\\\\");
                        }

                        // Correccion de ruta para linux (Untested)
                        if (sistema.contains("x")) {
                            ruta = ruta.replace("\\","//");
                        }

                        String dirName = ruta;
                        try {
                            Files.list(new File(dirName).toPath())
                                    .limit(10)
                                    .forEach(path -> {
                                        System.out.println(path);
                                    });
                        } catch (IOException e) {
                            System.err.println("Algo ha ido mal...");
                        }
                        
                } // END OPT 2
                    
                    // --------
                    // OPCION 3
                    // --------
                    
                    
                    if (opcion == 3) {
                        System.out.println("Dame el archivo que quieres revisar...\n puedes dar ruta completa y archivo o solo un archivo del directorio actual\nEJEMPLO: C:\\hola.txt");
                        System.out.println("NOTA : El directorio actual es = " + System.getProperty("user.dir"));
                        
                        Scanner pidefile = new Scanner(System.in);
                        String archivo = pidefile.nextLine();
                        
                        BufferedReader br = new BufferedReader(new FileReader(archivo));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }

                    } // END OPT 4
                    
                    // --------
                    // OPCION 4
                    // --------
                    if (opcion == 3) {
                        System.out.println("Saliendo del programa...");
                        System.exit(0);

                    } // END OPT 4

                    // ----------------------
                    // GESTION DE EXCEPCIONES
                    // ----------------------
                } catch (InputMismatchException e) {
                    System.err.println("Error en el formato de los campos");
                    Logger.getLogger(Tarea43.class.getName()).log(Level.SEVERE, null, e);
                 } catch (FileNotFoundException e) {
                    System.err.println("No se localiza el archivo");
                    Logger.getLogger(Tarea43.class.getName()).log(Level.SEVERE, null, e); 
                    
                } catch (Exception e) {
                    System.err.println("Excepcion genérica");
                    Logger.getLogger(Tarea43.class.getName()).log(Level.SEVERE, null, e);
                }
            } while (true);
        }
    }

}
