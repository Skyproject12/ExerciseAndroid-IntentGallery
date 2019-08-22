package com.example.intentgallery;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    ImageView imagePengguna;
    Button buttonImage;
    int intentToActivity=1;
    String pathToFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagePengguna= findViewById(R.id.image_pengguna);
        buttonImage= findViewById(R.id.button_pengguna);
        imagePengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,intentToActivity);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK && resultCode==1){
            Uri pickImage= data.getData();
            String [] filePath= {MediaStore.Images.Media.DATA};
            Cursor cursor= getContentResolver().query(pickImage, filePath, null, null);
            cursor.moveToFirst();
            String imagePath= cursor.getString(cursor.getColumnIndex(filePath[0]));
            BitmapFactory.Options options= new BitmapFactory.Options();
            options.inPreferredConfig= Bitmap.Config.ARGB_8888;
            Bitmap bitmap= BitmapFactory.decodeFile(imagePath, options);
            imagePengguna.setImageBitmap(bitmap);
        }
    }
}
