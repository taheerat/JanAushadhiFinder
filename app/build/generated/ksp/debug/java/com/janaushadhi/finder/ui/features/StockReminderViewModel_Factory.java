package com.janaushadhi.finder.ui.features;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class StockReminderViewModel_Factory implements Factory<StockReminderViewModel> {
  @Override
  public StockReminderViewModel get() {
    return newInstance();
  }

  public static StockReminderViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static StockReminderViewModel newInstance() {
    return new StockReminderViewModel();
  }

  private static final class InstanceHolder {
    static final StockReminderViewModel_Factory INSTANCE = new StockReminderViewModel_Factory();
  }
}
