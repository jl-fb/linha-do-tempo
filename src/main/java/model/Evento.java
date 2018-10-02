package model;

public class Evento {
    Integer id;
    String nome;
    String data;
    String descricao;

    public Evento() {
    }

    public Evento(Integer id, String nome, String data) {
        this.nome = nome;
        this.data = data;
        this.id = id;
    }

    public Evento(Integer id, String nome, String data, String descricao) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

