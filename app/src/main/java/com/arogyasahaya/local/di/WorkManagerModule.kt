package com.arogyasahaya.local.di

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkManagerModule {

    @Binds
    abstract fun bindWorkerFactory(
        workerFactory: HiltWorkerFactory
    ): WorkerFactory

    companion object {

        @Provides
        @Singleton
        fun provideWorkManager(
            @ApplicationContext context: Context,
            workerFactory: WorkerFactory
        ): WorkManager {
            val configuration = Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .setMinimumLoggingLevel(android.util.Log.INFO)
                .build()

            WorkManager.initialize(context, configuration)

            return WorkManager.getInstance(context)
        }
    }
}
