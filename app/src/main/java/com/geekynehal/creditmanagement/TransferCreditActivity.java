package com.geekynehal.creditmanagement;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TransferCreditActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    String name_from,name_to;
    Spinner spinner_to;
    Button submit;
    EditText amt;
    int amount;
    MyDatabase myDatabase;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_transfer_credit);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Bundle bundle=getIntent().getExtras();
        name_from=bundle.getString("name_a");
        spinner_to=findViewById(R.id.spinner_to);
        submit=findViewById(R.id.submit);
        amt=findViewById(R.id.amount_text);
        myDatabase=new MyDatabase(this);

        //Writing values in to_spinner
        ArrayList<String> nameList=new ArrayList<String>();
        SQLiteDatabase sd=myDatabase.getReadableDatabase();
        Cursor cursor=sd.query(MyDatabase.TABLE_NAME_USERS,null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            nameList.add(cursor.getString(0));
        }
        adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,nameList);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_to.setAdapter(adapter);
        spinner_to.setOnItemSelectedListener(this);
        submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {
        amount=Integer.parseInt(amt.getText().toString());
        int curr_credit=getCredit();
        int credit_from=curr_credit-amount;
        int credit_to=curr_credit+amount;
        updateCredit(name_from,credit_from);
        updateCredit(name_to,credit_to);
        Toast.makeText(this, "Value Updated successfully..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),UserListActivity.class));
    }

    private void updateCredit(String name, int credit)
    {
        SQLiteDatabase sd=myDatabase.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MyDatabase.COL_USER_CREDIT,credit);
        String whereClause=MyDatabase.COL_USER_NAME+"=?";
        String[] whereArgs={name};
        sd.update(MyDatabase.TABLE_NAME_USERS,contentValues,whereClause,whereArgs);
    }

    private int getCredit()
    {
        SQLiteDatabase sd=myDatabase.getReadableDatabase();
        String[] columns={MyDatabase.COL_USER_CREDIT};
        String selection=MyDatabase.COL_USER_NAME+"=?";
        String[] selection_args={name_from};
        Cursor cursor=sd.query(MyDatabase.TABLE_NAME_USERS,columns,selection,selection_args,null,null,null);
        int curr_credit=0;
        int flag=0;
        while(cursor.moveToNext()&&flag!=1)
        {
            curr_credit=cursor.getInt(cursor.getColumnIndex(MyDatabase.COL_USER_CREDIT));
            flag++;
        }
        return curr_credit;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        name_to=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
