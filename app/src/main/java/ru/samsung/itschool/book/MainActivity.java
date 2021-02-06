package ru.samsung.itschool.book;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button start_button;
    EditText textEdit;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_button = (Button) findViewById(R.id.start_button);
        textEdit = (EditText) findViewById(R.id.textEdit);
        imageView = (ImageView) findViewById(R.id.image);
        final Thread thread = new Thread(new LoadImage());

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Делаем операцию в потоке", Toast.LENGTH_SHORT).show();
                thread.start();
            }
        });
    }

    private class LoadImage implements Runnable {
        @Override
        public void run() {
            try {
                URL url = new URL(textEdit.getText().toString());
                InputStream image = (InputStream) url.getContent();
                Bitmap myImage = BitmapFactory.decodeStream(image);
                imageView.setImageBitmap(myImage);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
