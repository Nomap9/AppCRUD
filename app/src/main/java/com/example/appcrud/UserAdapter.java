package com.example.appcrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVieeHolder>{

    private List<User> mListUser;

    public UserAdapter(List<User> mListUser) {
        this.mListUser = mListUser;
    }

    @NonNull
    @Override
    public UserVieeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserVieeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVieeHolder holder, int position) {
        User user = mListUser.get(position);
        if(user == null) {
            return;
        }

        holder.imgUser.setImageResource(user.getImage());
        holder.tvName.setText(user.getName());
        holder.tvAddress.setText(user.getAddress());
    }

    @Override
    public int getItemCount() {
        if(mListUser != null) {
            return  mListUser.size();
        }
        return 0;
    }

    public class UserVieeHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgUser;
        private TextView tvName;
        private TextView tvAddress;

        public UserVieeHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }


    }
}
