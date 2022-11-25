package Utils;
import Model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;



public interface UsuarioService {


    @POST("auth/login/")
    Call<Usuario> setLogin(@Body Usuario usuario);

    @POST("auth/register/")
    Call<Usuario> setRegister(@Body Usuario usuario);



}


