package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;


    public Usuario(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Usuario() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id;  }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username;  }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}