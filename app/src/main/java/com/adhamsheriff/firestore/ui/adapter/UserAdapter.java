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
import com.adhamsheriff.firestore.model.UserName;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<UserName> userNameList;

    public UserAdapter(Context mCtx, List<UserName> userNameList) {
        this.mCtx = mCtx;
        this.userNameList = userNameList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_user_name_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        UserName storeName = userNameList.get(position);

        holder.textViewUserName.setText(storeName.getUserName());
        holder.textViewMobileNumber.setText(storeName.getMobilenumber());
    }

    @Override
    public int getItemCount() {
        return userNameList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewUserName, textViewMobileNumber;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewMobileNumber = itemView.findViewById(R.id.textViewMobileNumber);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
//            UserName product = userNameList.get(getAdapterPosition());
//            Intent intent = new Intent(mCtx, UpdateProductActivity.class);
//            intent.putExtra("product", product);
//            mCtx.startActivity(intent);
        }
    }

    public void filterList(ArrayList<UserName> filteredList) {
        userNameList = filteredList;
        notifyDataSetChanged();
    }
}