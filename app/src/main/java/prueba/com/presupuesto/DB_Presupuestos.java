package prueba.com.presupuesto;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import prueba.com.presupuesto.Model.Categorias;

/**
 * Created by levaa on 6/30/2017.
 */

public class DB_Presupuestos extends SQLiteOpenHelper {

    private static final String TABLE_CATEGORIAS = "Categoria";
    public static final String DATABASE_NAME = "PresupuestoDataBase";


    /**--------COLUMNAS PARA LA TABLA DE CATEGORIAS--------*/
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CATEGORIA = "title";
    private static final String COLUMN_MONTO_DISPONIBLE= "available";
    private static final String COLUMN_MONTO_PRESUPUESTADO= "Budgeted";
    private static final String COLUMN_IMAGEN = "imagen";
    private static final String COLUMN_FECHA = "date";

    /**----------------------COMANDOS SQL------------------------*/
    public static final String CREATE_SQL_PRESUPUESTO = "Create table " + TABLE_CATEGORIAS
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_IMAGEN + " INTEGER,"
            +COLUMN_CATEGORIA+" text, "+COLUMN_MONTO_DISPONIBLE+ " text, "
            +COLUMN_MONTO_PRESUPUESTADO+ " text, "+COLUMN_FECHA+ " text);";

    private final String[] projectionSwipe = {COLUMN_ID,COLUMN_CATEGORIA,COLUMN_IMAGEN,COLUMN_MONTO_DISPONIBLE,COLUMN_MONTO_PRESUPUESTADO,COLUMN_FECHA};
    private Context context;

    public DB_Presupuestos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL_PRESUPUESTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNewCategoria(Categorias categorias, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues register = new ContentValues();

        register.put(COLUMN_CATEGORIA, categorias.getTitles());
        register.put(COLUMN_IMAGEN, categorias.getImagen());
        register.put(COLUMN_MONTO_DISPONIBLE, categorias.getMontoDisponoble());
        register.put(COLUMN_MONTO_PRESUPUESTADO, categorias.getPresupuestoInicial());
        register.put(COLUMN_FECHA,date);

        db.insertWithOnConflict(TABLE_CATEGORIAS, null, register, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public Cursor cargarCursor() {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columnas= new String[]{COLUMN_ID,COLUMN_CATEGORIA,COLUMN_IMAGEN,COLUMN_MONTO_DISPONIBLE,COLUMN_MONTO_PRESUPUESTADO,COLUMN_FECHA};
        return db.query(TABLE_CATEGORIAS,columnas,null,null,null,null,null,null );
    }



    public List<Categorias> getListCategorias(){
        List<Categorias> list = new ArrayList<>();
        Cursor c = cargarCursor();
        while (c.moveToNext()){
            Categorias categorias = new Categorias(context);
            categorias.setId(c.getString(0));
            categorias.setTitles(c.getString(1));
            categorias.setImagen(Integer.valueOf(c.getString(2)));
            categorias.setMontoDisponoble(c.getString(3));
            categorias.setPresupuestoInicial(c.getString(4));
            categorias.setFecha(c.getString(5));
            list.add(categorias);
        }
        return list;
    }
}
