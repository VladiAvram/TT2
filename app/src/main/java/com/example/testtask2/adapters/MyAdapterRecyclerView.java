package com.example.testtask2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask2.R;

import java.util.List;

public final class MyAdapterRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<String> itemList;
    Context context;

    public MyAdapterRecyclerView(List<String> itemList, Context context){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return
                new RecyclerView.ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_rec_view, parent, false)){};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView tvItem = holder.itemView.findViewById(R.id.tvRec);
        tvItem.setText(this.itemList.get(position));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
