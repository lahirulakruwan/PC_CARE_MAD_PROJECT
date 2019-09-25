package com.example.pc_care;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.pc_care.Employee.mDatabaseHelper;

public class EmployeeList extends AppCompatActivity {

    ListView mListView;

ArrayList<Model> mList;
EmployeeListAdapter mAdapter = null;
//ImageView imageViewIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

       // ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Employee List");
        //to enable back button actionbar set parent main activity in manifest

     mListView = findViewById(R.id.listView);
     mList = new ArrayList<>();
     mAdapter = new EmployeeListAdapter(this,R.layout.row,mList);
     mListView.setAdapter(mAdapter);
     //get all data from sqlite
        Cursor cursor = mDatabaseHelper.getData("SELECT * FROM employees");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name  = cursor.getString(1);
            int age = cursor.getInt(2);
            int phone = cursor.getInt(3);
            String email = cursor.getString(4);
            double salary = cursor.getDouble(5);
            //byte[] image = cursor.getBlob(6);
            mList.add(new Model(id,name,age,phone,email,salary));
        }
           mAdapter.notifyDataSetChanged();
          if(mList.size()==0){
         //if there is no reord in table of database
              Toast.makeText(this,"No record found...",Toast.LENGTH_SHORT).show();


          }
          mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
              @Override
              public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l){
               //alert dialog to display options of update and delete
                 CharSequence[] items = {"Update","Delete"};
                  AlertDialog.Builder dialog = new AlertDialog.Builder(EmployeeList.this);
                  dialog.setTitle("Choose an action");
                  dialog.setItems(items,new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface,int i) {
                        if(i == 0){
                            //update
                            Cursor c = mDatabaseHelper.getData("SELECT _id FROM employees");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){

                                arrID.add(c.getInt(0));
                            }
                            //show update dialog
                            showDialogUpdate(EmployeeList.this,arrID.get(position));



                        }
                        if(i == 1){

                                 //delete
                             Cursor c = mDatabaseHelper.getData("SELECT _id FROM employees");
                             ArrayList<Integer> arrID = new ArrayList<Integer>();
                             while(c.moveToNext()){
                                 arrID.add(c.getInt(0));



                             }
                             showDialogDelete(arrID.get(position));

                        }

                     }

                  });
                  dialog.show();

                  return true;

              }



          });



    }

    private void showDialogDelete(final int idRecord) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(EmployeeList.this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure to delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                    mDatabaseHelper.deleteUser(idRecord);
                    Toast.makeText(EmployeeList.this,"Delete successfully",Toast.LENGTH_SHORT).show();


                }catch(Exception e)
                {

                    Log.e("error",e.getMessage());


                }
                updateRecordList();


            }
        });
          dialogDelete.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                  dialogInterface.dismiss();
              }
          });
          dialogDelete.show();
    }

    private void showDialogUpdate(final Activity activity, final int position)
    {

           final Dialog dialog = new Dialog(activity);
           dialog.setContentView(R.layout.activity_update__employee);
           dialog.setTitle("Update");

          // imageViewicon = dialog.findViewById(R.id.imageView);
        final EditText edtName = dialog.findViewById(R.id.editName);
        final EditText edtAge = dialog.findViewById(R.id.editAge);
        final EditText edtPhone = dialog.findViewById(R.id.editPhone);
        final EditText edtEmail = dialog.findViewById(R.id.editEmail);
        final EditText edtSalary = dialog.findViewById(R.id.editSalary);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

        Cursor cursor = mDatabaseHelper.getData("SELECT * FROM employees WHERE  _id="+position);
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name  = cursor.getString(1);
            edtName.setText(name);
            int age = cursor.getInt(2);
            edtAge.setText(String.valueOf(age));
            int phone = cursor.getInt(3);
            edtPhone.setText(String.valueOf(phone));
            String email = cursor.getString(4);
            edtEmail.setText(email);
            double salary = cursor.getDouble(5);
            edtSalary.setText(String.valueOf(salary));
            //byte[] image = cursor.getBlob(6);
            mList.add(new Model(id,name,age,phone,email,salary));
        }
        //get data of row clicked from sqlite


        //set width of dialog
        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        //set height o dialog
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        //in update dialog click image view to update image
       /* imageViewIcon.setOnClickListner(new mListView.hasOnClickListeners() {
            @Override
            public void onClick(View view)
            {
             //check external storage permissions



            }


                                        }



        );
*/
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Employee.mDatabaseHelper.updateUser(edtName.getText().toString(),Integer.parseInt(edtAge.getText().toString()), Integer.parseInt(edtPhone.getText().toString()),  edtEmail.getText().toString(), Double.parseDouble(edtSalary.getText().toString()));

                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();

                } catch (Exception error) {
                    Log.e("Upadte error", error.getMessage());
                }

                updateRecordList();

            }
        });



    }

    private void updateRecordList() {
        //get all data from sqlite
        Cursor cursor = mDatabaseHelper.getData("SELECT * FROM employees");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name  = cursor.getString(1);
            int age = cursor.getInt(2);
            int phone = cursor.getInt(3);
            String email = cursor.getString(4);
            double salary = cursor.getDouble(5);

            mList.add(new Model(id,name,age,phone,email,salary));


        }
        mAdapter.notifyDataSetChanged();

    }

}
