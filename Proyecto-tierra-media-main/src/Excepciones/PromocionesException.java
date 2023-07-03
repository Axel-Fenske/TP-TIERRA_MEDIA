package Excepciones;

public class PromocionesException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String mensaje;

	public PromocionesException(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return this.mensaje;
	}

}
