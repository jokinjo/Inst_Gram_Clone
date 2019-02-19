package com.example.inst_gram_clone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUP extends AppCompatActivity implements View.OnClickListener {

    private TextView btnClicked;
    private EditText editTextN;
    private EditText editTextKP;
    private EditText editTextKT;
    private EditText editTextPP;
    private EditText editTextPT;
    private TextView txtGetData;
    private Button btnGetAll;
    String strGetAll;

    private static final String TAG = "SignUP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnClicked = findViewById(R.id.btnSave);
        editTextN = findViewById(R.id.editTextN);
        editTextKP = findViewById(R.id.editText2);
        editTextKT = findViewById(R.id.editText3);
        editTextPP = findViewById(R.id.editText4);
        editTextPT = findViewById(R.id.editText5);
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAll = findViewById(R.id.btnGetAll);

        btnClicked.setOnClickListener(SignUP.this);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("jfzzCvM9kN", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            txtGetData.setText("Name: " +  object.get("Name").toString() +
                                       "\n" + "Punch Strength: " + object.get("punch_strength").toString() +
                                       "\n" + "Punch Speed: " + object.get("punch_speed").toString() +
                                       "\n" + "Kick Speed: " + object.get("kick_speed").toString() +
                                       "\n" + "Kick Strength: " + object.get("kick_strength").toString());
                        }
                    }
                });
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if ((objects.size() > 0) && (e == null)) {
                            strGetAll = "";
                            for (ParseObject kickBoxer : objects) {
                                strGetAll += kickBoxer.get("Name") + " - " + kickBoxer.get("kick_speed") + " - " + kickBoxer.get("kick_strength") + "\n";
                            }
                            FancyToast.makeText(SignUP.this, strGetAll, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        } else {
                            FancyToast.makeText(SignUP.this, "Empty list or " + e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });
        
    }

    @Override
    public void onClick(View view) {
        try {

            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("Name", editTextN.getText().toString());
            kickBoxer.put("kick_speed", Integer.parseInt(editTextKP.getText().toString()));
            kickBoxer.put("kick_strength", Integer.parseInt(editTextKT.getText().toString()));
            kickBoxer.put("punch_speed", Integer.parseInt(editTextPP.getText().toString()));
            kickBoxer.put("punch_strength", Integer.parseInt(editTextPT.getText().toString()));

            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUP.this, "kickBoxer: " + kickBoxer.get("Name") + " saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        //                    Toast.makeText(SignUP.this, "kickBoxer object saved sucessfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SignUP.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e) {
            FancyToast.makeText(SignUP.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true);
        }
    }
}


//    public void btnSaveClicked(View view) {
//        ParseObject kickBoxer = new ParseObject("KickBoxer");
//        kickBoxer.put("Name", editTextN.getText().toString());
//        kickBoxer.put("kick_speed", editTextKP.getText().toString());
////        kickBoxer.put("kick_strength", editTextKT.getText());
////        kickBoxer.put("punch_speed", editTextPP.getText());
////        kickBoxer.put("punch_strength", editTextPT.getText());
//            try {
//                kickBoxer.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if (e == null)
//                            Toast.makeText(SignUP.this, "kickBoxer object saved sucessfully", Toast.LENGTH_LONG).show();
//                    }
//                });
//            } catch (Exception e) {
//                Toast.makeText(SignUP.this, e.getMessage(), Toast.LENGTH_LONG).show();
//            }
//    }
//}}