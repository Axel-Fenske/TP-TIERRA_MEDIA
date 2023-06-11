package tierraMedia;

import java.util.List;

public class PromocionAXB extends Promocion {

	public PromocionAXB(List<Atraccion> atracciones, int lugarADescontar) {
		super(atracciones, lugarADescontar);
	}

	protected int calcularCosto() {
		if (this.valor < 1 || this.valor > this.atracciones.size()) {
			throw new RuntimeException("el valor no puede ser negativo");
		}
		Oferta a = this.atracciones.get(this.valor - 1);
		return this.costoOriginal - a.costo;
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
