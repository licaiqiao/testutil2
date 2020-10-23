package com.my.mylibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.my.mylibrary.R;
import com.my.mylibrary.bean.StarTypeBean;

import java.util.List;

public class StarTypeAdapter extends RecyclerView.Adapter<StarTypeAdapter.ViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<StarTypeBean> list;



    public StarTypeAdapter(Context context, List<StarTypeBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.star_type_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.nameTv=view.findViewById(R.id.name_tv);
        viewHolder.scoreTv=view.findViewById(R.id.score_tv);
        viewHolder.startsRv=view.findViewById(R.id.starts_rv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        StarTypeBean bean = list.get(position);
        String name = bean.getName();
        if (name != null) {
            holder.nameTv.setText(name);
        }
        if(bean.getScore()==5){
            holder.scoreTv.setText("非常好");
        }else if(bean.getScore()==4){
            holder.scoreTv.setText("好");
        }else if(bean.getScore()==3){
            holder.scoreTv.setText("一般");

        }else if(bean.getScore()==2){
            holder.scoreTv.setText("差");

        }else if(bean.getScore()==1){
            holder.scoreTv.setText("非常差");

        }else{
            holder.scoreTv.setText(" ");

        }
        if (bean.getStarts() != null) {
            StarAdapter startAdapter = new StarAdapter(context, bean.getStarts());
            LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            holder.startsRv.setLayoutManager(manager);
            holder.startsRv.setAdapter(startAdapter);
            startAdapter.setOnStarClickListener(new StarAdapter.OnStarClickListener() {
                @Override
                public void onClick(int p) {

                    if (onStarTypeOnclickListener != null) {
                        onStarTypeOnclickListener.onClickType(position, p);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView startsRv;

        TextView nameTv;

        TextView scoreTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    OnStarTypeOnclickListener onStarTypeOnclickListener;

    public OnStarTypeOnclickListener getOnStarTypeOnclickListener() {
        return onStarTypeOnclickListener;
    }

    public void setOnStarTypeOnclickListener(OnStarTypeOnclickListener onStarTypeOnclickListener) {
        this.onStarTypeOnclickListener = onStarTypeOnclickListener;
    }

    public interface OnStarTypeOnclickListener {
        void onClickType(int typePostion, int position);
    }
}

