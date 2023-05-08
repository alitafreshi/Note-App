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

    //retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson)
    implementation(Retrofit.gson_convertor)


    //task domain - module
    implementation(project(Modules.taskDomain))

    //room - db - module
    implementation(project(Modules.roomDb))

    //core domain module
    implementation(project(Modules.domain))

    implementation("androidx.annotation:annotation-jvm:+")


}