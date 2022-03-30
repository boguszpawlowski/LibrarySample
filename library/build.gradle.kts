@file:Suppress("UnusedPrivateMember")

plugins {
  kotlin(Kotlin.MultiplatformPluginId)
}

kotlin {
  explicitApi()

  jvm {
    compilations.all {
      kotlinOptions.jvmTarget = "1.8"
    }
    testRuns["test"].executionTask.configure {
      useJUnitPlatform()
    }
  }

  sourceSets {
    matching { it.name.endsWith("Test") }.all {
      languageSettings {
        optIn("kotlin.time.ExperimentalTime")
      }
    }

    val commonMain by getting {
      dependencies {
        implementation(Coroutines.Core)
        implementation(Kotlin.StdLib)
      }
    }
    val jvmMain by getting
  }
}
