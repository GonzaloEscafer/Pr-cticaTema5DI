package es.studium.tiendecita;

public class ArticuloModelo {

    public int id;
    public String descripcion;
    public double precio;
    public int cantidad;

    public ArticuloModelo(int id, String descripcion, double precio, int cantidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return descripcion + " - " + precio + " €";
    }
}
