package com.elkriefy.apps.material.recycleviewdemos;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.elkriefy.apps.material.recycleviewdemos.animators.SlideInOutBottomItemAnimator;
import com.elkriefy.apps.material.recycleviewdemos.animators.SlideInOutLeftItemAnimator;
import com.elkriefy.apps.material.recycleviewdemos.animators.SlideInOutRightItemAnimator;
import com.elkriefy.apps.material.recycleviewdemos.animators.SlideInOutTopItemAnimator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by MaTriXy on 1/3/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    private ArrayList<Integer> mImages;
    public boolean mCardLayout;
    private int mFirstLayout;
    private int mAlternateLayout;

    public static final int LAST_POSITION = -1;

    public MyAdapter(String[] myDataset, ArrayList<Integer> myImages) {
        mDataset = new ArrayList<String>(Arrays.asList(myDataset));
        mImages = myImages;
        mCardLayout = false;
    }

    public void setmFirstLayout(int firstLayout) {
        mFirstLayout = firstLayout;
    }

    public void setmAlternateLayout(int alternateLayout) {
        mAlternateLayout = alternateLayout;
    }

    public void add(String s, int position, int resource) {
        position = position <= LAST_POSITION ? getItemCount() : position;
        mDataset.add(position, s);
        mImages.add(position, resource);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (position == LAST_POSITION && getItemCount() > 0)
            position = getItemCount() - 1;

        if (position > LAST_POSITION && position < getItemCount()) {
            mDataset.remove(position);
            mImages.remove(position);
            notifyItemRemoved(position);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.txt);
            mImageView = (ImageView) v.findViewById(R.id.img);
        }
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mCardLayout ? mFirstLayout : mAlternateLayout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position));
        holder.mImageView.setImageResource(mImages.get(position));
    }

    public int getItemCount() {
        return mDataset.size();
    }

    public void setupSpinner(Spinner spinner, final RecyclerView mRecyclerView, Activity act) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(act,
                R.array.animators, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        mRecyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(mRecyclerView));
                        break;
                    case 1:
                        mRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mRecyclerView));
                        break;
                    case 2:
                        mRecyclerView.setItemAnimator(new SlideInOutTopItemAnimator(mRecyclerView));
                        break;
                    case 3:
                        mRecyclerView.setItemAnimator(new SlideInOutBottomItemAnimator(mRecyclerView));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
