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

    //ktor client
    implementation(Ktor.ktor_client_core)
    implementation(Ktor.ktor_client_gson)



    //task domain - module
    implementation(project(Modules.taskDomain))

    //room - db - module
    implementation(project(Modules.roomDb))

    //core domain module
    implementation(project(Modules.domain))

    implementation(project(Modules.data))

    //constance module
    implementation(project(Modules.constance))

    implementation("androidx.annotation:annotation-jvm:+")


}