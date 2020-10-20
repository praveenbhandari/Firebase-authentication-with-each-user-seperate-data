package com.example.authentication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText email, pass;
    Button existing, newuser;
    String email1, pass1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newuser = findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);
        existing = findViewById(R.id.button);

        existing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email1 = email.getText().toString();
                pass1 = pass.getText().toString();
                if (!email1.equals("") && !pass1.equals("")) {

                    mAuth.signInWithEmailAndPassword(email1, pass1)                                 //Sign in using credentials i.e. email and password
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        String username = user.getUid();
                                        Toast.makeText(MainActivity.this, "Logging in", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(MainActivity.this, Main2Activity.class);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email1 = email.getText().toString();
                pass1 = pass.getText().toString();
                if (!email1.equals("") && !pass1.equals("")) {
                    mAuth.createUserWithEmailAndPassword(email1, pass1)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        String username = user.getUid();
                                        Toast.makeText(MainActivity.this, "Logging in", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(MainActivity.this, Main2Activity.class);
                                        i.putExtra("userid", username);
                                        startActivity(i);

                                    } else {
                                        Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}