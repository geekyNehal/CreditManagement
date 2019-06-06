package com.geekynehal.creditmanagement;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button viewUsers,add_item;
    EditText name_text,email_text,credit_text;
    MyDatabase md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        md=new MyDatabase(this);
        viewUsers=findViewById(R.id.view_users_button);
        add_item=findViewById(R.id.add_item);
        name_text=findViewById(R.id.name_text);
        email_text=findViewById(R.id.email_text);
        credit_text=findViewById(R.id.current_credit_text);
        add_item.setOnClickListener(this);
        viewUsers.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.view_users_button:
                startActivity(new Intent(this,UserListActivity.class));
                break;

            case R.id.add_item:
                String name=name_text.getText().toString();
                String email=email_text.getText().toString();
                int credit=Integer.parseInt(credit_text.getText().toString());
                SQLiteDatabase sd=md.getWritableDatabase();
                ContentValues cv=new ContentValues();
                cv.put(md.COL_USER_NAME,name);
                cv.put(md.COL_USER_EMAIL,email);
                cv.put(md.COL_USER_CREDIT,credit);
                sd.insert(md.TABLE_NAME_USERS,md.COL_USER_NAME,cv);
                cv.clear();
                md.close();
                sd.close();
                Toast.makeText(this, "Values Added...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
