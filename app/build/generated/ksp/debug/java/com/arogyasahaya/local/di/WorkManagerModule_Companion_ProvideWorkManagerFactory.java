package com.arogyasahaya.local.di;

import android.content.Context;
import androidx.work.WorkManager;
import androidx.work.WorkerFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class WorkManagerModule_Companion_ProvideWorkManagerFactory implements Factory<WorkManager> {
  private final Provider<Context> contextProvider;

  private final Provider<WorkerFactory> workerFactoryProvider;

  public WorkManagerModule_Companion_ProvideWorkManagerFactory(Provider<Context> contextProvider,
      Provider<WorkerFactory> workerFactoryProvider) {
    this.contextProvider = contextProvider;
    this.workerFactoryProvider = workerFactoryProvider;
  }

  @Override
  public WorkManager get() {
    return provideWorkManager(contextProvider.get(), workerFactoryProvider.get());
  }

  public static WorkManagerModule_Companion_ProvideWorkManagerFactory create(
      Provider<Context> contextProvider, Provider<WorkerFactory> workerFactoryProvider) {
    return new WorkManagerModule_Companion_ProvideWorkManagerFactory(contextProvider, workerFactoryProvider);
  }

  public static WorkManager provideWorkManager(Context context, WorkerFactory workerFactory) {
    return Preconditions.checkNotNullFromProvides(WorkManagerModule.Companion.provideWorkManager(context, workerFactory));
  }
}
