package com.example.appcrud;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.equals(holder.delete)){
                    removeAt(position);
                }
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v, position);
            }
        });
    }

    public void showDialog(View itemView, int position) {
        Dialog dialog = new Dialog(itemView.getContext());
        dialog.setContentView(R.layout.dialog_update_user);

        TextView newNameEditText = dialog.findViewById(R.id.new_name);
        TextView newHireDateEditText = dialog.findViewById(R.id.new_hire_date);
        TextView newSalaryEditText = dialog.findViewById(R.id.new_salary);

        Button updateButton = dialog.findViewById(R.id.btn_update);
        Button cancelButton = dialog.findViewById(R.id.btn_cancel);

        // Nút "Update" được bấm
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = newNameEditText.getText().toString();
                String newHireDate = newHireDateEditText.getText().toString();
                double newSalary = Double.parseDouble(newSalaryEditText.getText().toString());

                // Cập nhật thông tin người dùng và cập nhật RecyclerView
                mListUser.get(position).setName(newName);
                mListUser.get(position).setHireDate(newHireDate);
                mListUser.get(position).setSalary(newSalary);

                notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        // Nút "Cancel" được bấm
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

//    public void showDialog(View itemView){
//        Dialog dialog = new Dialog(itemView.getContext());
//        dialog.setContentView(R.layout.dialog_update_user);
//        dialog.show();
//    }

    public void removeAt(int position) {
        mListUser.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mListUser.size());
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
        private Button delete;

        private Button update;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user);
            tvID = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvHireDate = itemView.findViewById(R.id.tv_hireDate);
            tvSalary = itemView.findViewById(R.id.tv_salary);
            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);
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
