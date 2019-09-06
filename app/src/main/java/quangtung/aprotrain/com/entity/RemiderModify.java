package quangtung.aprotrain.com.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;

import quangtung.aprotrain.com.db.DBHelper;
import quangtung.aprotrain.com.modal.Remider;

public class RemiderModify {
    public static final String TABLE_NAME = "remider";

    public static final String TABLE_QUERY = "create table "+TABLE_NAME+" (\n" +
            "\t_id integer primary key autoincrement,\n" +
            "\tnoidung text,\n" +
            "\tquantrong integer\n" +
            ")";

    public static void insert(Remider remider) {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("noidung", remider.getNoidung());
        values.put("quantrong", remider.isQuantrong());

        db.insert(TABLE_NAME, null, values);
    }

    public static void update(Remider remider) {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("noidung", remider.getNoidung());
        values.put("quantrong", remider.isQuantrong());

        db.update(TABLE_NAME, values, "_id="+remider.getId(), null);
    }

    public static void delete(int id) {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();

        db.delete(TABLE_NAME, "_id = " + id, null);
    }

    public static Cursor findAll() {
        SQLiteDatabase db = DBHelper.getInstance(null).getReadableDatabase();

        String sql = "select * from " + TABLE_NAME;

        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }
}
