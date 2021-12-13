package com.example.covid_19app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProvincedbAdapter extends RecyclerView.Adapter<ProvincedbAdapter.ItemViewHolder> {

    ArrayList<Summary> listRecyclerItem;
    private final Context context;

    public ProvincedbAdapter(Context context, ArrayList <Summary> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView ActiveCases;
        private TextView ReportedCases;
        private TextView RecoveredCases;
        private TextView DeathCases;
        private TextView Date;
        private TextView ProvinceName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ReportedCases = (TextView) itemView.findViewById(R.id.reportedCases);
            ActiveCases = (TextView) itemView.findViewById(R.id.activeCases);
            RecoveredCases = (TextView) itemView.findViewById(R.id.recoveredCases);
            DeathCases = (TextView) itemView.findViewById(R.id.totalDeath);
            Date = (TextView) itemView.findViewById(R.id.date);
            ProvinceName=(TextView) itemView.findViewById(R.id.Provincename);
        }

        public TextView getReportedCases() {
            return ReportedCases;
        }

        public TextView getActiveCases() {
            return ActiveCases;
        }

        public TextView getRecoveredCases() {
            return RecoveredCases;
        }

        public TextView getDeathCases() {
            return DeathCases;
        }
        public TextView getDate(){return  Date;}
        public TextView getProvinceName(){return ProvinceName;}
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ProvincedbAdapter.ItemViewHolder itemViewHolder = (ProvincedbAdapter.ItemViewHolder) holder ;
        Summary s=  (Summary) listRecyclerItem.get(position);

        holder.getReportedCases().setText(String.valueOf("Reported Cases"+" "+s.getCumulative_cases()));
        holder.getActiveCases().setText(String.valueOf("Active Cases"+" "+s.getActive_cases()));
        holder.getRecoveredCases().setText(String.valueOf("Recovered Cases"+" "+s.getRecovered()));
        holder.getDeathCases().setText(String.valueOf("Death Cases"+"  "+s.getDeaths()));
        holder.getDate().setText(s.getDate());
        holder.getProvinceName().setText(s.getProvince());
    }

    @Override
    public int getItemCount() {

            return listRecyclerItem.size();
        };
    }

