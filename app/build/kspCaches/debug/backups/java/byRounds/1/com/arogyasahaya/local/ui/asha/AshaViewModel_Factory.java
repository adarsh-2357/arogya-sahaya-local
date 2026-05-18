package com.arogyasahaya.local.ui.asha;

import android.content.Context;
import com.arogyasahaya.local.data.repository.HealthCampRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AshaViewModel_Factory implements Factory<AshaViewModel> {
  private final Provider<HealthCampRepository> healthCampRepositoryProvider;

  private final Provider<Context> contextProvider;

  public AshaViewModel_Factory(Provider<HealthCampRepository> healthCampRepositoryProvider,
      Provider<Context> contextProvider) {
    this.healthCampRepositoryProvider = healthCampRepositoryProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public AshaViewModel get() {
    return newInstance(healthCampRepositoryProvider.get(), contextProvider.get());
  }

  public static AshaViewModel_Factory create(
      Provider<HealthCampRepository> healthCampRepositoryProvider,
      Provider<Context> contextProvider) {
    return new AshaViewModel_Factory(healthCampRepositoryProvider, contextProvider);
  }

  public static AshaViewModel newInstance(HealthCampRepository healthCampRepository,
      Context context) {
    return new AshaViewModel(healthCampRepository, context);
  }
}
