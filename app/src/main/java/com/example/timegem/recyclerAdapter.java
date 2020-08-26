package com.example.timegem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timegem.databinding.CardLayoutBinding;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyHolder> {

    private ArrayList<RoomModel> mModels=new ArrayList<>();
    private clickInterface mClickInterface;

    public recyclerAdapter(ArrayList<RoomModel> models,clickInterface clickInterface) {
        mModels = models;
        mClickInterface=clickInterface;
    }

    @NonNull
    @Override
    public recyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        CardLayoutBinding cardLayoutBinding= DataBindingUtil.inflate(layoutInflater
                ,R.layout.card_layout,parent,false);
        return new MyHolder(cardLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyHolder holder, int position) {
            holder.mCardLayoutBinding.setMode(mModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CardLayoutBinding mCardLayoutBinding;
        public MyHolder(@NonNull final CardLayoutBinding itemView) {
            super(itemView.getRoot());
            this.mCardLayoutBinding=itemView;
            itemView.status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickInterface.click(getAdapterPosition(),itemView.status.getRootView());
                }
            });
        }
    }
}
