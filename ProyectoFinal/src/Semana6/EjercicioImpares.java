/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana6;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author User
 */
public class EjercicioImpares {
    public static void main (String []args){
    Scanner lea = new Scanner (System.in);
    
    int dimension = -1;
    int impares[];
    int numero = 0;
    int cont =0; 
    
    while (dimension <=0){
        System.out.println("Ingrese la dimension del arreglo: ");
        dimension = lea.nextInt();
    }
    
    impares = new int[dimension];
    
    for (int i=0; i < impares.length; i++){
        if (impares[i] !=0){
            System.out.println(impares[i]);
        }
    }
    
}
}
