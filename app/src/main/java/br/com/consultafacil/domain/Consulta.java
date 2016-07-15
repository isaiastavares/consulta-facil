package br.com.consultafacil.domain;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.ValueEventListener;

import br.com.consultafacil.domain.util.LibraryClass;

/**
 * Created by Isaias on 10/07/2016.
 */
public class Consulta {

    public static final String CONSULTAS = "consultas";

    private String id;
    private String idUsuario;
    private String convenio;
    private String especialidade;
    private String data;
    private String prestador;
    private String endereco;
    private String horario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrestador() {
        return prestador;
    }

    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void saveDB(DatabaseReference.CompletionListener... completionListener) {
        DatabaseReference firebase = LibraryClass.getFirebaseDatabase()
                .child(User.USUARIOS)
                .child(getIdUsuario())
                .child(CONSULTAS)
                .child(getId());

        if (completionListener.length == 0) {
            firebase.setValue(this);
        } else {
            firebase.setValue(this, completionListener[0]);
        }
    }

    public DatabaseReference contextDataDB(Context context) {
        String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference firebase = LibraryClass.getFirebaseDatabase()
                .child(User.USUARIOS)
                .child(idUsuario)
                .child(CONSULTAS);
        firebase.addValueEventListener((ValueEventListener) context);
        return firebase;
    }
}
