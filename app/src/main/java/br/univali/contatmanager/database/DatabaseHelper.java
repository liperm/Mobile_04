package br.univali.contatmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.univali.contatmanager.Contato;
import br.univali.contatmanager.Pessoa;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "contact_manager";
    private static final String TABLE_PESSOA = "pessoa";
    private static final String CREATE_TABLE_PESSOA =
            "CREATE TABLE " + TABLE_PESSOA + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(100));";
    private static final String DROP_TABLE_PESSOA = "DROP TABLE IF EXISTS " + TABLE_PESSOA;

    private static final String TABLE_CONTATO = "contato";
    private static final String CREATE_TABLE_CONTATO =
            "CREATE TABLE " + TABLE_CONTATO + " " + "(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_pessoa INTEGER," +
                    "number VARCHAR(11)," +
                    "type VARCHAR(30)," +
                    "FOREIGN KEY(id_pessoa) REFERENCES pessoa(_id)" +
                    ");";
    private static final String DROP_TABLE_CONTATO = "DROP TABLE IF EXISTS " + TABLE_CONTATO;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PESSOA);
        db.execSQL(CREATE_TABLE_CONTATO);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_PESSOA);
        db.execSQL(DROP_TABLE_CONTATO);
        onCreate(db);
    }
    public void closeDBConnection() {
        db.close();
    }

    public long createPessoa (Pessoa p) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", p.getName());

        long id = db.insert(TABLE_PESSOA, null, cv);
        db.close();
        return id;
    }

    public ArrayList<Pessoa> getPessoas() {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select _id, name from pessoa;", null);

        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        if(c.moveToFirst()) {
            do {
                pessoas.add(new Pessoa(c.getLong(0), c.getString(1)));
            } while(c.moveToNext());
        }
        db.close();
        return pessoas;
    }

    public void deletePessoa(long id) {
        db = this.getWritableDatabase();
        db.delete(TABLE_PESSOA, "_id=?", new String[]{Long.toString(id)});
        db.close();
    }

    public long createContato (Contato c) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("number", c.getNumber());
        cv.put("type", c.getType());

        long id = db.insert(TABLE_PESSOA, null, cv);
        db.close();
        return id;
    }
}