import java.io.FileInputStream
import java.util.Date
import java.util.Properties

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

val releaseTime = Date().time

val keystorePropertiesFile: File = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

val localPropertiesFile: File = rootProject.file("local.properties")
val localProperties = Properties()
localProperties.load(FileInputStream(localPropertiesFile))

val buglyId = localProperties["bugly_id"] as String
val buglyPrivacyUrl = localProperties["bugly_privacy_url"] as String
val userAgreementUrl = localProperties["user_agreement_url"] as String
val userAgreementVersion = 1 // 用户协议变动时 +1
val icp = localProperties["icp"] as String

val baseUrl1 = localProperties["base_url1"] as String
val baseUrl2 = localProperties["base_url2"] as String
val baseUrl3 = localProperties["base_url3"] as String

android {
    namespace = "com.hefengbao.jingmo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.hefengbao.jingmo"
        minSdk = 26
        targetSdk = 36
        versionCode = 1_015_001
        versionName = "1.15.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
        /*ndk {
            // 设置支持的SO库架构
            abiFilters += setOf(
                "armeabi",
                "x86",
                "arm64-v8a",
                "x86_64"
            )//, 'x86', 'armeabi-v7a', 'x86_64', 'arm64-v8a'
        }*/

    }

    // 多渠道打包配置
    flavorDimensions += listOf("version")

    productFlavors {
        create("github"){
            dimension = "version"
            manifestPlaceholders["APP_CHANNEL"] = "github"
        }
        create("gitee"){
            dimension = "version"
            manifestPlaceholders["APP_CHANNEL"] = "gitee"
        }
        create("huawei"){
            dimension = "version"
            manifestPlaceholders["APP_CHANNEL"] = "huawei"
        }
        create("xiaomi"){
            dimension = "version"
            manifestPlaceholders["APP_CHANNEL"] = "xiaomi"
        }
    }
    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            buildConfigField("String","BUGLY_ID", buglyId)
            buildConfigField("String","BUGLY_PRIVACY_URL", buglyPrivacyUrl)
            buildConfigField("String","USER_AGREEMENT_URL", userAgreementUrl)
            buildConfigField("int","USER_AGREEMENT_VERSION",  "$userAgreementVersion")
            buildConfigField("String","ICP", icp)
            buildConfigField("String","BASE_URL_1", baseUrl1)
            buildConfigField("String","BASE_URL_2", baseUrl2)
            buildConfigField("String","BASE_URL_3", baseUrl3)
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            applicationVariants.all {
                val buildType = this.buildType.name
                outputs.all {
                    if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                        outputFileName = "jingmo_v${defaultConfig.versionName}_$buildType.apk"
                    }
                }
            }
            buildConfigField("String","BUGLY_ID", buglyId)
            buildConfigField("String","BUGLY_PRIVACY_URL", buglyPrivacyUrl)
            buildConfigField("String","USER_AGREEMENT_URL", userAgreementUrl)
            buildConfigField("int","USER_AGREEMENT_VERSION",  "$userAgreementVersion")
            buildConfigField("String","ICP", icp)
            buildConfigField("String","BASE_URL_1", baseUrl1)
            buildConfigField("String","BASE_URL_2", baseUrl2)
            buildConfigField("String","BASE_URL_3", baseUrl3)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        // 参考 https://developer.android.google.cn/jetpack/androidx/releases/compose-kotlin
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    ksp {
        arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
    }
}

dependencies {
    implementation(libs.accompanist.permissions)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.material.iconsExtended)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.kotlinx.datetime)
    //implementation(libs.protobuf.kotlin.lite)
    implementation(libs.capturable)
    implementation(libs.retrofit)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.kotlin.serialization)

    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    implementation(libs.lunar)

    implementation(libs.hutool)

    implementation(libs.telephoto)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.bugly)

    // For AppWidgets support
    implementation(libs.androidx.glance.appwidget)
    // For interop APIs with Material 3
    implementation(libs.androidx.glance.material3)
}

class RoomSchemaArgProvider(
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val schemaDir: File,
) : CommandLineArgumentProvider {
    override fun asArguments() = listOf("room.schemaLocation=${schemaDir.path}")
}