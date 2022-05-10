package sv.edu.udb.pizzeria_forkify.OrderPizza.Activities;
import static sv.edu.udb.pizzeria_forkify.OrderPizza.Activities.RecipeModification.ingredientesList;
import static sv.edu.udb.pizzeria_forkify.OrderPizza.Activities.RecipeModification.pasosList;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import sv.edu.udb.pizzeria_forkify.R;

public class PasosIngredientesMod extends AppCompatActivity {

    TextView tv_pasos_ingredientes_mod,tv_btnAgregar,tv_btnGuardar;
    ListView lv_ingredientes_pasos;
    ArrayAdapter<String> mAdapterIngre;
    ArrayAdapter<String> mAdapterPasos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasos_ingredientes_mod);
        hooks();

        mAdapterIngre = new ArrayAdapter<>(PasosIngredientesMod.this, R.layout.mod_list_items, ingredientesList);
        mAdapterPasos = new ArrayAdapter<>(PasosIngredientesMod.this, R.layout.mod_list_items, pasosList);


        if (getIntent().getStringExtra("type").contains("pasos")){
            lv_ingredientes_pasos.setAdapter(mAdapterPasos);
            mAdapterPasos.notifyDataSetChanged();
        }else {
            lv_ingredientes_pasos.setAdapter(mAdapterIngre);
            mAdapterIngre.notifyDataSetChanged();
        }



        lv_ingredientes_pasos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int list_in, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PasosIngredientesMod.this);
                builder.setMessage("Desea Eliminiar item?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (getIntent().getStringExtra("type").contains("pasos")){

                                    pasosList.remove(list_in);
                                    mAdapterPasos.notifyDataSetChanged();
                                }else {
                                    ingredientesList.remove(list_in);
                                    mAdapterIngre.notifyDataSetChanged();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });

        lv_ingredientes_pasos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


    }

    private void hooks() {
        tv_pasos_ingredientes_mod = findViewById(R.id.tv_pasos_ingredientes_mod);
        lv_ingredientes_pasos = findViewById(R.id.lv_ingredientes_pasos);

        tv_btnAgregar = findViewById(R.id.tv_btnAgregar);
        tv_btnGuardar = findViewById(R.id.tv_btnGuardar);
    }
}