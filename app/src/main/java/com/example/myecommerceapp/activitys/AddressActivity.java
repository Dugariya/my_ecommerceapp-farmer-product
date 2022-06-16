package com.example.myecommerceapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myecommerceapp.R;
import com.example.myecommerceapp.adapter.AddressAdapter;
import com.example.myecommerceapp.models.AddressModel;
import com.example.myecommerceapp.models.NewProductsModel;
import com.example.myecommerceapp.models.PopularProductsModel;
import com.example.myecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements  AddressAdapter.SelectedAddress{

    Button addAddressBtn,paymentBtn;
    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdapter addressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Toolbar toolbar;
    String mAddress = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


        firestore =FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        // get Data from detailed activity---------------------------------;
        Object obj= getIntent().getSerializableExtra("item");


        // --RecyclerView --------------------------------------------------;

        recyclerView=findViewById(R.id.address_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressModelList = new ArrayList<>();
        addressAdapter= new AddressAdapter(getApplicationContext(),addressModelList,this);
        recyclerView.setAdapter(addressAdapter);
        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                   if (task.isSuccessful()){
                       for (DocumentSnapshot doc : task.getResult().getDocuments()){
                           AddressModel addressModel =doc.toObject(AddressModel.class);
                           addressModelList.add(addressModel);
                           addressAdapter.notifyDataSetChanged();
                       }
                   }

            }
        });

        // --------------------------------------------------------------------------------

        // tool bar areas ---------------------------------------------------------------
        toolbar=findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       // --------------------------------------------------------------------------------



        // AddresButton call-------------------------------------------------------------
        addAddressBtn = findViewById(R.id.add_address_btn);

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this,AddAddressActivity2.class));
            }
        });
      //-----------------------------------------------------------------------------------


      // Payment Button call-------------------------------------------------------------

        paymentBtn = findViewById(R.id.payment_btn);

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double amount = 0.0;
                if (obj instanceof NewProductsModel){
                    NewProductsModel newProductsModel =  ( NewProductsModel ) obj ;
                    amount=newProductsModel.getPrice();
                }
                if (obj instanceof PopularProductsModel){
                    PopularProductsModel popularProductsModel =  ( PopularProductsModel ) obj ;
                    amount=popularProductsModel.getPrice();
                }
                if (obj instanceof ShowAllModel){
                    ShowAllModel showAllModel =  ( ShowAllModel ) obj ;
                    amount=showAllModel.getPrice();
                }
               Intent intent = new Intent(AddressActivity.this,PaymentActivity.class);
               intent.putExtra("amount",amount);
               startActivity(intent);


            }
        });
      //-----------------------------------------------------------------------------------

    }

    @Override
    public void setAddress(String address) {
       mAddress =address;
    }


}