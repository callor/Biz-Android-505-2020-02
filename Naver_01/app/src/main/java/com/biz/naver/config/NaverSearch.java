package com.biz.naver.config;

import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.biz.naver.adapter.MovieAdapter;
import com.biz.naver.domain.NaverMovieVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/*
네이버 API를 비동기 방식으로 호출하여 데이터 가져오기
 */
public class NaverSearch extends AsyncTask<Integer,Integer,Void> {

    private final String naver_movie_url = "https://openapi.naver.com/v1/search/movie.json";
    private String strSearch;

    private List<NaverMovieVO> mList = null;
    private RecyclerView recyclerView;

    public NaverSearch() {
    }


    // 검색어와 RecyclerView를 전달받아서
    // 검색을 수행하고 RecyclerView에 보이기
    public NaverSearch(String strSearch, RecyclerView recyclerView) {
        this.strSearch = strSearch;
        this.recyclerView = recyclerView;
    }

    /*
                매개변수에 변수타입... 변수명 형식으로 지정을 하면
                매개변수가 몇개인지 관계없이 어떤 부분이라도 이 메서드를 호출할수 있다
                매개변수의 개수가 정해지 않은 호출방식
                doInBackground(3,4,5,6,7,8,9,0)
                Integer[] integers = new Interger[8]

                 */
    @Override
    protected Void doInBackground(Integer... integers) {
        this.nave_search();
        return null;
    }

    /*
    doInBackground() method가 naver_search() method를 호출하여
    백그라운드에서 실행을 하고
    실행이 완료되면 완료 event를 받을 method
    RecyclerView에 데이터를 표현
     */
    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);

        MovieAdapter movieAdapter = new MovieAdapter(mList);
        recyclerView.setAdapter(movieAdapter);

        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void nave_search() {

        try {

            String apiURL = naver_movie_url;

            apiURL += "?query=" + URLEncoder.encode(strSearch,"UTF-8");
            apiURL += "&start=1";
            apiURL += "&display=20";

            URL url = new URL(apiURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("X-Naver-Client-Id",
                                    NaverSecur.NAVER_ID);
            httpURLConnection.setRequestProperty("X-Naver-Client-Secret",
                    NaverSecur.NAVER_SEC);

            int resCode = httpURLConnection.getResponseCode();

            BufferedReader buffer;
            if(resCode == 200) {
                buffer = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            } else {
                buffer = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
            }

            String resString = "";
            String reader ;
            while(true) {

                reader = buffer.readLine();
                if(reader == null) break;
                resString += reader;
            }

            JSONObject resJson = new JSONObject(resString);
            JSONArray resItems = resJson.getJSONArray("items");

            // java 1.8에서 List<Type>이 설정되면 ArrayList<> Type을 설정하지 않아도 된다
            mList = new ArrayList<>();

            for(int i = 0 ; i < resItems.length(); i++) {

                JSONObject item = resItems.getJSONObject(i);

                NaverMovieVO mVO = NaverMovieVO.builder()
                                .title(item.getString("title"))
                                .director(item.getString("director"))
                                .actor(item.getString("actor"))
                                .link(item.getString("link"))
                                .image(item.getString("image"))
                                .build();
                mList.add(mVO);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
