package sv.edu.udb.pizzeria_forkify.OrderPizza.Activities;
import static sv.edu.udb.pizzeria_forkify.OrderPizza.Activities.RecipeModification.ingredientesList;
import static sv.edu.udb.pizzeria_forkify.OrderPizza.Activities.RecipeModification.pasosList;
import static sv.edu.udb.pizzeria_forkify.OrderPizza.LandingMenuActivity.refMixto;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

import sv.edu.udb.pizzeria_forkify.OrderPizza.model.ModelArray;
import sv.edu.udb.pizzeria_forkify.OrderPizza.model.ModelRecetas;
import sv.edu.udb.pizzeria_forkify.OrderPizza.model.ModelRecetasInsert;
import sv.edu.udb.pizzeria_forkify.R;

public class PasosIngredientesMod extends AppCompatActivity {

    EditText et_btnAgregar;
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
            tv_pasos_ingredientes_mod.setText(R.string.Pasos);
            lv_ingredientes_pasos.setAdapter(mAdapterPasos);
            mAdapterPasos.notifyDataSetChanged();
        }else {
            lv_ingredientes_pasos.setAdapter(mAdapterIngre);
            tv_pasos_ingredientes_mod.setText(R.string.Ingredientes);
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
                showModInterface(i);
            }
        });

        tv_btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasosIngredientesMod.super.onBackPressed();
            }
        });

        tv_btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().getStringExtra("type").contains("pasos")) {
                    if (!et_btnAgregar.getText().toString().isEmpty()){
                        pasosList.add(pasosList.size(),et_btnAgregar.getText().toString());
                        mAdapterPasos.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(PasosIngredientesMod.this,R.string.CampoVacio, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (!et_btnAgregar.getText().toString().isEmpty()){
                        ingredientesList.add(ingredientesList.size(),et_btnAgregar.getText().toString());
                        mAdapterIngre.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(PasosIngredientesMod.this,R.string.CampoVacio, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    private void showModInterface(int indexList) {
        Dialog dialog = new Dialog(PasosIngredientesMod.this);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialog.setContentView(R.layout.mod_alert);
        EditText editText;
        editText= dialog.findViewById(R.id.mod_item_text);

        if (getIntent().getStringExtra("type").contains("pasos")){
            editText.setText(pasosList.get(indexList));

        }else {
            editText.setText(ingredientesList.get(indexList));
        }

        //DEFINICION de acciones sobre los botones del menu mergente

        dialog.findViewById(R.id.btn_cancelar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.btn_agregar).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String mod_item=editText.getText().toString().trim();

                //actualizacion de la lista
                if (getIntent().getStringExtra("type").contains("pasos")){
                    pasosList.set(indexList,mod_item);
                    mAdapterPasos.notifyDataSetChanged();
                }else {
                    ingredientesList.set(indexList,mod_item);
                    mAdapterIngre.notifyDataSetChanged();
                }

                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void hooks() {
        tv_pasos_ingredientes_mod = findViewById(R.id.tv_pasos_ingredientes_mod);
        lv_ingredientes_pasos = findViewById(R.id.lv_ingredientes_pasos);

        et_btnAgregar = findViewById(R.id.et_btnAgregar);
        tv_btnAgregar = findViewById(R.id.tv_btnAgregar);
        tv_btnGuardar = findViewById(R.id.tv_btnGuardar);
    }
}