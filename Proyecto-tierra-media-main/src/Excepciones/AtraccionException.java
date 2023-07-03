package Excepciones;

public class AtraccionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String mensaje;

	public AtraccionException(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return this.mensaje;
	}

}
