package tierraMedia;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class testAceptarSugerencias {

	@Test
	public void test() {
		Oferta oferta1 = new Atraccion("Atraccion 1",2,10,5,TipoDeAtraccion.AVENTURA);
		Oferta oferta2 = new Atraccion("Atraccion 2",2,10,5,TipoDeAtraccion.AVENTURA);
		Oferta oferta3 = new Atraccion("Atraccion 3",2,10,5,TipoDeAtraccion.PAISAJE);
		
		Usuario usuario = new Usuario("Usuario 1",TipoDeAtraccion.AVENTURA,10,10);
		
		List<Oferta> listaDeOfertas = new ArrayList<Oferta>();
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		
		listaDeUsuarios.add(usuario);
		
		listaDeOfertas.add(oferta1);
		listaDeOfertas.add(oferta2);
		listaDeOfertas.add(oferta3);
		
		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas,listaDeUsuarios);
		
		generador.generarSugerencias();		
		
		assertFalse(usuario.mostrarItinerario().contains(oferta3.toString()));
		
	}
	
	@Test(expected = GeneradorDeSugerenciasException.class)
	public void testOfertaVacia() {
		List<Oferta> listaDeOfertas = new ArrayList<Oferta>();
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		
		Usuario usuario = new Usuario("Usuario 2",TipoDeAtraccion.AVENTURA,10,10);
		
		Oferta oferta= new Atraccion("Atraccion 1",2,10,5,TipoDeAtraccion.AVENTURA);
		
		listaDeUsuarios.add(usuario);
		
		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas,listaDeUsuarios);
		
		generador.generarSugerencias();	
		
		assertFalse(usuario.mostrarItinerario().contains(oferta.toString()));
	}
	
	@Test(expected = GeneradorDeSugerenciasException.class)
	public void testUsuariosVacio() {
		List<Oferta> listaDeOfertas = new ArrayList<Oferta>();
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		
		Oferta oferta = new Atraccion("Atraccion 1",2,10,5,TipoDeAtraccion.AVENTURA);
		
		listaDeOfertas.add(oferta);
		
		GeneradorDeSegurencias generador = new GeneradorDeSegurencias(listaDeOfertas,listaDeUsuarios);
		
		generador.generarSugerencias();	
		
	}
}
