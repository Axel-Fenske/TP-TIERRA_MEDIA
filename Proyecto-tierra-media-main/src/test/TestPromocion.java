package test;

import Excepciones.PromocionesException;
import org.junit.Before;
import org.junit.Test;
import tierraMedia.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestPromocion {

    private Atraccion atraccion1;
    private Atraccion atraccion2;
    private Atraccion atraccion3;
    private Atraccion atraccion4;

    @Before
    public void setUp() {
        this.atraccion1 = new Atraccion("Atraccion 1", 12, 10, 5, TipoDeAtraccion.AVENTURA);
        this.atraccion2 = new Atraccion("Atraccion 2", 3, 15, 8, TipoDeAtraccion.AVENTURA);
        this.atraccion3 = new Atraccion("Atraccion 3", 25, 15, 8, TipoDeAtraccion.AVENTURA);
        this.atraccion4 = new Atraccion("Atraccion 4", 75, 15, 8, TipoDeAtraccion.AVENTURA);
    }

    @Test
    public void calcularCostoAbsoluta() {

        List<Atraccion> atracciones = new ArrayList<>();
        atracciones.add(this.atraccion1);
        atracciones.add(this.atraccion2);
        Promocion promocion = new PromocionAbsoluta(atracciones, 5);

        int esperado = 10;

        assertEquals(esperado, promocion.calcularCosto());
    }

    @Test(expected = PromocionesException.class)
    public void excepcionAXBMaximoAtracciones() {
        List<Atraccion> atracciones = new ArrayList<>();
        atracciones.add(this.atraccion1);
        atracciones.add(this.atraccion2);
        atracciones.add(this.atraccion3);
        atracciones.add(this.atraccion4);

        Promocion promocion = new PromocionAXB(atracciones, 4);

    }

    @Test
    public void calcularCostoAXB() {
        List<Atraccion> atracciones = new ArrayList<>();
        atracciones.add(this.atraccion1);
        atracciones.add(this.atraccion2);
        atracciones.add(this.atraccion3);
        atracciones.add(this.atraccion4);
        Promocion promocion = new PromocionAXB(atracciones, 2);

        int esperado = 15;

        assertEquals(esperado, promocion.calcularCosto());
    }

    @Test
    public void calcularCostoPorcentual() {
        List<Atraccion> atracciones = new ArrayList<>();
        atracciones.add(this.atraccion1);
        atracciones.add(this.atraccion2);
        atracciones.add(this.atraccion3);
        atracciones.add(this.atraccion4);
        Promocion promocion = new PromocionPorcentual(atracciones, 20);

        int esperado = (int) ((12 + 3 + 25 + 75) * 0.8);

        assertEquals(esperado, promocion.calcularCosto());
    }

    @Test(expected = PromocionesException.class)
    public void excepcionPorcentajeMax() {
        List<Atraccion> atracciones = new ArrayList<>();
        atracciones.add(new Atraccion("Atraccion 1", 12, 10, 5, TipoDeAtraccion.AVENTURA));
        atracciones.add(new Atraccion("Atraccion 2", 3, 15, 8, TipoDeAtraccion.AVENTURA));
        atracciones.add(new Atraccion("Atraccion 3", 25, 15, 8, TipoDeAtraccion.AVENTURA));
        atracciones.add(new Atraccion("Atraccion 4", 75, 15, 8, TipoDeAtraccion.AVENTURA));
        Promocion promocion = new PromocionPorcentual(atracciones, 100);

    }
}
