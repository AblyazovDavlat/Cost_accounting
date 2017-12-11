package com.example.dablyazov.cost_accounting;

/**
 * Created by ablya on 27.11.2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DataBase extends SQLiteOpenHelper {
    private static DataBase _Instance = null;

    private SecondActivity _Activity;
    private SQLiteDatabase _Database;
    private int complements = 0;

    public static DataBase Get() {
        return _Instance;
    }

    public static void Initialize(Context context) {
        _Instance = new DataBase(context);
    }

    private DataBase(Context context) {
        super(context, "elementsDB", null, 2);
        _Activity = (SecondActivity) context;
        _Database = getWritableDatabase();
    }

    public void AddElement(String name, boolean isActive, int value) {
        _Database.execSQL("INSERT INTO elements VALUES (" +
                "\"" + name + "\"," + (isActive ? "1" : "0") + "," + value +  ");");
        complements++;
    }

    public int GetCount() {return complements;}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        _Database = sqLiteDatabase;
        _Database.execSQL("CREATE TABLE elements (ELNAME TEXT, ELTYPE INT, ELVALUE INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        _Activity.UpdateElements();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        _Database = db;
    }

    public ArrayList<ObjectLV> GetElements() {
        ArrayList<ObjectLV> result = new ArrayList<>();
        Cursor cursor = _Database.rawQuery(
                "SELECT * FROM elements WHERE 1", new String[]{});
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            result.add(new ObjectLV(cursor.getString(0), cursor.getInt(1) != 0, cursor.getInt(2)));
            cursor.moveToNext();
        }
        return result;
    }

    public void DeleteElement(int activeElement) {
        ObjectLV deleteel = DataBase.Get().GetElements().get(activeElement);
        _Database.execSQL("DELETE FROM elements WHERE ELNAME=\"" + deleteel.getInformation()+"\";");
        complements--;
        _Activity.UpdateElements();
    }

}