tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            element = JacocoOption.element

            limit {
                counter = JacocoOption.limitCounter
                value = JacocoOption.limitValue
                minimum = JacocoOption.limitMinimum
            }

            excludes = JacocoOption.excludeList
        }
    }
}
