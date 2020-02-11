package com.biz.myapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.biz.myapp.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    TextInputEditText textView1;
    TextInputEditText textView2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // final TextView textView = root.findViewById(R.id.text_home);
        textView1 = root.findViewById(R.id.txt_name);
        textView2 = root.findViewById(R.id.txt_tel);
        Button btn_save = root.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                // textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onClick(View v) {

        // EdtiText는 getText() method로 값을 추출
        // TextInputEditText 는 getText().toString() 연결해야 정상적인 문자열 추출가능
        String strName = textView1.getText().toString();
        String strTel = textView2.getText().toString();

        // btn_save 버튼이 클릭(터치)되었을때만 반응하기
        int id = v.getId();
        if(id == R.id.btn_save) {
            String msg = String.format("이름 : %s, 전화번호 : %s",strName,strTel);
            Snackbar.make(v,msg,Snackbar.LENGTH_SHORT).show();
        }
    }

}



