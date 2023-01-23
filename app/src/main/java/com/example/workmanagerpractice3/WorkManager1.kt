package com.example.workmanagerpractice3

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class WorkManager1(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {

        val simpleDateFormat = SimpleDateFormat("hh:mm:ss")
        val currentTime = simpleDateFormat.format(Date())

        // 15분마다 현재 시간에 대한 알림 처리를 위한 로그
        Log.d("dowork1", currentTime)

        return Result.success()
    }
}