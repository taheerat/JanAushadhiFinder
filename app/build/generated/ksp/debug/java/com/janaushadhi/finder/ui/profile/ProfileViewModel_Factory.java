package com.janaushadhi.finder.ui.profile;

import com.janaushadhi.finder.data.local.session.SessionManager;
import com.janaushadhi.finder.data.repository.SettingsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public ProfileViewModel_Factory(Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(settingsRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static ProfileViewModel_Factory create(
      javax.inject.Provider<SettingsRepository> settingsRepositoryProvider,
      javax.inject.Provider<SessionManager> sessionManagerProvider) {
    return new ProfileViewModel_Factory(Providers.asDaggerProvider(settingsRepositoryProvider), Providers.asDaggerProvider(sessionManagerProvider));
  }

  public static ProfileViewModel_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new ProfileViewModel_Factory(settingsRepositoryProvider, sessionManagerProvider);
  }

  public static ProfileViewModel newInstance(SettingsRepository settingsRepository,
      SessionManager sessionManager) {
    return new ProfileViewModel(settingsRepository, sessionManager);
  }
}
