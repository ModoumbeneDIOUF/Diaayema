package com.example.bichri.diaayema;

import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogout = findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
