package com.example.workmanagerpractice3

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class WorkManager2(context: Context, workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {

    // WorkManager 의 '고유 작업' 처리는 doWork() 메서드에 구현한 작업이 한 번만 실행되도록 함
    // 동일한 작업이 여러 번 대기열에 포함되더라도 여러 번 실행되지 않도록 하고 싶을 때 유용함
    override suspend fun doWork(): Result {

        for (i in 1..5) {
            Log.d("doWork2", "$i")
            delay(1000)
        }

        return Result.success()
    }

}