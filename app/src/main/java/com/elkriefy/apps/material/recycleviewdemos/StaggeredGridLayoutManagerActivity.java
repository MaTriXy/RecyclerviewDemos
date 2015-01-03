package com.elkriefy.apps.material.recycleviewdemos;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

public class StaggeredGridLayoutManagerActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutmanager_layout);
        initComponents();
    }

    private void initComponents() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mSpinner = (Spinner) findViewById(R.id.spinnerAnimations);
        mSpinner.setVisibility(View.VISIBLE);

        String[] myDataset = {" One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine",
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
                "Seventeen", "Eighteen", "Nineteen",
                "Twenty", "Twenty one", "Twenty two", "Twenty three", "Twenty four",
                "Twenty five", "Twenty six", "Twenty seven", "Twenty eight", "Twenty nine"};
        ArrayList<Integer> myImages = new ArrayList<>();
        myImages.add(R.drawable.img1);
        myImages.add(R.drawable.img2);
        myImages.add(R.drawable.img3);
        myImages.add(R.drawable.img16);
        myImages.add(R.drawable.img5);
        myImages.add(R.drawable.img6);
        myImages.add(R.drawable.img7);
        myImages.add(R.drawable.img16);
        myImages.add(R.drawable.img9);
        myImages.add(R.drawable.img1);
        myImages.add(R.drawable.img16);
        myImages.add(R.drawable.img3);
        myImages.add(R.drawable.img4);
        myImages.add(R.drawable.img5);
        myImages.add(R.drawable.img16);
        myImages.add(R.drawable.img7);
        myImages.add(R.drawable.img8);
        myImages.add(R.drawable.img9);
        myImages.add(R.drawable.img1);
        myImages.add(R.drawable.img2);
        myImages.add(R.drawable.img3);
        myImages.add(R.drawable.img4);
        myImages.add(R.drawable.img5);
        myImages.add(R.drawable.img6);
        myImages.add(R.drawable.img7);
        myImages.add(R.drawable.img8);
        myImages.add(R.drawable.img9);
        myImages.add(R.drawable.img5);
        myImages.add(R.drawable.img6);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(myDataset, myImages);
        ((MyAdapter) mAdapter).mCardLayout = true;
        ((MyAdapter) mAdapter).setmFirstLayout(R.layout.item_view_staggered);
        mRecyclerView.setAdapter(mAdapter);
        ((MyAdapter) mAdapter).setupSpinner(mSpinner, mRecyclerView, this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recycler, menu);
        menu.removeItem(R.id.action_switch);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int img = (int) (Math.random() * 6 + 1);
        int result = R.drawable.img13;
        switch (img) {
            case 1:
                result = R.drawable.img1;
                break;
            case 2:
                result = R.drawable.img16;
                break;
            case 3:
                result = R.drawable.img3;
                break;
            case 4:
                result = R.drawable.img16;
                break;
            case 5:
                result = R.drawable.img9;
                break;
            case 6:
                result = R.drawable.img14;
                break;
        }
        int[] pos;

        switch (item.getItemId()) {
            case android.R.id.home:
                mSpinner.setVisibility(View.GONE);
                finish();
                break;
            case R.id.action_add:
                pos = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null);
                ((MyAdapter) mAdapter).add("New Item", pos[0], result);
                return true;
            case R.id.action_remove:
                if (mAdapter.getItemCount() == 1) {
                    pos = new int[]{0};
                } else {
                    pos = ((StaggeredGridLayoutManager) mLayoutManager).findFirstVisibleItemPositions(null);
                    pos[0]++;
                }
                ((MyAdapter) mAdapter).remove(pos[0]);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}
