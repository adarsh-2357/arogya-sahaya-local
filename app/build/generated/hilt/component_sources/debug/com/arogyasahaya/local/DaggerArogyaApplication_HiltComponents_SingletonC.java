package com.arogyasahaya.local;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.arogyasahaya.local.data.local.ArogyaDatabase;
import com.arogyasahaya.local.data.local.DatabaseModule_ProvideArogyaDatabaseFactory;
import com.arogyasahaya.local.data.local.DatabaseModule_ProvideHealthCampDaoFactory;
import com.arogyasahaya.local.data.local.DatabaseModule_ProvideMedicineDaoFactory;
import com.arogyasahaya.local.data.local.DatabaseModule_ProvideVitalLogDaoFactory;
import com.arogyasahaya.local.data.local.dao.HealthCampDao;
import com.arogyasahaya.local.data.local.dao.MedicineDao;
import com.arogyasahaya.local.data.local.dao.VitalLogDao;
import com.arogyasahaya.local.data.repository.HealthCampRepository;
import com.arogyasahaya.local.data.repository.MedicineRepository;
import com.arogyasahaya.local.data.repository.RepositoryModule_ProvideHealthCampRepositoryFactory;
import com.arogyasahaya.local.data.repository.RepositoryModule_ProvideMedicineRepositoryFactory;
import com.arogyasahaya.local.data.repository.RepositoryModule_ProvideVitalLogRepositoryFactory;
import com.arogyasahaya.local.data.repository.VitalLogRepository;
import com.arogyasahaya.local.receiver.MedicineAlarmReceiver;
import com.arogyasahaya.local.receiver.MedicineAlarmReceiver_MembersInjector;
import com.arogyasahaya.local.receiver.MedicineNotificationActionReceiver;
import com.arogyasahaya.local.receiver.MedicineNotificationActionReceiver_MembersInjector;
import com.arogyasahaya.local.ui.MainActivity;
import com.arogyasahaya.local.ui.MainActivity_MembersInjector;
import com.arogyasahaya.local.ui.asha.AshaViewModel;
import com.arogyasahaya.local.ui.asha.AshaViewModel_HiltModules;
import com.arogyasahaya.local.ui.medicine.MedicineViewModel;
import com.arogyasahaya.local.ui.medicine.MedicineViewModel_HiltModules;
import com.arogyasahaya.local.ui.profile.MedicalProfileViewModel;
import com.arogyasahaya.local.ui.profile.MedicalProfileViewModel_HiltModules;
import com.arogyasahaya.local.ui.vitals.VitalsViewModel;
import com.arogyasahaya.local.ui.vitals.VitalsViewModel_HiltModules;
import com.arogyasahaya.local.util.AlarmScheduler;
import com.arogyasahaya.local.util.DataStoreManager;
import com.arogyasahaya.local.util.NotificationHelper;
import com.arogyasahaya.local.worker.MedicineReminderWorker;
import com.arogyasahaya.local.worker.MedicineReminderWorker_AssistedFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerArogyaApplication_HiltComponents_SingletonC {
  private DaggerArogyaApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public ArogyaApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements ArogyaApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public ArogyaApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements ArogyaApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public ArogyaApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements ArogyaApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public ArogyaApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements ArogyaApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ArogyaApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements ArogyaApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ArogyaApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements ArogyaApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public ArogyaApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements ArogyaApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public ArogyaApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends ArogyaApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends ArogyaApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends ArogyaApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends ArogyaApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(4).put(LazyClassKeyProvider.com_arogyasahaya_local_ui_asha_AshaViewModel, AshaViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_arogyasahaya_local_ui_profile_MedicalProfileViewModel, MedicalProfileViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_arogyasahaya_local_ui_medicine_MedicineViewModel, MedicineViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_arogyasahaya_local_ui_vitals_VitalsViewModel, VitalsViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectNotificationHelper(instance, singletonCImpl.notificationHelperProvider.get());
      return instance;
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_arogyasahaya_local_ui_profile_MedicalProfileViewModel = "com.arogyasahaya.local.ui.profile.MedicalProfileViewModel";

      static String com_arogyasahaya_local_ui_asha_AshaViewModel = "com.arogyasahaya.local.ui.asha.AshaViewModel";

      static String com_arogyasahaya_local_ui_vitals_VitalsViewModel = "com.arogyasahaya.local.ui.vitals.VitalsViewModel";

      static String com_arogyasahaya_local_ui_medicine_MedicineViewModel = "com.arogyasahaya.local.ui.medicine.MedicineViewModel";

      @KeepFieldType
      MedicalProfileViewModel com_arogyasahaya_local_ui_profile_MedicalProfileViewModel2;

      @KeepFieldType
      AshaViewModel com_arogyasahaya_local_ui_asha_AshaViewModel2;

      @KeepFieldType
      VitalsViewModel com_arogyasahaya_local_ui_vitals_VitalsViewModel2;

      @KeepFieldType
      MedicineViewModel com_arogyasahaya_local_ui_medicine_MedicineViewModel2;
    }
  }

  private static final class ViewModelCImpl extends ArogyaApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AshaViewModel> ashaViewModelProvider;

    private Provider<MedicalProfileViewModel> medicalProfileViewModelProvider;

    private Provider<MedicineViewModel> medicineViewModelProvider;

    private Provider<VitalsViewModel> vitalsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.ashaViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.medicalProfileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.medicineViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.vitalsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(4).put(LazyClassKeyProvider.com_arogyasahaya_local_ui_asha_AshaViewModel, ((Provider) ashaViewModelProvider)).put(LazyClassKeyProvider.com_arogyasahaya_local_ui_profile_MedicalProfileViewModel, ((Provider) medicalProfileViewModelProvider)).put(LazyClassKeyProvider.com_arogyasahaya_local_ui_medicine_MedicineViewModel, ((Provider) medicineViewModelProvider)).put(LazyClassKeyProvider.com_arogyasahaya_local_ui_vitals_VitalsViewModel, ((Provider) vitalsViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_arogyasahaya_local_ui_asha_AshaViewModel = "com.arogyasahaya.local.ui.asha.AshaViewModel";

      static String com_arogyasahaya_local_ui_profile_MedicalProfileViewModel = "com.arogyasahaya.local.ui.profile.MedicalProfileViewModel";

      static String com_arogyasahaya_local_ui_medicine_MedicineViewModel = "com.arogyasahaya.local.ui.medicine.MedicineViewModel";

      static String com_arogyasahaya_local_ui_vitals_VitalsViewModel = "com.arogyasahaya.local.ui.vitals.VitalsViewModel";

      @KeepFieldType
      AshaViewModel com_arogyasahaya_local_ui_asha_AshaViewModel2;

      @KeepFieldType
      MedicalProfileViewModel com_arogyasahaya_local_ui_profile_MedicalProfileViewModel2;

      @KeepFieldType
      MedicineViewModel com_arogyasahaya_local_ui_medicine_MedicineViewModel2;

      @KeepFieldType
      VitalsViewModel com_arogyasahaya_local_ui_vitals_VitalsViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.arogyasahaya.local.ui.asha.AshaViewModel 
          return (T) new AshaViewModel(singletonCImpl.provideHealthCampRepositoryProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 1: // com.arogyasahaya.local.ui.profile.MedicalProfileViewModel 
          return (T) new MedicalProfileViewModel(singletonCImpl.dataStoreManagerProvider.get());

          case 2: // com.arogyasahaya.local.ui.medicine.MedicineViewModel 
          return (T) new MedicineViewModel(singletonCImpl.provideMedicineRepositoryProvider.get(), singletonCImpl.alarmSchedulerProvider.get());

          case 3: // com.arogyasahaya.local.ui.vitals.VitalsViewModel 
          return (T) new VitalsViewModel(singletonCImpl.provideVitalLogRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends ArogyaApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends ArogyaApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends ArogyaApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<NotificationHelper> notificationHelperProvider;

    private Provider<MedicineReminderWorker_AssistedFactory> medicineReminderWorker_AssistedFactoryProvider;

    private Provider<AlarmScheduler> alarmSchedulerProvider;

    private Provider<ArogyaDatabase> provideArogyaDatabaseProvider;

    private Provider<HealthCampDao> provideHealthCampDaoProvider;

    private Provider<HealthCampRepository> provideHealthCampRepositoryProvider;

    private Provider<DataStoreManager> dataStoreManagerProvider;

    private Provider<MedicineDao> provideMedicineDaoProvider;

    private Provider<MedicineRepository> provideMedicineRepositoryProvider;

    private Provider<VitalLogDao> provideVitalLogDaoProvider;

    private Provider<VitalLogRepository> provideVitalLogRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return Collections.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>singletonMap("com.arogyasahaya.local.worker.MedicineReminderWorker", ((Provider) medicineReminderWorker_AssistedFactoryProvider));
    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.notificationHelperProvider = DoubleCheck.provider(new SwitchingProvider<NotificationHelper>(singletonCImpl, 1));
      this.medicineReminderWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<MedicineReminderWorker_AssistedFactory>(singletonCImpl, 0));
      this.alarmSchedulerProvider = DoubleCheck.provider(new SwitchingProvider<AlarmScheduler>(singletonCImpl, 2));
      this.provideArogyaDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<ArogyaDatabase>(singletonCImpl, 5));
      this.provideHealthCampDaoProvider = DoubleCheck.provider(new SwitchingProvider<HealthCampDao>(singletonCImpl, 4));
      this.provideHealthCampRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<HealthCampRepository>(singletonCImpl, 3));
      this.dataStoreManagerProvider = DoubleCheck.provider(new SwitchingProvider<DataStoreManager>(singletonCImpl, 6));
      this.provideMedicineDaoProvider = DoubleCheck.provider(new SwitchingProvider<MedicineDao>(singletonCImpl, 8));
      this.provideMedicineRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<MedicineRepository>(singletonCImpl, 7));
      this.provideVitalLogDaoProvider = DoubleCheck.provider(new SwitchingProvider<VitalLogDao>(singletonCImpl, 10));
      this.provideVitalLogRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<VitalLogRepository>(singletonCImpl, 9));
    }

    @Override
    public void injectArogyaApplication(ArogyaApplication arogyaApplication) {
      injectArogyaApplication2(arogyaApplication);
    }

    @Override
    public void injectMedicineAlarmReceiver(MedicineAlarmReceiver medicineAlarmReceiver) {
      injectMedicineAlarmReceiver2(medicineAlarmReceiver);
    }

    @Override
    public void injectMedicineNotificationActionReceiver(
        MedicineNotificationActionReceiver medicineNotificationActionReceiver) {
      injectMedicineNotificationActionReceiver2(medicineNotificationActionReceiver);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private ArogyaApplication injectArogyaApplication2(ArogyaApplication instance) {
      ArogyaApplication_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      return instance;
    }

    private MedicineAlarmReceiver injectMedicineAlarmReceiver2(MedicineAlarmReceiver instance) {
      MedicineAlarmReceiver_MembersInjector.injectAlarmScheduler(instance, alarmSchedulerProvider.get());
      return instance;
    }

    private MedicineNotificationActionReceiver injectMedicineNotificationActionReceiver2(
        MedicineNotificationActionReceiver instance) {
      MedicineNotificationActionReceiver_MembersInjector.injectNotificationHelper(instance, notificationHelperProvider.get());
      MedicineNotificationActionReceiver_MembersInjector.injectAlarmScheduler(instance, alarmSchedulerProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.arogyasahaya.local.worker.MedicineReminderWorker_AssistedFactory 
          return (T) new MedicineReminderWorker_AssistedFactory() {
            @Override
            public MedicineReminderWorker create(Context context, WorkerParameters workerParams) {
              return new MedicineReminderWorker(context, workerParams, singletonCImpl.notificationHelperProvider.get());
            }
          };

          case 1: // com.arogyasahaya.local.util.NotificationHelper 
          return (T) new NotificationHelper(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.arogyasahaya.local.util.AlarmScheduler 
          return (T) new AlarmScheduler(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.arogyasahaya.local.data.repository.HealthCampRepository 
          return (T) RepositoryModule_ProvideHealthCampRepositoryFactory.provideHealthCampRepository(singletonCImpl.provideHealthCampDaoProvider.get());

          case 4: // com.arogyasahaya.local.data.local.dao.HealthCampDao 
          return (T) DatabaseModule_ProvideHealthCampDaoFactory.provideHealthCampDao(singletonCImpl.provideArogyaDatabaseProvider.get());

          case 5: // com.arogyasahaya.local.data.local.ArogyaDatabase 
          return (T) DatabaseModule_ProvideArogyaDatabaseFactory.provideArogyaDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 6: // com.arogyasahaya.local.util.DataStoreManager 
          return (T) new DataStoreManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.arogyasahaya.local.data.repository.MedicineRepository 
          return (T) RepositoryModule_ProvideMedicineRepositoryFactory.provideMedicineRepository(singletonCImpl.provideMedicineDaoProvider.get());

          case 8: // com.arogyasahaya.local.data.local.dao.MedicineDao 
          return (T) DatabaseModule_ProvideMedicineDaoFactory.provideMedicineDao(singletonCImpl.provideArogyaDatabaseProvider.get());

          case 9: // com.arogyasahaya.local.data.repository.VitalLogRepository 
          return (T) RepositoryModule_ProvideVitalLogRepositoryFactory.provideVitalLogRepository(singletonCImpl.provideVitalLogDaoProvider.get());

          case 10: // com.arogyasahaya.local.data.local.dao.VitalLogDao 
          return (T) DatabaseModule_ProvideVitalLogDaoFactory.provideVitalLogDao(singletonCImpl.provideArogyaDatabaseProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
