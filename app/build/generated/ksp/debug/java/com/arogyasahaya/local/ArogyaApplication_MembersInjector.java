package com.arogyasahaya.local;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@QualifierMetadata
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
public final class ArogyaApplication_MembersInjector implements MembersInjector<ArogyaApplication> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public ArogyaApplication_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<ArogyaApplication> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new ArogyaApplication_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(ArogyaApplication instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.arogyasahaya.local.ArogyaApplication.workerFactory")
  public static void injectWorkerFactory(ArogyaApplication instance,
      HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
