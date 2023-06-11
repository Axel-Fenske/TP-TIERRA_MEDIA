package tierraMedia;

import java.util.*;

public class GeneradorDeSegurencias { ////SEGURENCIAS????
	private List<Oferta> listaDeOfertas;
	private List<Usuario> listaDeUsuarios;

	public GeneradorDeSegurencias(List<Oferta> listaDeOfertas, List<Usuario> listaDeUsuarios) {

		if(listaDeOfertas.isEmpty() || listaDeUsuarios.isEmpty()) {
			throw new GeneradorDeSugerenciasException("Las listas no pueden estar vacias");
		}
		
		this.listaDeUsuarios = listaDeUsuarios;
		this.listaDeOfertas = listaDeOfertas;
	}

	public void generarSugerencias() {
		
		HashSet<Oferta> ofertasYaSugeridas = new HashSet<>();
		Scanner sc = new Scanner(System.in);
		boolean tieneDineroYTiempo = true;

		for (Usuario usuario : listaDeUsuarios) {

			listaDeOfertas.sort(new OfertaComparator(usuario.getPreferencia()));
			System.out.println("Bienvenido/a " + usuario.getNombre() + "\n");

			while (tieneDineroYTiempo) {

				tieneDineroYTiempo = false;

				for (Oferta oferta : listaDeOfertas) {

					boolean yaOfertado = estaOfertada(ofertasYaSugeridas, oferta);

					double tiempoNecesario = oferta.getTiempo();
					int dineroNecesario = oferta.getCosto();

					if (tieneTiempoYDineroYNoOfertada(usuario, yaOfertado, tiempoNecesario, dineroNecesario))
						tieneDineroYTiempo = true;

					if (tieneTiempoYDineroYNoOfertada(usuario, yaOfertado, tiempoNecesario, dineroNecesario)
							&& oferta.hayCupo() && ofrecer(oferta, sc)) {
						aceptarOferta(ofertasYaSugeridas, usuario, oferta, tiempoNecesario, dineroNecesario);
					}

				}
			}

			ofertasYaSugeridas.clear();

			usuario.ordenarItenerario(new OfertaComparator(usuario.getPreferencia()));

			System.out.println("Itinerario de " + usuario.getNombre() + "\n");
			System.out.println(usuario.mostrarItinerario());

			tieneDineroYTiempo = true;
		}

		sc.close();
		this.guardarItinerariosDeUsuarios();

	}

	private boolean estaOfertada(HashSet<Oferta> ofertasYaSugeridas, Oferta oferta) {
		boolean yaOfertado = false;
		if (oferta.esPromo()) {
			Promocion promo = (Promocion) oferta;

			for (Oferta atraccion : promo.atracciones) {
				if (ofertasYaSugeridas.contains(atraccion))
					yaOfertado = true;
			}

		} else {
			if (ofertasYaSugeridas.contains(oferta))
				yaOfertado = true;
		}
		return yaOfertado;
	}

	private boolean tieneTiempoYDineroYNoOfertada(Usuario usuario, boolean yaOfertado, double tiempoNecesario,
			int dineroNecesario) {
		return !yaOfertado && tiempoNecesario <= usuario.getTiempoDisponible()
				&& dineroNecesario <= usuario.getDineroDisponible();
	}

	private boolean ofrecer(Oferta oferta, Scanner sc) {
		String respuesta;
		System.out.println(oferta);
		do {
			System.out.println("Acepta la oferta? S/N\n");
			respuesta = sc.nextLine();

		} while (respuestaValida(respuesta));

		return respuestaAfirmativa(respuesta);
	}

	private boolean respuestaValida(String respuesta) {
		return !respuesta.equals("S") && !respuesta.equals("N") && !respuesta.equals("s") && !respuesta.equals("n");
	}

	private boolean respuestaAfirmativa(String respuesta) {
		return respuesta.equals("S") || respuesta.equals("s");
	}

	private void aceptarOferta(HashSet<Oferta> ofertasYaSugeridas, Usuario usuario, Oferta oferta,
			double tiempoNecesario, int dineroNecesario) {

		System.out.println("Acepto la oferta!\n");

		usuario.agregarAItinerario(oferta);

		if (oferta.esPromo()) {
			Promocion promo = (Promocion) oferta;

			for (Oferta atraccion : promo.atracciones) {
				ofertasYaSugeridas.add(atraccion);
			}
		} else
			ofertasYaSugeridas.add(oferta);

		oferta.reducirCupo();
		usuario.reducirDinero(dineroNecesario);
		usuario.reducirTiempo(tiempoNecesario);

	}

	private void guardarItinerariosDeUsuarios() {
		for (Usuario u : this.listaDeUsuarios) {
			Archivo archivoSalida = new Archivo(u.getNombre());
			archivoSalida.guardarArchivo(u);
		}
	}

}