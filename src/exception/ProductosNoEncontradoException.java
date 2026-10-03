package exception;

public class ProductosNoEncontradoException extends RuntimeException {
    public ProductosNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

