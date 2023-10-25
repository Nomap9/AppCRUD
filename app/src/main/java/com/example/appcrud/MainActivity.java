package com.example.appcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvUser;
    private UserAdapter userAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rcvUser = findViewById(R.id.rcv_user);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);

        userAdapter = new UserAdapter(getListUsers());
        rcvUser.setAdapter(userAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvUser.addItemDecoration(itemDecoration);
    }

    private List<User> getListUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.img_1, "Minh", "Viet Tri"));
        list.add(new User(R.drawable.img_2, "Huy", "Ha noi"));
        list.add(new User(R.drawable.img_3, "Nguyen", "HCM"));
        list.add(new User(R.drawable.img_4, "Quan", "Viet Tri"));
        list.add(new User(R.drawable.img_5, "Dung", "Viet Tri"));
        list.add(new User(R.drawable.img_1, "Quang", "Viet Tri"));
        list.add(new User(R.drawable.img_2, "Abc", "Viet Tri"));
        list.add(new User(R.drawable.img_3, "Xyz", "Viet Tri"));
        list.add(new User(R.drawable.img_4, "Lam ngu", "Viet Tri"));
        list.add(new User(R.drawable.img_5, "Dung ngao", "Viet Tri"));
        list.add(new User(R.drawable.img_1, "Minh", "Viet Tri"));
        list.add(new User(R.drawable.img_2, "Huy", "Ha noi"));
        list.add(new User(R.drawable.img_3, "Nguyen", "HCM"));
        list.add(new User(R.drawable.img_4, "Quan", "Viet Tri"));
        list.add(new User(R.drawable.img_5, "Dung", "Viet Tri"));
        list.add(new User(R.drawable.img_1, "Quang", "Viet Tri"));
        list.add(new User(R.drawable.img_2, "Abc", "Viet Tri"));
        list.add(new User(R.drawable.img_3, "Xyz", "Viet Tri"));
        list.add(new User(R.drawable.img_4, "Lam ngu", "Viet Tri"));
        list.add(new User(R.drawable.img_5, "Dung ngao", "Viet Tri"));
        return  list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        return true;
    }


}