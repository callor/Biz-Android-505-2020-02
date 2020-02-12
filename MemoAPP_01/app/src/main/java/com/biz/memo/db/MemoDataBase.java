package com.biz.memo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.biz.memo.domain.MemoVO;
import com.biz.memo.repository.MemoDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {MemoVO.class}, version = 1, exportSchema = false)
public abstract class MemoDataBase extends RoomDatabase {


    public abstract MemoDao getMemoDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile MemoDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

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
