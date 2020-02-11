package com.biz.myapp.ui.dashboard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.biz.myapp.R;

public class DashboardFragment extends Fragment implements View.OnClickListener{

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ImageView imgView = root.findViewById(R.id.image_1);
        imgView.setOnClickListener(this);
        return root;

    }

    @Override
    public void onClick(View v) {

        /*
        SnackBar, Toast와 달리 popup alert를 띄우고 사용자의 선택을 기다리는 클래스
         */
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext())
                .setTitle("종료")
                .setMessage("app을 종료할까요")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            // finish();
                    }
                }).setNegativeButton("아니오",null);

        AlertDialog dialog = alert.create();
        dialog.show();

    }
}