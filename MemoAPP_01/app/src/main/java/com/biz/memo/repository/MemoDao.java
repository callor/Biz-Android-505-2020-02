package com.biz.memo.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.biz.memo.domain.MemoVO;

import java.util.List;

@Dao
public interface MemoDao {

    /*
    LiveData
    안드로이드 room DB와 연동하여
    MVVM 패턴을 사용할수있도록 도와주는 Helper class
    LifeCycle에 포함된 클래스로서
    DB의 내용이 변경되면 변경된 부분만 가져와서
    View에 표시할수 있도록 알람을 내부적으로 발생시키는 클래스
     */

    @Query("SELECT * FROM tbl_memo")
<<<<<<< HEAD
    public LiveData<List<MemoVO>> selectAll();
=======
    LiveData<List<MemoVO>> selectAll();
>>>>>>> d3dade02e2cd3f6e60c01b41804fb6f61101529a

    @Query("SELECT * FROM tbl_memo WHERE rowid = :rowid ")
    MemoVO findByRowId(String rowid);

    @Query("SELECT * FROM tbl_memo WHERE m_text LIKE :m_text")
<<<<<<< HEAD
    public LiveData<List<MemoVO>> findByText(String m_text);
=======
    LiveData<List<MemoVO>> findByText(String m_text);
>>>>>>> d3dade02e2cd3f6e60c01b41804fb6f61101529a

    /*
    ORM 구조에서는 새로운 데이터는 insert를 수행하고
    기존 데이터는 replace를 수행하는 메서드를 공통으로 사용을 한다.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(MemoVO memoVO);

    @Update
    void update(MemoVO memoVO);

<<<<<<< HEAD
    /*
    표준 room @Delete method는
    vo를 매개변수로 받아서 delete를 수행
     */
    @Delete
    public void delete(MemoVO memoVO);

=======
    @Query("DELETE FROM tbl_memo WHERE rowid = :rowid")
    void delete(String rowid);
>>>>>>> d3dade02e2cd3f6e60c01b41804fb6f61101529a

    @Delete
    void delete(MemoVO post);
}