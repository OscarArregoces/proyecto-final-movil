package Utils;

import java.util.List;

import Model.Notas;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;



public interface NotasServices {

    @GET("notas/")
    Call<List<Notas>> getNotas();

    @POST("notas/create/")
    Call<Notas>addNotas(@Body Notas notas);

    @PUT("notas/update/{id}/")
    Call<Notas>updateNotas(@Body Notas notas,@Path("id") int id);

    @DELETE("notas/delete/{id}/")
    Call<Notas>deleteNotas(@Path("id")int id);


}




