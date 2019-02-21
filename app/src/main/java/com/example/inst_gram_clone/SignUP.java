package com.example.inst_gram_clone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class SignUP extends AppCompatActivity {

    EditText edtEMail, edtUserName, edtPW, edtSignUp, edtLogIn;

    private static final String TAG = "SignUP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Sign Up");

//        setVisible(true);
//        setTitle("Sign Up");
     edtEMail = findViewById(R.id.edtEMail);
     edtUserName = findViewById(R.id.edtUserName);
     edtPW = findViewById(R.id.edtPW);
     edtSignUp = findViewById(R.id.edtSignUp);
     edtLogIn = findViewById(R.id.edtLogIn);

     edtSignUp.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
//             SignUp person
         }
     });
     edtLogIn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
//             Log In person
         }
      });
    }


}
