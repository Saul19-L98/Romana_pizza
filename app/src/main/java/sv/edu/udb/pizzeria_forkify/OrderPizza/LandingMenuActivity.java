package sv.edu.udb.pizzeria_forkify.OrderPizza;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sv.edu.udb.pizzeria_forkify.R;
import sv.edu.udb.pizzeria_forkify.databinding.ActivityLandingMenuBinding;


public class LandingMenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityLandingMenuBinding binding;
    public static FirebaseAuth myAuth = FirebaseAuth.getInstance();
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refMixto = database.getReference()
            .child("RecetarioForkify")
            .child("Categoria")
            .child("Mixto");
    public static DatabaseReference refUser = database.getReference()
            .child("RecetarioForkify")
            .child("Usuarios");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandingMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarLandingMenu.toolbar);
        binding.appBarLandingMenu.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_menu, R.id.nav_gallery, R.id.nav_bookmarks,R.id.nav_admin)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_landing_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //Metodo de ingreso de inforacion en la seccion de perfil del navigation
        //Drawer

        View nav = navigationView.getHeaderView(0);
        TextView Username= (TextView) nav.findViewById(R.id.username);
        TextView UserEmail= (TextView) nav.findViewById(R.id.user_mail);


        //Metodo de obtencion del Nombre usuario a travez del nickname del correo
        String userEmail= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        int index = userEmail.indexOf('@');
        userEmail = userEmail.substring(0,index);
        Username.setText(userEmail);
        UserEmail.setText(userEmail);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_landing_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}