package com.arogyasahaya.local.ui.profile;

import com.arogyasahaya.local.util.DataStoreManager;
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
public final class MedicalProfileViewModel_Factory implements Factory<MedicalProfileViewModel> {
  private final Provider<DataStoreManager> dataStoreManagerProvider;

  public MedicalProfileViewModel_Factory(Provider<DataStoreManager> dataStoreManagerProvider) {
    this.dataStoreManagerProvider = dataStoreManagerProvider;
  }

  @Override
  public MedicalProfileViewModel get() {
    return newInstance(dataStoreManagerProvider.get());
  }

  public static MedicalProfileViewModel_Factory create(
      Provider<DataStoreManager> dataStoreManagerProvider) {
    return new MedicalProfileViewModel_Factory(dataStoreManagerProvider);
  }

  public static MedicalProfileViewModel newInstance(DataStoreManager dataStoreManager) {
    return new MedicalProfileViewModel(dataStoreManager);
  }
}
