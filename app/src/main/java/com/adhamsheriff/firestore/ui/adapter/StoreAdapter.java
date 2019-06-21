package com.adhamsheriff.firestore.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adhamsheriff.firestore.R;
import com.adhamsheriff.firestore.model.StoreName;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<StoreName> storeNameList;

    public StoreAdapter(Context mCtx, List<StoreName> storeNameList) {
        this.mCtx = mCtx;
        this.storeNameList = storeNameList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_store_name_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        StoreName storeName = storeNameList.get(position);

        holder.textViewStoreName.setText(storeName.getStorename());
        holder.textViewMobileNumber.setText(storeName.getMobilenumber());
        holder.textViewContactPerson.setText(storeName.getContactperson());
        holder.textViewAddress.setText(storeName.getAddress());
    }

    @Override
    public int getItemCount() {
        return storeNameList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewStoreName, textViewMobileNumber, textViewContactPerson, textViewAddress;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewStoreName = itemView.findViewById(R.id.textViewStoreName);
            textViewMobileNumber = itemView.findViewById(R.id.textViewMobileNumber);
            textViewContactPerson = itemView.findViewById(R.id.textViewContactPerson);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
//            StoreName product = storeNameList.get(getAdapterPosition());
//            Intent intent = new Intent(mCtx, UpdateProductActivity.class);
//            intent.putExtra("product", product);
//            mCtx.startActivity(intent);
        }
    }
}