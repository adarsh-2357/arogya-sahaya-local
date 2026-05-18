package com.arogyasahaya.local.data.repository;

import com.arogyasahaya.local.data.local.dao.VitalLogDao;
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
public final class RepositoryModule_ProvideVitalLogRepositoryFactory implements Factory<VitalLogRepository> {
  private final Provider<VitalLogDao> vitalLogDaoProvider;

  public RepositoryModule_ProvideVitalLogRepositoryFactory(
      Provider<VitalLogDao> vitalLogDaoProvider) {
    this.vitalLogDaoProvider = vitalLogDaoProvider;
  }

  @Override
  public VitalLogRepository get() {
    return provideVitalLogRepository(vitalLogDaoProvider.get());
  }

  public static RepositoryModule_ProvideVitalLogRepositoryFactory create(
      Provider<VitalLogDao> vitalLogDaoProvider) {
    return new RepositoryModule_ProvideVitalLogRepositoryFactory(vitalLogDaoProvider);
  }

  public static VitalLogRepository provideVitalLogRepository(VitalLogDao vitalLogDao) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideVitalLogRepository(vitalLogDao));
  }
}
