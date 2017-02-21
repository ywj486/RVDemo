package com.bc.ywj.rvdemo;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> datas=new ArrayList<>();
    private MyAdapter myAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        mRecyclerView= (RecyclerView) findViewById(R.id.rv);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.sw);
        initRecyclerView();
        initRefresh();
    }
    private void initRecyclerView(){
        myAdapter=new MyAdapter(datas);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        // mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置item动画
        myAdapter.setListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, String city) {
                Toast.makeText(MainActivity.this,"city:"+city+"position:"+position,Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initRefresh(){
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.holo_purple);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<10;i++){
                            myAdapter.addData(i,"new city:"+i);
                        }
                        myAdapter.notifyItemRangeChanged(0,10);
                        mRecyclerView.scrollToPosition(0);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });

    }

    private void initDatas(){
        datas.add("New York");
        datas.add("Boston");
        datas.add("Washington");
        datas.add("San Francisco");
        datas.add("California");
        datas.add("Chicago");
        datas.add("Houston");
        datas.add("Phoenix");
        datas.add("Philadelphia");
        datas.add("Pennsylvania");
        datas.add("San Antonio");
        datas.add("Austin");
        datas.add("Milwaukee");
        datas.add("Las Vegas");
        datas.add("Oklahoma");
        datas.add("Portland");
        datas.add("Mexico");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_action_add:
                myAdapter.addData(0,"new city");
                mRecyclerView.scrollToPosition(0);
                break;
            case R.id.id_action_delete:
                myAdapter.removeData(1);
                break;
            case R.id.id_action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_horizontalgridview:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
        }
        return true;
    }
}
