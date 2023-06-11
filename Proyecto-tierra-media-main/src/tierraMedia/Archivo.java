package tierraMedia;

import java.util.List;
import java.util.Scanner;
import java.util.Locale;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Archivo {
    private static final String EXTENSION = ".in";
    private static final String PATH = "archivos/in/";
    private final String nombre;

    public Archivo(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> leerArchivoUsuario() {
        List<Usuario> listaDeUsuarios = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(PATH + this.nombre + EXTENSION))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNextLine()) {
                listaDeUsuarios.add(new Usuario(scanner.next().replace('-', ' '),
                        TipoDeAtraccion.valueOf(scanner.next().toUpperCase()), scanner.nextInt(),
                        scanner.nextDouble()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDeUsuarios;
    }

    public Map<String, Atraccion> leerArchivoAtraccion() {
        Map<String, Atraccion> mapaDeAtracciones = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(PATH + this.nombre + EXTENSION))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNextLine()) {
                String nombreAtraccion = scanner.next().replace('-', ' ');
                mapaDeAtracciones.put(nombreAtraccion,
                        new Atraccion(nombreAtraccion, scanner.nextInt(), scanner.nextDouble(), scanner.nextInt(),
                                TipoDeAtraccion.valueOf(scanner.next().toUpperCase())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapaDeAtracciones;
    }

    public List<Promocion> leerArchivoPromocion(Map<String, Atraccion> atraccion) {
        List<Promocion> listaDePromociones = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(PATH + this.nombre + EXTENSION))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNextLine()) {
                String[] campos = scanner.nextLine().split(" ");
                int j = 0;
                TipoDePromocion tipo = TipoDePromocion.valueOf(campos[j++]);
                int valor = Integer.parseInt(campos[j++]);
                List<Atraccion> atraccionPromo = new ArrayList<>();
                while (j < campos.length) {
                    String nombreAtraccion = campos[j++].replace('-', ' ');
                    atraccionPromo.add(atraccion.get(nombreAtraccion));
                }
                if (tipo == TipoDePromocion.ABS) {
                    listaDePromociones.add(new PromocionAbsoluta(atraccionPromo, valor));
                } else if (tipo == TipoDePromocion.PER) {
                    listaDePromociones.add(new PromocionPorcentual(atraccionPromo, valor));
                } else {
                    listaDePromociones.add(new PromocionAXB(atraccionPromo, valor));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDePromociones;
    }

    public void guardarArchivo(Usuario usuario) {
        File fileName = new File("archivos/out/" + this.nombre + ".out");
        try (PrintWriter printer = new PrintWriter(fileName)) {
            printer.println(usuario + usuario.mostrarItinerario());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

