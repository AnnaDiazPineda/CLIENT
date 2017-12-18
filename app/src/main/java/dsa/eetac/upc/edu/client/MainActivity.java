package dsa.eetac.upc.edu.client;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dsa.eetac.upc.edu.client.ClasesClon.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText nomUsuari;
    private EditText contraUsuari;
    private ProgressBar progressBar;
    private Button iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nomUsuari= (EditText) findViewById(R.id.edNom);
        contraUsuari = (EditText) findViewById(R.id.edPassword);
        iniciar= (Button) findViewById(R.id.btIniciar);

        progressBar = (ProgressBar) findViewById(R.id.progresBar);
        iniciar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (!isOnline()) {

                     showLoginError("error de xarxa");
                     return;
                 }
                 attemptLogin();
             }

         });


    }

    private void attemptLogin() {
        String mail = nomUsuari.getText().toString();
        String pass = contraUsuari.getText().toString();

        Call<Usuario> loginCall = ApiAdapter.getApiService().getLogin(mail,new Login(pass));
        loginCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                showLoginError("login correcte");
                Intent intent = new Intent(MainActivity.this, Gestions.class);
                Usuario usuario = response.body();
                ObjectMapper mapper= new ObjectMapper();
                try
                {
                    String jsonResult = mapper.writeValueAsString(usuario);
                    intent.putExtra("usuario", jsonResult);
                    MainActivity.this.startActivity(intent);
                } catch (JsonProcessingException e) {
                    showLoginError("no serializable");
                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                showLoginError("no se ha pogut gestionar");
                return;
            }
        });

    }

    //verificar connexió de xarxa
    private boolean isOnline()
    {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
    private void showLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
