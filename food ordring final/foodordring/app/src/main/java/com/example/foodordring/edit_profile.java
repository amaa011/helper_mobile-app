package com.example.foodordring;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class edit_profile extends AppCompatActivity {

    private DBHelper mDBHelper;
    private EditText mUsernameEditText;
    private EditText mCurrentPasswordEditText;
    private EditText mNewPasswordEditText;
    private EditText mConfirmNewPasswordEditText;
    private Button mChangePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mDBHelper = new DBHelper(this);

        mUsernameEditText = findViewById(R.id.username_edit_text);
        mCurrentPasswordEditText = findViewById(R.id.current_password_edit_text);
        mNewPasswordEditText = findViewById(R.id.new_password_edit_text);
        mConfirmNewPasswordEditText = findViewById(R.id.confirm_new_password_edit_text);
        mChangePasswordButton = findViewById(R.id.change_password_button);

        mChangePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsernameEditText.getText().toString();
                String currentPassword = mCurrentPasswordEditText.getText().toString();
                String newPassword = mNewPasswordEditText.getText().toString();
                String confirmNewPassword = mConfirmNewPasswordEditText.getText().toString();

                // Check if the current password is correct
                if (mDBHelper.checkUsernamePassword(username, currentPassword)) {
                    // Check if the new password and confirm new password fields match
                    if (newPassword.equals(confirmNewPassword)) {
                        // Update the password in the database
                        mDBHelper.updatePassword(username, newPassword);

                        // Show a message to the user indicating that the password was successfully changed
                        Toast.makeText(edit_profile.this, "Password successfully changed", Toast.LENGTH_SHORT).show();
                    } else {
                        // Show an error message if the new password and confirm new password fields do not match
                        showErrorMessage("New password and confirm new password do not match");
                    }
                } else {
                    // Show an error message if the current password is incorrect
                    showErrorMessage("Incorrect current password");
                }
            }
        });
    }

    private void showErrorMessage(String message) {
        // TODO: Display error message
    }
}
