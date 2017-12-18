package dsa.eetac.upc.edu.client;



import java.util.List;


import dsa.eetac.upc.edu.client.ClasesClon.Producte;
import dsa.eetac.upc.edu.client.ClasesClon.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {


    @POST("Usuario/{email}")
    Call<Usuario> getLogin(@Path("email") String user, @Body Login loginBody);

    @GET("ProductosPorPrecio")
    Call<List<Producte>> getProductes();

    @GET("ProductosPorVenda")
    Call<List<Producte>> getProductesVenda();







}
