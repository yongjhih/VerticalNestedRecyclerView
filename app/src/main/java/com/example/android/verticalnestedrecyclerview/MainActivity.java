package com.example.android.verticalnestedrecyclerview;

import static android.view.ViewGroup.LayoutParams.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
/*
 * By: M7md.zain@gamil.com
 * 04.July.2021
 * */

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "LOG_TAG";
    private RecyclerView.RecycledViewPool sharedViewPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedViewPool = new RecyclerView.RecycledViewPool();

        LinearLayout viewGroup = findViewById(R.id.container);
        for (Month month : createMonths()) {
            inflateNestedRecycler(viewGroup, month);
        }
    }

    private void inflateNestedRecycler(LinearLayout viewGroup, Month month) {
        View child = LayoutInflater.from(this).inflate(R.layout.recycler_view_row_outer, viewGroup, false);
        child.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, 0, 1F));
        viewGroup.addView(child);

        TextView header = child.findViewById(R.id.tv_month);
        header.setText(month.name);

        setMonthAdapter(month, child.findViewById(R.id.recycler_view_inner));
    }

    private void setMonthAdapter(Month month, RecyclerView recyclerView) {
        InnerRecyclerAdapter adapter = new InnerRecyclerAdapter();
        adapter.setDays(month.dayCount);
        recyclerView.setAdapter(adapter);
        recyclerView.setRecycledViewPool(sharedViewPool);
    }

    List<Month> createMonths() {
        List<Month> months = new ArrayList<>();
        months.add(new Month("January", 31));
        months.add(new Month("February", 28));
        months.add(new Month("March", 31));
        months.add(new Month("April", 30));
        months.add(new Month("May", 31));
        months.add(new Month("June", 30));
        months.add(new Month("July", 31));
        months.add(new Month("August", 31));
        months.add(new Month("September", 30));
        months.add(new Month("October", 31));
        months.add(new Month("November", 30));
        months.add(new Month("December", 31));
        return months;
    }
}