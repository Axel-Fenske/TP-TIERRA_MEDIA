package tierraMedia;

import java.util.List;

import Excepciones.PromocionesException;

public class PromocionAXB extends Promocion {

	public PromocionAXB(List<Atraccion> atracciones, int lugaresADescontar) {
		super(atracciones, lugaresADescontar);
	}

	public int calcularCosto() {
		if (this.valor < 1) {
			throw new PromocionesException("el valor no puede ser negativo");
		}
		if (this.valor > this.atracciones.size() - 1) {
			throw new PromocionesException("el valor no la cantidad maxima de atracciones o mayor");
		}
		int costoADescontar = 0;

		for (int i = this.atracciones.size() - valor; i < this.atracciones.size(); i++) {
			costoADescontar += atracciones.get(i).getCosto();
		}
		return this.costoOriginal - costoADescontar;
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
