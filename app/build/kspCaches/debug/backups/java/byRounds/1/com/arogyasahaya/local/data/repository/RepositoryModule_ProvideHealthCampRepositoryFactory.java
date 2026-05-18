package com.arogyasahaya.local.data.repository;

import com.arogyasahaya.local.data.local.dao.HealthCampDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RepositoryModule_ProvideHealthCampRepositoryFactory implements Factory<HealthCampRepository> {
  private final Provider<HealthCampDao> healthCampDaoProvider;

  public RepositoryModule_ProvideHealthCampRepositoryFactory(
      Provider<HealthCampDao> healthCampDaoProvider) {
    this.healthCampDaoProvider = healthCampDaoProvider;
  }

  @Override
  public HealthCampRepository get() {
    return provideHealthCampRepository(healthCampDaoProvider.get());
  }

  public static RepositoryModule_ProvideHealthCampRepositoryFactory create(
      Provider<HealthCampDao> healthCampDaoProvider) {
    return new RepositoryModule_ProvideHealthCampRepositoryFactory(healthCampDaoProvider);
  }

  public static HealthCampRepository provideHealthCampRepository(HealthCampDao healthCampDao) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideHealthCampRepository(healthCampDao));
  }
}
