package com.example.and_project.mainActivity;

public class Header implements RecyclerViewItem
{
    private String mName;

    Header(String name)
    {
        mName = name;
    }

    public String getName()
    {
        return mName;
    }

    @Override
    public int getType()
    {
        return RecyclerViewItem.TYPE_HEADER;
    }
}
