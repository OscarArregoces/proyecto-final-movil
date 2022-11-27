package Utils;

import java.util.List;
import Model.Recordatorio;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;



public interface RecordatorioService {


    @GET("recordatorios/")
    Call<List<Recordatorio>> getRecordatorio();

    @POST("recordatorios/create/")
    Call<Recordatorio>addRecordatorio(@Body Recordatorio recordatorio);

    @PUT("recordatorios/update/{id}/")
    Call<Recordatorio>updateRecordatorio(@Body Recordatorio recordatorio,@Path("id") int id);

    @DELETE("recordatorios/delete/{id}/")
    Call<Recordatorio>deleteRecordatorio(@Path("id")int id);


}




