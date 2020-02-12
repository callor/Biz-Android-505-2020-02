package com.biz.memo.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biz.memo.R;
import com.biz.memo.domain.MemoVO;

import java.util.List;

public class MemoViewAdapter extends RecyclerView.Adapter {

    // item 삭제를 위한 이벤트 선
    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClicked(MemoVO post);
    }

    private Context context = null;
    private List<MemoVO> memoList = null;
    private LayoutInflater layoutInflater;
    private OnDeleteButtonClickListener onDeleteButtonClickListener;

    /*
    MainActivity에서 이벤트 인터페이스를 구현하고
    생성자에 주입한다.
     */
    public MemoViewAdapter(Context context, OnDeleteButtonClickListener onDeleteButtonClickListener) {
        this.context = context;
        this.onDeleteButtonClickListener = onDeleteButtonClickListener;
    }

    public void setMemoList(List<MemoVO> memoList) {
        this.memoList = memoList;
    }


    public MemoViewAdapter(Context context) {
        this.context = context;
    }

    /*
        MainActivity에서 MemoViewAdapter를 만들때 Context와 memoList를 주입할 생성자
         */
    public MemoViewAdapter(Context context, List<MemoVO> memoList) {
        this.context = context;
        this.memoList = memoList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*
        memo_item.xml파일을 가져와서 view객체로 생성(확장)하기
         */
        /*
        View view = LayoutInflater.from(context).inflate(R.layout.memo_item,
                            parent,
                false);
        */

        View view = layoutInflater.inflate(R.layout.memo_item, parent, false);
        MemoHolder holder = new MemoHolder(view);
        return holder;
    }

    /*
    memo_item.xml에 설정한 여러가지 view들을 사용할수있도록 초기화 하는 과정
    */
    class MemoHolder extends RecyclerView.ViewHolder {

        public TextView item_view_time;
        public TextView item_view_date;
        public TextView item_view_text;
        public Button item_delete;

        public MemoHolder(@NonNull View itemView) {
            super(itemView);
            item_view_time = itemView.findViewById(R.id.item_time);
            item_view_date = itemView.findViewById(R.id.item_date);
            item_view_text = itemView.findViewById(R.id.item_text);
            item_delete = itemView.findViewById(R.id.btn_delete);
        }

    }

    /*
    memoList의 개수만큼 반복문으로 호출되는 메서드
    반복문이 호출되면서 몇번째 데이터인가를 posion 변수에 주입해 준다.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        /*
            RecyclerView.ViewHolder를 MemoHolder로 형변환 하여
            MemoHoder에 직접 접근할 수 있도록 한다.
         */
        MemoHolder memoHolder = (MemoHolder) holder;

        /*
        memoList의 각 아이템 요소를 한개씩 읽어서
        TextView setText() method를 이용해서 문자열을 채워 넣어준다.
         */
        memoHolder.item_view_date.setText(memoList.get(position).getM_date());
        memoHolder.item_view_time.setText(memoList.get(position).getM_time());
        memoHolder.item_view_text.setText(memoList.get(position).getM_text());


        memoHolder.item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteButtonClickListener.onDeleteButtonClicked(memoList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return memoList != null ? memoList.size() : 0;
    }


}











