package com.ydhnwb.knowsit;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ydhnwb.knowsit.models.StudentModel;
import com.ydhnwb.knowsit.utilities.Constants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText nim, password;
    private Button btn_login;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        initComponents();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _nim = nim.getText().toString().trim();
                final String _password = password.getText().toString().trim();
                if(!_nim.isEmpty() && !_password.isEmpty()){
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference(Constants.REF_STUDENTS);
                    databaseReference.orderByChild("nim").equalTo(_nim).limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                String email = "";
                                for(DataSnapshot ds : dataSnapshot.getChildren()){
                                    email = ds.child("email").getValue(String.class);
                                }
                                Toast.makeText(LoginActivity.this, dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();
                                firebaseAuth.signInWithEmailAndPassword(email, _password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(LoginActivity.this, "Login cancelled", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(LoginActivity.this, "Please fill all forms first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initComponents(){
        btn_login = findViewById(R.id.btn_login);
        nim = findViewById(R.id.et_nim);
        password = findViewById(R.id.et_password);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void isLoggedIn(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isLoggedIn();
    }
}
