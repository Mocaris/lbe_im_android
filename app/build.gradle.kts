plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.lbe.chatapp"

    compileSdk = 34

    defaultConfig {
        applicationId = "com.lbe.chatapp"
        minSdk = 24
        versionCode = 21
        versionName = "0.2.1"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        if (System.getenv("JITPACK") == null) {
            create("release") {
                keyAlias = "lbe"
                keyPassword = "lbe168"
                storeFile = file("../lbe.jks")
                storePassword = "lbe168"
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            if (System.getenv("JITPACK") == null) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

}

dependencies {
//    implementation("androidx.core:core-ktx:1.13.1")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
//    implementation("androidx.activity:activity-compose:1.9.3")
//    implementation(platform("androidx.compose:compose-bom:2024.10.00"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.documentfile:documentfile:1.0.1")
//    implementation("androidx.navigation:navigation-compose:2.8.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(project(":LbeIMSdk"))
//    implementation("com.github.haishuangsu:lbeim:1.1.3")
}
