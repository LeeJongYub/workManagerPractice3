package com.example.workmanagerpractice3

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class WorkManager3(context: Context, workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {
    @SuppressLint("SimpleDateFormat")
    override suspend fun doWork(): Result {

        val simpleDateFormat = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
        val currentTime = simpleDateFormat.format(Date())

        Log.d("dowork3", currentTime)

        return Result.success()
    }
}