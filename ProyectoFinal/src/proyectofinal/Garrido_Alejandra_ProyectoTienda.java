/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class Garrido_Alejandra_ProyectoTienda {
    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);
        
        double caja = 0.0;
        boolean cajaAbierta = false;
        boolean salir = false;
        
        // Contadores y acumuladores
        int ventasRealizadas = 0;
        int comprasRealizadas = 0;
        double totalVentas = 0;
        double totalCompras = 0;
        double mayorGanancia = 0;
        double mayorGasto = 0;
        int ventasAzucar = 0;
        int ventasAvena = 0;
        int ventasTrigo = 0;
        int ventasMaiz = 0;

        while (!salir) {
            System.out.println("====== MENÚ PRINCIPAL ======");
            System.out.println("1. Abrir Caja");
            System.out.println("2. Ventas");
            System.out.println("3. Compras");
            System.out.println("4. Reportes");
            System.out.println("5. Cierre de Caja");
            System.out.println("6. Salir del Sistema");
            System.out.print("Seleccione una opción: ");
            int opcion = lea.nextInt();

            if (opcion == 1) {
                if (!cajaAbierta) {
                    cajaAbierta = true;
                    System.out.print("Ingrese cantidad para abrir la caja: Lps. ");
                    double efectivo = lea.nextDouble();
                    caja += efectivo;
                } else {
                    System.out.print("Ingrese efectivo adicional a caja: Lps. ");
                    double efectivoExtra = lea.nextDouble();
                    caja += efectivoExtra;
                }
                System.out.println("Caja abierta con: Lps. " + caja);
            }

            else if (opcion == 2) {
                if (!cajaAbierta) {
                    System.out.println("Debe abrir la caja primero.");
                    continue;
                }
            double subtotal = 0;
                double totalFactura = 0;
                double descuento = 0;
                double impuesto = 0;
                String factura = "";

                System.out.print("Ingrese tipo de cliente (A, B, C): ");
                char tipoCliente = lea.next().toUpperCase().charAt(0);
                String continuar = "SI";

                while (continuar.equals("SI")) {
                    System.out.print("Ingrese código del producto (1-4): ");
                    int codigo = lea.nextInt();

                    String nombre = "";
                    double precioVenta = 0;
                    boolean puedeComprar = false;
                if (codigo == 1) {
                        nombre = "Azúcar";
                        precioVenta = 30;
                        if (tipoCliente == 'A' || tipoCliente == 'B') puedeComprar = true;
                    } else if (codigo == 2) {
                        nombre = "Avena";
                        precioVenta = (tipoCliente == 'C') ? 22 : 25;
                        if (tipoCliente == 'A' || tipoCliente == 'B' || tipoCliente == 'C') puedeComprar = true;
                    } else if (codigo == 3) {
                        nombre = "Trigo";
                        precioVenta = 32;
                        if (tipoCliente == 'A' || tipoCliente == 'B') puedeComprar = true;
                    } else if (codigo == 4) {
                        nombre = "Maíz";
                        precioVenta = 20;
                        if (tipoCliente == 'A' || tipoCliente == 'C') puedeComprar = true;
                    }

                    if (!puedeComprar) {
                        System.out.println("No puede comprar dicho producto.");
                    } else {
                        System.out.print("Ingrese cantidad en kg: ");
                        double kg = lea.nextDouble();
                        double totalProducto = kg * precioVenta;
                        subtotal += totalProducto;

            factura += String.format("%.2f kg de %s a Lps. %.2f = Lps. %.2f\n", kg, nombre, precioVenta, totalProducto);

                        if (codigo == 1) ventasAzucar++;
                        if (codigo == 2) ventasAvena++;
                        if (codigo == 3) ventasTrigo++;
                        if (codigo == 4) ventasMaiz++;
                    }

                    System.out.print("¿Desea comprar otro producto? (SI/NO): ");
                    continuar = lea.next().toUpperCase();
                }

                // Descuento
                if (subtotal >= 5000) {
                    descuento = subtotal * 0.10;
                } else if (subtotal >= 1000) {
                    descuento = subtotal * 0.05;
                }

                // Impuesto y total final
                impuesto = subtotal * 0.07;
                totalFactura = subtotal - descuento + impuesto;
                caja += totalFactura;
                totalVentas += totalFactura;
                ventasRealizadas++;

                if ((subtotal - descuento) > mayorGanancia) {
                    mayorGanancia = subtotal - descuento;
                }
                System.out.println("\n***** FACTURA *****");
                System.out.print(factura);
                System.out.println("Subtotal: Lps. " + subtotal);
                System.out.println("Descuento: Lps. " + descuento);
                System.out.println("ISV (7%): Lps. " + impuesto);
                System.out.println("Total a Pagar: Lps. " + totalFactura);
            }

            else if (opcion == 3) {
                if (!cajaAbierta) {
                    System.out.println("Debe abrir la caja primero.");
                    continue;
                }

                System.out.print("Ingrese tipo de proveedor (A, B, C): ");
                char tipoProv = lea.next().toUpperCase().charAt(0);

                System.out.print("Ingrese código del producto (1-4): ");
                int codigo = lea.nextInt();

                String nombre = "";
                double precioCompra = 0;
                boolean puedeComprar = false;
            if (codigo == 1 && (tipoProv == 'A')) {
                    nombre = "Azúcar";
                    precioCompra = 25;
                    puedeComprar = true;
                } else if (codigo == 2 && (tipoProv == 'B' || tipoProv == 'C')) {
                    nombre = "Avena";
                    precioCompra = 20;
                    puedeComprar = true;
                } else if (codigo == 3 && (tipoProv == 'B')) {
                    nombre = "Trigo";
                    precioCompra = 30;
                    puedeComprar = true;
                } else if (codigo == 4 && (tipoProv == 'A')) {
                    nombre = "Maíz";
                    precioCompra = 18;
                    puedeComprar = true;
                }

            if (!puedeComprar) {
                    System.out.println("Proveedor no vende dicho producto.");
                } else {
                    System.out.print("Ingrese cantidad a comprar (kg): ");
                    double kg = lea.nextDouble();
                    double totalCompra = kg * precioCompra;

                    if (caja >= totalCompra) {
                        caja -= totalCompra;
                        totalCompras += totalCompra;
                        comprasRealizadas++;
                        if (totalCompra > mayorGasto) {
                            mayorGasto = totalCompra;
                        }
                        System.out.println("Compra realizada de " + nombre + " por Lps. " + totalCompra);
                    } else {
                        System.out.println("No se puede pagar la compra.");
                    }
                }
            }
            else if (opcion == 4) {
                System.out.println("====== REPORTES ======");
                System.out.println("Dinero actual en caja: Lps. " + caja);
                System.out.println("Ventas realizadas: " + ventasRealizadas);
                System.out.println("Compras realizadas: " + comprasRealizadas);
                System.out.println("Total ventas: Lps. " + totalVentas);
                System.out.println("Total compras: Lps. " + totalCompras);
                System.out.println("Margen de ganancia: Lps. " + (totalVentas - totalCompras));

                if (comprasRealizadas > 0) {
                    System.out.println("Valor medio de compra: Lps. " + (totalCompras / comprasRealizadas));
                }
                if (ventasRealizadas > 0) {
                    System.out.println("Valor medio de venta: Lps. " + (totalVentas / ventasRealizadas));
                }

                System.out.println("Mayor ganancia en una venta: Lps. " + mayorGanancia);
                System.out.println("Mayor gasto en una compra: Lps. " + mayorGasto);

                // Producto estrella
                String estrella = "Azúcar";
                int maxVentas = ventasAzucar;
                if (ventasAvena > maxVentas) {
                    maxVentas = ventasAvena;
                    estrella = "Avena";
                }
                if (ventasTrigo > maxVentas) {
                    maxVentas = ventasTrigo;
                    estrella = "Trigo";
                }
                if (ventasMaiz > maxVentas) {
                    maxVentas = ventasMaiz;
                    estrella = "Maíz";
                }

                System.out.println("Producto estrella: " + estrella);
            }

            else if (opcion == 5) {
                System.out.println("Total en caja: Lps. " + caja);
                System.out.print("¿Cuánto desea depositar en el banco? (máximo 60%): ");
                double deposito = lea.nextDouble();
                if (deposito <= caja * 0.60) {
                    caja -= deposito;
                    System.out.println("Se depositaron Lps. " + deposito + ". Caja queda con Lps. " + caja);
                    // Reiniciar contadores
                    ventasRealizadas = 0;
                    comprasRealizadas = 0;
                    totalVentas = 0;
                    totalCompras = 0;
                    mayorGanancia = 0;
                    mayorGasto = 0;
                    ventasAzucar = 0;
                    ventasAvena = 0;
                    ventasTrigo = 0;
                    ventasMaiz = 0;
                    cajaAbierta = false;
                } else {
                    System.out.println("No puede depositar más del 60%.");
                }
            }
            else if (opcion == 6) {
                salir = true;
                System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
            }

            else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }

            System.out.println();
        }

        lea.close();
    }
}
