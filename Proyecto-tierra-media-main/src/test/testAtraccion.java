package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tierraMedia.Atraccion;
import tierraMedia.TipoDeAtraccion;

public class testAtraccion {
	
	Atraccion atraccion;

	@Before
	public void setUp () {
		atraccion = new Atraccion("Atraccion 1", 10, 2, 5, TipoDeAtraccion.AVENTURA);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Atraccion 1", atraccion.getNombre());
	}
	
	@Test
	public void tesHayCupo() {
		assertTrue(atraccion.hayCupo());
	}
	
	@Test
	public void testReducirCupo() {
		int esperado = atraccion.getCupo() - 1;
		atraccion.reducirCupo();
		
		assertEquals(esperado, atraccion.getCupo());
	}
	
	@Test
	public void testEquals() {
		Atraccion atraccion2 = new Atraccion("Atraccion 1", 10, 2, 5, TipoDeAtraccion.AVENTURA);
		
		assertEquals(atraccion2, atraccion);
	}
}
