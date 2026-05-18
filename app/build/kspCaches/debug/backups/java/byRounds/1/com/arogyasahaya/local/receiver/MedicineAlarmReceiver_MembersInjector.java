package com.arogyasahaya.local.receiver;

import com.arogyasahaya.local.util.AlarmScheduler;
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
public final class MedicineAlarmReceiver_MembersInjector implements MembersInjector<MedicineAlarmReceiver> {
  private final Provider<AlarmScheduler> alarmSchedulerProvider;

  public MedicineAlarmReceiver_MembersInjector(Provider<AlarmScheduler> alarmSchedulerProvider) {
    this.alarmSchedulerProvider = alarmSchedulerProvider;
  }

  public static MembersInjector<MedicineAlarmReceiver> create(
      Provider<AlarmScheduler> alarmSchedulerProvider) {
    return new MedicineAlarmReceiver_MembersInjector(alarmSchedulerProvider);
  }

  @Override
  public void injectMembers(MedicineAlarmReceiver instance) {
    injectAlarmScheduler(instance, alarmSchedulerProvider.get());
  }

  @InjectedFieldSignature("com.arogyasahaya.local.receiver.MedicineAlarmReceiver.alarmScheduler")
  public static void injectAlarmScheduler(MedicineAlarmReceiver instance,
      AlarmScheduler alarmScheduler) {
    instance.alarmScheduler = alarmScheduler;
  }
}
