package Database_company;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabseHelper2 extends SQLiteOpenHelper {
    public DatabseHelper2(Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("Create table userLogin(mail text primary key,password text,username text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
      db.execSQL("drop table if exists userLogin");
    }
    //inserting in database
    public boolean insert(String username,String email,String password){
        SQLiteDatabase db =this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",username);
        contentValues.put("mail",email);
        contentValues.put("pwd",password);
        long ins = db.insert("userLogin",null,contentValues);
        if(ins==1)return false;
        else return true;



    }
   //public if email exists
    public Boolean chkemail(String email){
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("Select * from userLogin where email=?",new String[]{email});
    if(cursor.getCount()>0) return false;
    else return true;
    }
}
