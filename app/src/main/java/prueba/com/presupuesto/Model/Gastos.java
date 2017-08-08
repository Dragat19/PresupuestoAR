package prueba.com.presupuesto.Model;

import android.content.Context;

/**
 * Created by levaa on 6/23/2017.
 */

public class Gastos {
    private String index;
    private Context context;
    private String title;
    private String disponible;
    private String presupuestado;
    private String descripcion;
    private String valorGastos;
    private String fecha;

    public Gastos(String title,String descripcion, String valorGastos) {
        this.title = title;
        this.descripcion = descripcion;
        this.valorGastos = valorGastos;
    }

    public Gastos(Context context) {
        this.context = context;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public void setPresupuestado(String presupuestado) {
        this.presupuestado = presupuestado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setValorGastos(String valorGastos) {
        this.valorGastos = valorGastos;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitle() {
        return title;
    }

    public String getDisponible() {
        return disponible;
    }

    public String getPresupuestado() {
        return presupuestado;
    }

    public String getIndex() {
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
