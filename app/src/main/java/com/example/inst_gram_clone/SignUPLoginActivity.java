package com.example.inst_gram_clone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUPLoginActivity extends AppCompatActivity {

    EditText edtNameSignUp, edtPasswordSignUp, edtNameLogin, edtPasswordLogin;
    Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtNameSignUp = findViewById(R.id.edtNameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtNameLogin = findViewById(R.id.edtNameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnSignUp = findViewById(R.id.btnSgnUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser parseUser = new ParseUser();
                parseUser.setUsername(edtNameSignUp.getText().toString());
                parseUser.setPassword(edtPasswordSignUp.getText().toString());
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUPLoginActivity.this, " " + parseUser.get("UserName") + parseUser.get("username") + parseUser.getUsername(),
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent = new Intent(SignUPLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);

                        } else {
                            FancyToast.makeText(SignUPLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null && user != null) {
                            Intent intent = new Intent(SignUPLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);

                            FancyToast.makeText(SignUPLoginActivity.this, user.get("user_name") + " is Logged In", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        } else {
                            FancyToast.makeText(SignUPLoginActivity.this, user.get("user_name") + " is NOT Logged In : " + e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });
    }
}
