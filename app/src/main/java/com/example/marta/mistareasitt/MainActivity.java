package com.example.marta.mistareasitt;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marta.mistareasitt.db.ControladorDB;

public class MainActivity extends AppCompatActivity {

    ControladorDB controladorDB;
    private ArrayAdapter<String> mAdapter;
    ListView listViewTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controladorDB = new ControladorDB(this);
        listViewTareas = (ListView) findViewById(R.id.listaTareas);
        actualizarUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final EditText cajaTexto = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Nueva Tarea")
                .setMessage("¿Qué te gustaría hacer?")
                .setView(cajaTexto)
                .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String tarea = cajaTexto.getText().toString();
                        controladorDB.addTarea(tarea);
                        actualizarUI();

                        Toast toast1 = new Toast(getApplicationContext());
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_layout,
                                (ViewGroup) findViewById(R.id.lytLayout));
                        TextView txtMsg =
                                (TextView)layout.findViewById(R.id.txtMensaje);
                        txtMsg.setText("Tarea añadida");
                        toast1.setDuration(Toast.LENGTH_SHORT);
                        toast1.setView(layout);
                        toast1.setGravity(Gravity.CENTER,0,0);
                        toast1.show();

                    }
                })

                .setNegativeButton("Cancelar", null)
                .create();
        dialog.show();


        return super.onOptionsItemSelected(item);
    }

    private void actualizarUI(){
        if(controladorDB.numeroRegistros()==0){
            listViewTareas.setAdapter(null);
        }else{
            mAdapter = new ArrayAdapter<>(this, R.layout.item_tarea,R.id.task_title,controladorDB.obtenerTareas());
            listViewTareas.setAdapter(mAdapter);
        }

    }

    public void borrarTarea(View view){
        View parent = (View) view.getParent();
        TextView tareaTextView = (TextView) parent.findViewById(R.id.task_title);
        String tarea = tareaTextView.getText().toString();
        controladorDB.borrarTarea(tarea);
        actualizarUI();

        Toast toast2 = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.lytLayout2));
        TextView txtMsg =
                (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText("Tarea borrada");
        toast2.setDuration(Toast.LENGTH_SHORT);
        toast2.setView(layout);
        toast2.setGravity(Gravity.CENTER,0,0);
        toast2.show();
    }

}
