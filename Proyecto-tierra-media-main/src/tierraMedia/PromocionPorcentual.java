package tierraMedia;

import java.util.List;

import Excepciones.PromocionesException;

public class PromocionPorcentual extends Promocion {

	public PromocionPorcentual(List<Atraccion> atracciones, int porcentaje) {
		super(atracciones, porcentaje);
	}

	public int calcularCosto() {
		if (this.valor >= 100 || this.valor <= 1) {
			throw new PromocionesException("el porcentaje no puede ser mayor al 99% o menor al 1%");
		}
		return this.costoOriginal - this.costoOriginal * this.valor / 100;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}
