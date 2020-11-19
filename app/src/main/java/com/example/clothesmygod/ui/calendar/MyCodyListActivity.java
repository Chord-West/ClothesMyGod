package com.example.clothesmygod.ui.calendar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clothesmygod.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MyCodyListActivity extends AppCompatActivity {
    private ListView listView;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    DatabaseReference codylistRef;
    StorageReference mStorageRef;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycodylist);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        codylistRef = database.getReference().child("users");
        listView= (ListView)findViewById(R.id.codylist);
        final ArrayList<CodyList> codyLists =  new ArrayList<>();




        adapter = new ArrayAdapter<String>(this, R.layout.activity_listitem, fileList);

        listView.setAdapter(adapter);


            // Read from the database
        codylistRef.child("codylist").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    CodyList str = fileSnapshot.getKey(CodyList.class);
                    codyLists.add(str);
                    adapter.add(str);
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("error");
            }
        });
    }

};
