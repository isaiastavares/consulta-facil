package br.com.consultafacil.adapter;

/**
 * Created by Isaias on 10/07/2016.
 */
public class Especialidades {

    private int imagem;
    private String nome;

    public Especialidades(int imagem, String nome) {
        this.imagem = imagem;
        this.nome = nome;
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
}
