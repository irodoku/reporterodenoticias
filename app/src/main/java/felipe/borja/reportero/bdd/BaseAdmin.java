package felipe.borja.reportero.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import felipe.borja.reportero.Noticia;
import felipe.borja.reportero.R;

public class BaseAdmin {

    private BaseDeDatos baseDeDatos;
    private SQLiteDatabase bdd;
    private Context context;

    public BaseAdmin(Context context) {
        this.context = context;
    }

    public BaseAdmin abrir() throws SQLException {
        baseDeDatos = new BaseDeDatos(context);
        bdd = baseDeDatos.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        baseDeDatos.close();
    }

    public void insertar(String titulo, String cuerpo,String url,String sitio) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(BaseDeDatos.TITULO, titulo);
        contentValue.put(BaseDeDatos.CUERPO, cuerpo);
        contentValue.put(BaseDeDatos.URL, url);
        contentValue.put(BaseDeDatos.SITIO, sitio);
        bdd.insert(BaseDeDatos.TABLE_NAME, null, contentValue);
    }

    public void insertarBase(){
        InputStream archivoSQL = context.getResources().openRawResource(R.raw.noticias);
        Scanner sc = (new Scanner(archivoSQL));
        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()){
            sb.append(sc.nextLine());
        }
        String query = sb.toString();
        try{
            Cursor cursor = bdd.rawQuery(query,null);
            cursor.moveToFirst();
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Noticia> getBDD() {
        ArrayList<Noticia> noticias=new ArrayList<>();
        String[] columnas = new String[] { BaseDeDatos._ID, BaseDeDatos.TITULO,
                BaseDeDatos.CUERPO,BaseDeDatos.URL,BaseDeDatos.SITIO };
        Cursor cursor = bdd.query(
                BaseDeDatos.TABLE_NAME, columnas, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do{
                Noticia n= new Noticia(
                        cursor.getString(cursor.getColumnIndex(BaseDeDatos.TITULO)),
                        cursor.getString(cursor.getColumnIndex(BaseDeDatos.CUERPO)),
                        cursor.getString(cursor.getColumnIndex(BaseDeDatos.URL)),
                        cursor.getString(cursor.getColumnIndex(BaseDeDatos.SITIO))
                );
                noticias.add(n);
            }while (cursor.moveToNext());
        }
        return noticias;
    }

    public int actualizar(long _id, String titulo, String cuerpo,String url,String sitio) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseDeDatos.TITULO, titulo);
        contentValues.put(BaseDeDatos.CUERPO, cuerpo);
        contentValues.put(BaseDeDatos.URL, url);
        contentValues.put(BaseDeDatos.SITIO, sitio);
        int i = bdd.update(BaseDeDatos.TABLE_NAME, contentValues, BaseDeDatos._ID + " = " + _id, null);
        return i;
    }

    public void borrar(long _id) {
        bdd.delete(BaseDeDatos.TABLE_NAME, BaseDeDatos._ID + "=" + _id, null);
    }

    public void borrarTabla() {
        String query = "DELETE FROM "+ BaseDeDatos.TABLE_NAME;
        Log.w("query",""+query);
        try{
            Cursor cursor = bdd.rawQuery(query,null);
            cursor.moveToFirst();
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
