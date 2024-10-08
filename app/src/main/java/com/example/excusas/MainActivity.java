package com.example.excusas;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.window.SplashScreen;

import com.example.excusas.database.Core;
import com.example.excusas.database.DatabaseExcuses;
import com.example.excusas.database.ExcusesDao;
import com.example.excusas.database.Intro;
import com.example.excusas.database.Outcome;
import com.example.excusas.databinding.ActivityMainBinding;
import com.example.excusas.fragments.ExcusesFragment;
import com.example.excusas.fragments.FavoriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private DatabaseExcuses db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        db = DatabaseExcuses.getDatabase(this);
        populateDatabaseIfNeeded(db);
        loadFragment(new ExcusesFragment());
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if(item.getItemId()==R.id.excuses){
                    loadFragment(new ExcusesFragment());
                }
                if(item.getItemId()==R.id.favorite){
                    loadFragment(new FavoriteFragment());
                }
                if(item.getItemId()==R.id.language){
                    showLanguagePopupMenu();
                }

                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
    }

    private void showLanguagePopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.language));
        popupMenu.getMenuInflater().inflate(R.menu.language_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            if(item.getItemId()==R.id.language_english){
                // Cambiar el idioma a inglés
            }
            if(item.getItemId()==R.id.language_spanish){
                // Cambiar el idioma a español
            }
            return false;
        });
        popupMenu.show();
    }

    public void populateDatabaseIfNeeded(DatabaseExcuses db) {
        // Ejecutar en un hilo aparte para evitar el bloqueo de la UI
        Executors.newSingleThreadExecutor().execute(() -> {
            ExcusesDao excusesDao = db.excusesDao();

            // Comprueba si las tablas están vacías
            if (excusesDao.countIntro() == 0) {
                // Inserta los datos de introducción
                List<Intro> introList = Arrays.asList(
                        new Intro("Lo siento, no puedo ir,"),
                        new Intro("Por favor, perdona mi ausencia,"),
                        new Intro("Esto va a sonar loco, pero"),
                        new Intro("Entiende esto:"),
                        new Intro("No puedo ir porque"),
                        new Intro("Se que vas a odiarme, pero"),
                        new Intro("Estaba con mis cosas y BOOM!"),
                        new Intro("Disculpa por el inconveniente, pero"),
                        new Intro("Desgraciadamente no puedo atender,"),
                        new Intro("Esto va a sonar como una excusa, pero")
                );
                excusesDao.insertAllIntro(introList);
            }

            if (excusesDao.countNudo() == 0) {
                // Inserta los datos de nudo
                List<Core> coreList = Arrays.asList(
                        new Core("mi sobrino"),
                        new Core("el fantasma de hitler"),
                        new Core("el papa"),
                        new Core("mi ex"),
                        new Core("la banda del pueblo"),
                        new Core("Pedro Piqueras"),
                        new Core("un payaso triste"),
                        new Core("el actor de 'Salvados por la campana'"),
                        new Core("un equipo profesional de petanca"),
                        new Core("mi cita de Tinder")
                );
                excusesDao.insertAllCore(coreList);
            }

            if (excusesDao.countDesenlace() == 0) {
                List<Outcome> outcomeList = Arrays.asList(
                        new Outcome("se acaba de cagar en mi cama."),
                        new Outcome("ha muerto delante mía."),
                        new Outcome("no paraba de contarme chistes de 'toc toc'."),
                        new Outcome("esta sufriendo un ataque de nervios."),
                        new Outcome("me ha dado sifilis."),
                        new Outcome("ha echado limonada en el depósito de gasolina."),
                        new Outcome("me ha apuñalado."),
                        new Outcome("ha encontrado mi caja de dientes humanos."),
                        new Outcome("ha robado mi bicicleta."),
                        new Outcome("ha posteado mis desnudos en Instagram.")
                );
                excusesDao.insertAllOutcome(outcomeList);
            }
        });
    }
    public DatabaseExcuses getDatabase() {
        return db;
    }
}