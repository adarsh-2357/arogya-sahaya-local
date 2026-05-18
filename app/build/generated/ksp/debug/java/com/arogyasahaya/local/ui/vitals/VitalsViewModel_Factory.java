package com.arogyasahaya.local.ui.vitals;

import com.arogyasahaya.local.data.repository.VitalLogRepository;
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
public final class VitalsViewModel_Factory implements Factory<VitalsViewModel> {
  private final Provider<VitalLogRepository> vitalLogRepositoryProvider;

  public VitalsViewModel_Factory(Provider<VitalLogRepository> vitalLogRepositoryProvider) {
    this.vitalLogRepositoryProvider = vitalLogRepositoryProvider;
  }

  @Override
  public VitalsViewModel get() {
    return newInstance(vitalLogRepositoryProvider.get());
  }

  public static VitalsViewModel_Factory create(
      Provider<VitalLogRepository> vitalLogRepositoryProvider) {
    return new VitalsViewModel_Factory(vitalLogRepositoryProvider);
  }

  public static VitalsViewModel newInstance(VitalLogRepository vitalLogRepository) {
    return new VitalsViewModel(vitalLogRepository);
  }
}
