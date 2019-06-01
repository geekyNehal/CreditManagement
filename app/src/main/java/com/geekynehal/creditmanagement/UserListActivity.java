package com.geekynehal.creditmanagement;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class UserListActivity extends ListActivity
{
    ArrayAdapter<String> adapter;
    MyDatabase md;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        md=new MyDatabase(this);
        retrieve();
        setListAdapter(adapter);

    }

    private void retrieve()
    {
        ArrayList<String> nameList=new ArrayList<String>();
        ArrayList<String> emailList=new ArrayList<String>();
        ArrayList<Integer> creditList=new ArrayList<Integer>();
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
            combineList.add(nameList.get(i)+"   --->   "+creditList.get(i));
        }
        adapter=new ArrayAdapter<String>(this,R.layout.user_list,combineList);

    }
}
