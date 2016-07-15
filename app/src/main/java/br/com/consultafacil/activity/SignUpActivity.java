package br.com.consultafacil.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import br.com.consultafacil.domain.User;
import br.com.consultafacil.fragment.DatePickerFragment;
import br.com.consultafacil.util.Utilidade;

/**
 * Created by Isaias on 17/06/2016.
 */
public class SignUpActivity extends BaseActivity implements DatabaseReference.CompletionListener {

    private EditText mNomeField;
    private EditText mDataNascimentoField;
    private Spinner mSexoField;
    private EditText mTelefoneField;
    private EditText mEmailField;
    private EditText mSenhaField;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initToolbar();

        initButtonBack();

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser == null || user.getId() != null){
                    return;
                }

                user.setId(firebaseUser.getUid());
                user.saveDB(SignUpActivity.this);
            }
        };

        initFields();

        preencherSpinner();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    protected void initFields() {
        mNomeField = (EditText) findViewById(R.id.nomeCompleto);
        mDataNascimentoField = (EditText) findViewById(R.id.dataNascimento);
        mSexoField = (Spinner) findViewById(R.id.sexo);
        mTelefoneField = (EditText) findViewById(R.id.telefone);
        mEmailField = (EditText) findViewById(R.id.email);
        mSenhaField = (EditText) findViewById(R.id.senha);
    }

    private void preencherSpinner() {
        List<String> listSexos = new ArrayList<String>();
        listSexos.add("Sexo");
        listSexos.add("Masculino");
        listSexos.add("Feminino");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                listSexos);
        mSexoField.setAdapter(spinnerArrayAdapter);
    }

    private void initUser() {
        user = new User();
        user.setNome(mNomeField.getText().toString());
        user.setDataNascimento(mDataNascimentoField.getText().toString());
        user.setSexo(mSexoField.getSelectedItem().toString());
        user.setTelefone(mTelefoneField.getText().toString());
        user.setEmail(mEmailField.getText().toString());
        user.setPassword(mSenhaField.getText().toString());
    }

    private boolean validateForm() {
        boolean valid = true;

        String nomeCompleto = mNomeField.getText().toString();
        if (TextUtils.isEmpty(nomeCompleto)) {
            mNomeField.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            mNomeField.setError(null);
        }

        String dataNascimento = mDataNascimentoField.getText().toString();
        if (TextUtils.isEmpty(dataNascimento)) {
            mDataNascimentoField.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            mDataNascimentoField.setError(null);
        }

        String sexo = mSexoField.getSelectedItem().toString();
        if (sexo.equals("Sexo")) {
            showToast(getString(R.string.field_sexo_required));
            valid = false;
        }

        String celular = mTelefoneField.getText().toString();
        if (TextUtils.isEmpty(celular)) {
            mTelefoneField.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            mTelefoneField.setError(null);
        }

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String senha = mSenhaField.getText().toString();
        if (TextUtils.isEmpty(senha)) {
            mSenhaField.setError(getString(R.string.error_field_required));
            valid = false;
        } else if (senha.length() < 6) {
            mSenhaField.setError(getString(R.string.field_password_invalid));
            valid = false;
        } else {
            mSenhaField.setError(null);
        }

        return valid;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String data = Utilidade.getDataString(year, monthOfYear, dayOfMonth);
                EditText dataNascimento = (EditText) getActivity().findViewById(R.id.dataNascimento);
                dataNascimento.setText(data);
            }
        };
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void createAccount(View v) {
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
        initUser();

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            hideProgressDialog();
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
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        mAuth.signOut();

        showToast("Conta criada com sucesso!");
        hideProgressDialog();
        finish();
    }
}
