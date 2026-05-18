package com.arogyasahaya.local.data.repository;

import com.arogyasahaya.local.data.local.dao.HealthCampDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class HealthCampRepository_Factory implements Factory<HealthCampRepository> {
  private final Provider<HealthCampDao> healthCampDaoProvider;

  public HealthCampRepository_Factory(Provider<HealthCampDao> healthCampDaoProvider) {
    this.healthCampDaoProvider = healthCampDaoProvider;
  }

  @Override
  public HealthCampRepository get() {
    return newInstance(healthCampDaoProvider.get());
  }

  public static HealthCampRepository_Factory create(Provider<HealthCampDao> healthCampDaoProvider) {
    return new HealthCampRepository_Factory(healthCampDaoProvider);
  }

  public static HealthCampRepository newInstance(HealthCampDao healthCampDao) {
    return new HealthCampRepository(healthCampDao);
  }
}
