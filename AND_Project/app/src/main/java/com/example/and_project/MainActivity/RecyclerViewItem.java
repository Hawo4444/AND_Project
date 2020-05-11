package com.example.and_project.MainActivity;

public interface RecyclerViewItem
{
    int TYPE_HEADER = 1;
    int TYPE_ADD_BUTTON = 2;
    int TYPE_MEAL = 3;

    int getType();
}
