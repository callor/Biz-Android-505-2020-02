package com.biz.naver.adapter;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biz.naver.R;
import com.biz.naver.domain.NaverMovieItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {

    List<NaverMovieItem> movieVOList;

    public MovieAdapter(List<NaverMovieItem> movieVOList) {
        this.movieVOList = movieVOList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.movie_item_view,
                                parent,
                                false
                        );
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MovieHolder mHolder = (MovieHolder) holder;

        String strTitle = movieVOList.get(position).getTitle();
        strTitle = "<font color=blue>" + strTitle + "</font>";
        //strTitle = "<a href=" + movieVOList.get(position).getLink() + ">"
        //                    + strTitle + "</a>";

        mHolder.txt_title.setText( this.getHTML(strTitle)  );

        String strDirector = movieVOList.get(position).getDirector();
        strDirector = "<b>감독: </b>" + strDirector;
        mHolder.txt_director.setText(  this.getHTML( strDirector ) ) ;

        String strActor = movieVOList.get(position).getActor();
        strActor = "<b>주연: </b>" + strActor;
        mHolder.txt_actor.setText( this.getHTML( strActor ) );
        mHolder.txt_link.setText(movieVOList.get(position).getLink());


        try {

            int intRating = (int)(Float.valueOf(movieVOList.get(position).getUserRating()) / 2);
            String strRating = "";
            for(int i = 0 ; i < intRating ; i++) {
                strRating += "★";
            }
            strRating = "<b>평점 : </b><font color=blue>" + strRating + "</font>";
            mHolder.txt_rating.setText(this.getHTML(strRating));

        } catch (Exception e) {
        }

        // 이미지 세팅 잠시 보류
        String imageLink = movieVOList.get(position).getImage();
        if(!imageLink.isEmpty())  {

            // imageLink에 있는 이미지를 다운로드하여
            // mHolder.m_image ImageView에 표시하라
            Picasso.get().load(imageLink).into(mHolder.m_image);

            // Glide.with(mHolder.itemView.getContext())
            //        .load(imageLink).into(mHolder.m_image) ;

        }

    }

    // 문자열에 HTML Tag가 들어있는 경우 해당문자열에 HTML 효과를 적용하기 위해
    // 변환 method를 생성
    private Spanned getHTML(String strText) {
        Spanned spText;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spText = Html.fromHtml(strText,Html.FROM_HTML_MODE_LEGACY);
        } else {
            spText = Html.fromHtml(strText);
        }
        return spText;
    }

    @Override
    public int getItemCount() {
        return movieVOList == null ? 0 : movieVOList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        TextView txt_title;
        TextView txt_director;
        TextView txt_actor;
        TextView txt_rating;
        TextView txt_link;
        ImageView m_image;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.m_item_title);
            txt_director = itemView.findViewById(R.id.m_item_director);
            txt_actor = itemView.findViewById(R.id.m_item_actor);
            txt_rating = itemView.findViewById(R.id.m_item_rating);
            txt_link = itemView.findViewById(R.id.m_item_link);
            m_image = itemView.findViewById(R.id.m_item_image);


        }
    }

}
