package com.example.and_project.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Steps
{
    @PrimaryKey
    @NonNull
    private String date;

    @NonNull
    private int steps;

    public Steps(String date, int steps)
    {
        this.date = date;
        this.steps = steps;
    }

    public void setSteps(int steps)
    {
        this.steps = steps;
    }

    public String getDate()
    {
        return date;
    }

    public int getSteps()
    {
        return steps;
    }
}
