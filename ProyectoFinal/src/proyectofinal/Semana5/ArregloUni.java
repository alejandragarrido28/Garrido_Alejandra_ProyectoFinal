/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Semana5;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class ArregloUni {
    public static void main (String [] args){
        Scanner lea = new Scanner (System.in);
    /*
    Formato:
    Version1
    TipoDatos IdVarArray[] = new TipoDatos [tamaño]
    
    Version2
    TipoDatos IdVarArray[];
    IdVarArray = new TipoDatos [tamaño]
    
    Version 3
        TipoDatos idVarArray [] = {value1, value2,...}
    */
    
    int numeros []= new int [5];
    numeros[0] = 45;
    numeros[2] = 102;
    
        System.out.println("Resultado"+numeros[2]);
    
    double altura [], precio;
    altura = new double [5];
    
    altura [0] = 1.7;
    for (int i=0; i <altura.length; i++){
        System.out.println("Ingresa altura["+i+"]");
        altura [i]=lea.nextDouble();
    }
    for (int i=0;i<altura.length;i++){
        System.out.println("["+i+"]:"+altura[i]);
    }
    
    //V3
    String listaNombre[] = {"Said","Jiny","Moises","Maria"};
    for (int i= 0; i<listaNombre.length;i++){
        //for (int c=0; c<listaNombre[i].length();c++);{
        
    //}
        System.out.println("["+i+"]"+listaNombre[i]+"-"+listaNombre[i].length());
    }
    }
}
