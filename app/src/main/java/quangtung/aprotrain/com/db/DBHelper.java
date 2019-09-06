package quangtung.aprotrain.com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import quangtung.aprotrain.com.entity.RemiderModify;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Remiders";
    private static final int VERSION = 1;

    private static DBHelper instance = null;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public synchronized static DBHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RemiderModify.TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
