package tierraMedia;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TierraMedia {

	public static void main(String[] args) {
		Archivo archivoUsuarios = new Archivo("usuarios");
		Archivo archivoAtracciones = new Archivo("atracciones");
		Archivo archivoPromociones = new Archivo("promociones");

		List<Usuario> listaDeUsuarios = archivoUsuarios.leerArchivoUsuario();
		Map<String, Atraccion> mapaAtracciones = archivoAtracciones.leerArchivoAtraccion();
		List<Promocion> listaDePromociones = archivoPromociones.leerArchivoPromocion(mapaAtracciones);

		List<Oferta> listaDeOfertas = new LinkedList<>();
		listaDeOfertas.addAll(listaDePromociones);
		listaDeOfertas.addAll(mapaAtracciones.values());

		GeneradorDeSegurencias gs = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);
		gs.generarSugerencias();
	}

}
