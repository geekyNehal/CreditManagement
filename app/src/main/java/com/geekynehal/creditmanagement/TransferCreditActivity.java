package com.geekynehal.creditmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TransferCreditActivity extends AppCompatActivity {
    String name;
    Spinner spinner_from,spinner_to;
    Button submit;
    EditText amt;
    int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_credit);
        Bundle bundle=getIntent().getExtras();
        name=bundle.getString("name");
        spinner_from=findViewById(R.id.spinner_from);
        spinner_to=findViewById(R.id.spinner_to);
        submit=findViewById(R.id.submit);
        amt=findViewById(R.id.amount_text);
        amount=Integer.parseInt(amt.getText().toString());
    }
}
