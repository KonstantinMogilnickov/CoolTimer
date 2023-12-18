package com.phttproduction.cool_timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarTimer;
    private Button startTimerButton;
    private TextView textViewSelectedTime;
    private TextView textViewRemainingTime;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarTimer = findViewById(R.id.seekBarTimer);
        startTimerButton = findViewById(R.id.startTimerButton);
        textViewSelectedTime = findViewById(R.id.textViewSelectedTime);
        textViewRemainingTime = findViewById(R.id.textViewRemainingTime);

        startTimerButton.setOnClickListener(v -> startTimer());

        seekBarTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Обработка изменения значения SeekBar
                textViewSelectedTime.setText("Выбранное время: " + progress + " секунд");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Начало отслеживания касания SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Конец отслеживания касания SeekBar
            }
        });
    }

    private void startTimer() {
        if (!isTimerRunning) {
            isTimerRunning = true;

            int totalTimeInSeconds = seekBarTimer.getProgress(); // Получение времени из SeekBar
            seekBarTimer.setEnabled(false); // Блокировка SeekBar во время отсчета

            countDownTimer = new CountDownTimer(totalTimeInSeconds * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // Вызывается каждую секунду во время отсчета
                    textViewRemainingTime.setText(millisUntilFinished / 1000 + " секунд");
                }

                @Override
                public void onFinish() {
                    // Вызывается по завершении отсчета
                    textViewRemainingTime.setText(" 0 секунд");
                    Toast.makeText(MainActivity.this, "Таймер завершен!", Toast.LENGTH_SHORT).show();
                    resetTimer();
                }
            };
            countDownTimer.start(); // Запуск таймера
            startTimerButton.setText("Стоп");
        }
        else {
            resetTimer();
        }



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {

            Intent intent = new Intent(this, settings_activity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetTimer() {
        isTimerRunning = false;

        // Остановка таймера
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        // Установка значения SeekBar обратно на 30 секунд
        seekBarTimer.setProgress(30);
        textViewSelectedTime.setText("Выбранное время: 30 секунд");

        // Изменение текста кнопки на "Старт"
        startTimerButton.setText("Старт");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Остановка таймера при уничтожении активности
        }
    }
}