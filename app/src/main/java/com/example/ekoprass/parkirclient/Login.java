package com.example.ekoprass.parkirclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button buttonLogin;
    SessionManagement sm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sm = new SessionManagement(this);
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        //jika session masih ada maka akan langsung diarahkan ke halaman berikutnya
        if (sm.isLoggedIn()) {
            goToActivity();
        }


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (isValidEmail(email)) {
                    //jika validasi benar maka akan di arahkan ke halaman selanjutnya
                    Toast.makeText(Login.this, "Format email sudah benar", Toast.LENGTH_SHORT).show();
                    sm.createLoginSession(email, password);
                    goToActivity();

                }else if(email.equals("") || email.trim().isEmpty() || password.equals("") || password.trim().isEmpty() ){
                    //Jika email dan password kososng maka muncul pesan kesalahan
                    edtPassword.setError("Password Tidak B oleh Kosong");
                    edtEmail.setError("Email Tidak Boleh Kosong");
                    Toast.makeText(Login.this,"Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();

                }else {
                    //Jika Email salah muncul pesan
                    edtEmail.setError("Format email salah");
                    Toast.makeText(Login.this,"Format email salah", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    private void goToActivity(){
        Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mIntent);
    }

    //Validasi email
    public static boolean isValidEmail(String email) {
        boolean validate;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern)) { //jika email sama dengan patern1 maka true
            validate = true;
        } else if (email.matches(emailPattern2)) { //jika email sama dengan patern2 maka true
            validate = true;
        } else {
            validate = false; //jika sama sekali tidak sama maka false
        }

        return validate;
    }

}
