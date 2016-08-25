package utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public  static final int DB_VERSION = 1;

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
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room1", "赵茜", "10:00-10:30", "神州专车需求讨论会-1", "E11-1" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room1", "赵茜", "14:00-15:00", "神州专车需求讨论会-2", "E11-1" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room1", "赵茜", "17:00-18:00", "神州专车需求讨论会-3", "E11-1" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room2", "赵茜", "14:00-15:00", "神州专车需求讨论会-23", "E11-1" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room3", "赵茜", "14:00-15:00", "神州专车需求讨论会-33", "E11-2" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room4", "赵茜", "14:00-15:00", "神州专车需求讨论会-43", "E11-3" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room5", "赵茜", "14:00-15:00", "神州专车需求讨论会-53", "E11-4" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room6", "赵茜", "14:00-15:00", "神州专车需求讨论会-63", "E11-5" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room7", "赵茜", "14:00-15:00", "神州专车需求讨论会-73", "E13-1" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room8", "赵茜", "14:00-15:00", "神州专车需求讨论会-83", "E13-1" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room9", "赵茜", "14:00-15:00", "神州专车需求讨论会-93", "E13-2" });
		db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
				new String[] { "Room10", "", "", "", "E13-2" });
		Toast.makeText(mContext, "Insert succeeded-ROOM.", Toast.LENGTH_SHORT).show();
		Log.d("权兴权意SQLite:","Insert succeeded-ROOM.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		switch (oldVersion){
//			case 1:
//			case 2:
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room1", "赵茜", "9:00-10:00", "神州专车需求讨论会-1", "E11-1" });
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room1", "赵茜", "10:00-10:30", "神州专车需求讨论会-2", "E11-1" });
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room1", "赵茜", "14:00-15:00", "神州专车需求讨论会-3", "E11-1" });
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room2", "赵茜", "14:00-15:00", "神州专车需求讨论会-23", "E11-1" });
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room3", "赵茜", "14:00-15:00", "神州专车需求讨论会-33", "E11-2" });
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room4", "赵茜", "14:00-15:00", "神州专车需求讨论会-43", "E11-3" });
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room5", "赵茜", "14:00-15:00", "神州专车需求讨论会-53", "E11-4" });
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room6", "赵茜", "14:00-15:00", "神州专车需求讨论会-63", "E11-5" });
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room7", "赵茜", "14:00-15:00", "神州专车需求讨论会-73", "E13-1" });
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room8", "赵茜", "14:00-15:00", "神州专车需求讨论会-83", "E13-1" });
//				db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
//						new String[] { "Room9", "赵茜", "14:00-15:00", "神州专车需求讨论会-93", "E13-2" });
//				Toast.makeText(mContext, "Insert succeeded-ROOM.", Toast.LENGTH_SHORT).show();
//				Log.d("权兴权意SQLite:","Insert succeeded-ROOM.");
//			default:
//		}

		db.execSQL("drop table if exists User");
		db.execSQL("drop table if exists Room");
		onCreate(db);
	}

}
