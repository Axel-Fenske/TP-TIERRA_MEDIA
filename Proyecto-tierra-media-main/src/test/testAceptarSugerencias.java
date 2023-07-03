package test;

import static org.junit.Assert.*;

import org.junit.Before;
import tierraMedia.Oferta;
import tierraMedia.TipoDeAtraccion;
import tierraMedia.Atraccion;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import tierraMedia.Usuario;
import tierraMedia.GeneradorDeSegurencias;

import Excepciones.GeneradorDeSugerenciasException;

import org.junit.Test;

public class testAceptarSugerencias {
	private Oferta oferta1;
	private Oferta oferta2;
	private Oferta oferta3;
	private Oferta oferta4;
	private Oferta oferta5;
	private Oferta oferta6;
	private Oferta oferta7;

	@Before
	public void setUp() {
		this.oferta1 = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		this.oferta2 = new Atraccion("Atraccion 2", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		this.oferta3 = new Atraccion("Atraccion 3", 2, 10, 5, TipoDeAtraccion.PAISAJE);

		this.oferta4 = new Atraccion("Atraccion 4", 2, 9, 5, TipoDeAtraccion.AVENTURA);
		this.oferta5 = new Atraccion("Atraccion 5", 4, 10, 5, TipoDeAtraccion.AVENTURA);
		this.oferta6 = new Atraccion("Atraccion 6", 2, 10, 5, TipoDeAtraccion.PAISAJE);

		this.oferta7 = new Atraccion("Atraccion 7", 2, 10, 5, TipoDeAtraccion.AVENTURA);
	}

	@Test
	public void test() {

		Usuario usuario = new Usuario("Usuario 1", TipoDeAtraccion.AVENTURA, 40, 10);

		List<Oferta> listaDeOfertas = new ArrayList<>();
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		listaDeUsuarios.add(usuario);

		listaDeOfertas.add(oferta1);
		listaDeOfertas.add(oferta2);
		listaDeOfertas.add(oferta3);

		String separator = System.getProperty("line.separator");
		String input = "S" + separator + "S" + separator + "S" + separator;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();

		assertTrue(usuario.mostrarItinerario().contains(oferta3.toString()));

	}

	@Test
	public void usuarioNoTieneDineroSuficiente() {

		Usuario usuario = new Usuario("Usuario_1", TipoDeAtraccion.AVENTURA, 30, 1);

		List<Oferta> listaDeOfertas = new ArrayList<>();
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		listaDeUsuarios.add(usuario);

		listaDeOfertas.add(oferta3);
		listaDeOfertas.add(oferta1);
		listaDeOfertas.add(oferta2);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();
		assertEquals(0, listaDeUsuarios.get(0).tamanioItinerario());

	}

	@Test
	public void usuarioNoTieneTiempoSuficiente() {

		Usuario usuario = new Usuario("Usuario_1", TipoDeAtraccion.AVENTURA, 1, 100);

		List<Oferta> listaDeOfertas = new ArrayList<>();
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		listaDeUsuarios.add(usuario);

		listaDeOfertas.add(oferta6);
		listaDeOfertas.add(oferta4);
		listaDeOfertas.add(oferta5);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();
		assertEquals(0, listaDeUsuarios.get(0).tamanioItinerario());

	}

	@Test(expected = GeneradorDeSugerenciasException.class)
	public void testOfertaVacia() {
		List<Oferta> listaDeOfertas = new ArrayList<>();
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		Usuario usuario = new Usuario("Usuario_2", TipoDeAtraccion.AVENTURA, 10, 10);

		listaDeUsuarios.add(usuario);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();

		assertFalse(usuario.mostrarItinerario().contains(oferta7.toString()));
	}

	@Test(expected = GeneradorDeSugerenciasException.class)
	public void testUsuariosVacio() {
		List<Oferta> listaDeOfertas = new ArrayList<>();
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		listaDeOfertas.add(oferta7);

		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

		generador.generarSugerencias();

	}
}
