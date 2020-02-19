package com.biz.memo.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.biz.memo.domain.MemoVO;
import com.biz.memo.repository.MemoDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MemoVO.class},version = 1)
public abstract  class MemoDataBase extends RoomDatabase {

    /*
        데이터베이스 INSTANCE가 생성이 되면서
        MemoDao interface를 가져다가 사용할수 있는 class 생성을 한다.
     */
    public abstract  MemoDao getMemoDao();

    // 고전적인 Thread 클래스를 도와서 Thread를 관리해주는
    // Helper 클래스
    // 앞으로 실행할(생성할) Thread를 위한 Context 정보를 담을 객체를
    // 미리 비어있는 상태로 생성을 해두고 필요할때 공급하는 용도
    static final ExecutorService dbWriterThread =
            Executors.newFixedThreadPool(3);

    /*
    Database를 생성하는 클래스를 싱글톤으로 선언하기 위해
    외부에서 접근하는 변수 선언
     */
    private static volatile MemoDataBase INSTANCE;
    public static MemoDataBase getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (MemoDataBase.class) {
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        MemoDataBase.class,
                        "memo.dbf"
                ).build();
            }
        }
        return INSTANCE;
    }

}
