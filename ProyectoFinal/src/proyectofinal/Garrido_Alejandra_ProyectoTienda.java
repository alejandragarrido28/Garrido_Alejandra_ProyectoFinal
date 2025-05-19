package proyectofinal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Garrido_Alejandra_ProyectoTienda {

    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);

        double caja = 0.0;
        boolean cajaAbierta = false;
        boolean salir = false;

        int ventasRealizadas = 0, comprasRealizadas = 0;
        double totalVentas = 0, totalCompras = 0;
        double mayorGanancia = 0, mayorGasto = 0;
        int ventasAzucar = 0, ventasAvena = 0, ventasTrigo = 0, ventasMaiz = 0;

        double stockAzucar = 0, stockAvena = 0, stockTrigo = 0, stockMaiz = 0;

        while (!salir) {
            System.out.println("\n------------------------");
            System.out.println("     MENU PRINCIPAL      ");
            System.out.println("  1. Abrir Caja           ");
            System.out.println("  2. Realizar Venta       ");
            System.out.println("  3. Registrar Compra     ");
            System.out.println("  4. Ver Reportes         ");
            System.out.println("  5. Cierre de Caja       ");
            System.out.println("  6. Salir                ");
            System.out.println("\n------------------------");
            System.out.print("Seleccione una opción: ");

            int opcion = 0;
            try {
                opcion = lea.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("[Error] Entrada inválida. Intente nuevamente.");
                lea.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n[Abrir Caja]");
                    try {
                        System.out.print("Ingrese monto inicial/adicional: Lps. ");
                        double monto = lea.nextDouble();
                        if (monto < 0) {
                            System.out.println("[Error] No se permiten montos negativos.");
                            break;
                        }
                        caja += monto;
                        cajaAbierta = true;
                        System.out.println("Caja actual: Lps. " + caja);
                    } catch (InputMismatchException e) {
                        System.out.println("[Error] Monto inválido.");
                        lea.nextLine();
                    }
                    break;

                case 2:
                    if (!cajaAbierta) {
                        System.out.println("[!] La caja no está abierta.");
                        break;
                    }

                    if (stockAzucar == 0 && stockAvena == 0 && stockTrigo == 0 && stockMaiz == 0) {
                        System.out.println("[!] No hay stock disponible. Registre compras antes de vender.");
                        break;
                    }

                    System.out.println("\n[Realizar Venta]");
                    double subtotal = 0,
                     totalFactura = 0,
                     descuento = 0,
                     impuesto;
                    String factura = "";

                    System.out.print("Tipo de cliente (A/B/C): ");
                    char tipoCliente = lea.next().toUpperCase().charAt(0);
                    lea.nextLine();
                    String continuar = "SI";

                    while (continuar.equals("SI")) {
                        int codigo = 0;
                        try {
                            System.out.print("Código producto (1-4): ");
                            codigo = lea.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("[Error] Código inválido.");
                            lea.nextLine();
                            continue;
                        }

                        String nombre = "";
                        double precioVenta = 0;
                        boolean permitido = false;

                        switch (codigo) {
                            case 1:
                                nombre = "Azúcar";
                                precioVenta = 30;
                                permitido = (tipoCliente == 'A' || tipoCliente == 'B');
                                break;
                            case 2:
                                nombre = "Avena";
                                precioVenta = (tipoCliente == 'C') ? 22 : 25;
                                permitido = true;
                                break;
                            case 3:
                                nombre = "Trigo";
                                precioVenta = 32;
                                permitido = (tipoCliente == 'A' || tipoCliente == 'B');
                                break;
                            case 4:
                                nombre = "Maíz";
                                precioVenta = 20;
                                permitido = (tipoCliente == 'A' || tipoCliente == 'C');
                                break;
                            default:
                                System.out.println("Producto no válido.");
                                continue;
                        }

                        if (!permitido) {
                            System.out.println("No puede adquirir ese producto.");
                            continue;
                        }

                        try {
                            System.out.print("Cantidad en kg: ");
                            double kg = lea.nextDouble();
                            if (kg <= 0) {
                                System.out.println("[Error] Cantidad debe ser mayor que cero.");
                                continue;
                            }

                            boolean hayStock = false;
                            switch (codigo) {
                                case 1:
                                    hayStock = stockAzucar >= kg;
                                    break;
                                case 2:
                                    hayStock = stockAvena >= kg;
                                    break;
                                case 3:
                                    hayStock = stockTrigo >= kg;
                                    break;
                                case 4:
                                    hayStock = stockMaiz >= kg;
                                    break;
                            }

                            if (!hayStock) {
                                System.out.println("[Error] No hay suficiente stock para este producto.");
                                continue;
                            }

                            double total = kg * precioVenta;
                            subtotal += total;
                            factura += String.format("%.2f kg de %s a Lps. %.2f = Lps. %.2f\n", kg, nombre, precioVenta, total);

                            switch (codigo) {
                                case 1:
                                    stockAzucar -= kg;
                                    ventasAzucar++;
                                    break;
                                case 2:
                                    stockAvena -= kg;
                                    ventasAvena++;
                                    break;
                                case 3:
                                    stockTrigo -= kg;
                                    ventasTrigo++;
                                    break;
                                case 4:
                                    stockMaiz -= kg;
                                    ventasMaiz++;
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("[Error] Cantidad inválida.");
                            lea.nextLine();
                            continue;
                        }

                        System.out.print("¿Desea otro producto? (SI/NO): ");
                        continuar = lea.next().toUpperCase();
                        lea.nextLine();
                    }

                    if (subtotal == 0) {
                        System.out.println("[!] No se realizó ninguna venta.");
                        break;
                    }

                    if (subtotal >= 5000) {
                        descuento = subtotal * 0.10;
                    } else if (subtotal >= 1000) {
                        descuento = subtotal * 0.05;
                    }

                    if (tipoCliente == 'A') {
                        impuesto = subtotal * 0.15;
                    } else {
                        impuesto = subtotal * 0.07;
                    }

                    totalFactura = subtotal - descuento + impuesto;
                    caja += totalFactura;
                    totalVentas += totalFactura;
                    ventasRealizadas++;

                    if ((subtotal - descuento) > mayorGanancia) {
                        mayorGanancia = subtotal - descuento;
                    }

                    System.out.println("\n------- FACTURA -------");
                    System.out.print(factura);
                    System.out.printf("Subtotal: Lps. %.2f\n", subtotal);
                    System.out.printf("Descuento: Lps. %.2f\n", descuento);
                    System.out.printf("ISV (%d%%): Lps. %.2f\n", (tipoCliente == 'A' ? 15 : 7), impuesto);
                    System.out.printf("TOTAL: Lps. %.2f\n", totalFactura);
                    break;
                case 3:
                    if (!cajaAbierta) {
                        System.out.println("[!] La caja no está abierta.");
                        break;
                    }

                    System.out.println("\n[Registrar Compra]");
                    System.out.print("Tipo proveedor (A/B/C): ");
                    char tipoProv = lea.next().toUpperCase().charAt(0);

                    System.out.print("Código producto (1-4): ");
                    int codCompra = lea.nextInt();

                    String nombreCompra = "";
                    double precioCompra = 0;
                    boolean permitidoCompra = false;

                    switch (codCompra) {
                        case 1:
                            if (tipoProv == 'A') {
                                nombreCompra = "Azúcar";
                                precioCompra = 25;
                                permitidoCompra = true;
                            }
                            break;
                        case 2:
                            if (tipoProv == 'B' || tipoProv == 'C') {
                                nombreCompra = "Avena";
                                precioCompra = 20;
                                permitidoCompra = true;
                            }
                            break;
                        case 3:
                            if (tipoProv == 'B') {
                                nombreCompra = "Trigo";
                                precioCompra = 30;
                                permitidoCompra = true;
                            }
                            break;
                        case 4:
                            if (tipoProv == 'A') {
                                nombreCompra = "Maíz";
                                precioCompra = 18;
                                permitidoCompra = true;
                            }
                            break;
                    }

                    if (!permitidoCompra) {
                        System.out.println("Este proveedor no puede vender ese producto.");
                    } else {
                        try {
                            System.out.print("Cantidad (kg): ");
                            double kgCompra = lea.nextDouble();
                            if (kgCompra < 0) {
                                System.out.println("[Error] No se permiten cantidades negativas.");
                                break;
                            }
                            double totalCompra = kgCompra * precioCompra;

                            if (caja >= totalCompra) {
                                caja -= totalCompra;
                                totalCompras += totalCompra;
                                comprasRealizadas++;
                                if (totalCompra > mayorGasto) {
                                    mayorGasto = totalCompra;
                                }
                                System.out.println("Compra realizada: " + nombreCompra + " por Lps. " + totalCompra);

                                switch (codCompra) {
                                    case 1:
                                        stockAzucar += kgCompra;
                                        break;
                                    case 2:
                                        stockAvena += kgCompra;
                                        break;
                                    case 3:
                                        stockTrigo += kgCompra;
                                        break;
                                    case 4:
                                        stockMaiz += kgCompra;
                                        break;
                                }
                            } else {
                                System.out.println("[!] Fondos insuficientes para esta compra.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("[Error] Cantidad inválida.");
                            lea.nextLine();
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n[Reportes del Día]");
                    System.out.printf("Caja actual: Lps. %.2f\n", caja);
                    System.out.println("Ventas realizadas: " + ventasRealizadas);
                    System.out.println("Compras realizadas: " + comprasRealizadas);
                    System.out.printf("Total en ventas: Lps. %.2f\n", totalVentas);
                    System.out.printf("Total en compras: Lps. %.2f\n", totalCompras);
                    System.out.printf("Ganancia neta: Lps. %.2f\n", (totalVentas - totalCompras));

                    if (comprasRealizadas > 0) {
                        System.out.printf("Promedio compra: Lps. %.2f\n", totalCompras / comprasRealizadas);
                    }
                    if (ventasRealizadas > 0) {
                        System.out.printf("Promedio venta: Lps. %.2f\n", totalVentas / ventasRealizadas);
                    }

                    System.out.println("Mayor ganancia: Lps. " + mayorGanancia);
                    System.out.println("Mayor gasto: Lps. " + mayorGasto);

                    String estrella = "";
                    int maxV = 0;
                    if (ventasAzucar > 0 && ventasAzucar > maxV) {
                        estrella = "Azúcar";
                        maxV = ventasAzucar;
                    }
                    if (ventasAvena > 0 && ventasAvena > maxV) {
                        estrella = "Avena";
                        maxV = ventasAvena;
                    }
                    if (ventasTrigo > 0 && ventasTrigo > maxV) {
                        estrella = "Trigo";
                        maxV = ventasTrigo;
                    }
                    if (ventasMaiz > 0 && ventasMaiz > maxV) {
                        estrella = "Maíz";
                        maxV = ventasMaiz;
                    }
                    if (!estrella.isEmpty()) {
                        System.out.println("Producto estrella: " + estrella);
                    } else {
                        System.out.println("Aún no hay producto estrella.");
                    }

                    System.out.println("\nInventario actual:");
                    System.out.printf("Azúcar: %.2f kg\n", stockAzucar);
                    System.out.printf("Avena: %.2f kg\n", stockAvena);
                    System.out.printf("Trigo: %.2f kg\n", stockTrigo);
                    System.out.printf("Maíz: %.2f kg\n", stockMaiz);
                    break;

                case 5:
                    System.out.println("\n[Cierre de Caja]");
                    System.out.printf("Total en caja: Lps. %.2f\n", caja);
                    System.out.print("¿Cuánto desea depositar en el banco? (máx 60%): ");
                    double deposito = lea.nextDouble();
                    if (deposito <= caja * 0.60) {
                        caja -= deposito;
                        System.out.printf("Depósito realizado: Lps. %.2f\nCaja restante: Lps. %.2f\n", deposito, caja);
                        // Resetear
                        ventasRealizadas = 0;
                        comprasRealizadas = 0;
                        totalVentas = 0;
                        totalCompras = 0;
                        mayorGanancia = 0;
                        mayorGasto = 0;
                        ventasAzucar = ventasAvena = ventasTrigo = ventasMaiz = 0;
                        stockAzucar = stockAvena = stockTrigo = stockMaiz = 0;
                        cajaAbierta = false;
                    } else {
                        System.out.println("No puede depositar más del 60%.");
                    }
                    break;

                case 6:
                    salir = true;
                    System.out.println("Saliendo del sistema. ¡Gracias!");
                    break;

                default:
                    System.out.println("[!] Opción inválida.");
            }
        }
        lea.close();
    }
}
