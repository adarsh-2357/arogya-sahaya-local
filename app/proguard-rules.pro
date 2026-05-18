# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager { *; }

# MPAndroidChart
-keep class com.github.mikephil.charting.** { *; }
-dontwarn io.realm.**

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Keep data classes used by Room
-keepclassmembers class com.arogyasahaya.local.data.local.entity.** {
    <fields>;
    <init>(...);
}
