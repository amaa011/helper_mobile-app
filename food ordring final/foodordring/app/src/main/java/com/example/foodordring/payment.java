package com.example.foodordring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class payment extends Activity {

    private EditText mCardHolderNameEditText;
    private EditText mCardNumberEditText;
    private EditText mExpiryDateEditText;
    private EditText mCVVEditText;
    private Button mPayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Initialize the views
        mCardHolderNameEditText = findViewById(R.id.card_holder_name_edit_text);
        mCardNumberEditText = findViewById(R.id.card_number_edit_text);
        mExpiryDateEditText = findViewById(R.id.expiry_date_edit_text);
        mCVVEditText = findViewById(R.id.cvv_edit_text);
        mPayButton = findViewById(R.id.pay_button);

        // Set the click listener for the pay button
        mPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input data
                String cardHolderName = mCardHolderNameEditText.getText().toString().trim();
                String cardNumber = mCardNumberEditText.getText().toString().trim();
                String expiryDate = mExpiryDateEditText.getText().toString().trim();
                String cvv = mCVVEditText.getText().toString().trim();

                // Validate the input data
                if (cardHolderName.isEmpty()) {
                    Toast.makeText(payment.this, "Please enter the card holder name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cardNumber.isEmpty()) {
                    Toast.makeText(payment.this, "Please enter the card number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (expiryDate.isEmpty()) {
                    Toast.makeText(payment.this, "Please enter the expiry date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cvv.isEmpty()) {
                    Toast.makeText(payment.this, "Please enter the CVV", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Process the payment
                // ...

                // Show a success message
                Toast.makeText(payment.this, "Payment successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}