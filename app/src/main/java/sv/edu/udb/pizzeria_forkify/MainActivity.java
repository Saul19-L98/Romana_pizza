package sv.edu.udb.pizzeria_forkify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import sv.edu.udb.pizzeria_forkify.OrderPizza.LandingMenuActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, LandingMenuActivity.class);
        startActivity(intent);
    }
}