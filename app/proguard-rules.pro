# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


#----------<RoomDb>-----------
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**

#----------</RoomDb>-----------


#-------------<Data Store>------------------

-keep class androidx.datastore.*.** {*;}

-keepclassmembers class * extends androidx.datastore.preferences.protobuf.GeneratedMessageLite {
    <fields>;
}

#-------------</Data Store>------------------


#--------------<Hilt>------------------------------


-keepnames @dagger.hilt.android.lifecycle.HiltViewModel class * extends androidx.lifecycle.ViewModel


#--------------</Hilt>------------------------------


#------------<Models>------------------

-keep class com.alitafreshi.data.datasource.local.room.NoteDao{ *; }
-keep class com.alitafreshi.domain.model.**{ *; }
-keep class com.alitafreshi.domain.util.**{ *; }

#------------</Models>------------------


#------------<Ayan Models>------------------

-keep class ir.tafreshiali.ayan_core.util.**{ *; }

#------------</Ayan Models>------------------






