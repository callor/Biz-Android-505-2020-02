package com.biz.memo.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity(tableName = "tbl_memo")
public class MemoVO {

    @PrimaryKey
    @ColumnInfo(name="rowid")
    private long id;

    @ColumnInfo(name="m_date")
    private String m_date;

    @ColumnInfo(name="m_time")
    private String m_time;

    @ColumnInfo
    private String m_text;


}



