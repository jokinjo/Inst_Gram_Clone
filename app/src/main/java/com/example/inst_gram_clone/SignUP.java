package com.example.inst_gram_clone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUP extends AppCompatActivity {

    private EditText edtEMail, edtUserName, edtPW, edtSignUp, edtLogIn;

    private static final String TAG = "SignUP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Sign Up");

        edtEMail = findViewById(R.id.edtEMail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPW = findViewById(R.id.edtPW);
        edtSignUp = findViewById(R.id.edtSignUp);
        edtLogIn = findViewById(R.id.edtLogIn);

        if (ParseUser.getCurrentUser() != null) ParseUser.getCurrentUser().logOut();

        edtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser parseUser = new ParseUser();
                parseUser.setUsername(edtUserName.getText().toString());
                parseUser.setEmail(edtEMail.getText().toString());
                parseUser.setPassword(edtPW.getText().toString());

                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {
                            FancyToast.makeText(SignUP.this, parseUser.getUsername() + " is logged in", FancyToast.LENGTH_LONG);
                        } else {
                            FancyToast.makeText(SignUP.this, e.getMessage(), FancyToast.LENGTH_LONG);
                        }
                    }
                });
            }
        });
        edtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUP.this, LoginActivity.class);
                startActivity(intent);

                //            ParseUser parseUser = new ParseUser();
                //            parseUser.setUsername(edtUserName.getText().toString());
                //            parseUser.setEmail(edtEMail.getText().toString());
                //            parseUser.setPassword(edtPW.getText().toString());
            }
        });
    }


}
