package com.example.excusas;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.window.SplashScreen;

import com.example.excusas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int intro = (int) Math.floor(Math.random() * 10 + 1);
                int chivoExpiatorio = (int) Math.floor(Math.random() * 10 + 1);
                int retraso = (int) Math.floor(Math.random() * 10 + 1);

                switch (intro) {
                    case 1:
                        binding.intro.setText("Lo siento, no puedo ir, ");
                        break;
                    case 2:
                        binding.intro.setText("Por favor, perdona mi ausencia, ");
                        break;
                    case 3:
                        binding.intro.setText("Esto va a sonar loco, pero ");
                        break;
                    case 4:
                        binding.intro.setText("Entiende esto: ");
                        break;
                    case 5:
                        binding.intro.setText("No puedo ir porque ");
                        break;
                    case 6:
                        binding.intro.setText("Se que vas a odiarme, pero ");
                        break;
                    case 7:
                        binding.intro.setText("Estaba con mis cosas y BOOM! ");
                        break;
                    case 8:
                        binding.intro.setText("Disculpa por el inconveniente, pero ");
                        break;
                    case 9:
                        binding.intro.setText("Desgraciadamente no puedo atender, ");
                        break;
                    case 10:
                        binding.intro.setText("Esto va a sonar como una excusa, pero ");
                        break;
                }

                switch (chivoExpiatorio) {
                    case 1:
                        binding.chivoExpiatorio.setText("mi sobrino ");
                        break;
                    case 2:
                        binding.chivoExpiatorio.setText("el fantasma de hitler ");
                        break;
                    case 3:
                        binding.chivoExpiatorio.setText("el papa ");
                        break;
                    case 4:
                        binding.chivoExpiatorio.setText("mi ex ");
                        break;
                    case 5:
                        binding.chivoExpiatorio.setText("la banda del pueblo ");
                        break;
                    case 6:
                        binding.chivoExpiatorio.setText("Pedro Piqueras ");
                        break;
                    case 7:
                        binding.chivoExpiatorio.setText("un payaso triste ");
                        break;
                    case 8:
                        binding.chivoExpiatorio.setText("el actor de 'Salvados por la campana' ");
                        break;
                    case 9:
                        binding.chivoExpiatorio.setText("un equipo profesional de petanca ");
                        break;
                    case 10:
                        binding.chivoExpiatorio.setText("mi cita de Tinder ");
                        break;
                }
                switch (retraso) {
                    case 1:
                        binding.desenlace.setText("se acaba de cagar en mi cama.");
                        break;
                    case 2:
                        binding.desenlace.setText("ha muerto delante mía.");
                        break;
                    case 3:
                        binding.desenlace.setText("no paraba de contarme chistes de 'toc toc'.");
                        break;
                    case 4:
                        binding.desenlace.setText("esta sufriendo un ataque de nervios.");
                        break;
                    case 5:
                        binding.desenlace.setText("me ha dado sifilis.");
                        break;
                    case 6:
                        binding.desenlace.setText("ha echado limonada en el depósito de gasolina.");
                        break;
                    case 7:
                        binding.desenlace.setText("me ha apuñalado.");
                        break;
                    case 8:
                        binding.desenlace.setText("ha encontrado mi caja de dientes humanos.");
                        break;
                    case 9:
                        binding.desenlace.setText("ha robado mi bicicleta.");
                        break;
                    case 10:
                        binding.desenlace.setText("ha posteado mis desnudos en Instagram.");
                        break;
                }
            }
        });

    }
}