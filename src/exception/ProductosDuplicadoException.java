package exception;

public class ProductosDuplicadoException extends RuntimeException {
    public ProductosDuplicadoException(String mensaje) {
        super(mensaje);
    }
    
}
