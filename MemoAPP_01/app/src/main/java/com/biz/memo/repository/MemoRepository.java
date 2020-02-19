package com.biz.memo.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.biz.memo.db.MemoDataBase;
import com.biz.memo.domain.MemoVO;

import java.util.List;

/*
DB 접근할때 사용할 Service 클래스
 */
public class MemoRepository {

    private MemoDao mDao;
    private LiveData<List<MemoVO>> memoList;

    public MemoRepository(Application application) {
        MemoDataBase db = MemoDataBase.getInstance(application);
        mDao = db.getMemoDao();
        memoList = mDao.selectAll();
    }

    public LiveData<List<MemoVO>> selectAll() {
<<<<<<< HEAD
        return mDao.selectAll();
=======
        return memoList;
>>>>>>> d3dade02e2cd3f6e60c01b41804fb6f61101529a
    }

    public void insert(final MemoVO memoVO) {
        /*
        MemoDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.save(memoVO);
            }
        });
         */
        MemoDataBase.databaseWriteExecutor.execute(()->{
            mDao.save(memoVO);
        });

    }

    public void delete(MemoVO post) {
        mDao.delete(post);
    }
}
