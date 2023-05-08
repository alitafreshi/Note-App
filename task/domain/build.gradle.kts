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

    //retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson)
    implementation(Retrofit.gson_convertor)

    //domain module
    implementation(project(Modules.domain))

    //roomDb Module
    implementation(project(Modules.roomDb))

    //constance module
    implementation(project(Modules.constance))

    implementation("androidx.annotation:annotation-jvm:1.6.0")

    implementation(project(Modules.data))

}