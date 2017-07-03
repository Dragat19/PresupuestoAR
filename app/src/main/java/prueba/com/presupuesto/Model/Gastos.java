package prueba.com.presupuesto.Model;

/**
 * Created by levaa on 6/23/2017.
 */

public class Gastos {
    private int index;
    private String descripcion;
    private String valorGastos;
    private String fecha;

    public Gastos(String descripcion, String valorGastos) {
        this.descripcion = descripcion;
        this.valorGastos = valorGastos;
    }

    public int getIndex() {
        return index;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getValorGastos() {
        return valorGastos;
    }

}
