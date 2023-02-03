package jp.ac.kadai08_ih13b_15;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Spinnerアダプタの準備
    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ボタンアクションのじゅんっび
        Button registerBtn = findViewById(R.id.register);
        Button deleteBtn = findViewById(R.id.delete);
        Button allDeleteBtn = findViewById(R.id.allDelete);

        //Spinnerのインスタンス化
        Spinner spinner = findViewById(R.id.spinner);

        //Spinner用のアダプタとしてインスタンス化
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item);
        //spinnerの見せ方
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //アダプタアとの接続
        spinner.setAdapter(adapter);

        //adapterへの初期値(dummy)の設定
        adapter.add("----------------登録内容----------------");

        //登録処理
        registerBtn.setOnClickListener(view -> register());

        //削除処理
        deleteBtn.setOnClickListener(view -> delete());

        //全権削除
        allDeleteBtn.setOnClickListener(view -> allDelete());

//        //起動時にSpinnerに格納する
//        //それにより最初の選択状態をなくす
//        SharedPreferences sharedPreferences =
//                getSharedPreferences("pref_name", MODE_PRIVATE);
//        String savedValue = sharedPreferences.getString("key", "");
//
//        spinner = findViewById(R.id.spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
//
//        if (!savedValue.equals("")) {
//            adapter.add(savedValue);
//        }
//
//        spinner.setAdapter(adapter);

        //Spinnerの選択時の処理→EditTextに選択内容を表示すること
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                //部品の特定
                Spinner spinner = (Spinner) adapterView;
                //選択された場所の内容を取得する
                String title = (String) spinner.getSelectedItem();

                //選択内夜をEdintTextに表示する
                EditText etTitle = findViewById(R.id.title);
                etTitle.setText(title);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //editTextの内容を取得し、sharePreferencesに登録する
    private void register(){
        //EditTextをインスタンス化し、内容（）を取得する
        EditText etTitle = findViewById(R.id.title);
        EditText etContent = findViewById(R.id.content);
        String title = etTitle.getText().toString();
        String contents = etContent.getText().toString();

        //入力内容があるのか否かをチェックする
        if (title != null && !title.isEmpty() && contents != null && !contents.isEmpty()) {
            //入力値がある場合
            //sharedPreferencesへの登録
            SharedPreferences sharedPreferences = getSharedPreferences("memo",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(title,contents);

            //登録内容のコミット
            editor.commit();

            //スピナーへの登録
            adapter.add(title);

            //Toastで登録した旨を表示し、EditTextの内容をクリアする
            Toast.makeText(MainActivity.this, "登録しました", Toast.LENGTH_SHORT).show();
            etTitle.setText("");
            etContent.setText("");
        } else {
            //入力値がない場合
            Toast.makeText(this, "何か入力して下さい", Toast.LENGTH_SHORT).show();
        }

    }

    //　editTextの内容を取得し、sharePreferencesに登録する
    private void delete(){
        //Spinnerおよびsharedpreferencesから削除
        //spinnerからの削除
        //editTextxの文字列を取得し、削除する
        EditText etTitle = findViewById(R.id.title);

        adapter.remove(etTitle.getText().toString());

        //sharedPreferencesから削除
        SharedPreferences sharedPreferences = getSharedPreferences("memo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(etTitle.getText().toString());
        editor.commit();

        Toast.makeText(MainActivity.this,"削除しました",Toast.LENGTH_SHORT).show();

    }

    //全権削除
    private void allDelete(){
        //SharedPreferencesない削除
        SharedPreferences sharedPreferences =
                getSharedPreferences("pref_name", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        //Spinnerない削除
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.clear();
        spinner.setAdapter(adapter);

        //adapterへの初期値(dummy)の設定
        adapter.add("----------------登録内容----------------");
    }


}