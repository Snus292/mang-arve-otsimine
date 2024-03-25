package com.example.mangarveotsimine;

import android.widget.Toast;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    TextView tvInfo;

    EditText etInput;
    Button bControl;
    int guess;
    boolean gameFinished;

    int attempts = 0;
    // + количество попыток

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView)findViewById(R.id.textView);
        etInput = (EditText)findViewById(R.id.editText);
        bControl = (Button)findViewById(R.id.button);

        guess = (int)(Math.random()*10);
        gameFinished = false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //вызван при нажатии на кнопку
    public void sisend(View v){
        //завершена ли уже игра?
        if (!gameFinished){
            //хранения введенного числа
            int inp;
            //увеличение счетчика попыток
            attempts++;


            try {
                inp = Integer.parseInt(etInput.getText().toString());
                if (inp < 0 || inp > 10) {
                    // число вне диапозона
                    Toast.makeText(getApplicationContext(), "Число должно быть от 0 до 10", Toast.LENGTH_SHORT).show();
                    return;
                }

            } catch (NumberFormatException e) {
                // значение не является числом
                Toast.makeText(getApplicationContext(), "Пожалуйста, введите число", Toast.LENGTH_SHORT).show();
                return;
            }
            //соответствует ли введенное число загаданному
            //меньше загаданного
            if (inp > guess)
                tvInfo.setText(getResources().getString(R.string.ahead));
            //больше загаданного
            if (inp < guess)
                tvInfo.setText(getResources().getString(R.string.behind));
            //число угадано
            if (inp == guess)
            {


                tvInfo.setText(getResources().getString(R.string.hit));
                //Изменение текста кнопки
                bControl.setText(getResources().getString(R.string.play_more));
                //флаг завершения
                gameFinished = true;
                //поиск тектового поля
                TextView textViewAttempts = findViewById(R.id.textViewAttempts);
                //отображаем попыток
                textViewAttempts.setText(getResources().getString(R.string.attempts) + attempts);
            } else {
                //если число не угадано
            }
        }
        else
        {
            guess = (int)(Math.random()*10); // новое число
            bControl.setText(getResources().getString(R.string.input_value)); // ввод
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            TextView attemptsTextView = findViewById(R.id.textViewAttempts); очистка поля коллисества попыток

            attemptsTextView.setText("");
            attempts = 0; сборс счетчика
            gameFinished = false; флаг завершения
        }
        etInput.setText("");
    }

}