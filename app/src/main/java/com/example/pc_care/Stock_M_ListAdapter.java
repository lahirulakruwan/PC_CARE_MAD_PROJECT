package com.example.pc_care;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Stock_M_ListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Model_Stock_M> stockList;

    public Stock_M_ListAdapter(Context context, int layout, ArrayList<Model_Stock_M> stockList) {
        this.context = context;
        this.layout = layout;
        this.stockList = stockList;
    }

    @Override
    public int getCount() {
        return stockList.size();
    }

    @Override
    public Object getItem(int i) {
        return stockList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView txtItemcode,txtModel, txtBrand, txtAvailability;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.txtItemcode = row.findViewById(R.id.txtItemCode);
            holder.txtModel = row.findViewById(R.id.txtModel);
            holder.txtBrand = row.findViewById(R.id.txtBrand);
            holder.txtAvailability = row.findViewById(R.id.txtQty);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder)row.getTag();
        }

        Model_Stock_M model = stockList.get(i);
        holder.txtItemcode.setText(model.getItemcode());
        holder.txtModel.setText(model.getModel());
        holder.txtBrand.setText(model.getBrand());
        holder.txtAvailability.setText(model.getAvailability());



        return row;
    }
}