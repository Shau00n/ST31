package jp.ac.kadai07_ih13b_15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class CheckAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_answer);

        int selectedAnswer = getIntent().getIntArrayExtra("ans",999);
        int correctAnswer = getIntent().getByteExtra("correctAnswer",999);

        Toast.makeText(CheckAnswerActivity.this, String.valueOf(selectedAnswer), Toast.LENGTH_SHORT).show();

        if(selectedAnswer == correctAnswer){
            Toast.makeText(this, "正解!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "不正解!", Toast.LENGTH_SHORT).show();
        }

        Button backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(view -> back());

    }
    private void back(){
        finish();
    }
}