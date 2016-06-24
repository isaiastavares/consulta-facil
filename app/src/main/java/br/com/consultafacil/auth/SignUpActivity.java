package br.com.consultafacil.auth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.consultafacil.R;
import br.com.consultafacil.activity.BaseActivity;
import br.com.consultafacil.domain.User;
import br.com.consultafacil.fragment.DatePickerFragment;

/**
 * Created by Isaias on 17/06/2016.
 */
public class SignUpActivity extends BaseActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initToolbar();

        /*ArrayList<String> list = new ArrayList<String>();
        list.add("Sexo");
        list.add("Masculino");
        list.add("Feminino");

        ArrayAdapter<String> listSexo = new ArrayAdapter<String>(this, 0, list);
        Spinner spinnerSexo = (Spinner) findViewById(R.id.sexo);
        spinnerSexo.setAdapter(listSexo);*/

        mAuth = getFirebaseAuth();
    }

    /**
     * TODO validar o formulario
     * @return
     */
    private boolean validateForm() {
        boolean valid = true;

        return valid;
    }

    /**
     * TODO iniciar o usuario
     * @return
     */
    private void initUser() {
        User user = new User();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            initUser();
                        } else {
                            showToast(getResources().getString(R.string.authentication_failed));
                        }

                        hideProgressDialog();
                    }
                });
    }
}
