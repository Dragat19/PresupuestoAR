package prueba.com.presupuesto.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import prueba.com.presupuesto.Model.Gastos;

/**
 * Created by albertsanchez on 7/8/17.
 */

public class DBGastos extends SQLiteOpenHelper {

    private static final String TABLE_GASTOS = "Gastos";
    public static final String DATABASE_NAME2 = "GastosDataBase";

    /**
     * --------COLUMNAS PARA LA TABLA DE GASTOS--------
     */
    private static final String COLUMN_ID_GASTOS = "id";
    private static final String COLUMN_CATEGORIA_GASTOS = "titulo";
    private static final String COLUMN_MONTO_DISPONIBLE_GASTOS = "disponible";
    private static final String COLUMN_MONTO_PRESUPUESTADO_GASTOS = "presupuesto";
    private static final String COLUMN_DESCRIPCION_GASTOS = "descripcion";
    private static final String COLUMN_MONTO_GASTOS = "gastados";

    /**
     * ----------------------TABLA DE GASTOS ------------------------
     */
    public static final String CREATE_SQL_GASTOS = "Create table " + TABLE_GASTOS
            + "(" + COLUMN_ID_GASTOS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CATEGORIA_GASTOS + " text, "
            + COLUMN_DESCRIPCION_GASTOS + " text, " + COLUMN_MONTO_GASTOS + " text);";

    private Context context;


    public DBGastos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL_GASTOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addNewItemsGastos(Gastos gastos, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues regiter = new ContentValues();

        regiter.put(COLUMN_CATEGORIA_GASTOS, gastos.getTitle());
        regiter.put(COLUMN_DESCRIPCION_GASTOS, gastos.getDescripcion());
        regiter.put(COLUMN_MONTO_GASTOS, gastos.getValorGastos());

        db.insertWithOnConflict(TABLE_GASTOS, null, regiter, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();

    }


    private Cursor cargarCursorGastos() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columnas = new String[]{COLUMN_ID_GASTOS, COLUMN_CATEGORIA_GASTOS
                , COLUMN_DESCRIPCION_GASTOS, COLUMN_MONTO_GASTOS};
        return db.query(TABLE_GASTOS, columnas, null, null, null, null, null, null);
    }

    public List<Gastos> getByGastos(String title) {
        List<Gastos> list = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        String selectQuery = "select * from  " + TABLE_GASTOS + " where title"+"'"+title+"'";
        //SELECT * FROM COMPANY WHERE NAME GLOB 'Ki*'
        String[] columnas = new String[]{COLUMN_ID_GASTOS, COLUMN_CATEGORIA_GASTOS
                , COLUMN_DESCRIPCION_GASTOS, COLUMN_MONTO_GASTOS};
        //Cursor cursor = database.query(TABLE_GASTOS, columnas,COLUMN_CATEGORIA_GASTOS+"'"+title+"'", null, null, null, null);

        Cursor cursor1 = database.rawQuery(selectQuery,null);
        while (cursor1.moveToNext()) {
            Gastos gastos = new Gastos(context);
            gastos.setIndex(cursor1.getString(0));
            gastos.setTitle(cursor1.getString(1));
            gastos.setDescripcion(cursor1.getString(3));
            gastos.setValorGastos(cursor1.getString(4));
            list.add(gastos);
        }
        return list;
    }


    public List<Gastos> getListGastos() {
        List<Gastos> list = new ArrayList<>();
        Cursor c = cargarCursorGastos();
        while (c.moveToNext()) {
            Gastos gastos = new Gastos(context);
            gastos.setIndex(c.getString(0));
            gastos.setTitle(c.getString(1));
            gastos.setDescripcion(c.getString(3));
            gastos.setValorGastos(c.getString(4));
            list.add(gastos);

        }
        return list;
    }

}
