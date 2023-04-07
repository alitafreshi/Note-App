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
    //hilt annotations
    implementation(Hilt.javaInject)

    //room
    implementation(Room.roomKtxModules)

    implementation(Kotlin.kotlinCoroutines)

    //domain module
    implementation(project(Modules.domain))

}