package com.example.youtubethumbnailapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView video1;

    public Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize variables
        video1 = findViewById(R.id.video1);

        video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
    }//end of onCreate

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && null != data && data.getData()!=null){
            imageUri = data.getData();
            video1.setImageURI(imageUri);
            addPicture();
        }
    }

    private void addPicture(){
        Picasso.get()
                .load(imageUri)
                .fit().centerCrop()
                .into(video1);
    }
}