package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Bundle bundle = getIntent().getExtras();
        String role = bundle.getString("role");


        TextView userProfile = (TextView) findViewById(R.id.UserProfile);
        userProfile.setText(role);


        Button logoff = (Button) findViewById(R.id.LogOff);
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInPage(v);
            }
        });

        Button makeComplaint = (Button) findViewById(R.id.MakeComplaint);
        makeComplaint.setVisibility(View.GONE);
        makeComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {CreateComplaints(v); }
        });

        Button viewPro = (Button) findViewById(R.id.viewProfile);
        viewPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ViewProfile(v); }
        });

        Button nextActivity = (Button) findViewById(R.id.nextActivityButton);
        Button requests = (Button)findViewById(R.id.requestsButton);
        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewRequests(v,role);
            }
        });

        if(role.equals("CLIENT")){
            nextActivity.setText("Search Meals");
            nextActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchMeals(v);
                }
            });
            makeComplaint.setVisibility(View.VISIBLE);
            makeComplaint.setVisibility(View.VISIBLE);
        }

        else if(role.equals("ADMIN")){
            nextActivity.setText("View Complaints");
            requests.setVisibility(View.GONE);
            nextActivity.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    ComplaintsPage(v);
                }
            });
            //makeComplaint.setVisibility(View.GONE);
        }
        else if(role.equals("COOK")){
            nextActivity.setText("View Menu");
            nextActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuPage(v);
                }
            });
            //makeComplaint.setVisibility(View.GONE);
        }


    }

    public void LogInPage(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);


    }

    public void viewRequests(View view,String role){
        Intent viewRequestsPage = new Intent(getApplicationContext(), ViewPurchaseRequests.class);
        viewRequestsPage.putExtra("UID",UserDatabase.getUID());
        viewRequestsPage.putExtra("role",role);
        startActivity(viewRequestsPage);
    }

    public void CreateComplaints(View view){
        Intent intent = new Intent(getApplicationContext(), CreateComplaint.class);
        startActivity(intent);
    }

    public void ComplaintsPage(View view){
        Intent intent = new Intent(getApplication(), ComplaintsPage.class);
        startActivity(intent);
    }

    public void ViewProfile(View view){
        Intent intent = new Intent(getApplication(), ViewProfile.class);
        startActivity(intent);
    }

    public void menuPage(View view){
        Intent menuPage = new Intent(getApplicationContext(), MenuPage.class);
        UserDatabase dtb = new UserDatabase();
        String uid = dtb.getUID();
        menuPage.putExtra("UID", uid);
            startActivity(menuPage);

    }

    public void searchMeals(View view){
        Intent searchMealsPage = new Intent(getApplicationContext(),SearchMeals.class);
        startActivity(searchMealsPage);
    }


}