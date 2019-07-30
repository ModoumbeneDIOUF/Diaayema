package com.example.bichri.diaayema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginAdminActivity extends AppCompatActivity {
    TextView Alogin,Apassword;
    Button Abtn;
    String phone,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        Alogin =  findViewById(R.id.alogin_phone_number_input);
        Apassword = findViewById(R.id.alogin_phone_password_input);
        Abtn = findViewById(R.id.alogin_btn);

        Abtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               phone = Alogin.getText().toString();
               pass = Apassword.getText().toString();
               if (phone.equals("773283705")){
                   if (pass.equals("bichri")){
                       Intent intent = new Intent(LoginAdminActivity.this,AdminAddNewwProdctActivity.class);
                       startActivity(intent);
                   }
                   else{
                       Toast.makeText(LoginAdminActivity.this, "Mot de passe incorrecte", Toast.LENGTH_SHORT).show();
                   }
               }
               else {
                   Toast.makeText(LoginAdminActivity.this, "Le némero est incorrecte", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}
