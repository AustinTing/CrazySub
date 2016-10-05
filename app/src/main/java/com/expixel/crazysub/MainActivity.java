package com.expixel.crazysub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerView_main_base)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linearLayoutManager = new LinearLayoutManager(this);
        // 讓列表資料反轉 THIS ALSO SETS setStackFromBottom to true
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Post, ItemViewHolder> adapter =
                new FirebaseRecyclerAdapter<Post, ItemViewHolder>(
                        Post.class,
                        ItemViewHolder.layoutResId,
                        ItemViewHolder.class,
                        dbRef.child("post")
                ) {
                    @Override
                    protected void populateViewHolder(ItemViewHolder viewHolder, Post post, int position) {
                        Glide.with(MainActivity.this)
                                .load(post.userImgUrl)
                                .crossFade()
                                .into(viewHolder.userImage);
                        Glide.with(MainActivity.this)
                                .load(post.imgUrl)
                                .crossFade()
                                .into(viewHolder.img);
                        viewHolder.userName.setText(post.userName);
                        viewHolder.sub.setText(post.subtitle);
                        viewHolder.dsc.setText(post.description);
                        viewHolder.startCount.setText(String.valueOf(post.startCount));
                        viewHolder.commentCount.setText(String.valueOf(post.commentCount));

                    }
                };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Intent intent = new Intent();
        intent.setClass(this, PostActivity.class);
        startActivity(intent);
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public final static int layoutResId = R.layout.item_post;

        CircleImageView userImage;
        TextView userName;
        ImageView img;
        TextView sub;
        TextView dsc;
        TextView startCount;
        TextView commentCount;


        public ItemViewHolder(View view) {
            super(view);
            userImage = (CircleImageView) view.findViewById(R.id.userImg_item_post);
            userName = (TextView) view.findViewById(R.id.userName_item_post);
            img = (ImageView) view.findViewById(R.id.img_item_post);
            sub = (TextView) view.findViewById(R.id.sub_item_post);
            dsc = (TextView) view.findViewById(R.id.dsc_item_post);
            startCount = (TextView) view.findViewById(R.id.startCount_item_post);
            commentCount = (TextView) view.findViewById(R.id.commentCount_item_post);
        }
    }

}
