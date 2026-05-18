package com.arogyasahaya.local.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MedicineReminderWorker_AssistedFactory_Impl implements MedicineReminderWorker_AssistedFactory {
  private final MedicineReminderWorker_Factory delegateFactory;

  MedicineReminderWorker_AssistedFactory_Impl(MedicineReminderWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public MedicineReminderWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<MedicineReminderWorker_AssistedFactory> create(
      MedicineReminderWorker_Factory delegateFactory) {
    return InstanceFactory.create(new MedicineReminderWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<MedicineReminderWorker_AssistedFactory> createFactoryProvider(
      MedicineReminderWorker_Factory delegateFactory) {
    return InstanceFactory.create(new MedicineReminderWorker_AssistedFactory_Impl(delegateFactory));
  }
}
