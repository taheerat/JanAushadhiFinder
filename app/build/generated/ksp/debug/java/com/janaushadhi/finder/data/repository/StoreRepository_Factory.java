package com.janaushadhi.finder.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class StoreRepository_Factory implements Factory<StoreRepository> {
  @Override
  public StoreRepository get() {
    return newInstance();
  }

  public static StoreRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static StoreRepository newInstance() {
    return new StoreRepository();
  }

  private static final class InstanceHolder {
    static final StoreRepository_Factory INSTANCE = new StoreRepository_Factory();
  }
}
