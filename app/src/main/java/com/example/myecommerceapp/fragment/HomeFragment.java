package com.example.myecommerceapp.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myecommerceapp.R;
import com.example.myecommerceapp.activitys.ShowAllActivity;
import com.example.myecommerceapp.adapter.CategoryAdapter;
import com.example.myecommerceapp.adapter.NewProductsAdapter;
import com.example.myecommerceapp.adapter.PopularProductsAdapter;
import com.example.myecommerceapp.adapter.SliderAdapter;
import com.example.myecommerceapp.models.CategoryModel;
import com.example.myecommerceapp.models.NewProductsModel;
import com.example.myecommerceapp.models.PopularProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    TextView catShowAll,poularShowAll, newProductShowAll;



    LinearLayout linearLayout;
    ProgressDialog progressDialog;

      // category recyclerview
    RecyclerView catRecyclerview , newProductRecyclerview,popularRecyclerview;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelslist;

    // new product recyclerview
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;

    //PopularProduct Recyclerview

 PopularProductsAdapter popularProductsAdapter;
 List<PopularProductsModel> popularProductsModelList;


    // fireStore

    FirebaseFirestore db;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

          db= FirebaseFirestore.getInstance();

         progressDialog = new ProgressDialog(getActivity());


        catRecyclerview =root.findViewById(R.id.rec_category);
        newProductRecyclerview=root.findViewById(R.id.new_product_rec);
        popularRecyclerview=root.findViewById(R.id.popular_rec);
        catShowAll=root.findViewById(R.id.category_see_all)  ;
        newProductShowAll=root.findViewById(R.id.newProducts_see_all)  ;
        poularShowAll=root.findViewById(R.id.popular_see_all)  ;


            catShowAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ShowAllActivity.class);
                    startActivity(intent);
                }
            });
            newProductShowAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ShowAllActivity.class);
                    startActivity(intent);
                }
            });
            poularShowAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ShowAllActivity.class);
                    startActivity(intent);
                }
            });

           linearLayout=root.findViewById(R.id.home_layout);
           linearLayout.setVisibility(View.GONE);
        // image slider
        ImageSlider imageSlider =root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.gehubanner,"Discount On Wheats Items", ScaleTypes.CENTER_CROP));

        slideModels.add(new SlideModel(R.drawable.ricebanner,"Discount On Rice", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.liquidefertilizer," Liquidefertilizer 70% OFF", ScaleTypes.CENTER_CROP));
         slideModels.add(new SlideModel(R.drawable.dap," fertilizer 30% OFF", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);


         progressDialog.setTitle("Welcome To My ECommerce APP");
         progressDialog.setMessage("Please wait......");
         progressDialog.setCanceledOnTouchOutside(false);
         progressDialog.show();


        // category

        catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelslist=new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(),categoryModelslist);
        catRecyclerview.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoryModel categoryModel =document.toObject(CategoryModel.class);
                                categoryModelslist.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                                 }
                        } else {


                        }
                    }
                });

        // New Products
        newProductRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModelList=new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter(getContext(),newProductsModelList);
        newProductRecyclerview.setAdapter(newProductsAdapter);
        db.collection("NewProductsNew")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NewProductsModel newProductsModel=document.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                               newProductsAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // Popular products

        popularRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductsModelList=new ArrayList<>();
        popularProductsAdapter = new PopularProductsAdapter(getContext(),popularProductsModelList);
        popularRecyclerview.setAdapter(popularProductsAdapter);
        db.collection("NewProductsNew")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularProductsModel popularProductsModel=document.toObject(PopularProductsModel.class);
                                popularProductsModelList.add(popularProductsModel);
                                popularProductsAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }
}