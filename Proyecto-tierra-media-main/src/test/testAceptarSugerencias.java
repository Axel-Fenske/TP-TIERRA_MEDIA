package test;

import static org.junit.Assert.*;

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
import org.junit.rules.ExpectedException;

public class testAceptarSugerencias {

    @Test
    public void test() {
        Oferta oferta1 = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);
        Oferta oferta2 = new Atraccion("Atraccion 2", 2, 10, 5, TipoDeAtraccion.AVENTURA);
        Oferta oferta3 = new Atraccion("Atraccion 3", 2, 10, 5, TipoDeAtraccion.PAISAJE);

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
        Oferta oferta1 = new Atraccion("Atraccion 1", 2, 10, 5, TipoDeAtraccion.AVENTURA);
        Oferta oferta2 = new Atraccion("Atraccion 2", 2, 10, 5, TipoDeAtraccion.AVENTURA);
        Oferta oferta3 = new Atraccion("Atraccion 3", 2, 10, 5, TipoDeAtraccion.PAISAJE);

        Usuario usuario = new Usuario("Usuario_1", TipoDeAtraccion.AVENTURA, 30, 1);

        List<Oferta> listaDeOfertas = new ArrayList<>();
        List<Usuario> listaDeUsuarios = new ArrayList<>();

        listaDeUsuarios.add(usuario);

        listaDeOfertas.add(oferta3);
        listaDeOfertas.add(oferta1);
        listaDeOfertas.add(oferta2);

        GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

        generador.generarSugerencias();
        assertTrue(listaDeUsuarios.get(0).tamanioItinerario() == 0);

    }

    @Test
    public void usuarioNoTieneTiempoSuficiente(){
        Oferta oferta1 = new Atraccion("Atraccion 1", 2, 9, 5, TipoDeAtraccion.AVENTURA);
        Oferta oferta2 = new Atraccion("Atraccion 2", 4, 10, 5, TipoDeAtraccion.AVENTURA);
        Oferta oferta3 = new Atraccion("Atraccion 3", 2, 10, 5, TipoDeAtraccion.PAISAJE);

        Usuario usuario = new Usuario("Usuario_1", TipoDeAtraccion.AVENTURA, 1, 100);

        List<Oferta> listaDeOfertas = new ArrayList<>();
        List<Usuario> listaDeUsuarios = new ArrayList<>();

        listaDeUsuarios.add(usuario);

        listaDeOfertas.add(oferta3);
        listaDeOfertas.add(oferta1);
        listaDeOfertas.add(oferta2);

        GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas, listaDeUsuarios);

        generador.generarSugerencias();
        assertTrue(listaDeUsuarios.get(0).tamanioItinerario() == 0);

    }

    @Test(expected = GeneradorDeSugerenciasException.class)
    public void testOfertaVacia() {
        List<Oferta> listaDeOfertas = new ArrayList<Oferta>();
        List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

        Usuario usuario = new Usuario("Usuario_2", TipoDeAtraccion.AVENTURA, 10, 10);

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
}
