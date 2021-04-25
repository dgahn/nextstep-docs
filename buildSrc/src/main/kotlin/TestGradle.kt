object TestPlugin {
    const val jacoco = "jacoco"
}

object TestVersion {
    const val jacoco = "0.8.6"
    const val junit = "5.7.1"
    const val assertj = "3.19.0"
    const val mockito = "3.+"
}

object TestLibs {
    const val junitBom = "org.junit:junit-bom:${TestVersion.junit}"
    const val jupiter = "org.junit.jupiter:junit-jupiter"
    const val assertjCore = "org.assertj:assertj-core:${TestVersion.assertj}"
    const val mockito = "org.mockito:mockito-core:${TestVersion.mockito}"
}

object JacocoProps {
    const val htmlEnabled = true
    const val xmlEnabled = false
    const val csvEnabled = false
    const val enabled = true
}

object JacocoOption {
    const val element = "SOURCEFILE"
    const val limitCounter = "LINE"
    const val limitValue = "COVEREDRATIO"

    val limitMinimum = (1.0).toBigDecimal()
    val excludeList = listOf(
            "me/dgahn/baseball/Launcher.java",
            "me/dgahn/baseball/view/BaseBallConsoleView.java"
    )
}
