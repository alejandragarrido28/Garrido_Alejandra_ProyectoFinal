/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Lab1Semana4;
import  java.util.Scanner;
import  java.util.Random;
/**
 *
 * @author User
 */
public class Garrido_Alejandra_Exam1 {
    public static void main (String []args){
        Scanner lea = new Scanner(System.in).useDelimiter("\n");
        int opcion = 0;
        
        while (opcion != 5) {
            System.out.println("********* Menú Principal*********");
            System.out.println("1. Piramide");
            System.out.println("2. Clave");
            System.out.println("3. Juego de Piedra, Papel y Tijera");
            System.out.println("4. Adivinar");
            System.out.println("5. Salir");
            System.out.println("Elija una de las opciones: ");
            opcion = lea.nextInt();
            
            switch (opcion){
                //Piramide
                case 1: 
                    System.out.println("");
                    
                //Clave
                case 2:
                    System.out.println("Cifrado por inversión");
                    System.out.print("Introduzca su mensaje: ");
                    String mensaje = lea.next();
                    String mensajeCifrado = "";

                    for (int i = 0; i < mensaje.length(); i++) {
                        char caracter = mensaje.charAt(i);

                        if (caracter >= 'a' && caracter <= 'z') {
                            mensajeCifrado += (char) ('a' + ('z' - caracter));
                        } else if (caracter >= 'A' && caracter <= 'Z') {
                            mensajeCifrado += (char) ('A' + ('Z' - caracter));
                        } else {
                            mensajeCifrado += caracter;
                        }
                    }
                    System.out.println("Mensaje transformado: " + mensajeCifrado);
                    break;
                    
                //Juego Piedara, Papel o Tijera
                case 3:
                    System.out.println("Juego Piedra, Papel o Tijera");
                    String jugarmas = "si";
                    Random random = new Random ();
                    
                    while (jugarmas.equalsIgnoreCase ("si")){
                        String respuesta = "";
                        boolean entradaValida = false;
                        while (!entradaValida){
                            System.out.println("Elija Piedra, Papel o Tijera: ");
                            respuesta = lea.next().toLowerCase();
                            if (respuesta.equals("piedra")|| respuesta.equals("papel")||respuesta.equals("tijera")){
                                entradaValida = true;
                            }else{
                                System.out.println("Su opción es inválida. Intenta de nuevo. ");
                            }
                        }
                        String [] opciones = {"piedra","papel","tijera"};
                        String eleccionComputadora = opciones[random.nextInt(opciones.length)];
                        
                        System.out.println("La computadora eligió: " + eleccionComputadora);

                        if (respuesta.equals(eleccionComputadora)) {
                            System.out.println("¡Es un empate!");
                        } else if ((opciones.equals("piedra") && eleccionComputadora.equals("tijera")) ||
                                   (opciones.equals("papel") && eleccionComputadora.equals("piedra")) ||
                                   (opciones.equals("tijera") && eleccionComputadora.equals("papel"))) {
                            System.out.println("¡Ganaste esta ronda!");
                        } else {
                            System.out.println("¡La computadora ganó esta ronda!");
                        }

                        System.out.print("Desea jugar de nuevo? (Si/No): ");
                        jugarmas = lea.next();
                    }
                    System.out.println("¡Gracias por jugar!");
                    break;
                    
                    //Adivinar
                case 4: 
                    System.out.println("Adivina el número del 1 al 100");
                    Random adivinar = new Random();
                    int numAdivinar = adivinar.nextInt(100) + 1;
                    int intentos = 0;
                    int intentodelUsuario = 0;
                    boolean adivinado = false;
                    
                    while (intentos < 10 && !adivinado){
                        System.out.println("Intento #" + (intentos +1)+" Adivina el número: ");
                        intentodelUsuario = lea.nextInt();
                        intentos++;
                        
                        if (intentodelUsuario == numAdivinar){
                            System.out.println("¡Adivinaste! Lo lograste en "+intentos+" intentos");
                            adivinado= true;
                        }else if (intentodelUsuario < numAdivinar){
                            System.out.println("El número que intentas adivinar es mayor.");
                        }else {
                            System.out.println("El número que intentas adivinar es menor.");
                        }
                }
                
                if (!adivinado){
                    System.out.println("Ya no tienes más intentos, el número a adivinar era: "+ numAdivinar);                   
                }
                break;
                
                }
                
            }
        }
        
}
