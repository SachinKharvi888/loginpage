package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.loginpage.modle.LoginUser;
import com.example.loginpage.modle.listAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    ImageView back;
    private RecyclerView recyclerView;
    listAdapter listAdapters;

    private List<LoginUser> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        back = findViewById(R.id.back);

        recyclerView = findViewById(R.id.listUser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserListActivity.this));
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        list = new ArrayList<>();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserListActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list.clear();
                for(DataSnapshot snapshot: datasnapshot.getChildren()){
                    LoginUser usr = snapshot.getValue(LoginUser.class);
                    list.add(usr);

                }
               listAdapters =new listAdapter(UserListActivity.this,list);
                recyclerView.setAdapter(listAdapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}