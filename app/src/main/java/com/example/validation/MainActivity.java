package com.example.validation;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonValidate = findViewById(R.id.buttonValidate);

        // Set OnClickListener for the validate button
        buttonValidate.setOnClickListener(v -> validateInputs());
    }

    // Method to validate username and password on button click
    private void validateInputs() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        boolean isUsernameValid = validateUsername(username);
        boolean isPasswordValid = validatePassword(password);

        if (isUsernameValid && isPasswordValid) {
            showToastMessage("Validation successful!");
        } else {
            showToastMessage("Validation failed. Please check your inputs.");
        }
    }

    // Method to validate username as it's being typed
    private boolean validateUsername(String username) {
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Username cannot be empty");
            return false;
        } else if (!username.matches("^(?=.*[0-9]).+[a-zA-Z0-9]+$")) {
            editTextUsername.setError("Username should contain at least one number and only alphabets or numbers");
            return false;
        } else {
            editTextUsername.setError(null); // Clear the error if valid
            return true;
        }
    }

    // Method to validate password as it's being typed
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Password cannot be empty");
            return false;
        } else if (!password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{6,}$")) {
            editTextPassword.setError("Password must have 1 uppercase, 1 number, and 1 special character");
            return false;
        } else {
            editTextPassword.setError(null); // Clear the error if valid
            return true;
        }
    }

    // Method to show a Toast message
    private void showToastMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
