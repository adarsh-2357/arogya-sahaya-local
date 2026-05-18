package com.arogyasahaya.local.di;

import com.arogyasahaya.local.data.local.AppDatabase;
import com.arogyasahaya.local.data.local.dao.HealthRecordDao;
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
public final class DatabaseModule_ProvideHealthRecordDaoFactory implements Factory<HealthRecordDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideHealthRecordDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public HealthRecordDao get() {
    return provideHealthRecordDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideHealthRecordDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideHealthRecordDaoFactory(databaseProvider);
  }

  public static HealthRecordDao provideHealthRecordDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideHealthRecordDao(database));
  }
}
