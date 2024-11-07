package com.example.gra_w_kosci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView[] kostki = new TextView[5];
    private TextView wynik, wynik_caly, l_rzuty;
    private Button rzut, resetButton;
    private int caly_wynik = 0;
    private int rolls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        kostki[0] = findViewById(R.id.kostka1);
        kostki[1] = findViewById(R.id.kostka2);
        kostki[2] = findViewById(R.id.kostka3);
        kostki[3] = findViewById(R.id.kostka4);
        kostki[4] = findViewById(R.id.kostka5);

        wynik = findViewById(R.id.wynik);
        wynik_caly = findViewById(R.id.wynik_caly);
        l_rzuty = findViewById(R.id.l_rzuty);

        rzut = findViewById(R.id.rzut);
        resetButton = findViewById(R.id.resetButton);

        rzut.setOnClickListener(v -> losowanie());
        resetButton.setOnClickListener(v -> resetGame());
    }

    private void losowanie() {
        Random random = new Random();
        int[] diceResults = new int[5];
        int[] counts = new int[6];


        for (int i = 0; i < 5; i++) {
            diceResults[i] = random.nextInt(6) + 1;
            kostki[i].setText(String.valueOf(diceResults[i]));
            counts[diceResults[i] - 1]++;
        }


        int wynik_l = 0;
        for (int i = 0; i < 6; i++) {
            if (counts[i] > 1) {
                wynik_l += (i + 1) * counts[i];
            }
        }

        updateWynik(wynik_l);
        wynik.setText("Wynik tego losowania: " + wynik_l);
        updatel_rzuty();
    }

    private void resetGame() {
        for (TextView diceView : kostki) {
            diceView.setText("?");
        }
        caly_wynik = 0;
        rolls = 0;
        wynik.setText("Wynik tego losowania: 0");
        wynik_caly.setText("Wynik gry: 0");
        l_rzuty.setText("Liczba rzutów: 0");
    }

    private void updateWynik(int nowy_wynik) {
        caly_wynik += nowy_wynik;
        wynik_caly.setText("Wynik gry: " + caly_wynik);
    }

    private void updatel_rzuty() {
        rolls++;
        l_rzuty.setText("Liczba rzutów: " + rolls);
    }
}