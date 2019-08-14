package br.com.livraria.livraria.modelo;

import com.google.gson.annotations.SerializedName;

public class Comentario {

    @SerializedName("id")
    private Long id;

    @SerializedName("comentario")
    private String comentario;

    @SerializedName("usuario")
    private String usuario;

    @SerializedName("data")
    private String data;

    public Comentario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
