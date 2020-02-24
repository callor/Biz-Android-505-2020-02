package com.biz.memo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.biz.memo.domain.MemoVO;
import com.biz.memo.repository.MemoDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {MemoVO.class},
                version = 1,
                exportSchema = false)
public abstract class MemoDataBase extends RoomDatabase {

    public abstract MemoDao getMemoDao();

    // 고전적인 Thread 클래스를 도와서 Thread를 관리해주는
    // Helper 클래스
    // 앞으로 실행할(생성할) Thread를 위한 Context 정보를 담을 객체를
    // 미리 비어있는 상태로 생성을 해두고 필요할때 공급하는 용도
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile MemoDataBase INSTANCE;

    public static MemoDataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MemoDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MemoDataBase.class, "word_database")
                            // .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
