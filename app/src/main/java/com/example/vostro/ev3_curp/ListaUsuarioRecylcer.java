package com.example.vostro.ev3_curp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.vostro.ev3_curp.Adapter.ListaUsuarioAdapter;
import com.example.vostro.ev3_curp.DataBase.ConexionSQLHelper;
import com.example.vostro.ev3_curp.DataBase.UsuarioContract;
import com.example.vostro.ev3_curp.DataBase.UsuarioContract.CurpUsuarioEntry;
import com.example.vostro.ev3_curp.Entidades.Usuario;

import java.util.ArrayList;

public class ListaUsuarioRecylcer extends AppCompatActivity {

    ArrayList<Usuario> listaUsuario = new ArrayList<>();;
    private RecyclerView recyclerViewUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario_recylcer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        recyclerViewUsuario= (RecyclerView) findViewById(R.id.recyclerViewUsuario);
        recyclerViewUsuario.setLayoutManager(new LinearLayoutManager(this));


        ListaUsuarioAdapter adapter = new ListaUsuarioAdapter(listaUsuario);

        recyclerViewUsuario.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsuario.setAdapter(adapter);


        /**
         * Clase que se extiende al AsynckTask esta permitirá mostrar los datos de manera rápida y si se llegase a
         * tardar podrá mencionarlo.
         **/
        new Background().execute("");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            finish();

        } else  if (id == R.id.action_return) {
            Intent intent = new Intent(ListaUsuarioRecylcer.this,MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Clase con la implementacion del AsyncTask mencionado anteriormente, es aqui donde se utilizará el doInBackground()
     * y el postExecute(), en el cual se mostrará un mensaje cuando ya se hayan podido consultar los datos de manera correcta.
     */

    private class Background extends AsyncTask<String, Void, String>
    {

        /**
         * Metodo que se implementará en el fondo al momento de que el usuario esté interactuando con la aplicacion, es aquin donde
         * se hará conexion con la base de datos con el getReadableDatabase, con el cual se tendrá un String de projeccion, en el
         * cual se obtienen todos los campos generados en el UsuarioContract de la base de datos.
         * Tambien se realizará un cursos en el cual solamente se dejará la projeccion,la cual muestra el contenido
         * del arreglo.
         * Para finalizar se realiza un while con cursor.moveToNext(), en el cual se hace un Set de cad auna de las variables y de
         * los campos realizados en la pagina web.
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(String... params) {
            ConexionSQLHelper conexion = new ConexionSQLHelper(getApplicationContext());
            SQLiteDatabase db = conexion.getReadableDatabase();

            String[] projection =
                    {
                            BaseColumns._ID,
                            CurpUsuarioEntry.CAMPO_NAMES,
                            CurpUsuarioEntry.CAMPO_FIRSTLASTNAME,
                            CurpUsuarioEntry.CAMPO_SECONDLASTNAME,
                            CurpUsuarioEntry.CAMPO_GENDER,
                            CurpUsuarioEntry.CAMPO_MONTH,
                            CurpUsuarioEntry.CAMPO_DAY,
                            CurpUsuarioEntry.CAMPO_YEAR,
                            CurpUsuarioEntry.CAMPO_FEDERAL_ENTITY,
                            CurpUsuarioEntry.CAMPO_URL
                    };

            Cursor cursor = db.query(
                    CurpUsuarioEntry.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );



            while(cursor.moveToNext()) {
                Usuario usuario=new Usuario();
                usuario.setName(cursor.getString(cursor.getColumnIndexOrThrow(CurpUsuarioEntry.CAMPO_NAMES)));
                usuario.setFirstlastname(cursor.getString(cursor.getColumnIndexOrThrow(CurpUsuarioEntry.CAMPO_FIRSTLASTNAME)));
                usuario.setSecondlastname(cursor.getString(cursor.getColumnIndexOrThrow(CurpUsuarioEntry.CAMPO_SECONDLASTNAME)));
                usuario.setGender(cursor.getString(cursor.getColumnIndexOrThrow(CurpUsuarioEntry.CAMPO_GENDER)));
                usuario.setMonth(cursor.getInt(cursor.getColumnIndexOrThrow(CurpUsuarioEntry.CAMPO_MONTH)));
                usuario.setDay(cursor.getInt(cursor.getColumnIndexOrThrow(CurpUsuarioEntry.CAMPO_DAY)));
                usuario.setYear(cursor.getInt(cursor.getColumnIndexOrThrow(CurpUsuarioEntry.CAMPO_YEAR)));
                usuario.setFederal_entity(cursor.getString(cursor.getColumnIndexOrThrow(CurpUsuarioEntry.CAMPO_FEDERAL_ENTITY)));
                usuario.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(CurpUsuarioEntry.CAMPO_URL)));

                listaUsuario.add(usuario);
            }

            cursor.close();

            return "Proceso Ejecutado";
        }


        /**
         * Texto que aparecerá una vez que la informacion sea cargada de manera rápida y correcta.
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),"El proceso de carga de los dato de usuario ha sido terminada", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
        }
    }


}
