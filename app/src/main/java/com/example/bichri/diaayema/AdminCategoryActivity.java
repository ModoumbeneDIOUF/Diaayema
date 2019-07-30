package com.example.bichri.diaayema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {
    private ImageView tshirt,sport,dress,sweather,glass,hat,purs,shoes,head,lop,watch,mobile,headphones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        tshirt = findViewById(R.id.t_shirt);
        sport = findViewById(R.id.sport);
        dress =  findViewById(R.id.femelle_dress);
        sweather = findViewById(R.id.sweather);
        glass = findViewById(R.id.glasses);
        hat = findViewById(R.id.hats);
        purs = findViewById(R.id.purse_bag);
        shoes = findViewById(R.id.shoes);
        head = findViewById(R.id.hats);
        lop = findViewById(R.id.loptops);
        watch = findViewById(R.id.watches);
        mobile = findViewById(R.id.mobilephones);
        headphones = findViewById(R.id.headphones);

        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","sports");
                startActivity(intent);
            }
        });

        dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","dresses");
                startActivity(intent);
            }
        });
        sweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","sweather");
                startActivity(intent);
            }
        });
        glass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","glasses");
                startActivity(intent);
            }
        });
        hat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","hats");
                startActivity(intent);
            }
        });

        purs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","purs");
                startActivity(intent);
            }
        });
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","shoes");
                startActivity(intent);
            }
        });

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","heads");
                startActivity(intent);
            }
        });

        lop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","lops");
                startActivity(intent);
            }
        });

        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","headphones");
                startActivity(intent);
            }
        });
        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","watch");
                startActivity(intent);
            }
        });
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewwProdctActivity.class);
                intent.putExtra("category","mobiles");
                startActivity(intent);
            }
        });
    }


}
