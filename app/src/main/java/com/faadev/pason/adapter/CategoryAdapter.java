package com.faadev.pason.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faadev.pason.R;
import com.faadev.pason.model.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<CategoryModel> mData;
    private int sebelum = 0;

    public CategoryAdapter(Context mContext, List<CategoryModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.layout_category, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.categoryname.setText(mData.get(position).getCategory());
        if (mData.get(position).isSelected()){
            holder.body.setBackgroundColor(Color.argb(255, 124, 7, 158));
        } else {
            holder.body.setBackgroundColor(Color.argb(255, 203, 78, 237));
        }

        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != sebelum){
                    mData.get(position).setSelected(true);
                    mData.get(sebelum).setSelected(false);
                    sebelum = position;
                    notifyDataSetChanged();
                }
                //itemClickListener.onItemClicked(mData.get(position).getCity());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout body;
        private TextView categoryname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            body = itemView.findViewById(R.id.body);
            categoryname = itemView.findViewById(R.id.categoryname);
        }
    }
}
