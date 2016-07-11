package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import br.com.consultafacil.domain.User;

/**
 * Created by Isaias on 17/06/2016.
 */
public class AlterarSenhaActivity extends BaseActivity implements ValueEventListener {

    private User user;
    private EditText newPassword;
    private EditText password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        initToolbar();

        initButtonBack();

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initFields();
        initUser();
    }

    @Override
    protected void initFields() {
        newPassword = (EditText) findViewById(R.id.nova_senha);
        password = (EditText) findViewById(R.id.senha);
    }

    private void initUser() {
        user = new User();
        user.setId(mAuth.getCurrentUser().getUid());
        user.contextDataDB(this);
    }

    private void update() {
        user.setNewPassword(newPassword.getText().toString());
        user.setPassword(password.getText().toString());

        reauthenticate();
    }

    private void reauthenticate() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        if (firebaseUser == null) {
            return;
        }

        AuthCredential credential = EmailAuthProvider.getCredential(
                user.getEmail(),
                user.getPassword()
        );

        firebaseUser.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            updateData();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        FirebaseCrash.report(e);
                        showToast(e.getMessage());
                    }
                });
    }

    private void updateData() {
        user.setNewPassword(newPassword.getText().toString());
        user.setPassword(password.getText().toString());

        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        if (firebaseUser == null) {
            return;
        }

        firebaseUser
                .updatePassword(user.getNewPassword())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            newPassword.setText("");
                            password.setText("");

                            showToast("Senha atualizada com sucesso");
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        FirebaseCrash.report(e);
                        showToast(e.getMessage());
                    }
                });
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        User u = dataSnapshot.getValue(User.class);
        user.setEmail(u.getEmail());
    }

    @Override
    public void onCancelled(DatabaseError firebaseError) {
        FirebaseCrash.report(firebaseError.toException());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        menu.findItem(R.id.salvar).setVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.salvar) {
            update();
            callPerfilActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void callPerfilActivity() {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
    }
}
