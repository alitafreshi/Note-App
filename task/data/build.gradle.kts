plugins {
    id(Plugins.javaLibrary)
    id(KotlinPlugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    //kotlinCoroutines
    implementation(Kotlin.kotlinCoroutines)

    //task domain - module
    implementation(project(Modules.taskDomain))

    //room - db - module
    implementation(project(Modules.roomDb))

    //retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson)
    implementation(Retrofit.gson_convertor)
}