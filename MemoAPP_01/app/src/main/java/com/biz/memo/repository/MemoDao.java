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
    public LiveData<List<MemoVO>> selectAll();

    @Query("SELECT * FROM tbl_memo WHERE rowid = :rowid ")
    MemoVO findByRowId(String rowid);

    @Query("SELECT * FROM tbl_memo WHERE m_text LIKE :m_text")
    public LiveData<List<MemoVO>> findByText(String m_text);

    /*
    ORM 구조에서는 새로운 데이터는 insert를 수행하고
    기존 데이터는 replace를 수행하는 메서드를 공통으로 사용을 한다.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void save(MemoVO memoVO);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(MemoVO memoVO);

    @Update
    public void update(MemoVO memoVO);

    /*
    표준 room @Delete method는
    vo를 매개변수로 받아서 delete를 수행
     */
    @Delete
    public void delete(MemoVO memoVO);

    @Query("DELETE FROM tbl_memo WHERE rowid = :rowid")
    public void delete(String rowid);


}