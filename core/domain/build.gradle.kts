plugins {
    id(Plugins.javaLibrary)
    id(KotlinPlugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies{
    //retrofit
    implementation(Ktor.ktor_client_gson)
    implementation("androidx.annotation:annotation-jvm:+")
}