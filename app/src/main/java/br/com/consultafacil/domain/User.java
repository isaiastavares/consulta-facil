package br.com.consultafacil.domain;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import br.com.consultafacil.domain.util.LibraryClass;

/**
 * Created by Isaias on 16/06/2016.
 */
public class User {

    public static final String USUARIOS = "usuarios";

    private String id;
    private String nome;
    private String dataNascimento;
    private String sexo;
    private String telefone;
    private String email;
    private String password;
    private String newPassword;

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    private void setNameInMap(Map<String, Object> map) {
        if (getNome() != null) {
            map.put("nome", getNome());
        }
    }

    public void setNameIfNull(String name) {
        if (this.nome == null) {
            this.nome = name;
        }
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setEmailInMap(Map<String, Object> map) {
        if (getEmail() != null) {
            map.put("email", getEmail());
        }
    }

    public void setEmailIfNull(String email) {
        if (this.email == null) {
            this.email = email;
        }
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Exclude
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void saveDB(DatabaseReference.CompletionListener... completionListener) {
        DatabaseReference firebase = LibraryClass.getFirebaseDatabase().child(USUARIOS).child(getId());

        if (completionListener.length == 0) {
            firebase.setValue(this);
        } else {
            firebase.setValue(this, completionListener[0]);
        }
    }

    public void updateDB(DatabaseReference.CompletionListener... completionListener) {
        DatabaseReference firebase = LibraryClass.getFirebaseDatabase().child(USUARIOS).child(getId());

        Map<String, Object> map = new HashMap<>();
        setNameInMap(map);
        setEmailInMap(map);

        if (map.isEmpty()) {
            return;
        }

        if (completionListener.length > 0) {
            firebase.updateChildren(map, completionListener[0]);
        } else {
            firebase.updateChildren(map);
        }
    }

    public void removeDB(DatabaseReference.CompletionListener completionListener) {
        DatabaseReference firebase = LibraryClass.getFirebaseDatabase().child(USUARIOS).child(getId());
        firebase.setValue(null, completionListener);
    }

    public void contextDataDB(Context context) {
        DatabaseReference firebase = LibraryClass.getFirebaseDatabase().child(USUARIOS).child(getId());
        firebase.addListenerForSingleValueEvent((ValueEventListener) context);
    }
}