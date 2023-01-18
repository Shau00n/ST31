package jp.ac.kadai07_ih13b_15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionMainActivity extends AppCompatActivity {

    private int selectedAnswer = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_main);

        Button checkTheAnswerBtn = findViewById(R.id.selected_answer);
        checkTheAnswerBtn.setOnClickListener(view -> checkTheAnswer());




        TextView tvExamination = findViewById(R.id.examination_sentence);
        RadioGroup radioGroup = findViewById(R.id.answer_group);
        RadioButton[] answer = new RadioButton[4];
        answer[0] = findViewById(R.id.ans1);
        answer[1] = findViewById(R.id.ans2);
        answer[2] = findViewById(R.id.ans3);
        answer[3] = findViewById(R.id.ans4);

        tvExamination.setText("藩を止めて府県を置いた政治改革を何というか？");
        answer[0].setText("廃藩置県");
        answer[1].setText("淵府県");
        answer[2].setText("首都整備計画");
        answer[3].setText("知事派遣令");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

//                Toast.makeText(QuestionMainActivity.this, "", Toast.LENGTH_SHORT).show();
                String msg = "";
                switch(checkedId){
                    case R.id.ans1:
                        selectedAnswer = 1;
                        msg = "1番が選ばれた";
                        break;
                    case R.id.ans2:
                        selectedAnswer = 2;
                        msg = "2番が選ばれた";
                        break;
                    case R.id.ans3:
                        selectedAnswer = 3;
                        msg = "3番が選ばれた";
                        break;
                    case R.id.ans4:
                        selectedAnswer = 4;
                        msg = "4番が選ばれた";
                        break;
                    default:
                        msg = "どれも選んでいない";
                }
                Toast.makeText(QuestionMainActivity.this,msg, Toast.LENGTH_SHORT).show();
            }
        });


        Button backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> back());
    }

    private void checkTheAnswerBtn(){
        Intent intent = new Intent(QuestionMainActivity.this,CheckAnswerActivity.class);

        intent.putExtra("ans",selectedAnswer);

        intent.putExtra("correctAnswer",1);


        startActivity(intent);
    }

    private void checkTheAnswer(){
        Intent intent = new Intent(QuestionMainActivity.this,CheckAnswerActivity.class);
        startActivity(intent);
    }

    private void back(){
        finish();
    }
}