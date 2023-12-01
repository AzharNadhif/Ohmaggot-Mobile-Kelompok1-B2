package com.example.projectakhir;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private static List<ProductModel> productList;
    private Context context;
    private AdapterView.OnItemClickListener onItemClickListener;

    public ProductAdapter(List<ProductModel> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        ProductModel product = productList.get(position);

        holder.productNameTextView.setText(product.getNama_produk());
        holder.productPriceTextView.setText(product.getHarga());

        Picasso.get().load(product.getFoto()).into(holder.productImageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView productNameTextView;
        TextView productPriceTextView;
        ImageView productImageView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.textViewName);
            productPriceTextView = itemView.findViewById(R.id.textViewPrice);
            productImageView = itemView.findViewById(R.id.ImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ProductModel clickedProduct = productList.get(position);
                        showProductDetails(clickedProduct);
                    }
                }
            });
        }
    }

    private void showProductDetails(ProductModel product) {
        Intent intent = new Intent(context, detailProduk.class);
        intent.putExtra("nama_produk", product.getNama_produk());
        intent.putExtra("harga", product.getHarga());
        intent.putExtra("foto", product.getFoto());
        intent.putExtra("deskripsi", product.getDeskripsi());
        context.startActivity(intent);
    }
}
