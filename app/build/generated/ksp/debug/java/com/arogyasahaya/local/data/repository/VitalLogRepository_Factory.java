package com.arogyasahaya.local.data.repository;

import com.arogyasahaya.local.data.local.dao.VitalLogDao;
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
public final class VitalLogRepository_Factory implements Factory<VitalLogRepository> {
  private final Provider<VitalLogDao> vitalLogDaoProvider;

  public VitalLogRepository_Factory(Provider<VitalLogDao> vitalLogDaoProvider) {
    this.vitalLogDaoProvider = vitalLogDaoProvider;
  }

  @Override
  public VitalLogRepository get() {
    return newInstance(vitalLogDaoProvider.get());
  }

  public static VitalLogRepository_Factory create(Provider<VitalLogDao> vitalLogDaoProvider) {
    return new VitalLogRepository_Factory(vitalLogDaoProvider);
  }

  public static VitalLogRepository newInstance(VitalLogDao vitalLogDao) {
    return new VitalLogRepository(vitalLogDao);
  }
}
