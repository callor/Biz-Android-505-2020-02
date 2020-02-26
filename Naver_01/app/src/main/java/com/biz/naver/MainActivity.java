package com.biz.naver;

import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;

import com.biz.naver.adapter.MovieAdapter;
import com.biz.naver.config.NaverSearch;
import com.biz.naver.config.NaverSecur;
import com.biz.naver.domain.NaverMovie;
import com.biz.naver.domain.NaverMovieItem;
import com.biz.naver.retrofit.RetrofieClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lombok.ToString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txt_search;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txt_search = findViewById(R.id.txt_search);
        recyclerView = findViewById(R.id.movie_list);

        /*
        키보드의 돋보기 버튼을 클릭했을때 발생하는 event
         */
        txt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String strSearch = txt_search.getText().toString();
                    if(strSearch.isEmpty()) {
                        Toast.makeText(MainActivity.this,
                               "검색어를 입력하세요",Toast.LENGTH_SHORT).show();

                    } else {

                        // Toast.makeText(MainActivity.this,strSearch, Toast.LENGTH_SHORT).show();
                        // NaverSearch naverSearch = new NaverSearch(strSearch,recyclerView);
                        // naverSearch.execute();
                        Call<NaverMovie> naverCall = RetrofieClient.getInstance().getMovice(
                                NaverSecur.NAVER_ID,
                                NaverSecur.NAVER_SEC,
                                strSearch
                        );

                        /**
                         * 2020.2.26 enqueue CallBack 설명 필요
                         * I will be Back ...
                         */
                        naverCall.enqueue(new Callback<NaverMovie>() {
                            @Override
                            public void onResponse(Call<NaverMovie> call, Response<NaverMovie> response) {

                                List<NaverMovieItem> mList
                                        = response.body().getItems();
                                MovieAdapter movieAdapter
                                        = new MovieAdapter(mList);
                                recyclerView.setAdapter(movieAdapter);

                                StaggeredGridLayoutManager layoutManager
                                        = new StaggeredGridLayoutManager(
                                                1,
                                        StaggeredGridLayoutManager.VERTICAL
                                );
                                recyclerView.setLayoutManager(layoutManager);
                            }

                            @Override
                            public void onFailure(Call<NaverMovie> call, Throwable t) {
                                Log.d("NAVER",t.getMessage());
                            }
                        });



                    }
                }
                return false;
            }
        });




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}
