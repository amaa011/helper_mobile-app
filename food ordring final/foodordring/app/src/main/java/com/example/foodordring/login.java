package com.example.foodordring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mEmailField = findViewById(R.id.email_field1);
        mPasswordField = findViewById(R.id.edittextpasswordlogin);
        mLoginButton = findViewById(R.id.btnLogin);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Login successful, navigate to main activity
                                        startActivity(new Intent(login.this, MainActivity.class));
                                        finish();
                                    } else {
                                        // Login failed, display error message
                                        Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Email or password field is empty, display error message
                    Toast.makeText(login.this, "Please fill in both email and password fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Get a reference to the TextViews
        TextView textView1 = findViewById(R.id.text_view_link);
        TextView textView2 = findViewById(R.id.textView30);

// Set an OnClickListener on the first TextView
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });

// Set an OnClickListener on the second TextView
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}