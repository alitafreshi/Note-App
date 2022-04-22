plugins {
    id(Plugins.javaLibrary)
    id(KotlinPlugins.kotlin)
    kotlin(KotlinPlugins.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {
//    implementation(AndroidX.coreKtx)
//    implementation(Kotlin.kotlinCoroutines)
    implementation(DataStore.preferencesDataStoreCore)
    implementation(DataStore.kotlinCollection)
    implementation(DataStore.kotlinJsonSerialization)
    implementation(Hilt.javaInject)
    implementation(ayan.Core.ayanCore)
}