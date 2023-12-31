package com.example.week9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class WorkAdapter extends ArrayAdapter {

    private SQLiteActivity context;
    private int resource;
    private ArrayList<Work> workList = new ArrayList<Work>();

    public WorkAdapter(@NonNull SQLiteActivity context, int resource,
                       @NonNull ArrayList<Work> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.workList = objects;
    }

    private class ViewHolder {
        TextView txtName;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
            holder.txtName = view.findViewById(R.id.textView_name);
            holder.imgDelete = view.findViewById(R.id.imageView_delete);
            holder.imgEdit = view.findViewById(R.id.imageView_edit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Work work = workList.get(position);

        holder.txtName.setText(work.getName());

        // Bắt sự kiện xóa & sửa
        holder.imgEdit.setOnClickListener(v -> {
            context.EditWork(work.getName(), work.getId());
        });

        holder.imgDelete.setOnClickListener(v -> {
            context.DeleteWork(work.getName(), work.getId());
        });

        return view;
    }
}
