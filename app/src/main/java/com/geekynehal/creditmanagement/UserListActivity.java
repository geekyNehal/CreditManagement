package com.geekynehal.creditmanagement;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class UserListActivity extends ListActivity
{
    ArrayAdapter<String> adapter;
    MyDatabase md;
    ArrayList<String> nameList=new ArrayList<String>();
    ArrayList<String> emailList=new ArrayList<String>();
    ArrayList<Integer> creditList=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        md=new MyDatabase(this);
        retrieve();
        setListAdapter(adapter);
        ListView listView=getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name=nameList.get(i).toString();
                String email=emailList.get(i).toString();
                String credit=creditList.get(i).toString();
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),UserDetails.class);
                intent.putExtra("name", name);
                intent.putExtra("email",email);
                intent.putExtra("credit",credit);
                startActivity(intent);
            }
        });
    }

    private void retrieve()
    {
        SQLiteDatabase sd=md.getReadableDatabase();
        Cursor cursor=sd.query(md.TABLE_NAME_USERS,null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            nameList.add(cursor.getString(0));
            emailList.add(cursor.getString(1));
            creditList.add(cursor.getInt(2));
        }
        ArrayList<String> combineList=new ArrayList<String>();
        for(int i=0;i<nameList.size();i++)
        {
            combineList.add(nameList.get(i)+"      --->     "+creditList.get(i));
        }
        adapter=new ArrayAdapter<String>(this,R.layout.user_list,combineList);

    }
}
