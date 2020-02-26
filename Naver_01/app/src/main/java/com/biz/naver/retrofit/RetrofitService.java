package com.biz.naver.retrofit;

import com.biz.naver.domain.NaverMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("search/movie.json")
    Call<NaverMovie> getMovice(
            // Header 값들 추가
            @Header("X-Naver-Client-Id") String clientId ,
            @Header("X-Naver-Client-Secret") String clientSec,

            // 쿼리 문자열을 query 변수에 받아서 주입하겠다
            // ?query=기생충
            @Query("query") String query
    ) ;

}
