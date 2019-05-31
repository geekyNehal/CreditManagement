package com.geekynehal.creditmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView myCredit;
    Button transferTable,viewUsers,transferCredit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCredit=findViewById(R.id.my_credit_text);
        transferTable=findViewById(R.id.transfer_table_button);
        viewUsers=findViewById(R.id.view_users_button);
        transferCredit=findViewById(R.id.transfer_credit_button);

    }
}
