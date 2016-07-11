package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;

import br.com.consultafacil.domain.User;

/**
 * Created by Isaias on 06/06/2016.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEmailField;
    private EditText mPasswordField;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = getFirebaseAuthResultHandler();

        initFields();
        initUser();
    }

    @Override
    protected void initFields() {
        mEmailField = (EditText) findViewById(R.id.field_email);
        mPasswordField = (EditText) findViewById(R.id.field_password);

        findViewById(R.id.email_sign_in_button).setOnClickListener(this);
        findViewById(R.id.email_create_account_button).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        verifyLogged();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    protected void initUser() {
        user = new User();
        user.setEmail(mEmailField.getText().toString());
        user.setPassword(mPasswordField.getText().toString());
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void signIn() {
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
        initUser();
        verifyLogin();
        hideProgressDialog();
    }

    private void verifyLogged() {
        if (mAuth.getCurrentUser() != null) {
            callMeusAgendamentosActivity();
        } else {
            mAuth.addAuthStateListener(mAuthListener);
        }
    }

    private void verifyLogin(){
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            showToast(getString(R.string.authentication_failed));
                            return;
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                FirebaseCrash.report(e);
            }
        });
    }

    private FirebaseAuth.AuthStateListener getFirebaseAuthResultHandler(){
        FirebaseAuth.AuthStateListener callback = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser userFirebase = firebaseAuth.getCurrentUser();

                if (userFirebase == null) {
                    return;
                }

                if (user.getId() == null && isNomeOk(user, userFirebase)) {
                    user.setId(userFirebase.getUid());
                    user.setNameIfNull(userFirebase.getDisplayName());
                    user.setEmailIfNull(userFirebase.getEmail());
                    user.saveDB();
                }

                callMeusAgendamentosActivity();
            }
        };
        return callback;
    }

    private boolean isNomeOk(User user, FirebaseUser firebaseUser) {
        return user.getNome() != null || firebaseUser.getDisplayName() != null;
    }

    private void callMeusAgendamentosActivity() {
        Intent intent = new Intent(this, MeusAgendamentosActivity.class);
        startActivity(intent);
        finish();
    }

    private void callSignUpActivity() {
        Intent intent = new Intent( this, SignUpActivity.class );
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_create_account_button:
                callSignUpActivity();
                break;
            case R.id.email_sign_in_button:
                signIn();
                break;
        }
    }
}
