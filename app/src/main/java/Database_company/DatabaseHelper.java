package Database_company;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.pc_care.Employee;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "EmployeeData.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES1 = "CREATE TABLE " + EmployeeMaster.Employee.TABLE_NAME + " (" +
                EmployeeMaster.Employee._ID + " INTEGER PRIMARY KEY," +
                EmployeeMaster.Employee.COLUMN_NAME_NAME + " TEXT," +
                EmployeeMaster.Employee.COLUMN_NAME_AGE + " INTEGER," +
                EmployeeMaster.Employee.COLUMN_NAME_PHONE + " INTEGER," +
                EmployeeMaster.Employee.COLUMN_NAME_EMAIL + " TEXT," +
                EmployeeMaster.Employee.COLUMN_NAME_SALARY + " DOUBLE);";

        db.execSQL(SQL_CREATE_ENTRIES1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(String name, int age, int phone, String email, double salary) {

            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(EmployeeMaster.Employee.COLUMN_NAME_NAME, name);
            values.put(EmployeeMaster.Employee.COLUMN_NAME_AGE, age);
            values.put(EmployeeMaster.Employee.COLUMN_NAME_PHONE, phone);
            values.put(EmployeeMaster.Employee.COLUMN_NAME_EMAIL, email);
            values.put(EmployeeMaster.Employee.COLUMN_NAME_SALARY, salary);

            long result = db.insert(EmployeeMaster.Employee.TABLE_NAME, null, values);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
    }

    public List readAllInfo() {

        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                EmployeeMaster.Employee._ID,
                EmployeeMaster.Employee.COLUMN_NAME_NAME,
                EmployeeMaster.Employee.COLUMN_NAME_AGE,
                EmployeeMaster.Employee.COLUMN_NAME_PHONE,
                EmployeeMaster.Employee.COLUMN_NAME_EMAIL,
                EmployeeMaster.Employee.COLUMN_NAME_SALARY,


        };
        String sortorder = EmployeeMaster.Employee.COLUMN_NAME_NAME + " DESC";
        Cursor cursor = db.query(EmployeeMaster.Employee.TABLE_NAME, projection, null, null, null, null, sortorder);

        List namelist = new ArrayList();
        List agelist = new ArrayList();
        List phonelist = new ArrayList();
        List emaillist = new ArrayList();
        List salarylist = new ArrayList();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(EmployeeMaster.Employee.COLUMN_NAME_NAME));
            String age = cursor.getString(cursor.getColumnIndexOrThrow(EmployeeMaster.Employee.COLUMN_NAME_AGE));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(EmployeeMaster.Employee.COLUMN_NAME_PHONE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(EmployeeMaster.Employee.COLUMN_NAME_EMAIL));
            String salary = cursor.getString(cursor.getColumnIndexOrThrow(EmployeeMaster.Employee.COLUMN_NAME_SALARY));

            namelist.add(name);
            agelist.add(age);
            phonelist.add(phone);
            emaillist.add(email);
            salarylist.add(salary);

        }
        return namelist;

    }

    public boolean deleteUser(int id) {

        SQLiteDatabase db = getReadableDatabase();

        String select  = EmployeeMaster.Employee._ID+" Like ?";

        String[] selectionargs = {String.valueOf(id)};

        int result = db.delete(EmployeeMaster.Employee.TABLE_NAME,select,selectionargs);

        if(result>0)
        {
            return true;
        }
        else
        {
            return false;
        }



    }

    public Cursor getData(String sql) {

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);

    }





    public boolean updateUser(String name,int age,int phone,String email,double salary)
    {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(EmployeeMaster.Employee.COLUMN_NAME_NAME,name);
        contentValues.put(EmployeeMaster.Employee.COLUMN_NAME_AGE,age);
        contentValues.put(EmployeeMaster.Employee.COLUMN_NAME_PHONE,phone);
        contentValues.put(EmployeeMaster.Employee.COLUMN_NAME_EMAIL,email);
        contentValues.put(EmployeeMaster.Employee.COLUMN_NAME_SALARY,salary);



        String select = EmployeeMaster.Employee.COLUMN_NAME_NAME +" Like ?";

        String[] selctionArgs = {name};

        int result = database.update(EmployeeMaster.Employee.TABLE_NAME,contentValues,select,selctionArgs);

        if(result > 0)
        {

            return true;
        }
        else
        {
            return false;
        }

    }


}