package com.example.secondderivable;
package com.example.mealerapp;


import android.util.Log;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;


    public class ComplaintsDataBase {
        private DatabaseReference userReference;
        private FirebaseDatabase database;
        private FirebaseAuth auth;
        public enum dataField{COMPLAINTS, COOKNAME, ACTION};
        private String uID = "null";

        public ComplaintsDataBase(){
            database = FirebaseDatabase.getInstance();
            userReference = database.getReference().child("USERS");
            auth = FirebaseAuth.getInstance();
        }

        private void addComplaint(Complaints complaints) {

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // inside the method of on Data change we are setting
                    // our object class to our database reference.
                    // data base reference will sends data to firebase.
                    databaseReference.setValue(complaints);

                    // after adding this data we are showing toast message.
                    Toast.makeText( "data added", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // if the data is not added or it is cancelled then
                    // we are displaying a failure toast message.
                    Toast.makeText( "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                }
            });
        }

        //
        public void changeInfo(User user,  dataField field, String newInfo){

            //Make sure someone is logged in before trying to change their information
            if(auth.getCurrentUser()!=null)
                userReference.child(user.getRole().toString()).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(field.toString().toLowerCase()).setValue(newInfo);
        }

        //Login user using email and password
        public void login(String email, String password){

            auth.signInWithEmailAndPassword(email,password);

        }

        //Log out current user
        public void logoff(){

            auth.signOut();

        }
    }

