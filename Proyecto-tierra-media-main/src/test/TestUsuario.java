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
		assertEquals(10, usuario.getTiempoDisponible());
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

		assertEquals(5, usuario.getTiempoDisponible());
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

		Usuario usuario = new Usuario("Usuario 1", TipoDeAtraccion.AVENTURA, 10, 20);

		Oferta oferta1 = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);
		Oferta oferta2 = new Atraccion("Atraccion 2", 3, 15, 8, TipoDeAtraccion.AVENTURA);
		Oferta oferta3 = new Atraccion("Atraccion 3", 1, 5, 3, TipoDeAtraccion.PAISAJE);

		usuario.agregarAItinerario(oferta3);
		usuario.agregarAItinerario(oferta1);
		usuario.agregarAItinerario(oferta2);

		List<Oferta> salidaEsperada = new ArrayList<Oferta>();
		salidaEsperada.add(oferta2);
		salidaEsperada.add(oferta1);
		salidaEsperada.add(oferta3);

		usuario.ordenarItenerario(new OfertaComparator(usuario.getPreferencia()));

		assertEquals(salidaEsperada, usuario.getItinerario());

	}

}
