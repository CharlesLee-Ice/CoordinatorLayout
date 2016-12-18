package com.learning.coordinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqilin on 2016/12/18.
 */

public class CheeseDetailsActivity extends AppCompatActivity {

    public static String KEY_APP_BAR = "appbar";

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /***
         * FullScreen
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_cheese_details);

        setupToolbar();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        setupDataAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }


    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent intent = getIntent();
        AppBarScrollType type = AppBarScrollType.values()[intent.getIntExtra(KEY_APP_BAR, 0)];
        int scrollFlags = 0;
        switch (type) {
            case AppBar_Scroll:
                scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL;
                break;
            case AppBar_Scroll_EnterAlways:
                scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS;
                break;
            case AppBar_Scroll_ExitUntilCollapsed:
                scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED;
                break;
            case AppBar_Scroll_EnterAlwaysCollapsed:
                scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED;
                break;
            case AppBar_Scroll_Snap:
                scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP;
                break;
        }

        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
        params.setScrollFlags(scrollFlags);
        mToolbar.setLayoutParams(params);

        setSupportActionBar(mToolbar);
    }

    private void setupDataAdapter() {
        mAdapter = new RecyclerViewAdapter(this);
        mAdapter.addData("");
        mAdapter.addData("");
        mAdapter.addData("");
        mAdapter.addData("");
        mAdapter.addData("");
    }

    private static class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private LayoutInflater mInflater;
        List<String> mData = new ArrayList<>();

        public RecyclerViewAdapter(Activity activity) {
            mInflater = LayoutInflater.from(activity);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerViewAdapter.ViewHolder(mInflater.inflate(R.layout.item_cheese_detail, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((RecyclerViewAdapter.ViewHolder) holder).fillData(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void addData(String data) {
            mData.add(data);
        }

        private static class ViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;
            public ViewHolder(View item) {
                super(item);
                mTextView = (TextView) item.findViewById(R.id.item_text_view);
            }

            public void fillData(String data) {
            }
        }
    }
}
