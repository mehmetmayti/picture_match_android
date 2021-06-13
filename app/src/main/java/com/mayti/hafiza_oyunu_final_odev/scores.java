package com.mayti.hafiza_oyunu_final_odev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class scores extends AppCompatActivity {

    ListView listView;
    Database db=new Database(this);
    List<String> list=new ArrayList<String>();
    List<Game> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        listView=findViewById(R.id.listViewScores);

        db.getGameList().forEach(item->{
            String game="Oyun Modu : "+item.gameMode+"  "+" Hamle Sayısı : "+item.move+"\n"+
                    "Oyun Tarihi : "+item.date;
            list.add(game);

        });


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(scores.this, android.R.layout.simple_expandable_list_item_1,list);
        listView.setAdapter(adapter);




    }
}