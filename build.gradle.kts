plugins {
    java
    idea
    jacoco
    kotlin(KotlinPlugin.jvm) version KotlinVersion.kotlin
    id(ProjectPlugin.editorConfig) version ProjectVersion.editorConfig
}

allprojects {
    group = ProjectGroup.dgahn
    version = ProjectVersion.dgahn

    repositories {
        google()
        mavenCentral()
    }

    apply(plugin = TestPlugin.jacoco)
    apply(plugin = ProjectPlugin.idea)
    apply(plugin = KotlinPlugin.kotlin)
    apply(plugin = ProjectPlugin.editorConfig)

    dependencies {
        testImplementation(platform(TestLibs.junitBom))
        testImplementation(TestLibs.jupiter)
        testImplementation(TestLibs.assertjCore)
        testImplementation(TestLibs.mockito)
    }

    jacoco {
        toolVersion = TestVersion.jacoco
    }

    editorconfig {
        excludes = listOf("*/build", "reviewguide", "codereview")
    }

    tasks.jacocoTestReport {
        reports {
            html.isEnabled = JacocoProps.htmlEnabled
            xml.isEnabled = JacocoProps.xmlEnabled
            csv.isEnabled = JacocoProps.csvEnabled
        }
        finalizedBy(tasks.jacocoTestCoverageVerification)
    }

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
        jacoco {
            enabled = JacocoProps.enabled
        }
        dependsOn(tasks.editorconfigCheck)
        finalizedBy(tasks.jacocoTestReport)
    }
}
