plugins {
    id("legacyforge-common-conventions")
}

dependencies {
    compileOnly("${libs.mixin.get().module}:${libs.versions.mixin.get()}")
}
