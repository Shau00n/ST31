package jp.ac.kadai07_ih13b_15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = findViewById(R.id.quiz_man_maru);
        iv.setOnClickListener(view -> moveQuestionActivity());

        TextView tv = findViewById(R.id.tap_to_back);
        tv.setOnClickListener(view -> moveQuestionActivity());

    }

    private void moveQuestionActivity(){
        Intent intent = new Intent(MainActivity.this,QuestionMainActivity.class);
        startActivity(intent);
    }
}