package tierraMedia;

import java.util.List;
import java.util.LinkedList;

public class Usuario {
	private final String nombre;
	final TipoDeAtraccion preferencia;
	private int tiempoDisponible;
	private double dineroDisponible;
	private List<Oferta> itinerario;

	public Usuario(String nombre, TipoDeAtraccion preferencia, int tiempoDisponible, double dineroDisponible) {
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.tiempoDisponible = tiempoDisponible;
		this.dineroDisponible = dineroDisponible;
		this.itinerario = new LinkedList<>();
	}

	@Override
	public String toString() {
		return "Usuario nombre=" + nombre + ", preferencia=" + preferencia + "\n\n";
	}

	public String getNombre() {
		return nombre;
	}

	public TipoDeAtraccion getPreferencia() {
		return preferencia;
	}

	public int getTiempoDisponible() {
		return tiempoDisponible;
	}

	public double getDineroDisponible() {
		return dineroDisponible;
	}

	public String mostrarItinerario() {
		String itenerario = "";

		for (Oferta o : this.itinerario) {
			itenerario += o.toString() + "\n";
		}

		itenerario += "Tiempo de duracion total: " + this.getTiempoTotal() + " horas\n" + "Precio total a pagar: $"
				+ this.getCostoTotal() + "\n";

		return itenerario;
	}

	public void agregarAItinerario(Oferta oferta) {
		this.itinerario.add(oferta);
	}

	public double getTiempoTotal() {
		double tiempoTotal = 0;

		for (Oferta oferta : itinerario) {
			tiempoTotal += oferta.getTiempo();
		}

		return tiempoTotal;
	}

	public int getCostoTotal() {
		int costoTotal = 0;

		for (Oferta oferta : itinerario) {
			costoTotal += oferta.getCosto();
		}

		return costoTotal;
	}

	public void reducirTiempo(double tiempo) {
		this.tiempoDisponible -= tiempo;
	}

	public void reducirDinero(int dinero) {
		this.dineroDisponible -= dinero;
	}

	public void ordenarItenerario(OfertaComparator ofertaComparator) {
		this.itinerario.sort(ofertaComparator);
	}
}
