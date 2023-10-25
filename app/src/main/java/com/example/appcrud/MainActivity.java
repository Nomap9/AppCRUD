package com.example.appcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
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
        list.add(new User(R.drawable.img_1, 1, "Minh", "12/3/2012", 200));
        list.add(new User(R.drawable.img_2, 2,  "Huy", "12/3/2012", 200));
        list.add(new User(R.drawable.img_3, 3,  "Nguyen", "12/3/2012", 200));
        list.add(new User(R.drawable.img_4, 4,  "Quan", "12/3/2012", 200));
        list.add(new User(R.drawable.img_5, 5,  "Dung", "12/3/2012", 200));
        list.add(new User(R.drawable.img_1, 6,  "Quang", "12/3/2012", 200));
        list.add(new User(R.drawable.img_2, 7,  "Abc", "12/3/2012", 200));
        list.add(new User(R.drawable.img_3, 8,  "Xyz", "12/3/2012", 200));
        list.add(new User(R.drawable.img_4, 9,  "Lam ngu", "12/3/2012", 200));
        list.add(new User(R.drawable.img_5, 10,  "Dung ngao", "12/3/2012", 200));
        list.add(new User(R.drawable.img_1, 11,  "Minh", "12/3/2012", 200));
        list.add(new User(R.drawable.img_2, 12,  "Huy", "12/3/2012", 200));
        list.add(new User(R.drawable.img_3, 13,  "Nguyen", "12/3/2012", 200));
        list.add(new User(R.drawable.img_4, 14,  "Quan", "12/3/2012", 200));
        list.add(new User(R.drawable.img_5, 15,  "Dung", "12/3/2012", 200));
        list.add(new User(R.drawable.img_1, 16,  "Quang", "12/3/2012", 200));
        list.add(new User(R.drawable.img_2, 17,  "Abc", "12/3/2012", 200));
        list.add(new User(R.drawable.img_3, 18,  "Xyz", "12/3/2012", 200));
        list.add(new User(R.drawable.img_4, 19,  "Lam ngu", "12/3/2012", 200));
        list.add(new User(R.drawable.img_5, 20,  "Dung ngao", "12/3/2012", 200));
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