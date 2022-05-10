package sv.edu.udb.pizzeria_forkify.OrderPizza.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.pizzeria_forkify.OrderPizza.model.ModelRecetas;
import sv.edu.udb.pizzeria_forkify.R;

public class RecipeModification extends AppCompatActivity {

    EditText et_recipe_title,et_nopersonas,et_tiempo,et_descripion,et_imgref;
    TextView tv_ingredintes_mod,tv_pasos_mod,save_update;

    public  static ArrayList<String> ingredientesList =new ArrayList<>();
    public  static ArrayList<String> pasosList  =new ArrayList<>();


    //declaracion de una actividad para un resultado
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d("Display Recipe", "on result");
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_modification);

        Hooks();
        Setters();


        tv_ingredintes_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(RecipeModification.this,PasosIngredientesMod.class);
                intent.putExtra("type","ingredientes");
                activityResultLauncher.launch(intent);

            }
        });

        tv_pasos_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RecipeModification.this,PasosIngredientesMod.class);
                intent.putExtra("type","pasos");
                activityResultLauncher.launch(intent);
            }
        });
    }


    private void Hooks() {
    et_recipe_title=findViewById(R.id.et_recipe_title);
    et_descripion=findViewById(R.id.et_descripion);
    et_nopersonas=findViewById(R.id.et_nopersonas);
    et_tiempo=findViewById(R.id.et_tiempo);
    et_imgref=findViewById(R.id.et_imgref);
    tv_ingredintes_mod=findViewById(R.id.tv_ingredintes_mod);
    tv_pasos_mod=findViewById(R.id.tv_pasos_mod);
    save_update= findViewById(R.id.save_update);
    }

    private void Setters() {
        ModelRecetas modelRecetas =new ModelRecetas();
        modelRecetas= (ModelRecetas) getIntent().getSerializableExtra("clase");

        pasosList.addAll(modelRecetas.getPasos());
        ingredientesList.addAll(modelRecetas.getIngredientes());

        et_imgref.setText(modelRecetas.getRefImg());
        et_recipe_title.setText(modelRecetas.getTitulo());
        et_nopersonas.setText(modelRecetas.getNoPersonas().toString());
        et_tiempo.setText(modelRecetas.getTiempo());
        et_descripion.setText(modelRecetas.getDescripcion());
    }
}