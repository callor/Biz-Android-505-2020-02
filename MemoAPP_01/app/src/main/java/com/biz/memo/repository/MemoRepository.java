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
        return memoList;
    }

<<<<<<< HEAD
    /* thread로 save 실행 */
    public void save(final MemoVO memoVO) {
        /*
        MemoDataBase.databaseWriteExecutor.execute(new Runnable() {
=======
    /* thread로 insert 실행 */
    public void insert(final MemoVO memoVO) {

        // 기본자바 코드
        /*
        MemoDataBase.dbWriterThread.execute(new Runnable() {
>>>>>>> 1f72a97f7508dc1a2891941a7437d1ebca0786ca
            @Override
            public void run() {
                mDao.save(memoVO);
            }
        });
<<<<<<< HEAD

         */

        MemoDataBase.databaseWriteExecutor.execute(()->{
            mDao.save(memoVO);
        });
=======
        */
        MemoDataBase.dbWriterThread.execute( ()->mDao.save(memoVO)  );
>>>>>>> 1f72a97f7508dc1a2891941a7437d1ebca0786ca

    }
    public void delete(MemoVO memoVO) {
        MemoDataBase.dbWriterThread.execute( ()->mDao.delete(memoVO) );
    }
}
