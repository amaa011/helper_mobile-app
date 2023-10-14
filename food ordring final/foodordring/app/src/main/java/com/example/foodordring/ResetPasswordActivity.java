package com.example.foodordring;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText mUsernameEditText;
    private Button mResetPasswordButton;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        mUsernameEditText = findViewById(R.id.email_field);
        mResetPasswordButton = findViewById(R.id.reset_button);
        mDBHelper = new DBHelper(this);

        mResetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsernameEditText.getText().toString();
                if (mDBHelper.checkUsername(username)) {
                    // Send reset password email
                    sendResetEmail(username);
                } else {
                    // Display error message
                    showErrorMessage("Username does not exist");
                }
            }
        });
    }

    private void sendResetEmail(String username) {
        // TODO: Implement email sending logic
    }

    private void showErrorMessage(String message) {
        // TODO: Display error message
    }
}


