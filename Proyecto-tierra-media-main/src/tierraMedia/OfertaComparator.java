package tierraMedia;

import java.util.Comparator;

public class OfertaComparator implements Comparator<Oferta> {
	private final TipoDeAtraccion tipoDelCliente;

	public OfertaComparator(TipoDeAtraccion tipo) {
		this.tipoDelCliente = tipo;
	}

	@Override
	public int compare(Oferta o1, Oferta o2) {
		if (ambosSonDelTipoDelCliente(o1, o2)) {
			return comparadorInterno(o1, o2);
		}
		if (ningunoEsDelTipoDelCliente(o1, o2)) {
			return comparadorInterno(o1, o2);
		}

		return -compararContraTipoDelCliente(o1, o2);
	}

	private int comparadorInterno(Oferta o1, Oferta o2) {
		if (ambosSonPromocion(o1, o2)) {
			if (mismoCosto(o1, o2)) {
				return -Double.compare(o1.getTiempo(), o2.getTiempo());
			} else {
				return -Integer.compare(o1.getCosto(), o2.getCosto());
			}
		} else if (ningunoEsPromocion(o1, o2)) {
			if (mismoCosto(o1, o2)) {
				return -Double.compare(o1.getTiempo(), o2.getTiempo());
			} else {
				return -Integer.compare(o1.getCosto(), o2.getCosto());
			}
		} else {
			return -Boolean.compare(o1.esPromo(), o2.esPromo());
		}
	}

	private boolean mismoCosto(Oferta o1, Oferta o2) {
		return o1.getCosto() == o2.getCosto();
	}

	private boolean ambosSonDelTipoDelCliente(Oferta o1, Oferta o2) {
		return o1.getTipo() == this.tipoDelCliente && o2.getTipo() == this.tipoDelCliente;
	}

	private boolean ningunoEsDelTipoDelCliente(Oferta o1, Oferta o2) {
		return o1.getTipo() != this.tipoDelCliente && o2.getTipo() != this.tipoDelCliente;
	}

	private boolean ambosSonPromocion(Oferta o1, Oferta o2) {
		return o1.esPromo() && o2.esPromo();
	}

	private boolean ningunoEsPromocion(Oferta o1, Oferta o2) {
		return !o1.esPromo() && !o2.esPromo();
	}

	private int compararContraTipoDelCliente(Oferta o1, Oferta o2) {
		if (o1.getTipo() == this.tipoDelCliente && o2.getTipo() != this.tipoDelCliente) {
			return 1;
		} else if (o1.getTipo() != this.tipoDelCliente && o2.getTipo() == this.tipoDelCliente) {
			return -1;
		}
		return 0;
	}
}