package com.arogyasahaya.local.worker;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.annotation.processing.Generated;

@Generated("androidx.hilt.AndroidXHiltProcessor")
@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = MedicineReminderWorker.class
)
public interface MedicineReminderWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.arogyasahaya.local.worker.MedicineReminderWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(
      MedicineReminderWorker_AssistedFactory factory);
}
