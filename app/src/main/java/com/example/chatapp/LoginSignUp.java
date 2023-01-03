package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginSignUp extends AppCompatActivity {

    TextInputLayout email,password;
    Button login,signup;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getEditText().getText().toString();
                String p = password.getEditText().getText().toString();

                //Toast.makeText(LoginSignUp.this, ""+e+p, Toast.LENGTH_SHORT).show();
                auth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(LoginSignUp.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(LoginSignUp.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getEditText().getText().toString();
                String p = password.getEditText().getText().toString();

                //Toast.makeText(LoginSignUp.this, ""+e+p, Toast.LENGTH_SHORT).show();
                auth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginSignUp.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginSignUp.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(LoginSignUp.this,MainActivity.class));
            finish();
        }
    }
}