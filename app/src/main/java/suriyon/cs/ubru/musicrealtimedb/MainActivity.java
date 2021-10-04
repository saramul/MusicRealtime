package suriyon.cs.ubru.musicrealtimedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnvMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchView();
        bnvMusic.setOnItemSelectedListener(listener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
    }

    private NavigationBarView.OnItemSelectedListener listener =
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()) {
                        case R.id.menu_home: fragment = new FragmentHome();break;
                        case R.id.menu_add: fragment = new FragmentAdd();break;
                        case R.id.menu_search: fragment = new FragmentSearch();break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    return true;
                }
            };
    private void matchView() {
        bnvMusic = findViewById(R.id.bnv_menu);
    }
}