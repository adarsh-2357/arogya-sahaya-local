package com.arogyasahaya.local.data.repository;

import com.arogyasahaya.local.data.local.dao.MedicineDao;
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
public final class MedicineRepository_Factory implements Factory<MedicineRepository> {
  private final Provider<MedicineDao> medicineDaoProvider;

  public MedicineRepository_Factory(Provider<MedicineDao> medicineDaoProvider) {
    this.medicineDaoProvider = medicineDaoProvider;
  }

  @Override
  public MedicineRepository get() {
    return newInstance(medicineDaoProvider.get());
  }

  public static MedicineRepository_Factory create(Provider<MedicineDao> medicineDaoProvider) {
    return new MedicineRepository_Factory(medicineDaoProvider);
  }

  public static MedicineRepository newInstance(MedicineDao medicineDao) {
    return new MedicineRepository(medicineDao);
  }
}
