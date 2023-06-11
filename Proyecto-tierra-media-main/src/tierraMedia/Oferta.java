package tierraMedia;

import java.util.Objects;

public abstract class Oferta {
	protected TipoDeAtraccion tipo;
	protected int costo;
	protected double tiempo;

	protected Oferta() {

	}

	protected Oferta(TipoDeAtraccion tipo, int costo, double tiempo) {
		this.tipo = tipo;
		this.costo = costo;
		this.tiempo = tiempo;
	}

	public abstract String getNombre();

	public abstract boolean hayCupo();

	public abstract void reducirCupo();

	public abstract int getCosto();

	public abstract double getTiempo();

	public abstract TipoDeAtraccion getTipo();

	@Override
	public int hashCode() {
		return Objects.hash(costo, tiempo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oferta other = (Oferta) obj;
		return costo == other.costo && Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo)
				&& tipo == other.tipo;
	}

	@Override
	public abstract String toString();

	public boolean esPromo() {
		return false;
	}
}
