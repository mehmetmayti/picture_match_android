package com.mayti.hafiza_oyunu_final_odev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class ExpertMode extends AppCompatActivity {

    int screenImage;
    ArrayList<Integer> images=new ArrayList<Integer>();
    ArrayList<ImageView> imageViews= new ArrayList<ImageView>();
    TextView txtMove;
    int move;
    ImageView selected_1,selected_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_mode);
        txtMove=(TextView)findViewById(R.id.txtMove);
        screenImage=16;
        move=0;
        getImageViewUI();
        getImageDrawable();
        ListRandom();
        AtomicInteger i= new AtomicInteger();
        imageViews.forEach(imageView -> {
            imageView.setTag(images.get(i.get()));
            i.set(i.get() + 1);
            imageView.setOnClickListener(this::imageViewClick);
        });

    }
    private void ListRandom() {
        Collections.shuffle(images);
    }
    private void imageViewClick(View view){
        ImageView img=(ImageView) view;
        img.setImageResource(Integer.parseInt(img.getTag().toString()));
        onSelect(img);

    }

    private void onSelect(ImageView img) {
        if (selected_1==null){
            selected_1=img;
        }else{
            selected_2=img;
            (new Handler()).postDelayed(this::imageControl,1000);
        }

    }

    private void imageControl() {
        move=move+1;
        if (selected_1.getTag().toString().equals(selected_2.getTag().toString())){
            selected_1.setVisibility(View.INVISIBLE);
            selected_2.setVisibility(View.INVISIBLE);
            txtMove.setText(String.valueOf(move));
            screenImage=screenImage-2;
            cleanSelectedItemContent();
        }else{
            txtMove.setText(String.valueOf(move));
            cleanSelectedItemContent();
        }

    }
    private void cleanSelectedItemContent() {
        selected_1.setImageResource(R.drawable.qus_mark);
        selected_2.setImageResource(R.drawable.qus_mark);
        selected_1=null;
        selected_2=null;
        if (screenImage==0){
            setDatabase();
            buildAlertDialog();
        }
    }

    private void setDatabase() {
        Database db=new Database(ExpertMode.this);
        Game game=new Game();
        game.gameMode="Zor";
        game.move=String.valueOf(move);
        game.date= String.valueOf(Calendar.getInstance().getTime());
        db.gameAdded(game);
    }

    private void buildAlertDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ExpertMode.this);
        builder.setIcon(R.drawable.tropy);
        builder.setTitle("Tebrikler!!!");
        builder.setMessage(String.valueOf(move)+" Hamlede Oyunu Bitirdiniz");
        builder.setPositiveButton("Yeni Oyun", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(ExpertMode.this,EasyMode.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Ana Ekrana Dön", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(ExpertMode.this,MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNeutralButton("Oyunda Çık", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        builder.show();
    }


    private void getImageDrawable() {
        images.add(R.drawable.img_1);
        images.add(R.drawable.img_2);
        images.add(R.drawable.img_3);
        images.add(R.drawable.img_4);
        images.add(R.drawable.img_5);
        images.add(R.drawable.img_6);
        images.add(R.drawable.img_7);
        images.add(R.drawable.img_8);
        images.add(R.drawable.img_1);
        images.add(R.drawable.img_2);
        images.add(R.drawable.img_3);
        images.add(R.drawable.img_4);
        images.add(R.drawable.img_5);
        images.add(R.drawable.img_6);
        images.add(R.drawable.img_7);
        images.add(R.drawable.img_8);

    }

    private void getImageViewUI() {
        imageViews.add(findViewById(R.id.expert_img1));
        imageViews.add(findViewById(R.id.expert_img2));
        imageViews.add(findViewById(R.id.expert_img3));
        imageViews.add(findViewById(R.id.expert_img4));
        imageViews.add(findViewById(R.id.expert_img5));
        imageViews.add(findViewById(R.id.expert_img6));
        imageViews.add(findViewById(R.id.expert_img7));
        imageViews.add(findViewById(R.id.expert_img8));
        imageViews.add(findViewById(R.id.expert_img9));
        imageViews.add(findViewById(R.id.expert_img10));
        imageViews.add(findViewById(R.id.expert_img11));
        imageViews.add(findViewById(R.id.expert_img12));
        imageViews.add(findViewById(R.id.expert_img13));
        imageViews.add(findViewById(R.id.expert_img14));
        imageViews.add(findViewById(R.id.expert_img15));
        imageViews.add(findViewById(R.id.expert_img16));
    }




}