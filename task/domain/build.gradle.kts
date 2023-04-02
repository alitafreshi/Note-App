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

    //kotlinCoroutines
    implementation(Kotlin.kotlinCoroutines)

    //room
    implementation(Room.roomKtxModules)

    implementation(ayan.Core.ayanCore)

    implementation(project(Modules.domain))

}