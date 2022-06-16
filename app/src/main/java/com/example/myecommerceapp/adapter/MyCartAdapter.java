package com.example.myecommerceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.example.myecommerceapp.R;
import com.example.myecommerceapp.activitys.MainActivity;
import com.example.myecommerceapp.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolede> {

    Context context;
    List<MyCartModel> list;
    int totalAmount = 0;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    private ItemClickListener clickListener;


    public MyCartAdapter(Context context, List<MyCartModel> list) {
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override
    public ViewHolede onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolede(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolede holder, int position) {

        holder.date.setText(list.get(position).getCurrentDate());
        holder.time.setText(list.get(position).getCurrentTime());
        holder.price.setText(list.get(position).getProductPrice() + "₹");
        holder.name.setText(list.get(position).getProductName());
        holder.totalPrice.setText(String.valueOf(list.get(position).getTotalPrice()));
        holder.totalQuantity.setText(list.get(position).getTotalQuantity());


       //Log.e("id", list.get(position).getItemId());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "Item has been deleted from Database.", Toast.LENGTH_SHORT).show();

               /* db.collection("AddToCart").document(firebaseAuth.getCurrentUser().getUid())
                        .collection("User").document(String.valueOf(holder.getItemId())).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });*/
            }
        });

        // totalamound pass to card activity
        totalAmount = totalAmount + list.get(position).getTotalPrice();

        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount", totalAmount);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolede extends RecyclerView.ViewHolder {
        TextView name, price, date, time, totalQuantity, totalPrice;
        ImageView ivDelete;

        public ViewHolede(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            totalQuantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);
            ivDelete = itemView.findViewById(R.id.ivDelete);

        }
    }

}
