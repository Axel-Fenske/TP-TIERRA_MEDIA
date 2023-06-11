package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import tierraMedia.Atraccion;
import tierraMedia.TipoDeAtraccion;

import tierraMedia.Promocion;
import tierraMedia.PromocionAbsoluta;

import org.junit.Test;

public class TestPromocion {

	@Test
	public void testCalcularCosto() {

		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Atraccion 1", 12, 10, 5, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 2", 3, 15, 8, TipoDeAtraccion.AVENTURA));
		Promocion promocion = new PromocionAbsoluta(atracciones, 5);

		int esperado = 10;

		assertEquals(esperado, promocion.calcularCosto());
	}
	

}
