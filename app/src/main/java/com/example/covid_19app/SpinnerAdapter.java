package com.example.covid_19app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SpinnerAdapter  extends ArrayAdapter<ProvinceClass> {
    public SpinnerAdapter(@NonNull Context context, ArrayList<ProvinceClass> provinceList) {
        super(context, 0, provinceList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
          return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_row, parent, false
            );
        }
        ImageView imageViewFlag = convertView.findViewById(R.id.flag_image);
        TextView textViewName = convertView.findViewById(R.id.provName);
        ProvinceClass Province = getItem(position);
        if (Province != null) {
            imageViewFlag.setImageResource(Province.getFlagImage());
            textViewName.setText(Province.getProvinceName());
        }
       return convertView;
        }
    }
