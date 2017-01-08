package com.danielszabo.android.capitalquiz;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {

    private static String DB_NAME = "MyDB.db";
    private static String DB_PATH = "";
    private SQLiteDatabase mDataBase;
    private Context mContext = null;

    public DataBase(Context context) {
            super(context, DB_NAME, null, 10);

            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
            File file = new File(DB_PATH+"MyDB.db");
            if(file.exists())
                openDataBase();
            this.mContext = context;
    }

    public void openDataBase() {
            String myPath = DB_PATH + DB_NAME;
            mDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void copyDataBase() throws IOException {
            try {
                InputStream myInput = mContext.getAssets().open(DB_NAME);
                String outputFileName = DB_PATH + DB_NAME;
                OutputStream myOutput = new FileOutputStream(outputFileName);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0)
                    myOutput.write(buffer, 0, length);

                myOutput.flush();
                myOutput.close();
                myInput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private boolean checkDataBase() {
            SQLiteDatabase tempDB = null;
            try {
                String myPath = DB_PATH + DB_NAME;
                tempDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
            if (tempDB != null)
                tempDB.close();
            return tempDB != null ? true : false;
    }

    public void createDataBase() throws IOException {
            boolean isDBExists = checkDataBase();
            if (isDBExists) {

            } else {
                this.getReadableDatabase();
                try {
                    copyDataBase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    //Insert Name and Score to Ranking Table
    public void insertNameScore(int score, String name) {
        String query = "INSERT INTO Ranking(Score, Name) VALUES('" + score + "', '"+name+"')";
        mDataBase.execSQL(query);
    }




    //Get Score and sort ranking
    public List<Ranking> getRanking() {
        List<Ranking> listRanking = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM Ranking Order By Score DESC;", null);
            if (c == null) return null;
            c.moveToNext();
            do {
                int Id = c.getInt(c.getColumnIndex("Id"));
                int Score = c.getInt(c.getColumnIndex("Score"));
                String Name = c.getString(c.getColumnIndex("Name"));


                Ranking ranking = new Ranking(Id, Score, Name);

                listRanking.add(ranking);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return listRanking;

    }


}
