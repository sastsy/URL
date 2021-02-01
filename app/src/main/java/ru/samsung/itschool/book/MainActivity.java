package ru.samsung.itschool.book;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;

public class MainActivity extends AppCompatActivity {
    Character manager;
    Story story;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new Character();
        story = new Story(count);

        updateStatus();
    }


    private void go(int i) {
        story.go(i + 1);
        updateStatus();
        if (story.isEnd()) {
            if (manager.R + manager.I + manager.E >= 150) {
                ++count;
            }
            else {
                count += 2;
            }
            story = new Story(count);
        }
    }

    private void updateStatus() {

        manager.R += story.current_situation.dR;
        manager.I += story.current_situation.dI;
        manager.E += story.current_situation.dE;

        ((TextView) findViewById(R.id.status)).
                setText("Ответственность:" + manager.R + "\nИнтеллект:" + manager.I + "\nЭмпатия:" + manager.E);

        ((TextView) findViewById(R.id.title)).
                setText(story.current_situation.subject);

        ((TextView) findViewById(R.id.desc)).
                setText(story.current_situation.text);
        ((LinearLayout) findViewById(R.id.layout)).removeAllViews();

        for (int i = 0; i < story.current_situation.direction.length; i++) {
            Button b = new Button(this);
            b.setText(Integer.toString(i + 1));
            final int buttonId = i;

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    go(buttonId);

                }
            });
            ((LinearLayout) findViewById(R.id.layout)).addView(b);
        }

        if (story.current_situation.direction.length == 0 && count < 4) {
            Button b = new Button(this);
            b.setText("Далее");

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ++count;
                    story = new Story(count);
                    updateStatus();
                }
            });
            ((LinearLayout) findViewById(R.id.layout)).addView(b);
        }
    }

}
