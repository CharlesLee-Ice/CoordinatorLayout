package com.learning.coordinator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(new MyAdapter(this));
    }

    private static class MyAdapter extends RecyclerView.Adapter {

        Context mContent;
        LayoutInflater mInflater;

        public MyAdapter(Context context) {
            mContent = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(R.layout.item_main, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((ViewHolder) holder).fillContent(CoordinatorScrollType.values()[position].toString());
            ((ViewHolder) holder).mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (position <= CoordinatorScrollType.AppBar_Scroll_Snap.ordinal()) {
                        Intent intent = new Intent(mContent, CheeseDetailsActivity.class);
                        intent.putExtra(CheeseDetailsActivity.KEY_APP_BAR, position);
                        mContent.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContent, CollapsingToolbarActivity.class);
                        mContent.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return CoordinatorScrollType.values().length;
        }

        private static class ViewHolder extends RecyclerView.ViewHolder {
            View mView;
            TextView mTextView;
            public ViewHolder(View view) {
                super(view);
                mView = view;
                mTextView = (TextView) view.findViewById(R.id.card_text);
            }

            public void fillContent(String content) {
                mTextView.setText(content);
            }
        }
    }
}
