package com.example.pc_care;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import StockDatabase.SQLiteHelper_Stock_M;

public class Stock_M_ListActivity extends AppCompatActivity {
    ListView mListView;
    ArrayList<Model_Stock_M> mList;
    Stock_M_ListAdapter mAdapter = null;
    ImageButton btnAdd;
    public static SQLiteHelper_Stock_M mSQLiteHelper_Stock_M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock__m__list);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Record List");
*/
        btnAdd = findViewById(R.id.add);
        mListView = findViewById(R.id.ListStockM);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Stock_M_ListActivity.this,Stock_M_add.class));

            }
        });
        mList = new ArrayList<>();
        mAdapter = new Stock_M_ListAdapter(this,R.layout.row_stock_m,mList);
        mListView.setAdapter(mAdapter);
        final Cursor cursor = mSQLiteHelper_Stock_M.getData("SELECT * FROM Stock_Motherboard");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String itemcode = cursor.getString(1);
            String model = cursor.getString(2);
            String brand = cursor.getString(3);
            String availability = cursor.getString(4);
            mList.add(new Model_Stock_M(id,itemcode,model,brand,availability));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size()==0){
            Toast.makeText(this,"No record found....",Toast.LENGTH_SHORT).show();

        }

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Stock_M_ListActivity.this);
                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            Cursor c = mSQLiteHelper_Stock_M.getData("SELECT _id FROM Stock_Motherboard");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogUpdate(Stock_M_ListActivity.this,arrID.get(position));



                        }
                        if (i == 1){
                            Cursor c = mSQLiteHelper_Stock_M.getData("SELECT _id FROM Stock_Motherboard");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
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
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(Stock_M_ListActivity.this);
        dialogDelete.setTitle("Warning !");
        dialogDelete.setMessage("Are you sure to delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                    mSQLiteHelper_Stock_M.deleteStock(idRecord);
                    Toast.makeText(Stock_M_ListActivity.this,"Delete Successfully",Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Log.e("error", Objects.requireNonNull(e.getMessage()));
                }
            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();

    }

    private void showDialogUpdate(final Stock_M_ListActivity activity, final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog_stock_m);
        dialog.setTitle("Update");
        final EditText editItemcode = dialog.findViewById(R.id.edtItemCode);
        final EditText editModel = dialog.findViewById(R.id.edtModel);
        final EditText editBrand = dialog.findViewById(R.id.edtBrand);
        final EditText editAvailability = dialog.findViewById(R.id.edtQty);
        Button btnUpdate = dialog.findViewById(R.id.btnMoUpdate);


        final Cursor cursor = mSQLiteHelper_Stock_M.getData("SELECT * FROM Stock_Motherboard WHERE _id="+position);
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String itemcode = cursor.getString(1);
            editItemcode.setText(itemcode);
            String model = cursor.getString(2);
            editModel.setText(model);
            String brand = cursor.getString(3);
            editBrand.setText(brand);
            String availability = cursor.getString(4);
            editAvailability.setText(availability);
            mList.add(new Model_Stock_M(id,itemcode,model,brand,availability));
        }

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels*0.7);
        Objects.requireNonNull(dialog.getWindow()).setLayout(width,height);
        dialog.show();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    mSQLiteHelper_Stock_M.updateData(editItemcode.getText().toString(),editModel.getText().toString(), editBrand.getText().toString(), editAvailability.getText().toString());

                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();

                } catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }

                updateRecordList();

            }
        });

    }

    private void updateRecordList() {
        Cursor cursor = mSQLiteHelper_Stock_M.getData("SELECT * FROM Stock_Motherboard");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String itemcode = cursor.getString(1);
            String model = cursor.getString(2);
            String brand = cursor.getString(3);
            String availability = cursor.getString(4);
            mList.add(new Model_Stock_M(id,itemcode,model,brand,availability));


        }
        mAdapter.notifyDataSetChanged();
    }

}