package tierraMedia;

import java.util.List;

public class PromocionAbsoluta extends Promocion {

	public PromocionAbsoluta(List<Atraccion> atracciones, int precioADescontar) {
		super(atracciones, precioADescontar);
	}

	protected int calcularCosto() {
		return this.costoOriginal - this.valor;
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