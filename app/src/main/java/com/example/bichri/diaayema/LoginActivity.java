package com.example.bichri.diaayema;

import androidx.annotation.NonNull;
import  androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bichri.diaayema.Model.Users;
import com.example.bichri.diaayema.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

public class LoginActivity extends AppCompatActivity {

    private EditText inputNumber,inputPassword;
    private Button loginButton;
    private ProgressDialog loadingBar;
    private CheckBox chBoxRemember;
    private TextView AdminLink,NoAdminLink;
    private String parendDbName="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputNumber = findViewById(R.id.login_phone_number_input);
        inputPassword = findViewById(R.id.login_phone_password_input);
        loginButton = findViewById(R.id.login_btn);
        loadingBar = new ProgressDialog(this);
        chBoxRemember = (CheckBox) findViewById(R.id.remember_me_chkb);
        AdminLink = findViewById(R.id.admin_panel);
        NoAdminLink = findViewById(R.id.not_admin_panel);
        Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();

            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,LoginAdminActivity.class);
                startActivity(intent);

            }
        });

    }

    private void loginUser() {
        String phone = inputNumber.getText().toString();
        String password = inputPassword.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Veillez remplire tous les champs", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Veillez remplire tous les champs", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Connection en cour");
            loadingBar.setMessage("Veillez patienter un instant");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccesToCount(phone,password);

        }

    }

    private void AllowAccesToCount(final String phone, final String password) {
        if (chBoxRemember.isChecked()){
                Paper.book().write(Prevalent.UserPhoneKey,phone);
                Paper.book().write(Prevalent.UserPasswordKey,password);

        }
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parendDbName).child(phone).exists()){
                    Users usersData = dataSnapshot.child(parendDbName).child(phone).getValue(Users.class);
                    if(usersData.getPhone().equals(phone))
                    {
                        if(usersData.getPassword().equals(password))
                        {

                             if (parendDbName.equals("Users"))
                            {
                                loadingBar.dismiss();
                                Toast.makeText(LoginActivity.this, "Connexion réussi", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Le mot de passe est incorrecte", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Ce compte n'existe pas veillez enter le bon numero", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
