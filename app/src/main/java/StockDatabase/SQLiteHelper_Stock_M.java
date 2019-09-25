package StockDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper_Stock_M extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pccare.db";

    public SQLiteHelper_Stock_M(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    public boolean insertData(String itemcode,String model,String brand,String availability){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StockM_Master.StockMainActivity.COLUMN_NAME_ITEMCODE,itemcode);
        values.put(StockM_Master.StockMainActivity.COLUMN_NAME_MODEL,model);
        values.put(StockM_Master.StockMainActivity.COLUMN_NAME_BRAND,brand);
        values.put(StockM_Master.StockMainActivity.COLUMN_NAME_AVAILABILITY,availability);

        long result = db.insert(StockM_Master.StockMainActivity.TABLE_NAME,null,values);
        if (result > 0){
            return true;
        }else {
            return false;
        }

    }

    public boolean updateData(String itemcode, String model, String brand, String availability){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StockM_Master.StockMainActivity.COLUMN_NAME_ITEMCODE,itemcode);
        contentValues.put(StockM_Master.StockMainActivity.COLUMN_NAME_MODEL,model);
        contentValues.put(StockM_Master.StockMainActivity.COLUMN_NAME_BRAND,brand);
        contentValues.put(StockM_Master.StockMainActivity.COLUMN_NAME_AVAILABILITY,availability);

        String select = StockM_Master.StockMainActivity.COLUMN_NAME_ITEMCODE +" Like ?";
        String[] selctionArgs = {itemcode};
        int result = database.update(StockM_Master.StockMainActivity.TABLE_NAME,contentValues,select,selctionArgs);

        if (result > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updateStock(String itemcode, String model, String brand, String availability){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StockM_Master.StockMainActivity.COLUMN_NAME_ITEMCODE,itemcode);
        contentValues.put(StockM_Master.StockMainActivity.COLUMN_NAME_MODEL,model);
        contentValues.put(StockM_Master.StockMainActivity.COLUMN_NAME_BRAND,brand);
        contentValues.put(StockM_Master.StockMainActivity.COLUMN_NAME_AVAILABILITY,availability);
        String select = StockM_Master.StockMainActivity.COLUMN_NAME_ITEMCODE +" Like ?";
        String[] selectionArgs = {itemcode};
        int result = database.update(StockM_Master.StockMainActivity.TABLE_NAME,contentValues,select,selectionArgs);

        if (result > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteStock(int id){
        SQLiteDatabase db = getReadableDatabase();
        String select = StockM_Master.StockMainActivity._ID+ " Like ?";
        String[] selectionargs = {String.valueOf(id)};
        int result = db.delete(StockM_Master.StockMainActivity.TABLE_NAME,select,selectionargs);

        if (result > 0){
            return true;
        }else {
            return false;
        }
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES1 = "CREATE TABLE " +StockM_Master.StockMainActivity.TABLE_NAME + " (" +
                StockM_Master.StockMainActivity._ID + " INTEGER PRIMARY KEY," +
                StockM_Master.StockMainActivity.COLUMN_NAME_ITEMCODE + " TEXT," +
                StockM_Master.StockMainActivity.COLUMN_NAME_MODEL + " TEXT," +
                StockM_Master.StockMainActivity.COLUMN_NAME_BRAND + " TEXT," +
                StockM_Master.StockMainActivity.COLUMN_NAME_AVAILABILITY + " TEXT);";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
