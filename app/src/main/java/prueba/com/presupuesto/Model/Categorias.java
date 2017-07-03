package prueba.com.presupuesto.Model;

import android.content.Context;

/**
 * Created by levaa on 6/19/2017.
 */

public class Categorias {
    private String titles;
    private String id;
    private int imagen;
    private String presupuestoInicial;
    private String montoDisponoble;
    private String Fecha;
    private Context ctx;

    public Categorias(String titles, String presupuestoInicial, String montoDisponoble,int imagen) {
        this.titles = titles;
        this.presupuestoInicial = presupuestoInicial;
        this.montoDisponoble = montoDisponoble;
        this.imagen = imagen;
    }

    public Categorias(Context context) {
        this.ctx = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setPresupuestoInicial(String presupuestoInicial) {
        this.presupuestoInicial = presupuestoInicial;
    }

    public void setMontoDisponoble(String montoDisponoble) {
        this.montoDisponoble = montoDisponoble;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getFecha() {
        return Fecha;
    }

    public int getImagen() {
        return imagen;
    }

    public String getTitles() {
        return titles;
    }

    public String getPresupuestoInicial() {
        return presupuestoInicial;
    }

    public String getMontoDisponoble() {
        return montoDisponoble;
    }

}
