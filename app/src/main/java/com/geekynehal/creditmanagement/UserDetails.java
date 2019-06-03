package com.geekynehal.creditmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity implements View.OnClickListener {

    TextView userName,userEmail,creditText;
    Button transfer_button;
    String name,email,credit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Bundle bundle=getIntent().getExtras();
        userName=findViewById(R.id.user_name);
        userEmail=findViewById(R.id.user_email);
        creditText=findViewById(R.id.user_credit);
        transfer_button=findViewById(R.id.transfer_button);
        transfer_button.setOnClickListener(this);
        name=bundle.getString("name");
        email=bundle.getString("email");
        credit= bundle.getString("credit");
        userName.setText(name);
        userEmail.setText(email);
        creditText.setText(credit);
    }

    @Override
    public void onClick(View view) {
        try {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), TransferCreditActivity.class);
            intent.putExtra("name_a", name);
            startActivity(intent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
