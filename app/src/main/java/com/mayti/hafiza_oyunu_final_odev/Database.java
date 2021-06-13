package com.mayti.hafiza_oyunu_final_odev;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int version=1;
    private static final String databaseName="hafiza_oyunu";


    private static final String tableName="scores";
    private static final String gameId="gameId";
    private static final String gameMode="gameMode";
    private static final String move="move";
    private static final String date="date";


    public Database(@Nullable Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table scores(gameId integer primary key autoincrement,gameMode text,move text,date text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void gameAdded(Game game){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("gameMode",game.gameMode);
        values.put("move",game.move);
        values.put("date",game.date);
        db.insert("scores",null,values);
        db.close();
    }
    public List<Game> getGameList(){
        String[] columns={"gameId","gameMode","move","date"};
        ArrayList<Game> games=new ArrayList<Game>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("scores",columns,null,null,null,null,null);

        while (cursor.moveToNext()){
            Game game=new Game();
            game.gameId=cursor.getString(cursor.getColumnIndex("gameId"));
            game.gameMode=cursor.getString(cursor.getColumnIndex("gameMode"));
            game.move=cursor.getString(cursor.getColumnIndex("move"));
            game.date=cursor.getString(cursor.getColumnIndex("date"));
            games.add(game);
        }
        return games;
    }




}
