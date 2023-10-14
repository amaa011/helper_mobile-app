package com.example.foodordring;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.UserProfileChangeRequest;


public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mPhoneField;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        mEmailField = findViewById(R.id.email_field);
        mPasswordField = findViewById(R.id.edittextpassword);
        mSignUpButton = findViewById(R.id.btnsignup);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();
                String phone = mPhoneField.getText().toString();

                if (!email.isEmpty() && !password.isEmpty() && !phone.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign up successful
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(phone)
                                                .build();
                                        user.updateProfile(profileUpdates)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d("Signup", "User profile updated.");
                                                        }
                                                    }
                                                });
                                        startActivity(new Intent(signup.this, MainActivity.class));
                                        finish();
                                    } else {
                                        // Sign up failed, display error message
                                        Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Email, password, or phone field is empty, display error message
                    Toast.makeText(signup.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}




