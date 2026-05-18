package com.arogyasahaya.local.receiver;

import com.arogyasahaya.local.util.AlarmScheduler;
import com.arogyasahaya.local.util.NotificationHelper;
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
public final class MedicineNotificationActionReceiver_MembersInjector implements MembersInjector<MedicineNotificationActionReceiver> {
  private final Provider<NotificationHelper> notificationHelperProvider;

  private final Provider<AlarmScheduler> alarmSchedulerProvider;

  public MedicineNotificationActionReceiver_MembersInjector(
      Provider<NotificationHelper> notificationHelperProvider,
      Provider<AlarmScheduler> alarmSchedulerProvider) {
    this.notificationHelperProvider = notificationHelperProvider;
    this.alarmSchedulerProvider = alarmSchedulerProvider;
  }

  public static MembersInjector<MedicineNotificationActionReceiver> create(
      Provider<NotificationHelper> notificationHelperProvider,
      Provider<AlarmScheduler> alarmSchedulerProvider) {
    return new MedicineNotificationActionReceiver_MembersInjector(notificationHelperProvider, alarmSchedulerProvider);
  }

  @Override
  public void injectMembers(MedicineNotificationActionReceiver instance) {
    injectNotificationHelper(instance, notificationHelperProvider.get());
    injectAlarmScheduler(instance, alarmSchedulerProvider.get());
  }

  @InjectedFieldSignature("com.arogyasahaya.local.receiver.MedicineNotificationActionReceiver.notificationHelper")
  public static void injectNotificationHelper(MedicineNotificationActionReceiver instance,
      NotificationHelper notificationHelper) {
    instance.notificationHelper = notificationHelper;
  }

  @InjectedFieldSignature("com.arogyasahaya.local.receiver.MedicineNotificationActionReceiver.alarmScheduler")
  public static void injectAlarmScheduler(MedicineNotificationActionReceiver instance,
      AlarmScheduler alarmScheduler) {
    instance.alarmScheduler = alarmScheduler;
  }
}
