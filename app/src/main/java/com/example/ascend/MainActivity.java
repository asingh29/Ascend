/*package com.example.ascend;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText name;
    EditText password;
    Button button;
    Button butt2;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        button = findViewById(R.id.Signin);
        butt2 = findViewById(R.id.Signup);
        name = findViewById(R.id.Username);
        password = findViewById(R.id.Password);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                }
            }
        };

        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinUser();
            }
        });
    }
    public void goToHome(View view) {
        Intent i;
        i = new Intent(this, HomePage.class);
        startActivity(i);
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void createUser() {
        String username = name.getText().toString();
        String password2 = password.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Username is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password2)) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(username, password2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("Main", "createUserwithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    //updateUI(user);
                } else {
                    Log.w("Main", "createuserwithemail:fail");
                    Toast.makeText(MainActivity.this, "Authentification failed.", Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
            }
        });
    }

    public void signinUser() {
        String username = name.getText().toString();
        String pass2 = password.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Username is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass2)) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(username, pass2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                } else {
                    Log.w("Main", "createuserwithemail:fail");
                    Toast.makeText(MainActivity.this, "Couldn't log in.", Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
            }
        });
    }
}
*/