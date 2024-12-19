plugins {
    kotlin("multiplatform") version "2.1.0" apply false
    id("com.android.application") version "8.5.2" apply false
    id("com.android.library") version "8.1.0" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}