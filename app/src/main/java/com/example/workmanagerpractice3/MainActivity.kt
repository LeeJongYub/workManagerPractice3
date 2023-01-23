package com.example.workmanagerpractice3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 1. WorkManager 의 주기적 실행 방법

        // periodicWorkRequestBuilder 를 통해 주기적인 작업 실행에 대한 알림 처리를 할 수 있다.
        // 최소 시간 간격은 15분이며, 그 이하의 시간으로 지정하여도 15분마다 처리된다.

        val workManager1 = PeriodicWorkRequestBuilder<WorkManager1>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueue(workManager1)


        // 2. WorkManager 의 '고유 작업' 처리 방식

            // 1) 일회성 작업

        val btn1 = findViewById<Button>(R.id.work_manager2_button)
        btn1.setOnClickListener {

            val workManager2 = OneTimeWorkRequestBuilder<WorkManager2>().build()
            // 고유 작업은 일회성 작업과 주기적 작업에 모두 적용할 수 있음 ('반복 작업'인지 아닌지 여부에 따라 고르자)
            // enqueueUniqueWork()(일회성 작업)
            WorkManager.getInstance(this)
                .enqueueUniqueWork("enqueueUniqueWork", ExistingWorkPolicy.KEEP, workManager2)

            // WorkManager.enqueueUniquePeriodicWork()(주기적 작업)

        }
            // 2) 주기적 작업

            val workManager3 = PeriodicWorkRequestBuilder<WorkManager3>(1, TimeUnit.HOURS)
                .setConstraints(Constraints.Builder().setRequiresCharging(true).build()).build()
                // setConstraints 를 통해 실행에 대한 제약조건을 걸 수 있다.
                // 지금은 setRequiresCharging 을 통해 실행여부를 충전 중일 경우로 설정하였다.

            WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "enqueueUniquePeriodicWork",
                ExistingPeriodicWorkPolicy.KEEP,
                workManager3
            )

        // 고유 작업 실행간 충돌 처리 옵션
        // ExistingWorkPolicy.KEEP -> 해당 id의 작업을 이미 실행 중이라면, 아무것도 하지 않는다.
        // ExistingWorkPolicy.REPLACE -> 해당 id의 작업을 이미 실행 중이라면, 체인을 취소, 삭제한 후 다시 시작한다.
        // ExistingWorkPolicy.APPEND -> 해당 id의 작업을 이미 실행 중이라면, 그 작업이 끝난 이후 실행한다.

    }
}