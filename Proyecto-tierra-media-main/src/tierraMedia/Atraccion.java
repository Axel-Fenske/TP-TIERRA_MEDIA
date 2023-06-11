package tierraMedia;

import java.util.Objects;

public class Atraccion extends Oferta {
    private final String nombre;
    private int cupo;

    public Atraccion(String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo) {
        super(tipo, costo, tiempo);
        this.nombre = nombre;
        this.cupo = cupo;
    }

    @Override
    public String toString() {
        return "Atraccion\n" +
                "Nombre: [" + this.getNombre() + "]" +
                "\n-Precio: $" + this.getCosto() +
                "\n-Duracion: " + this.tiempo + " horas\n";
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public boolean hayCupo() {
        return this.cupo > 0;
    }

    @Override
    public void reducirCupo() {
        this.cupo--;
    }

    @Override
    public int getCosto() {
        return this.costo;
    }

    @Override
    public double getTiempo() {
        return this.tiempo;
    }

    @Override
    public TipoDeAtraccion getTipo() {
        return tipo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(nombre);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Atraccion other = (Atraccion) obj;
        return this.nombre.equals(other.nombre);
    }
}