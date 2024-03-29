package com.example.bichri.diaayema.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bichri.diaayema.Interface.ItemClickListener;
import com.example.bichri.diaayema.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
     public TextView txtProductName,txtProductDesc,txtProductPrice;
     public ImageView imageView;
     public ItemClickListener listener;

    public ProductViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView)itemView.findViewById(R.id.product_image);
        txtProductName = (TextView)itemView.findViewById(R.id.product_name);
        txtProductDesc = (TextView)itemView.findViewById(R.id.product_description);
        txtProductPrice = (TextView)itemView.findViewById(R.id.product_price);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;

    }

    @Override
    public void onClick(View view) {
        listener.onClick(view,getAdapterPosition(),false);
    }
}
