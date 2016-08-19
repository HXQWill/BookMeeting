package dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String CREATE_USER = "create table User ("
			+ "id integer primary key , "
			+ "name text, "
			+ "password text)";
	
	public static final String CREATE_ROOM = "create table Room ("
			+ "id integer primary key autoincrement, "
			+ "name text, "
			+ "owner text, "
			+ "time text, "
			+ "content text, "
			+ "local text)";

	private Context mContext;

	public MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER);
		db.execSQL(CREATE_ROOM);
		Toast.makeText(mContext, "Create succeeded-USER&ROOM.", Toast.LENGTH_SHORT).show();
		Log.d("权兴权意SQLite:","Create succeeded-USER&ROOM.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists User");
		db.execSQL("drop table if exists Room");
		onCreate(db);
	}

}
