package com.example.bichri.diaayema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputName,inputPhone,inputPassword;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = findViewById(R.id.register_name_input);
        inputPhone = findViewById(R.id.register_name_input);
        createAccountButton = findViewById(R.id.register_btn);
    }
}
