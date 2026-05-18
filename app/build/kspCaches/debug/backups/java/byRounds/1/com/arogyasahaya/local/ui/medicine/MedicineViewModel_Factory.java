package com.arogyasahaya.local.ui.medicine;

import com.arogyasahaya.local.data.repository.MedicineRepository;
import com.arogyasahaya.local.util.AlarmScheduler;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class MedicineViewModel_Factory implements Factory<MedicineViewModel> {
  private final Provider<MedicineRepository> medicineRepositoryProvider;

  private final Provider<AlarmScheduler> alarmSchedulerProvider;

  public MedicineViewModel_Factory(Provider<MedicineRepository> medicineRepositoryProvider,
      Provider<AlarmScheduler> alarmSchedulerProvider) {
    this.medicineRepositoryProvider = medicineRepositoryProvider;
    this.alarmSchedulerProvider = alarmSchedulerProvider;
  }

  @Override
  public MedicineViewModel get() {
    return newInstance(medicineRepositoryProvider.get(), alarmSchedulerProvider.get());
  }

  public static MedicineViewModel_Factory create(
      Provider<MedicineRepository> medicineRepositoryProvider,
      Provider<AlarmScheduler> alarmSchedulerProvider) {
    return new MedicineViewModel_Factory(medicineRepositoryProvider, alarmSchedulerProvider);
  }

  public static MedicineViewModel newInstance(MedicineRepository medicineRepository,
      AlarmScheduler alarmScheduler) {
    return new MedicineViewModel(medicineRepository, alarmScheduler);
  }
}
