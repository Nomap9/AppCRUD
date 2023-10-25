package com.example.appcrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{


    private List<User> mListUser;

    public UserAdapter(List<User> mListUser) {
        this.mListUser = mListUser;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (mListUser == null || mListUser.isEmpty()) {
            return;
        }

        User user = mListUser.get(position);
        if(user == null) {
            return;
        }

        holder.imgUser.setImageResource(user.getImg());
        //holder.tvID.setText(user.getId());
        holder.tvID.setText(String.valueOf(user.getId()));
        holder.tvName.setText(user.getName());
        holder.tvHireDate.setText(user.getHireDate());
        //holder.tvSalary.setText((int) user.getSalary());
        holder.tvSalary.setText(String.valueOf((int) user.getSalary()));
    }

    @Override
    public int getItemCount() {
        if(mListUser != null) {
            return  mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgUser;
        private TextView tvID;
        private TextView tvName;
        private TextView tvHireDate;
        private TextView tvSalary;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user);
            tvID = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvHireDate = itemView.findViewById(R.id.tv_hireDate);
            tvSalary = itemView.findViewById(R.id.tv_salary);
        }


    }
}
