package com.arogyasahaya.local.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.arogyasahaya.local.util.NotificationHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class MedicineReminderWorker_Factory {
  private final Provider<NotificationHelper> notificationHelperProvider;

  public MedicineReminderWorker_Factory(Provider<NotificationHelper> notificationHelperProvider) {
    this.notificationHelperProvider = notificationHelperProvider;
  }

  public MedicineReminderWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, notificationHelperProvider.get());
  }

  public static MedicineReminderWorker_Factory create(
      Provider<NotificationHelper> notificationHelperProvider) {
    return new MedicineReminderWorker_Factory(notificationHelperProvider);
  }

  public static MedicineReminderWorker newInstance(Context context, WorkerParameters workerParams,
      NotificationHelper notificationHelper) {
    return new MedicineReminderWorker(context, workerParams, notificationHelper);
  }
}
