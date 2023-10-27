package com.example.appcrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchPresenter extends RecyclerView.Adapter<SearchPresenter.UserViewHolder> implements Filterable {


    private List<User> mListUser;
    private List<User> mListUserOld;
    private SearchInterface searchInterface; // Tham chiếu đến giao diện View

    public SearchPresenter(List<User> mListUser, SearchInterface searchInterface) {
        this.mListUser = mListUser;
        this.mListUserOld = mListUser;
        this.searchInterface = searchInterface;
    }

    //đặt dữ liệu người dùng để hiển thị trong RecyclerView
    public void setUsers(List<User> userList) {
        mListUser = userList;
        notifyDataSetChanged();
    }

    public void setFilteredUsers(List<User> filteredList) {
        mListUser = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    // inflate bố cục cho một mục người dùng
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    mListUser = mListUserOld;
                } else {
                    List<User> list = new ArrayList<>();
                    for (User user : mListUserOld) {
                        if (user.getName().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(user);
                        }
                    }
                    mListUser = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListUser;

                // Giao tiếp dữ liệu đã lọc với View bằng cách sử dụng giao diện
                searchInterface.showFilteredUsers(mListUser);

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListUser = (List<User>) results.values;

                // Giao tiếp dữ liệu cập nhật với View bằng cách sử dụng giao diện
                searchInterface.showFilteredUsers(mListUser);
                notifyDataSetChanged();
            }
        };
    }
}
