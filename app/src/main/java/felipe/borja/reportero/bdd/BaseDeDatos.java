package felipe.borja.reportero.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDatos extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME = "reportero.db";
    public static final String TABLE_NAME = "NOTICIAS";
    public static final String _ID = "_id";
    public static final String TITULO = "titulo";
    public static final String CUERPO = "cuerpo";
    public static final String URL = "url";
    public static final String SITIO = "sitio";

    public BaseDeDatos(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + TITULO + " TEXT NOT NULL, "
            + CUERPO + " TEXT NOT NULL, "
            + URL + " TEXT NOT NULL, "
            + SITIO + " TEXT NOT NULL"
            + " ); ";

}
