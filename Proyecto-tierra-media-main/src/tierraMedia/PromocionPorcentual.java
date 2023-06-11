package tierraMedia;

import java.util.List;

public class PromocionPorcentual extends Promocion {

	public PromocionPorcentual(List<Atraccion> atracciones, int porcentaje) {
		super(atracciones, porcentaje);
	}

	protected int calcularCosto() {
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
