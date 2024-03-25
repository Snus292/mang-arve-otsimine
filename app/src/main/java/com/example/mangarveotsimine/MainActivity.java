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
    public void sisend(View v){
        if (!gameFinished){
            attempts++;

            int inp=Integer.parseInt(etInput.getText().toString());
            try {
                inp = Integer.parseInt(etInput.getText().toString());
                if (inp < 0 || inp > 10) {
                    // Выводим предупреждение, что число должно быть в диапазоне от 0 до 10
                    Toast.makeText(getApplicationContext(), "Число должно быть от 0 до 10", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                // Выводим предупреждение, если введенное значение не является числом
                Toast.makeText(getApplicationContext(), "Пожалуйста, введите число", Toast.LENGTH_SHORT).show();
                return;
            }
            if (inp > guess)
                tvInfo.setText(getResources().getString(R.string.ahead));
            if (inp < guess)
                tvInfo.setText(getResources().getString(R.string.behind));
            if (inp == guess)
            {
                //количество попыток в сообщение
                tvInfo.setText(getResources().getString(R.string.hit));
                bControl.setText(getResources().getString(R.string.play_more));
                gameFinished = true;
                TextView textViewAttempts = findViewById(R.id.textViewAttempts);
                textViewAttempts.setText(getResources().getString(R.string.attempts) + attempts);
            } else {
            }
        }
        else
        {
            guess = (int)(Math.random()*10);
            bControl.setText(getResources().getString(R.string.input_value));
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            TextView attemptsTextView = findViewById(R.id.textViewAttempts);
            attemptsTextView.setText("");
            attempts = 0;
            gameFinished = false;
        }
        etInput.setText("");
    }

}