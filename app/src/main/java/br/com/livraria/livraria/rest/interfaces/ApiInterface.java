package br.com.livraria.livraria.rest.interfaces;

import java.util.List;

import br.com.livraria.livraria.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("livros")
    Call<List<Livro>> getLivros(@Query("key") String keyword);

}
