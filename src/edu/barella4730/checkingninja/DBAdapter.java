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
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(DATABASE_CREATE);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			Log.w(TAG, "Upgrading database from version " + oldVersion + "to " + newVersion + ", which will destroy all previous data");
			db.execSQL("DROP TABLE IF EXISTS trans");
			onCreate(db);
		}			
	}
	
	public DBAdapter open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		DBHelper.close();
	}
	
	public long insertTrans(String date, String payee, String amount, String type, String category)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_DATE, date);
		initialValues.put(KEY_PAYEE, payee);
		initialValues.put(KEY_AMOUNT, amount);
		initialValues.put(KEY_TYPE, type);
		initialValues.put(KEY_CATEGORY, category);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	public boolean deleteTrans(long rowId)
	{
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	public Cursor getAllTrans()
	{
		return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_PAYEE, KEY_AMOUNT, KEY_TYPE, KEY_CATEGORY}, null, null, null, null, null);
	}
	
	public Cursor getTrans(long rowId) throws SQLException
	{
		Cursor mCursor = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_PAYEE, KEY_AMOUNT, KEY_TYPE, KEY_CATEGORY}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
		
		if (mCursor != null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	public long updateTrans(long rowId, String date, String payee, String amount, String type, String category)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_DATE, date);
		args.put(KEY_PAYEE, payee);
		args.put(KEY_AMOUNT, amount);
		args.put(KEY_TYPE, type);
		args.put(KEY_CATEGORY, category);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null);
	}
}