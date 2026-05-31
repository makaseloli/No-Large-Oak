plugins {
    id("neoforge-common-conventions")
}

dependencies {
    compileOnly("${libs.mixin.get().module}:${libs.versions.mixin.get()}")
}
