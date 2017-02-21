package com.bc.ywj.rvdemo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/14 0014.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<String> mDatas;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyAdapter(List<String> datas){
        this.mDatas=datas;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position,String city){
        mDatas.add(position,city);
        notifyItemInserted(position);
    }
    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(listener!=null) {
                        listener.onClick(v,getLayoutPosition(),
                                mDatas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
    interface OnItemClickListener{
        void onClick(View v,int position,String city);
    }
}
