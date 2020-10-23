package com.my.mylibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.my.mylibrary.R;
import com.my.mylibrary.adapter.StarTypeAdapter;
import com.my.mylibrary.bean.StarBean;
import com.my.mylibrary.bean.StarTypeBean;

import java.util.ArrayList;
import java.util.List;

public class StarSetView extends LinearLayout {
    int typs=3;//多少级
    List<String> names=new ArrayList<String>();//每一级的名称（质量/综合/物流等等）
    int start_num=5;//每级多少星星
    int default_num=3;//默认多少星
    List<StarTypeBean> list=new ArrayList<StarTypeBean>();
    RecyclerView startTypeLv;
    StarTypeAdapter typeAdapter;
    Context context;
    public StarSetView(Context context, int typs, List<String> names, int start_num, int default_num) {
        super(context);
        initView( context,null);
        this.typs=typs;
        this.names=names;
        this.start_num=start_num;
        this.default_num=default_num;
        this.context=context;
        initData();
    }

    public StarSetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView( context,attrs);
        initData();
    }

    public StarSetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView( context,attrs);
        initData();

    }

    public StarSetView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context=context;
        initView( context,attrs);
        initData();

    }

    void initView(Context context,AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.star_set_view, this, true);
        startTypeLv=this.findViewById(R.id.start_type_lv);
        if(attrs!=null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.startview);
            typs = ta.getInteger(R.styleable.startview_types, 3);
            start_num = ta.getInteger(R.styleable.startview_start_num, 5);
            default_num = ta.getInteger(R.styleable.startview_default_num, 3);
            String namestr=ta.getString(R.styleable.startview_names);

            Log.d("Test","types"+typs);
            Log.d("Test","start_num"+start_num);
            Log.d("Test","default_num"+default_num);
            Log.d("Test","namestr"+namestr);

            if(namestr!=null&&!namestr.equals("")){
                String[] strs=namestr.split(",");
                for(int i=0;i<strs.length;i++){
                    names.add(strs[i]);
                }
            }
            ta.recycle();
        }

    }
    void initData(){
        if(names.size()==0){
            names.add("质量") ;
            names.add("综合");
            names.add("物流");
        }
        for(int i=0;i<typs;i++){
            StarTypeBean startTypeBean=new StarTypeBean();
            if(i<names.size()) {
                startTypeBean.setName(names.get(i));
            }
            List<StarBean> startBeans=new ArrayList<StarBean>();

            for(int j=0;j<start_num;j++){
                StarBean bean=new StarBean();

                if(default_num>0&&j<default_num){
                    bean.setChoose(true);
                }
                startBeans.add(bean);

            }
            startTypeBean.setStarts(startBeans);
            startTypeBean.setScore(default_num);
            list.add(startTypeBean);
        }

        if(list!=null&&list.size()>0){

            typeAdapter=new StarTypeAdapter(context,list);
            LinearLayoutManager manager=new LinearLayoutManager(context);
            startTypeLv.setLayoutManager(manager);
            startTypeLv.setAdapter(typeAdapter);
            typeAdapter.setOnStarTypeOnclickListener(new StarTypeAdapter.OnStarTypeOnclickListener() {
                @Override
                public void onClickType(int typePostion, int position) {
                    Log.d("Test","typePostion"+typePostion);
                    Log.d("Test","position"+position);

                    list.get(typePostion).setScore(position+1);
                    List<StarBean> slist=list.get(typePostion).getStarts();

                    for(int i=0;i<slist.size();i++){
                        if(i<=position){
                            list.get(typePostion).getStarts().get(i).setChoose(true);
                        }else{
                            list.get(typePostion).getStarts().get(i).setChoose(false);

                        }
                    }
                    typeAdapter.notifyItemChanged(typePostion);
                }
            });

        }

    }



}
