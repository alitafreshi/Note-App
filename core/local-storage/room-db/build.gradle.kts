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

    //kotlinCoroutines
    implementation(Kotlin.kotlinCoroutines)

    //room
    implementation(Room.roomKtxModules)
}