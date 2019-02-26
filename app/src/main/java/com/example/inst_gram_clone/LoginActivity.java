package com.example.inst_gram_clone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEMail, edtPW, edtSignUp, edtLogIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Sign Up");

        edtEMail = findViewById(R.id.edtEMail_LI);
        edtPW = findViewById(R.id.edtPW_LI);
        edtSignUp = findViewById(R.id.edtSignUp_LI);
        edtLogIn = findViewById(R.id.edtLogIn_LI);

        edtSignUp.setOnClickListener(LoginActivity.this);
        edtLogIn.setOnClickListener(LoginActivity.this);

        if (ParseUser.getCurrentUser() != null)  if (ParseUser.getCurrentUser() != null) ParseUser.getCurrentUser().logOut();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.edtLogIn_LI):
//                ParseUser parseUser = new ParseUser();
//                parseUser.
                ParseUser.logInInBackground(edtEMail.getText().toString(), edtPW.getText().toString(),
                        new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText(LoginActivity.this, " user: " + user.getUsername().toString() + " is logged in.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false);
                        } else {
                            FancyToast.makeText(LoginActivity.this, e.getMessage(), FancyToast.ERROR, FancyToast.LENGTH_LONG, true);
                        }
                    }
                });
                break;
            case (R.id.edtSignUp_LI):
                Intent intent = new Intent(LoginActivity.this, SignUP.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
