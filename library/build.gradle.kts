@file:Suppress("UnusedPrivateMember")

plugins {
  kotlin(Kotlin.MultiplatformPluginId)
  id(MavenPublish.PluginId)
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
        implementation("io.github.boguszpawlowski.composecalendar:kotlinx-datetime:0.4.3-SNAPSHOT")
      }
    }
    val jvmMain by getting
  }
}

plugins.withId(MavenPublish.PluginId) {
  mavenPublish {
    sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
  }
}
