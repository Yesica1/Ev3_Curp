package com.example.vostro.ev3_curp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vostro.ev3_curp.Adapter.ListaUsuarioAdapter;
import com.example.vostro.ev3_curp.DataBase.ConexionSQLHelper;
import com.example.vostro.ev3_curp.DataBase.UsuarioContract;
import com.example.vostro.ev3_curp.DataBase.UsuarioContract.CurpUsuarioEntry;
import com.example.vostro.ev3_curp.Entidades.Usuario;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declaracion
    TextInputEditText names;
    TextInputEditText firstlastname;
    TextInputEditText secondlastname;
    RadioGroup gender;
    EditText month, day, year;
    Spinner federal_entity;
    ImageView imageview_photo;

    String imagenUrl="";


    private static final int seleccionar_imagen = 15;
    private static final int capturar_foto = 25;


    ArrayList<Usuario> user = new ArrayList<>();
    ListaUsuarioAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Es aqui donde se declara el Spinner realizado en el String-array para que se pueda seleccionar cada una de las opciones disponibles
        federal_entity = (Spinner) findViewById(R.id.spinnerfederal);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.entidades_fededrativas, android.R.layout.simple_spinner_item);
        federal_entity.setAdapter(adapter);

        names = (TextInputEditText) findViewById(R.id.ET_name);
        firstlastname = (TextInputEditText) findViewById(R.id.ET_firstlastname);
        secondlastname = (TextInputEditText) findViewById(R.id.ET_secondlastname);
        gender = (RadioGroup) findViewById(R.id.radioGroup);
        month = (EditText) findViewById(R.id.mes);
        day = (EditText) findViewById(R.id.dia);
        year = (EditText) findViewById(R.id.año);
        imageview_photo = (ImageView) findViewById(R.id.imageview_photo);

        userAdapter = new ListaUsuarioAdapter(user);


        Button agregardatosBoton = (Button) findViewById(R.id.BT_Calcu);
        agregardatosBoton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Al darle clic al boton de calcular primeramente se debe de verificar que el usuario haya ingresado datos para poder
                // proceder.
                if (names.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter your names", Toast.LENGTH_LONG).show();
                    return;
                }

                if (firstlastname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter your first lastname", Toast.LENGTH_LONG).show();
                    return;
                }
                if (secondlastname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter your second lastname", Toast.LENGTH_LONG).show();
                    return;
                }
                if (month.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the month", Toast.LENGTH_LONG).show();
                    return;
                }
                if (day.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the day", Toast.LENGTH_LONG).show();
                    return;
                }
                if (year.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the month", Toast.LENGTH_LONG).show();
                    return;
                }

                /**
                 * Establecer cada uno de los valores para poder hacer put y poderlos almacenar en una base de datos como
                 * getWritableDatabase. Es aqui en la cual se debe de poner el .getText().toString(), segun se requiera.
                 */

                ConexionSQLHelper conexion = new ConexionSQLHelper(getApplicationContext());
                SQLiteDatabase db = conexion.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(CurpUsuarioEntry.CAMPO_NAMES, names.getText().toString());
                values.put(CurpUsuarioEntry.CAMPO_FIRSTLASTNAME, firstlastname.getText().toString());
                values.put(CurpUsuarioEntry.CAMPO_SECONDLASTNAME, secondlastname.getText().toString());
                values.put(CurpUsuarioEntry.CAMPO_GENDER, ((RadioButton) findViewById(gender.getCheckedRadioButtonId())).getText().toString());
                values.put(CurpUsuarioEntry.CAMPO_MONTH, Integer.parseInt(month.getText().toString()));
                values.put(CurpUsuarioEntry.CAMPO_DAY, Integer.parseInt(day.getText().toString()));
                values.put(CurpUsuarioEntry.CAMPO_YEAR, Integer.parseInt(year.getText().toString()));
                values.put(CurpUsuarioEntry.CAMPO_FEDERAL_ENTITY, federal_entity.getSelectedItem().toString());
                values.put(CurpUsuarioEntry.CAMPO_URL, imagenUrl);

                db.insert(CurpUsuarioEntry.TABLE_NAME, null, values);

                Toast.makeText(getApplicationContext(), "Usuario guardado", Toast.LENGTH_SHORT).show();
            }
        });


        Button select_thephoto = (Button) findViewById(R.id.select_thephoto);
        select_thephoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, seleccionar_imagen);
            }
        });


        Button tomar_foto = (Button) findViewById(R.id.take_thephoto);
        tomar_foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivityForResult(
                        new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE),
                        capturar_foto);

                }

        });

    }

         public void onActivityResult(int requestCode, int resultCode, Intent data)
         {

             super.onActivityResult(requestCode, resultCode, data);

            try{
                 if (requestCode == seleccionar_imagen && resultCode == RESULT_OK)
                     {
                         Uri path_image = data.getData();
                         imageview_photo.setImageURI(path_image);
                         imagenUrl = path_image.toString();
                     }
                 if(requestCode == capturar_foto & resultCode == RESULT_OK)
                    {

                     Uri path_image = data.getData();
                     imageview_photo.setImageURI(path_image);
                     imagenUrl = String.valueOf(path_image);

                     }

             } catch(Exception e)
                {

                }
         }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
        } else  if (id == R.id.action_show) {
            Intent intent = new Intent(MainActivity.this,ListaUsuarioRecylcer.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}