package com.arogyasahaya.local.data.local;

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
public final class DatabaseModule_ProvideVitalLogDaoFactory implements Factory<VitalLogDao> {
  private final Provider<ArogyaDatabase> databaseProvider;

  public DatabaseModule_ProvideVitalLogDaoFactory(Provider<ArogyaDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public VitalLogDao get() {
    return provideVitalLogDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideVitalLogDaoFactory create(
      Provider<ArogyaDatabase> databaseProvider) {
    return new DatabaseModule_ProvideVitalLogDaoFactory(databaseProvider);
  }

  public static VitalLogDao provideVitalLogDao(ArogyaDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideVitalLogDao(database));
  }
}
