package edu.barella4730.checkingninja;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter
{
	public static final String KEY_ROWID = "_id";
	public static final String KEY_DATE = "date";
	public static final String KEY_PAYEE = "payee";
	public static final String KEY_AMOUNT = "amount";
	public static final String KEY_TYPE = "type";
	public static final String KEY_CATEGORY = "category";
	private static final String TAG = "DBAdapter";
	
	public static final String DATABASE_NAME = "transactions";
	public static final String DATABASE_TABLE = "trans";
	public static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE =
	        "create table titles (_id integer primary key autoincrement, "
	        + "date text not null, payee text not null, " 
	        + "amount real not null, type text not null, "
	        + "category text not null);";
	
	private final Context context;
	
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public DBAdapter(Context ctx)
	{
		
	}
	
}