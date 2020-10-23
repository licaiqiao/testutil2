package com.my.mylibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.mylibrary.R;
import com.my.mylibrary.bean.StarBean;

import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.ViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<StarBean> list;


    public StarAdapter(Context context, List<StarBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.star_item_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.ivIcon=view.findViewById(R.id.iv_icon);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        StarBean bean=list.get(position);
        if(bean.isChoose()){
            holder.ivIcon.setImageResource(R.mipmap.pj);
        }else{
            holder.ivIcon.setImageResource(R.mipmap.pj_n);

        }
        holder.ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onStarClickListener!=null){
                    onStarClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    OnStarClickListener onStarClickListener;

    public OnStarClickListener getOnStarClickListener() {
        return onStarClickListener;
    }

    public void setOnStarClickListener(OnStarClickListener onStarClickListener) {
        this.onStarClickListener = onStarClickListener;
    }

    public interface OnStarClickListener{

        void onClick(int position);
    }
}

