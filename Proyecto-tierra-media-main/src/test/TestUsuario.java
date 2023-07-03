package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tierraMedia.Atraccion;
import tierraMedia.Oferta;
import tierraMedia.TipoDeAtraccion;
import tierraMedia.Usuario;

import tierraMedia.OfertaComparator;

public class TestUsuario {

	Usuario usuario;

	@Before
	public void setUp() {
		usuario = new Usuario("Frodo", TipoDeAtraccion.AVENTURA, 10, 100);
	}

	@Test
	public void testGetTiempoDisponible() {
		assertEquals(10, usuario.getTiempoDisponible(), 0.01);
	}

	@Test
	public void testGetNombre() {
		assertEquals("Frodo", usuario.getNombre());
	}

	@Test
	public void testGetPreferencia() {

		assertEquals(TipoDeAtraccion.AVENTURA, usuario.getPreferencia());
	}

	@Test
	public void testGetDineroDisponible() {

		assertEquals(100, usuario.getDineroDisponible(), 0.01);
	}

	@Test
	public void testItinerarioVacio() {

		String salidaEsperada = "Tiempo de duracion total: " + usuario.getTiempoTotal() + " horas\n"
				+ "Precio total a pagar: $" + usuario.getCostoTotal() + "\n";

		assertEquals(salidaEsperada, usuario.mostrarItinerario());
	}

	@Test
	public void testReducirTiempo() {
		usuario.reducirTiempo(5);

		assertEquals(5, usuario.getTiempoDisponible(), 0.001);
	}

	@Test
	public void testReducirDinero() {
		usuario.reducirDinero(45);

		assertEquals(55, usuario.getDineroDisponible(), 0.01);
	}

	@Test
	public void testAgregarAItinerario() {
		Atraccion a = new Atraccion("atraccion 1", 45, 5, 13, TipoDeAtraccion.AVENTURA);

		usuario.agregarAItinerario(a);

		assertTrue(usuario.mostrarItinerario().contains(a.toString()));
	}

	@Test
	public void testOrdenarItinerario() {

		Usuario usuarioTest = new Usuario("Usuario 1", TipoDeAtraccion.AVENTURA, 10, 20);

		Oferta oferta1 = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta2 = new Atraccion("Atraccion 2", 3, 15, 8, TipoDeAtraccion.AVENTURA);
		Oferta oferta3 = new Atraccion("Atraccion 3", 1, 5, 3, TipoDeAtraccion.PAISAJE);

		usuarioTest.agregarAItinerario(oferta3);
		usuarioTest.agregarAItinerario(oferta1);
		usuarioTest.agregarAItinerario(oferta2);

		List<Oferta> salidaEsperada = new ArrayList<>();
		salidaEsperada.add(oferta2);
		salidaEsperada.add(oferta1);
		salidaEsperada.add(oferta3);

		usuarioTest.ordenarItenerario(new OfertaComparator(usuarioTest.getPreferencia()));

		assertEquals(salidaEsperada, usuarioTest.getItinerario());

	}

}
