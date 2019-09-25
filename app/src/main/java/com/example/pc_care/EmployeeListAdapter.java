package com.example.pc_care;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EmployeeListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Model> recordList;

    public EmployeeListAdapter(Context context, int layout, ArrayList<Model> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{

   //ImageView imageView;
        TextView txtName,txtAge,txtPhone,txtEmail,txtSalary;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();
        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.txtName = row.findViewById(R.id.editName);
            holder.txtAge = row.findViewById(R.id.editAge);
            holder.txtPhone = row.findViewById(R.id.editPhone);
            holder.txtEmail = row.findViewById(R.id.editEmail);
            holder.txtSalary = row.findViewById(R.id.editSalary);
            row.setTag(holder);
        }
        else {
                holder = (ViewHolder)row.getTag();


        }
        Model model = recordList.get(i);
        holder.txtName.setText(model.getName());
        holder.txtAge.setText(String.valueOf(model.getAge()));
        holder.txtPhone.setText(String.valueOf(model.getPhone()));
        holder.txtEmail.setText(model.getEmail());
        holder.txtSalary.setText(String.valueOf(model.getSalary()));

        /*byte[] recordImage = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0,recordImage.length);
        holder.imageView.setImageBitmap(bitmap);            */
        return row;
    }
}
