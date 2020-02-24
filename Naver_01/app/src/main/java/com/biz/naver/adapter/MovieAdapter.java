package com.biz.naver.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biz.naver.R;
import com.biz.naver.domain.NaverMovieVO;

import java.lang.reflect.Parameter;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {

    List<NaverMovieVO> movieVOList;

    public MovieAdapter(List<NaverMovieVO> movieVOList) {
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

        mHolder.txt_title.setText(movieVOList.get(position).getTitle());
        mHolder.txt_director.setText(movieVOList.get(position).getDirector());
        mHolder.txt_actor.setText(movieVOList.get(position).getActor());
        mHolder.txt_rating.setText(movieVOList.get(position).getUserRating());

        // 이미지 세팅 잠시 보류


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
        ImageView m_image;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.m_item_title);
            txt_director = itemView.findViewById(R.id.m_item_director);
            txt_actor = itemView.findViewById(R.id.m_item_actor);
            txt_rating = itemView.findViewById(R.id.m_item_rating);
            m_image = itemView.findViewById(R.id.m_item_image);


        }
    }

}
