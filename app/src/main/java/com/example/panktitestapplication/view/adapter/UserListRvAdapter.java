package com.example.panktitestapplication.view.adapter;

import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.panktitestapplication.R;
import com.example.panktitestapplication.database.RoomUserData;
import com.example.panktitestapplication.view.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserListRvAdapter  extends RecyclerView.Adapter<UserListRvAdapter.MyViewHolder> {

    private LiveData<List<RoomUserData>> dataSet;
    private UserViewModel userViewModel;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvEmail,tvAddress,tvPhone,tvWebsite,tvCompnay;
        Button btnDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            this.tvEmail = (TextView) itemView.findViewById(R.id.tv_email);
            this.tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            this.tvWebsite = (TextView) itemView.findViewById(R.id.tv_website);
            this.tvCompnay= (TextView) itemView.findViewById(R.id.tv_compnay);
            this.btnDelete= (Button) itemView.findViewById(R.id.btn_delete);
        }
    }

    public UserListRvAdapter(LiveData<List<RoomUserData>> data, UserViewModel userViewModel) {
        this.dataSet = data;
        this.userViewModel = userViewModel;
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

        holder.tvName.setText(dataSet.getValue().get(listPosition).getName());
        holder.tvCompnay.setText("Company : " + dataSet.getValue().get(listPosition).getCompany().getName());
        holder.tvWebsite.setText("WebSite : " + dataSet.getValue().get(listPosition).getWebsite());
        holder.tvPhone.setText("Phone : " + dataSet.getValue().get(listPosition).getPhone());
        holder.tvEmail.setText("Email : " + dataSet.getValue().get(listPosition).getEmail());
        holder.tvAddress.setText("Address : " + dataSet.getValue().get(listPosition).getAddress().getSuite()
        + "," + dataSet.getValue().get(listPosition).getAddress().getStreet()
                +"," + dataSet.getValue().get(listPosition).getAddress().getCity()
        );

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userViewModel.deleteUser(dataSet.getValue().get(listPosition));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.getValue().size();
    }
}
