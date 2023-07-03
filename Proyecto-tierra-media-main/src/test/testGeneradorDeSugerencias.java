package test;

import static org.junit.Assert.*;

import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import tierraMedia.Atraccion;

import tierraMedia.Oferta;
import tierraMedia.TipoDeAtraccion;
import tierraMedia.GeneradorDeSegurencias;
import tierraMedia.Usuario;
import Excepciones.GeneradorDeSugerenciasException;
import org.junit.Test;

public class testGeneradorDeSugerencias {

	@Test
	public void testAceptarSoloPreferencias() {
		Oferta oferta1 = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta2 = new Atraccion("Atraccion 2", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta3 = new Atraccion("Atraccion 3", 2, 10, 5, TipoDeAtraccion.PAISAJE);

		Usuario usuario = new Usuario("Usuario 1", TipoDeAtraccion.AVENTURA, 20, 10);

		List<Oferta> listaDeOfertas = new ArrayList<>();
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		listaDeUsuarios.add(usuario);

		listaDeOfertas.add(oferta1);
		listaDeOfertas.add(oferta2);
		listaDeOfertas.add(oferta3);

		String input = "S" + System.getProperty("line.separator") + "S" + System.getProperty("line.separator") + "N"
				+ System.getProperty("line.separator");
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();

		assertFalse(usuario.mostrarItinerario().contains(oferta3.toString()));

	}

	@Test
	public void testAceptarSoloNoPreferencias() {
		Oferta oferta1 = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta2 = new Atraccion("Atraccion 2", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta3 = new Atraccion("Atraccion 3", 2, 10, 5, TipoDeAtraccion.PAISAJE);

		Usuario usuario = new Usuario("Usuario 1", TipoDeAtraccion.AVENTURA, 10, 10);

		List<Oferta> listaDeOfertas = new ArrayList<>();
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		listaDeUsuarios.add(usuario);

		listaDeOfertas.add(oferta1);
		listaDeOfertas.add(oferta2);
		listaDeOfertas.add(oferta3);

		String input = "N" + System.getProperty("line.separator") + "N" + System.getProperty("line.separator") + "S"
				+ System.getProperty("line.separator");
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();

		assertTrue(usuario.mostrarItinerario().contains(oferta3.toString()));

	}

	@Test
	public void testAceptarTodasLasSugerencias() {
		Oferta oferta1 = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta2 = new Atraccion("Atraccion 2", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta3 = new Atraccion("Atraccion 3", 2, 10, 5, TipoDeAtraccion.PAISAJE);

		Usuario usuario = new Usuario("Usuario 1", TipoDeAtraccion.AVENTURA, 30, 10);

		List<Oferta> listaDeOfertas = new ArrayList<>();
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		listaDeUsuarios.add(usuario);

		listaDeOfertas.add(oferta1);
		listaDeOfertas.add(oferta2);
		listaDeOfertas.add(oferta3);

		String input = "S" + System.getProperty("line.separator") + "S" + System.getProperty("line.separator") + "S"
				+ System.getProperty("line.separator");
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();

		assertTrue(usuario.mostrarItinerario().contains(oferta3.toString()));

	}

	@Test(expected = GeneradorDeSugerenciasException.class)
	public void testOfertaVacia() {
		List<Oferta> listaDeOfertas = new ArrayList<Oferta>();
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

		Usuario usuario = new Usuario("Usuario 2", TipoDeAtraccion.AVENTURA, 10, 10);

		Oferta oferta = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);

		listaDeUsuarios.add(usuario);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();

		assertFalse(usuario.mostrarItinerario().contains(oferta.toString()));
	}

	@Test(expected = GeneradorDeSugerenciasException.class)
	public void testUsuariosVacio() {
		List<Oferta> listaDeOfertas = new ArrayList<Oferta>();
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

		Oferta oferta = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);

		listaDeOfertas.add(oferta);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();

	}

	@Test
	public void testUsuarioSinDineroSuficiente() {
		Oferta oferta1 = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta2 = new Atraccion("Atraccion 2", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta3 = new Atraccion("Atraccion 3", 2, 10, 5, TipoDeAtraccion.PAISAJE);

		Usuario usuario = new Usuario("Usuario 1", TipoDeAtraccion.AVENTURA, 1, 10);

		List<Oferta> listaDeOfertas = new ArrayList<>();
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		listaDeUsuarios.add(usuario);

		listaDeOfertas.add(oferta1);
		listaDeOfertas.add(oferta2);
		listaDeOfertas.add(oferta3);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();

		assertEquals(0, usuario.getItinerario().size());
	}

	@Test
	public void testUsuarioSinTiempoSuficiente() {
		Oferta oferta1 = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta2 = new Atraccion("Atraccion 2", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta3 = new Atraccion("Atraccion 3", 2, 10, 5, TipoDeAtraccion.PAISAJE);

		Usuario usuario = new Usuario("Usuario 1", TipoDeAtraccion.AVENTURA, 15, 1);

		List<Oferta> listaDeOfertas = new ArrayList<>();
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		listaDeUsuarios.add(usuario);

		listaDeOfertas.add(oferta1);
		listaDeOfertas.add(oferta2);
		listaDeOfertas.add(oferta3);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();

		assertEquals(0, usuario.getItinerario().size());
	}
}
