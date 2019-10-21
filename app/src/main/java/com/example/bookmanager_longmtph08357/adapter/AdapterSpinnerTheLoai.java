package com.example.bookmanager_longmtph08357.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.bookmanager_longmtph08357.R;
import com.example.bookmanager_longmtph08357.model.TheLoai;

import java.util.List;

public class SpinerAdapter implements SpinnerAdapter {

    private Context context;
    private List<TheLoai> theLoaiList;

    public SpinerAdapter(Context context, List<TheLoai> theLoaiList) {
        this.context = context;
        this.theLoaiList = theLoaiList;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row_spinner_theloai, parent, false);

        TextView tvSpnTheLoai;

        tvSpnTheLoai = convertView.findViewById(R.id.tvSpnTheLoai);

        tvSpnTheLoai.setText(theLoaiList.get(position).tenTheLoai);

        return convertView;

    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return theLoaiList.size();
    }

    @Override
    public Object getItem(int position) {
        return theLoaiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row_spinner_theloai, parent, false);

        TextView tvSpnTheLoai;

        tvSpnTheLoai = convertView.findViewById(R.id.tvSpnTheLoai);

        tvSpnTheLoai.setText(theLoaiList.get(position).tenTheLoai);

        tvSpnTheLoai.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
