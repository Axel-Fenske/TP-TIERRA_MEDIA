package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import tierraMedia.Atraccion;
import tierraMedia.TipoDeAtraccion;

import tierraMedia.Promocion;
import tierraMedia.PromocionAXB;
import tierraMedia.PromocionAbsoluta;
import tierraMedia.PromocionPorcentual;

import org.junit.Test;

import Excepciones.GeneradorDeSugerenciasException;
import Excepciones.PromocionesException;

public class TestPromocion {

	@Test
	public void CalcularCostoAbsoluta() {

		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Atraccion 1", 12, 10, 5, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 2", 3, 15, 8, TipoDeAtraccion.AVENTURA));
		Promocion promocion = new PromocionAbsoluta(atracciones, 5);

		int esperado = 10;

		assertEquals(esperado, promocion.calcularCosto());
	}

	@Test(expected = PromocionesException.class)
	public void ExcepcionAXBMaximoAtracciones() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Atraccion 1", 12, 10, 5, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 2", 3, 15, 8, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 3", 25, 15, 8, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 4", 75, 15, 8, TipoDeAtraccion.AVENTURA));

		Promocion promocion = new PromocionAXB(atracciones, 4);

	}
	@Test
	public void CalcularCostoAXB() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Atraccion 1", 12, 10, 5, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 2", 3, 15, 8, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 3", 25, 15, 8, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 4", 75, 15, 8, TipoDeAtraccion.AVENTURA));
		Promocion promocion = new PromocionAXB(atracciones, 2);

		int esperado = 15;

		assertEquals(esperado, promocion.calcularCosto());
	}
	@Test
	public void CalcularCostoPorcentual() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Atraccion 1", 12, 10, 5, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 2", 3, 15, 8, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 3", 25, 15, 8, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 4", 75, 15, 8, TipoDeAtraccion.AVENTURA));
		Promocion promocion = new PromocionPorcentual(atracciones, 20);

		int esperado = (int) ((12+3+25+75)*0.8);

		assertEquals(esperado, promocion.calcularCosto());
	}
	@Test(expected = PromocionesException.class)
	public void ExcepcionPorcentajeMax() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Atraccion 1", 12, 10, 5, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 2", 3, 15, 8, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 3", 25, 15, 8, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Atraccion 4", 75, 15, 8, TipoDeAtraccion.AVENTURA));

		Promocion promocion = new PromocionPorcentual(atracciones, 100);

	}
}
