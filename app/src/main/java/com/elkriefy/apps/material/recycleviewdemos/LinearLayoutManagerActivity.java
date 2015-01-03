package com.elkriefy.apps.material.recycleviewdemos;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

public class LinearLayoutManagerActivity extends ActionBarActivity {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recycler, menu);
        return true;
    }


    private void initComponents() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mSpinner = (Spinner) findViewById(R.id.spinnerAnimations);
        mSpinner.setVisibility(View.VISIBLE);

        String[] myDataset = {" One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine"};
        ArrayList<Integer> myImages = new ArrayList<>();
        myImages.add(R.drawable.img1);
        myImages.add(R.drawable.img2);
        myImages.add(R.drawable.img3);
        myImages.add(R.drawable.img4);
        myImages.add(R.drawable.img5);
        myImages.add(R.drawable.img6);
        myImages.add(R.drawable.img7);
        myImages.add(R.drawable.img8);
        myImages.add(R.drawable.img9);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mAdapter = new MyAdapter(myDataset, myImages);
        ((MyAdapter) mAdapter).mCardLayout = true;
        ((MyAdapter)mAdapter).setmFirstLayout(R.layout.item_view_linear_cardview);
        ((MyAdapter)mAdapter).setmAlternateLayout(R.layout.item_view_linear);
        mRecyclerView.setAdapter(mAdapter);
        ((MyAdapter) mAdapter).setupSpinner(mSpinner, mRecyclerView, this);
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
                result = R.drawable.img2;
                break;
            case 3:
                result = R.drawable.img3;
                break;
            case 4:
                result = R.drawable.img4;
                break;
            case 5:
                result = R.drawable.img9;
                break;
            case 6:
                result = R.drawable.img14;
                break;
        }

        int pos;
        switch (item.getItemId()) {
            case android.R.id.home:
                mSpinner.setVisibility(View.GONE);
                finish();
                break;
            case R.id.action_add:
                pos = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                ((MyAdapter) mAdapter).add("New Item", pos, result);
                return true;
            case R.id.action_remove:
                if (mAdapter.getItemCount() == 1) {
                    pos = 0;
                } else {
                    pos = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition() + 1;
                }
                ((MyAdapter) mAdapter).remove(pos);
                return true;
            case R.id.action_switch:
                ((MyAdapter) mAdapter).mCardLayout = !((MyAdapter) mAdapter).mCardLayout;
                mLayoutManager = new LinearLayoutManager(this,
                        ((MyAdapter) mAdapter).mCardLayout
                                ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}
