package dsa.eetac.upc.edu.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import dsa.eetac.upc.edu.client.ClasesClon.Usuario;

public class Gestions extends AppCompatActivity {

    private Usuario meuUsuari;
    //retrofit
    private ApiService mRestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestions);

        //servei rest, singleton
        mRestAdapter =  ApiAdapter.getApiService();

        Intent intent = getIntent();
        String value = intent.getStringExtra("usuario");

        ObjectMapper mapper = new ObjectMapper();
        Usuario usuario = null;
        try {
            usuario = mapper.readValue(value, Usuario.class);
            meuUsuari = usuario;
            TextView nomRebut = (TextView) findViewById(R.id.nomRebut);
            nomRebut.setText(usuario.getNombre());

            TextView mailRebut= (TextView) findViewById(R.id.mailRebut);
            mailRebut.setText(usuario.getEmail());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
