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

    //proto data store
    implementation(DataStore.preferencesDataStoreCore)
    implementation(DataStore.kotlinCollection)
    implementation(DataStore.kotlinJsonSerialization)

    //domain module
    implementation(project(Modules.domain))

    //data module
    implementation(project(Modules.data))
}