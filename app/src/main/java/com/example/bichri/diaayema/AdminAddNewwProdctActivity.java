package com.example.bichri.diaayema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddNewwProdctActivity extends AppCompatActivity {
    private  String categoryName,desc,price,pname;
    private Button AddNewProduit;
    private EditText inputProductName,inputProductDesc,inputProductPrice;
    private ImageView productImage;
    private static final int galleryPick=1;
    private Uri ImageUri;
    private String saveDate,saveTime;
    private String productRandomKey, dowloadImageUrl;
    private StorageReference productImageRefe;
    private DatabaseReference ProdcutRef;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_neww_prodct);

        categoryName = getIntent().getExtras().get("category").toString();
        productImageRefe = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProdcutRef = FirebaseDatabase.getInstance().getReference().child("Products");


        inputProductName =  findViewById(R.id.produit_name);
        inputProductDesc =  findViewById(R.id.produit_description);
        inputProductPrice =  findViewById(R.id.produit_price);
        productImage =  findViewById(R.id.select_image_produit);
        AddNewProduit =  findViewById(R.id.add_new_produit);
        loadingBar = new ProgressDialog(this);

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        AddNewProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,galleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==galleryPick && resultCode==RESULT_OK && data!=null){
            ImageUri = data.getData();
            productImage.setImageURI(ImageUri);
        }
    }

    private void ValidateProductData(){
        desc = inputProductDesc.getText().toString();
        price = inputProductPrice.getText().toString();
        pname = inputProductName.getText().toString();

        if(ImageUri == null){
            Toast.makeText(this, "Veillez cliqer sur la photo et choisir une image", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(desc)){
            Toast.makeText(this, "Veillez renseigner la description du produit", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(price)){
            Toast.makeText(this, "Veillez renseigner le prix du produit", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pname)){
            Toast.makeText(this, "Veillez renseigner le prix du produit", Toast.LENGTH_SHORT).show();
        }
        else {
            StoreProductInformation();
        }
    }

    private void StoreProductInformation() {

        loadingBar.setTitle("Enrigistrement en cour");
        loadingBar.setMessage("Veillez patienter un instant");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currenTtDate = new SimpleDateFormat("MMM dd, yyyy");
        saveDate = currenTtDate.format(calendar.getTime());

        SimpleDateFormat currenTime = new SimpleDateFormat("HH:mm:ss a");
        saveTime = currenTime.format(calendar.getTime());

        productRandomKey = saveDate + saveTime;

        final StorageReference filePath = productImageRefe.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(AdminAddNewwProdctActivity.this, message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AdminAddNewwProdctActivity.this, "Réussit ....", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask  = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                       if (!task.isSuccessful()){
                           throw task.getException();
                       }
                       dowloadImageUrl = filePath.getDownloadUrl().toString();
                       return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            dowloadImageUrl = task.getResult().toString();

                            Toast.makeText(AdminAddNewwProdctActivity.this, " success", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });

    }

    private void SaveProductInfoToDatabase() {

        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pId",productRandomKey);
        productMap.put("date",saveDate);
        productMap.put("time",saveTime);
        productMap.put("desc",desc);
        productMap.put("image",dowloadImageUrl);
        productMap.put("category",categoryName);
        productMap.put("price",price);
        productMap.put("name",pname);

        ProdcutRef.child(productRandomKey).updateChildren(productMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(AdminAddNewwProdctActivity.this,AdminCategoryActivity.class);
                                startActivity(intent);

                                loadingBar.dismiss();
                                Toast.makeText(AdminAddNewwProdctActivity.this, "Produit ajouter avec success...", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                loadingBar.dismiss();
                                String message = task.getException().toString();
                                Toast.makeText(AdminAddNewwProdctActivity.this, "Error "+message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    }

}
