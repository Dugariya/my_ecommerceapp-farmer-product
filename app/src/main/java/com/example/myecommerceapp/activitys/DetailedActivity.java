package com.example.myecommerceapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myecommerceapp.R;
import com.example.myecommerceapp.models.NewProductsModel;
import com.example.myecommerceapp.models.PopularProductsModel;
import com.example.myecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView rating ,name ,description,price,quantity;
    Button addToCard , buyNow;
    ImageView addItem,removeItem;

      Toolbar toolbar;

     int totalQuantity=1;
     int totalPrice =0;


    // new product
    NewProductsModel newProductsModel=null;

    private FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    // popular products

    PopularProductsModel popularProductsModel = null;

    //Show all
    ShowAllModel showAllModel =null;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

       // getSupportActionBar().hide();

        firebaseFirestore=FirebaseFirestore.getInstance();

        auth=FirebaseAuth.getInstance();

       toolbar=findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final Object obj = getIntent().getSerializableExtra("detailed");

        if(obj instanceof NewProductsModel){
            newProductsModel=(NewProductsModel) obj;
        }else if (obj instanceof PopularProductsModel){
            popularProductsModel=(PopularProductsModel) obj;
        }else if (obj instanceof ShowAllModel){
            showAllModel=(ShowAllModel) obj;
        }

        detailedImg = findViewById(R.id.detailed_img);

        quantity = findViewById(R.id.quantity);

        name = findViewById(R.id.detailed_name);
        rating = findViewById(R.id.my_ratingId);
        description = findViewById(R.id.detailed_desc);
        price = findViewById(R.id.detailed_price);

        addItem= findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        addToCard = findViewById(R.id.Add_To_card);
        buyNow = findViewById(R.id.buy_now);



        if(newProductsModel != null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg);
            name.setText(newProductsModel.getName());
            rating.setText(newProductsModel.getRating());
            description.setText(newProductsModel.getDescription());
            price.setText(String.valueOf(newProductsModel.getPrice()));
            name.setText(newProductsModel.getName());

            totalPrice=newProductsModel.getPrice()*totalQuantity;

        }

        //popular product

        if(popularProductsModel != null){
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg);
            name.setText(popularProductsModel.getName());
            rating.setText(popularProductsModel.getRating());
            description.setText(popularProductsModel.getDescription());
            price.setText(String.valueOf(popularProductsModel.getPrice()));
            name.setText(popularProductsModel.getName());

            totalPrice=popularProductsModel.getPrice()*totalQuantity;

        }
        // ShowAll product

        if(showAllModel != null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));
            name.setText(showAllModel.getName());

            totalPrice=showAllModel.getPrice()*totalQuantity;


        }
        //Buy NOw

          buyNow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(DetailedActivity.this,AddressActivity.class);

                 if (newProductsModel!=null){
                     intent.putExtra("item",newProductsModel);
                 }

                 if (popularProductsModel!=null){
                     intent.putExtra("item",popularProductsModel);
                 }
                 if (showAllModel!=null){
                     intent.putExtra("item",showAllModel);
                 }
                 startActivity(intent);
             }
         });
        // Add to cart

        addToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCard();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(totalQuantity<10){
                   totalQuantity++;
                  quantity.setText(String.valueOf(totalQuantity));

                  if(newProductsModel != null){
                      totalPrice=newProductsModel.getPrice()*totalQuantity;

                  }
                  if(popularProductsModel != null){
                      totalPrice=popularProductsModel.getPrice()*totalQuantity;
                      }
                  if(showAllModel != null){
                      totalPrice=showAllModel.getPrice()*totalQuantity;
                      }


               }else {
                   Toast.makeText(DetailedActivity.this, "Maximum 10 item Order", Toast.LENGTH_SHORT).show();
               }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

    }

    private void addToCard() {
        String saveCurrentTime,savecurrenDate;
        Calendar calForDate =Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        savecurrenDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH:MM:SS a");
        saveCurrentTime=currentTime.format(calForDate.getTime());

        final HashMap<String ,Object> cartMap = new HashMap<>();

        cartMap.put("ProductName",name.getText().toString());
        cartMap.put("ProductPrice",price.getText().toString());
        cartMap.put("CurrentTime",saveCurrentTime);
        cartMap.put("CurrentDate",savecurrenDate);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice );

         firebaseFirestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                 .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
             @Override
             public void onComplete(@NonNull Task<DocumentReference> task) {
                 Toast.makeText(DetailedActivity.this, "Added to A Cart ", Toast.LENGTH_SHORT).show();
                 finish();
             }
         });

    }
}