package com.geekynehal.creditmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="mydatabase.db";
    public static final int VERSION=1;
    //Table Name
    public static final String TABLE_NAME_TRANS="trans";
    public static final String TABLE_NAME_USERS="users";
    //Columns
    public static final String COL_TRANS_NAME="name";
    public static final String COL_TRANS_CREDIT="credit";
    public static final String COL_USER_NAME="name";
    public static final String COL_USER_EMAIL="email";
    public static final String COL_USER_CREDIT="credit";
    //Table Creation
    public static final String CREATE_TRANS="create table "+TABLE_NAME_TRANS+" ("+COL_TRANS_NAME+" text,"+COL_TRANS_CREDIT+" text)";
    public static final String CREATE_USERS="create table "+TABLE_NAME_USERS+" ("+COL_USER_NAME+" text,"+COL_USER_EMAIL+" text,"+COL_USER_CREDIT+" text)";

    public MyDatabase(Context context)
    {
       super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_TRANS);
        sqLiteDatabase.execSQL(CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME_USERS);
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME_TRANS);

    }
}
