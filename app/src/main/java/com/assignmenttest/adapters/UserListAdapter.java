package com.assignmenttest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.assignmenttest.R;
import com.assignmenttest.databinding.MenuFragmentProfileListItemBinding;
import com.assignmenttest.models.User;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    ArrayList<User> userList = new ArrayList<>();
    Context context;

    public UserListAdapter(ArrayList<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MenuFragmentProfileListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.menu_fragment_profile_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        User user = userList.get(position);
        viewHolder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public MenuFragmentProfileListItemBinding itemRowBinding;

        public ViewHolder(MenuFragmentProfileListItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(com.assignmenttest.BR.user, obj);
            itemRowBinding.executePendingBindings();
        }
    }

}

