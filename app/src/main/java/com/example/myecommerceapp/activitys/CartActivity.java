package com.example.myecommerceapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myecommerceapp.R;
import com.example.myecommerceapp.adapter.MyCartAdapter;
import com.example.myecommerceapp.models.MyCartModel;
import com.example.myecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.Internal;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity  {

    int overAllTotalAmount;
    TextView oveAllAmount;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<MyCartModel> myCartModelsList;
    MyCartAdapter cartAdapter;
    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        firebaseAuth =FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        toolbar=findViewById(R.id.My_Cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // get data form my crt

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessangerReceiver,new IntentFilter("MyTotalAmount"));

        oveAllAmount=findViewById(R.id.textView3);


        recyclerView=findViewById(R.id.cart_rec);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myCartModelsList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(this,myCartModelsList);
        recyclerView.setAdapter(cartAdapter);

        firebaseFirestore.collection("AddToCart").document(firebaseAuth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        MyCartModel myCartModel = doc.toObject(MyCartModel.class);
                        myCartModelsList.add(myCartModel);
                        cartAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        }
        public BroadcastReceiver mMessangerReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                int totalBill = intent.getIntExtra("totalAmount",0);
                oveAllAmount.setText("Total Amount : "+ totalBill +"₹");

            }
        };


    public void btnBuy(View view) {
        Intent intent = new Intent(this,AddressActivity.class);
        startActivity(intent);
    }
  /*  private void deleteItem() {
        // below line is for getting the collection
        // where we are storing our courses.
        db.collection("AddToCart").document(firebaseAuth.getCurrentUser().getUid())
                .collection("User").document()
                // after that we are getting the document
                // which we have to delete.
                // document(courses.getId()).

                // after passing the document id we are calling
                // delete method to delete this document.
                  //      delete().
                // after deleting call on complete listener
                // method to delete this data.
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // inside on complete method we are checking
                        // if the task is success or not.
                        if (task.isSuccessful()) {
                            // this method is called when the task is success
                            // after deleting we are starting our MainActivity.
                            // Toast.makeText(UpdateCourse.this, "Course has been deleted from Database.", Toast.LENGTH_SHORT).show();
                            // Intent i = new Intent(UpdateCourse.this, MainActivity.class);
                            // startActivity(i);
                        } else {
                            // if the delete operation is failed
                            // we are displaying a toast message.
                            //  Toast.makeText(UpdateCourse.this, "Fail to delete the course. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/
}
