package com.example.excusas;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.res.Configuration;

import com.example.excusas.database.Core;
import com.example.excusas.util.ExcuseData;
import com.example.excusas.database.DatabaseExcuses;
import com.example.excusas.database.ExcusesDao;
import com.example.excusas.database.Intro;
import com.example.excusas.database.Outcome;
import com.example.excusas.databinding.ActivityMainBinding;
import com.example.excusas.fragments.ExcusesFragment;
import com.example.excusas.fragments.FavoriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    ActivityMainBinding binding;
    private DatabaseExcuses db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = DatabaseExcuses.getDatabase(this);
        sharedPreferences = getSharedPreferences("language",MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");
        loadExcusesFromJSON(language);
        setLocale(language);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
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
                setLocale("en");
            }
            if(item.getItemId()==R.id.language_spanish){
                setLocale("es");
            }
            return false;
        });
        popupMenu.show();
    }

    private void setLocale(String languageCode) {
        String currentLanguage = Locale.getDefault().getLanguage();

        if (!currentLanguage.equals(languageCode)) {
            Locale locale = new Locale(languageCode);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("language", languageCode);
            editor.apply();

            loadExcusesFromJSON(languageCode);
            recreate();
        }
    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            int bytesRead = 0;
            while (bytesRead < size) {
                int read = is.read(buffer, bytesRead, size - bytesRead);
                if (read == -1) {
                    break;
                }
                bytesRead += read;
            }
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
    private void loadExcusesFromJSON(String languageCode) {
        String fileName = "excuses_" + languageCode + ".json";
        String json = loadJSONFromAsset(fileName);
        Gson gson = new Gson();
        ExcuseData excuseData = gson.fromJson(json, ExcuseData.class);

        Executors.newSingleThreadExecutor().execute(() -> {
            ExcusesDao excusesDao = db.excusesDao();

            excusesDao.clearTables();

            for (String intro : excuseData.getIntros()) {
                excusesDao.insertIntro(new Intro(intro));
            }

            for (String core : excuseData.getCores()) {
                excusesDao.insertCore(new Core(core));
            }

            for (String outcome : excuseData.getOutcomes()) {
                excusesDao.insertOutcome(new Outcome(outcome));
            }
        });
    }
    public DatabaseExcuses getDatabase() {
        return db;
    }
}