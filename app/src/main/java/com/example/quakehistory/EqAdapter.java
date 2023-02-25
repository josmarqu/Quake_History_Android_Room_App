package com.example.quakehistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quakehistory.db.room_tables.EarthQuake;

import java.util.List;

public class EqAdapter extends RecyclerView.Adapter<EqAdapter.EqViewHolder> {
    private List<EarthQuake> eqList;
    private Context context;

   public EqAdapter(List<EarthQuake> eqList, Context context) {
        this.eqList = eqList;
        this.context = context;
    }

    @NonNull
    @Override
    public EqAdapter.EqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquake_item, parent, false);
        return new EqViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull EqAdapter.EqViewHolder holder, int position) {
        EarthQuake eq = eqList.get(position);
        holder.tvTitle.setText(eq.deviceName);
        String[] parts = eq.date.split(", ");
        String date, time;
        if (parts.length == 1) {
            date = parts[0];
            time = "n/a";
        }
        else {
            date = parts[0];
            time = parts[1];
        }
        holder.tvDate.setText(context.getResources().getString(R.string.date) + " " + date);
        holder.tvTime.setText(context.getResources().getString(R.string.time) + " " + time);
        holder.tvMag.setText(context.getResources().getString(R.string.magnitude) + " " + eq.magnitude);
        holder.tvLoc.setText(context.getResources().getString(R.string.location) + " " + eq.location);
        holder.tvCoords.setText(context.getResources().getString(R.string.coordinates) + " " + eq.coordinates);
        holder.tvDt.setText(context.getResources().getString(R.string.death_toll) + " " + eq.deathToll);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
        int marginTopBot = (int) context.getResources().getDimension(R.dimen.margItem);
        int marginTop =  (int) context.getResources().getDimension(R.dimen.margItemTop);
        if (position == 0) {
               layoutParams.setMargins(0, marginTopBot , 0, 0);
        }
        else if (position == eqList.size() - 1) {
            layoutParams.setMargins(0, marginTop, 0, marginTopBot);
        }
        else {
            layoutParams.setMargins(0, marginTop, 0, 0);
        }

        holder.itemView.setLayoutParams(layoutParams);
   }

    @Override
    public int getItemCount() {
        return eqList.size();
    }

    public class EqViewHolder  extends RecyclerView.ViewHolder{
        public TextView tvTitle, tvDate, tvTime, tvMag, tvLoc, tvCoords, tvDt;
        public EqViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvMag = itemView.findViewById(R.id.tvMag);
            tvLoc = itemView.findViewById(R.id.tvLoc);
            tvCoords = itemView.findViewById(R.id.tvCoords);
            tvDt = itemView.findViewById(R.id.tvDt);
        }
    }
}
