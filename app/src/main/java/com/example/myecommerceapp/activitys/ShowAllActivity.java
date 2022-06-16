package com.example.myecommerceapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.myecommerceapp.R;
import com.example.myecommerceapp.adapter.ShowAllAdapter;
import com.example.myecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel>showAllModelList;

    FirebaseFirestore firebaseFirestore;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

       // getActionBar().hide();
        toolbar=findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);


        String type = getIntent().getStringExtra("type");


        firebaseFirestore=FirebaseFirestore.getInstance();


        recyclerView=findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModelList=new ArrayList<>();
        showAllAdapter=new ShowAllAdapter(this,showAllModelList);
        recyclerView.setAdapter(showAllAdapter);

      /*  firebaseFirestore.collection("ShowAll")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                showAllModelList.add(showAllModel);
                                showAllAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
*/


        if (type == null || type.isEmpty()){
             firebaseFirestore.collection("ShowAll")
                     .get()
                     .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                         @Override
                         public void onComplete(@NonNull Task<QuerySnapshot> task) {
                             if (task.isSuccessful()){
                                 for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                     ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                     showAllModelList.add(showAllModel);
                                     showAllAdapter.notifyDataSetChanged();
                                 }
                             }
                         }
                     });

         }
         if (type!=null && type.equalsIgnoreCase("pulses")){

             firebaseFirestore.collection("ShowAll").whereEqualTo("type" ,"pulses")
                     .get()
                     .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                         @Override
                         public void onComplete(@NonNull Task<QuerySnapshot> task) {
                             if (task.isSuccessful()){
                                 for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                     ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                     showAllModelList.add(showAllModel);
                                     showAllAdapter.notifyDataSetChanged();
                                 }
                             }
                         }
                     });

         }
         if (type!=null && type.equalsIgnoreCase("seeds")){

            firebaseFirestore.collection("ShowAll").whereEqualTo("type" ,"seeds")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });

        }
        if (type!=null && type.equalsIgnoreCase("fertilizer")){

            firebaseFirestore.collection("ShowAll").whereEqualTo("type" ,"fertilizer")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });

        }if (type!=null && type.equalsIgnoreCase("liquide Fertilizer")){

            firebaseFirestore.collection("ShowAll").whereEqualTo("type" ,"liquide Fertilizer")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });

        }if (type!=null && type.equalsIgnoreCase("fertilizer")){

            firebaseFirestore.collection("NewProductsNew").whereEqualTo("type" ,"fertilizer")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });

        }
        if (type!=null && type.equalsIgnoreCase("pulses")){

            firebaseFirestore.collection("NewProductsNew").whereEqualTo("type" ,"pulses")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });

        }




    }
}