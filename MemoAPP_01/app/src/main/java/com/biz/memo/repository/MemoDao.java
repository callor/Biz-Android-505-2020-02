package com.biz.memo.repository;

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

    @Query("SELECT * FROM tbl_memo")
    public List<MemoVO> selectAll();

    @Query("SELECT * FROM tbl_memo WHERE rowid = :rowid ")
    public MemoVO findByRowId(long rowid);

    @Query("SELECT * FROM tbl_memo WHERE m_text LIKE :m_text")
    public List<MemoVO> findByText(String m_text);

    /*
    ORM 구조에서는 새로운 데이터는 insert를 수행하고
    기존 데이터는 replace를 수행하는 메서드를 공통으로 사용을 한다.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void save(MemoVO memoVO);

    @Update
    public void update(MemoVO memoVO);

    @Delete
    public void delete(long rowid);

}
