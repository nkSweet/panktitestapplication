package com.example.panktitestapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.panktitestapplication.database.RoomUserData;

import java.util.ArrayList;

public class UserListRvAdapter  extends RecyclerView.Adapter<UserListRvAdapter.MyViewHolder> {

    private ArrayList<RoomUserData> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvEmail,tvAddress,tvPhone,tvWebsite,tvCompnay;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            this.tvEmail = (TextView) itemView.findViewById(R.id.tv_email);
            this.tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            this.tvWebsite = (TextView) itemView.findViewById(R.id.tv_website);
            this.tvCompnay= (TextView) itemView.findViewById(R.id.tv_compnay);
        }
    }

    public UserListRvAdapter(ArrayList<RoomUserData> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_userdata, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {


        holder.tvName.setText(dataSet.get(listPosition).getName());
        holder.tvCompnay.setText("Company :" + dataSet.get(listPosition).getCompany().getName());
        holder.tvWebsite.setText("WebSite :" + dataSet.get(listPosition).getWebsite());
        holder.tvPhone.setText("Phone :" + dataSet.get(listPosition).getPhone());
        holder.tvEmail.setText("Email :" + dataSet.get(listPosition).getEmail());
        holder.tvAddress.setText("Address :" + dataSet.get(listPosition).getAddress().getSuite()
        + "," + dataSet.get(listPosition).getAddress().getStreet()
                +"," + dataSet.get(listPosition).getAddress().getCity()
        );
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
