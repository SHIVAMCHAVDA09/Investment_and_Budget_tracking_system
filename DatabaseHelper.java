package com.example.mcminiprojectjava.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mcminiprojectjava.model.Investment;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "investments.db";
    private static final int DATABASE_VERSION = 2; // Incremented the version for schema changes

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE investments (id INTEGER PRIMARY KEY, name TEXT, amount REAL, date TEXT, type TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS investments");
        onCreate(db);
    }

    public void addInvestment(String name, double amount, String date, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("amount", amount);
        values.put("date", date);
        values.put("type", type); // New field for investment type
        db.insert("investments", null, values);
        db.close();
    }

    public List<Investment> getAllInvestments() {
        List<Investment> investmentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM investments", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                double amount = cursor.getDouble(cursor.getColumnIndex("amount"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String type = cursor.getString(cursor.getColumnIndex("type")); // Fetching the investment type

                Investment investment = new Investment(id, name, amount, date, type);
                investmentList.add(investment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return investmentList;
    }

    public void deleteInvestment(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("investments", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
