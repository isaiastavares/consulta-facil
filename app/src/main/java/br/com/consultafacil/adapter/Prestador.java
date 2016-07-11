package br.com.consultafacil.adapter;

/**
 * Created by Isaias on 11/07/2016.
 */
public class Prestador {

    private int imagem;
    private String nome;
    private String endereco;

    public Prestador(int imagem, String nome, String endereco) {
        this.imagem = imagem;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
