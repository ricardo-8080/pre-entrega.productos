package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validador {


    private Validador() {
        throw new UnsupportedOperationException("La clase Validador no puede ser instanciada.");
    }


    public static void validarNombre(String nombre) {
    
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
    }

    public static void validarPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
    }

    public static void validarCategoria (String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría del producto no puede estar vacía.");
        }
    }

    public static void validarStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock del producto no puede ser negativo.");
        }
    }

    public static int leerEntero(Scanner sc , String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                sc.nextLine();
            }
        }
    }

    public static double leerDouble(Scanner sc , String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un numero decimal. (coma o punto)");
                sc.nextLine();
            }
        }
    }

    public static String leerTexto(Scanner sc, String mensaje) {
        System.out.println(mensaje);
        return sc.nextLine();
    }
}