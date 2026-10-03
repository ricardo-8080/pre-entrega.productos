package service;

import exception.ProductosNoEncontradoException;
import exception.StockInsuficienteException;
import exception.ProductosDuplicadoException;
import model.Producto;
import util.Validador;

import java.util.HashMap;
import java.util.Map;



public class ProductoService {
    private static int generadorId = 1;
    private final Map<Integer, Producto> inventario;

    public ProductoService() {
        this.inventario = new HashMap<>();
    }
    
    public void agregarProducto(Producto producto) {
        Validador.validarNombre(producto.getNombre());
        Validador.validarPrecio(producto.getPrecio());
        Validador.validarStock(producto.getStock());
        Validador.validarCategoria(producto.getCategoria());

        for (Producto p : inventario.values()) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                throw new ProductosDuplicadoException("error: ya existe un producto con el nombre: " + producto.getNombre());
            }
        }

        producto.setId(generadorId++);

        inventario.put(producto.getId(), producto);
        System.out.println("✔ Producto agregado con éxito: " + producto.getNombre() + " (ID: " + producto.getId() + ")");

    }

    public Producto buscarProducto(int id) {
        if (!inventario.containsKey(id)) {
            throw new ProductosNoEncontradoException("error: no existe un producto con el ID: " + id);
        }
        return inventario.get(id);

    }

    public void listarProductos() {
        if (inventario.isEmpty()) {
            System.out.println("EL CATALOGO ESTA VACIO.");
            return;
        }
        System.out.println("\n--- CATÁLOGO DE PRODUCTOS ---");
        for (Producto producto : inventario.values()) {
            System.out.println(producto.toString());
        }
        System.out.println("-----------------------------");
    }

    public void reducirStock(int id, int cantidadComprada) {
        Producto producto = buscarProducto(id);

        if (cantidadComprada <= 0) {
            throw new IllegalArgumentException("La cantidad a comprar debe ser mayor que cero.");
        }

        if (producto.getStock() < cantidadComprada) {
            throw new StockInsuficienteException("error: stock insuficiente para el producto " + producto.getNombre() +
                                                ". Stock disponible: " + producto.getStock() +
                                                ", cantidad solicitada: " + cantidadComprada);
        }

        int stockResultante = producto.getStock() - cantidadComprada;
        producto.setStock(stockResultante);
        System.out.println("✔ Stock actualizado. Nuevo stock de " + producto.getNombre() + ": " + producto.getStock());
    }
}
