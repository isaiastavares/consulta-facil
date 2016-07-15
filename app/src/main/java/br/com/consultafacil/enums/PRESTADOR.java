package br.com.consultafacil.enums;

import br.com.consultafacil.activity.R;

/**
 * Created by Isaias on 11/07/2016.
 */
public enum PRESTADOR {

    PRESTADOR_1(R.mipmap.avatar1, "Prestador 1", "Rua 1, Bairro 1, Cidade 1 - XX"),
    PRESTADOR_2(R.mipmap.avatar2, "Prestador 2", "Rua 2, Bairro 2, Cidade 2 - XX"),
    PRESTADOR_3(R.mipmap.avatar3, "Prestador 3", "Rua 3, Bairro 3, Cidade 3 - XX"),
    PRESTADOR_4(R.mipmap.avatar4, "Prestador 4", "Rua 4, Bairro 4, Cidade 4 - XX"),
    PRESTADOR_5(R.mipmap.avatar5, "Prestador 5", "Rua 5, Bairro 5, Cidade 5 - XX"),
    PRESTADOR_6(R.mipmap.avatar6, "Prestador 6", "Rua 6, Bairro 6, Cidade 6 - XX"),
    PRESTADOR_7(R.mipmap.avatar7, "Prestador 7", "Rua 7, Bairro 7, Cidade 7 - XX"),
    PRESTADOR_8(R.mipmap.avatar8, "Prestador 8", "Rua 8, Bairro 8, Cidade 8 - XX");

    private int imagem;
    private String nome;
    private String endereco;

    PRESTADOR(int imagem, String nome, String endereco) {
        this.imagem = imagem;
        this.nome = nome;
        this.endereco = endereco;
    }

    public static PRESTADOR fromNome(String nome) {
        for (PRESTADOR prestador : PRESTADOR.values()) {
            if (prestador.getNome().equals(nome)) {
                return prestador;
            }
        }
        return null;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
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

}
