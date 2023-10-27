package com.example.appcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchInterface {

    private RecyclerView rcvUser;
    private SearchPresenter searchPresenter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rcvUser = findViewById(R.id.rcv_user);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);

        searchPresenter = new SearchPresenter(getListUsers(), (SearchInterface) this); // Truyền tham chiếu của giao diện UserInterface
        rcvUser.setAdapter(searchPresenter);

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
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPresenter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchPresenter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }


    @Override
    public void showUsers(List<User> userList) {
        searchPresenter.setUsers(userList);
    }

    @Override
    public void showFilteredUsers(List<User> filteredList) {
        searchPresenter.setFilteredUsers(filteredList);
        searchPresenter.notifyDataSetChanged();
    }
}