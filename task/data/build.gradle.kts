plugins {
    id(Plugins.javaLibrary)
    id(KotlinPlugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    /*implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.androidxAnnotation)*/

    //kotlinCoroutines
    implementation(Kotlin.kotlinCoroutines)

    //room
    implementation(Room.roomKtxModules)

    //task domain module
    implementation(project(Modules.taskDomain))
}