package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MenuPage extends AppCompatActivity implements MenuAdapter.RecyclerViewInterface{

    private String UID;
    private RecyclerView recyclerView;
    private MenuDatabase dtb;
    private MenuAdapter adapter;
    private ArrayList<MealModel> list;

    private Button welcomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        Bundle bundle = new Bundle();
        UID = bundle.getString("UID");
        welcomeButton = (Button) findViewById(R.id.returnWelcomeButton);
        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnWelcome = new Intent(getApplicationContext(),WelcomePage.class);
                returnWelcome.putExtra("role","COOK");
                startActivity(returnWelcome);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.menuRecyclerView);
        list = new ArrayList<>();
        adapter = new MenuAdapter(this, list,this);

        recyclerView.setAdapter(adapter);

        MenuDatabase.retrieveListener listener = new MenuDatabase.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                MealModel model = (MealModel) data;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError() {

            }
        };

        dtb.getMenu(UID,listener);

    }

    @Override
    public void onItemClick(int position, String name) {

    }
}