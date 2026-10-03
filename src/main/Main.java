package main;

import exception.ProductosNoEncontradoException;
import exception.StockInsuficienteException;
import exception.ProductosDuplicadoException;
import model.Producto;
import service.ProductoService;
import util.Validador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoService productoService = new ProductoService();
        boolean salir = false;

        System.out.println("=========================================");
        System.out.println("        GESTIÓN DE INVENTARIO            ");
        System.out.println("=========================================");

        while (!salir) {
            mostrarMenu();

            int opcion = Validador.leerEntero(scanner, "\nSeleccione una opción: ");

            switch (opcion) {
                case 1:
                    menuAgregarProducto(scanner, productoService);
                    break;
                case 2:
                    menuBuscarProducto(scanner, productoService);
                    break;
                case 3:
                    productoService.listarProductos();
                    break;
                case 4:
                    menuVenderProducto(scanner, productoService);
                    break;
                case 5:
                    salir = true;
                    System.out.println("Cerrando el sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("⚠ Opción no válida. Por favor, seleccione un número del 1 al 5.");
            }
        }
        
        scanner.close();
    }


    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Agregar nuevo producto");
        System.out.println("2. Buscar producto por ID");
        System.out.println("3. Listar todos los productos");
        System.out.println("4. Vender producto (Descontar stock)");
        System.out.println("5. Salir");
    }

    private static void menuAgregarProducto(Scanner scanner, ProductoService productoService) {
        System.out.println("\n-- AGREGAR PRODUCTO --");
        try {

            String nombre = Validador.leerTexto(scanner, "Ingrese el nombre del producto: ");
            double precio = Validador.leerDouble(scanner, "Ingrese el precio: ");
            int stock = Validador.leerEntero(scanner, "Ingrese el stock inicial: ");
            String categoria = Validador.leerTexto(scanner, "Ingrese la categoría: ");


            Producto nuevoProducto = new Producto(0, nombre, precio, stock, categoria);
            
            productoService.agregarProducto(nuevoProducto);


        } catch (IllegalArgumentException e) {

            System.out.println("⚠ Error de validación: " + e.getMessage());
        } catch (ProductosDuplicadoException e) {
            System.out.println("⚠ " + e.getMessage());
        }
    }

    private static void menuBuscarProducto(Scanner scanner, ProductoService productoService) {
        System.out.println("\n-- BUSCAR PRODUCTO --");
        int idBuscado = Validador.leerEntero(scanner, "Ingrese el ID del producto a buscar:");
        
        try {
            Producto productoEncontrado = productoService.buscarProducto(idBuscado);
            System.out.println("✔ Producto encontrado: " + productoEncontrado.toString());
        } catch (ProductosNoEncontradoException e) {
            System.out.println("⚠ " + e.getMessage());
        }
    }

    private static void menuVenderProducto(Scanner scanner, ProductoService productoService) {
        System.out.println("\n-- VENDER PRODUCTO --");
        int idVenta = Validador.leerEntero(scanner, "Ingrese el ID del producto a vender:");
        int cantidad = Validador.leerEntero(scanner, "Ingrese la cantidad a vender:");

        try {
            productoService.reducirStock(idVenta, cantidad);
        } catch (ProductosNoEncontradoException | StockInsuficienteException e) {
            System.out.println("⚠ Error en la venta: " + e.getMessage());
        }
    }
}
