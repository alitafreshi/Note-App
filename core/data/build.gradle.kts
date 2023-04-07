plugins {
    id(Plugins.javaLibrary)
    id(KotlinPlugins.kotlin)
    kotlin(KotlinPlugins.serialization)
    id(Plugins.googleKspPlugin) version (Plugins.googleKspPluginVersion)
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

    //hilt annotations
    implementation(Hilt.javaInject)

    //room
    implementation(Room.roomKtxModules)

    //domain module
    implementation(project(Modules.domain))

}