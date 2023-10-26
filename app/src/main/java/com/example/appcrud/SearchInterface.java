package com.example.appcrud;

import java.util.List;

public interface SearchInterface {
    void showUsers(List<User> userList);
    void showFilteredUsers(List<User> filteredList);
}
