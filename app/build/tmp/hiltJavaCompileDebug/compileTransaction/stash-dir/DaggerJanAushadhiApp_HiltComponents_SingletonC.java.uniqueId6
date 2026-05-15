package com.janaushadhi.finder;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.janaushadhi.finder.data.local.JanAushadhiDatabase;
import com.janaushadhi.finder.data.local.dao.MedicineDao;
import com.janaushadhi.finder.data.local.session.SessionManager;
import com.janaushadhi.finder.data.remote.GeminiApiService;
import com.janaushadhi.finder.data.remote.GeocodingApiService;
import com.janaushadhi.finder.data.repository.GeminiRepository;
import com.janaushadhi.finder.data.repository.MedicineRepository;
import com.janaushadhi.finder.data.repository.SettingsRepository;
import com.janaushadhi.finder.data.repository.StoreRepository;
import com.janaushadhi.finder.di.DatabaseModule_ProvideDatabaseFactory;
import com.janaushadhi.finder.di.DatabaseModule_ProvideMedicineDaoFactory;
import com.janaushadhi.finder.di.NetworkModule_ProvideGeminiApiServiceFactory;
import com.janaushadhi.finder.di.NetworkModule_ProvideGeminiRetrofitFactory;
import com.janaushadhi.finder.di.NetworkModule_ProvideGeocodingApiServiceFactory;
import com.janaushadhi.finder.di.NetworkModule_ProvideGoogleMapsRetrofitFactory;
import com.janaushadhi.finder.di.NetworkModule_ProvideOkHttpClientFactory;
import com.janaushadhi.finder.ui.MainViewModel;
import com.janaushadhi.finder.ui.MainViewModel_HiltModules;
import com.janaushadhi.finder.ui.MainViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.janaushadhi.finder.ui.MainViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.janaushadhi.finder.ui.auth.AuthViewModel;
import com.janaushadhi.finder.ui.auth.AuthViewModel_HiltModules;
import com.janaushadhi.finder.ui.auth.AuthViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.janaushadhi.finder.ui.auth.AuthViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.janaushadhi.finder.ui.chat.AIChatViewModel;
import com.janaushadhi.finder.ui.chat.AIChatViewModel_HiltModules;
import com.janaushadhi.finder.ui.chat.AIChatViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.janaushadhi.finder.ui.chat.AIChatViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.janaushadhi.finder.ui.features.StockReminderViewModel;
import com.janaushadhi.finder.ui.features.StockReminderViewModel_HiltModules;
import com.janaushadhi.finder.ui.features.StockReminderViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.janaushadhi.finder.ui.features.StockReminderViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.janaushadhi.finder.ui.maps.MapsViewModel;
import com.janaushadhi.finder.ui.maps.MapsViewModel_HiltModules;
import com.janaushadhi.finder.ui.maps.MapsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.janaushadhi.finder.ui.maps.MapsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.janaushadhi.finder.ui.profile.ProfileViewModel;
import com.janaushadhi.finder.ui.profile.ProfileViewModel_HiltModules;
import com.janaushadhi.finder.ui.profile.ProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.janaushadhi.finder.ui.profile.ProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.janaushadhi.finder.ui.search.SearchViewModel;
import com.janaushadhi.finder.ui.search.SearchViewModel_HiltModules;
import com.janaushadhi.finder.ui.search.SearchViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.janaushadhi.finder.ui.search.SearchViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
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
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DaggerJanAushadhiApp_HiltComponents_SingletonC {
  private DaggerJanAushadhiApp_HiltComponents_SingletonC() {
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

    public JanAushadhiApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements JanAushadhiApp_HiltComponents.ActivityRetainedC.Builder {
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
    public JanAushadhiApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements JanAushadhiApp_HiltComponents.ActivityC.Builder {
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
    public JanAushadhiApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements JanAushadhiApp_HiltComponents.FragmentC.Builder {
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
    public JanAushadhiApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements JanAushadhiApp_HiltComponents.ViewWithFragmentC.Builder {
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
    public JanAushadhiApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements JanAushadhiApp_HiltComponents.ViewC.Builder {
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
    public JanAushadhiApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements JanAushadhiApp_HiltComponents.ViewModelC.Builder {
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
    public JanAushadhiApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements JanAushadhiApp_HiltComponents.ServiceC.Builder {
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
    public JanAushadhiApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends JanAushadhiApp_HiltComponents.ViewWithFragmentC {
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

  private static final class FragmentCImpl extends JanAushadhiApp_HiltComponents.FragmentC {
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

  private static final class ViewCImpl extends JanAushadhiApp_HiltComponents.ViewC {
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

  private static final class ActivityCImpl extends JanAushadhiApp_HiltComponents.ActivityC {
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
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(7).put(AIChatViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AIChatViewModel_HiltModules.KeyModule.provide()).put(AuthViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AuthViewModel_HiltModules.KeyModule.provide()).put(MainViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, MainViewModel_HiltModules.KeyModule.provide()).put(MapsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, MapsViewModel_HiltModules.KeyModule.provide()).put(ProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ProfileViewModel_HiltModules.KeyModule.provide()).put(SearchViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SearchViewModel_HiltModules.KeyModule.provide()).put(StockReminderViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, StockReminderViewModel_HiltModules.KeyModule.provide()).build());
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
  }

  private static final class ViewModelCImpl extends JanAushadhiApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AIChatViewModel> aIChatViewModelProvider;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<MainViewModel> mainViewModelProvider;

    private Provider<MapsViewModel> mapsViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<SearchViewModel> searchViewModelProvider;

    private Provider<StockReminderViewModel> stockReminderViewModelProvider;

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
      this.aIChatViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.mainViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.mapsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.searchViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.stockReminderViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(7).put(AIChatViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) aIChatViewModelProvider)).put(AuthViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) authViewModelProvider)).put(MainViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) mainViewModelProvider)).put(MapsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) mapsViewModelProvider)).put(ProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) profileViewModelProvider)).put(SearchViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) searchViewModelProvider)).put(StockReminderViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) stockReminderViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
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
          case 0: // com.janaushadhi.finder.ui.chat.AIChatViewModel 
          return (T) new AIChatViewModel(singletonCImpl.geminiRepositoryProvider.get());

          case 1: // com.janaushadhi.finder.ui.auth.AuthViewModel 
          return (T) new AuthViewModel(singletonCImpl.sessionManagerProvider.get());

          case 2: // com.janaushadhi.finder.ui.MainViewModel 
          return (T) new MainViewModel(singletonCImpl.settingsRepositoryProvider.get());

          case 3: // com.janaushadhi.finder.ui.maps.MapsViewModel 
          return (T) new MapsViewModel(singletonCImpl.storeRepositoryProvider.get(), singletonCImpl.provideGeocodingApiServiceProvider.get());

          case 4: // com.janaushadhi.finder.ui.profile.ProfileViewModel 
          return (T) new ProfileViewModel(singletonCImpl.settingsRepositoryProvider.get(), singletonCImpl.sessionManagerProvider.get());

          case 5: // com.janaushadhi.finder.ui.search.SearchViewModel 
          return (T) new SearchViewModel(singletonCImpl.medicineRepositoryProvider.get());

          case 6: // com.janaushadhi.finder.ui.features.StockReminderViewModel 
          return (T) new StockReminderViewModel();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends JanAushadhiApp_HiltComponents.ActivityRetainedC {
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

  private static final class ServiceCImpl extends JanAushadhiApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends JanAushadhiApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideGeminiRetrofitProvider;

    private Provider<GeminiApiService> provideGeminiApiServiceProvider;

    private Provider<GeminiRepository> geminiRepositoryProvider;

    private Provider<SessionManager> sessionManagerProvider;

    private Provider<SettingsRepository> settingsRepositoryProvider;

    private Provider<StoreRepository> storeRepositoryProvider;

    private Provider<Retrofit> provideGoogleMapsRetrofitProvider;

    private Provider<GeocodingApiService> provideGeocodingApiServiceProvider;

    private Provider<JanAushadhiDatabase> provideDatabaseProvider;

    private Provider<MedicineDao> provideMedicineDaoProvider;

    private Provider<MedicineRepository> medicineRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 3));
      this.provideGeminiRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 2));
      this.provideGeminiApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<GeminiApiService>(singletonCImpl, 1));
      this.geminiRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<GeminiRepository>(singletonCImpl, 0));
      this.sessionManagerProvider = DoubleCheck.provider(new SwitchingProvider<SessionManager>(singletonCImpl, 4));
      this.settingsRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<SettingsRepository>(singletonCImpl, 5));
      this.storeRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<StoreRepository>(singletonCImpl, 6));
      this.provideGoogleMapsRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 8));
      this.provideGeocodingApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<GeocodingApiService>(singletonCImpl, 7));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<JanAushadhiDatabase>(singletonCImpl, 11));
      this.provideMedicineDaoProvider = DoubleCheck.provider(new SwitchingProvider<MedicineDao>(singletonCImpl, 10));
      this.medicineRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<MedicineRepository>(singletonCImpl, 9));
    }

    @Override
    public void injectJanAushadhiApp(JanAushadhiApp janAushadhiApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
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
          case 0: // com.janaushadhi.finder.data.repository.GeminiRepository 
          return (T) new GeminiRepository(singletonCImpl.provideGeminiApiServiceProvider.get());

          case 1: // com.janaushadhi.finder.data.remote.GeminiApiService 
          return (T) NetworkModule_ProvideGeminiApiServiceFactory.provideGeminiApiService(singletonCImpl.provideGeminiRetrofitProvider.get());

          case 2: // @javax.inject.Named("GeminiRetrofit") retrofit2.Retrofit 
          return (T) NetworkModule_ProvideGeminiRetrofitFactory.provideGeminiRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 3: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient();

          case 4: // com.janaushadhi.finder.data.local.session.SessionManager 
          return (T) new SessionManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 5: // com.janaushadhi.finder.data.repository.SettingsRepository 
          return (T) new SettingsRepository();

          case 6: // com.janaushadhi.finder.data.repository.StoreRepository 
          return (T) new StoreRepository();

          case 7: // com.janaushadhi.finder.data.remote.GeocodingApiService 
          return (T) NetworkModule_ProvideGeocodingApiServiceFactory.provideGeocodingApiService(singletonCImpl.provideGoogleMapsRetrofitProvider.get());

          case 8: // @javax.inject.Named("GoogleMapsRetrofit") retrofit2.Retrofit 
          return (T) NetworkModule_ProvideGoogleMapsRetrofitFactory.provideGoogleMapsRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 9: // com.janaushadhi.finder.data.repository.MedicineRepository 
          return (T) new MedicineRepository(singletonCImpl.provideMedicineDaoProvider.get());

          case 10: // com.janaushadhi.finder.data.local.dao.MedicineDao 
          return (T) DatabaseModule_ProvideMedicineDaoFactory.provideMedicineDao(singletonCImpl.provideDatabaseProvider.get());

          case 11: // com.janaushadhi.finder.data.local.JanAushadhiDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
