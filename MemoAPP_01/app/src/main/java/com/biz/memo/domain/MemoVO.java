package com.biz.memo.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
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
@Builder
/*
FTS Ver 4
Full Text Search수행하는 방법에 3와 4가 있는데
최신 AS에서는 Fts4를 사용하도록 권장하고
매우 빠른 속도로 전체텍스트 검색을 할수 있다.
Fts4는 Room 2.1.0 이상에서 제공되는 기능
 */
//
@Fts4
@Entity(tableName = "tbl_memo")
public class MemoVO {

    /*
        PK 지정된 숫자형 칼럼에 auto increment 를 부여하는 속성
        SQLite 에서 FTS 라는 패턴을 지원하는 DB 형식
        FTS : Full Text Search
        FTS를 사용하려면 id 칼럼이 반드시 int 형이고
        db 칼럼 이름은 rowid로 설정을 해야한다.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="rowid")
    private int rowid;

    @ColumnInfo(name="m_date")
    private String m_date;

    @ColumnInfo(name="m_time")
    private String m_time;

    @ColumnInfo(name="m_text")
    private String m_text;


}



