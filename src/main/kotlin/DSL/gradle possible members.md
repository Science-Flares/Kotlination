
```kotlin
project
plugins.run {
    hasPlugin("")
    apply("")
    findPlugin("")
    getAt("")
    getPlugin("")
    withId("") {}
}
kotlin.run {
    target {}
    ext {}
    experimental {}
    sourceSets {}
}
kotlinTestRegistry.run {
    project
    allTestsTask
    allTestsTaskName
    getOrCreateAggregatedTestTask("","")
    this.registerTestTask()
}
kotlinExtension
kotlinPackageFqn.run {
    parent()
    isRoot
    asString()
    child(Name.special(""))
    pathSegments()
    shortName()
    shortNameOrSpecial()
    startsWith(Name.special(""))
    toUnsafe()
}
embeddedKotlinVersion
expectedKotlinDslPluginsVersion
primitiveKotlinTypeNames
gradle.run {
    gradleVersion
    gradleHomeDir
    gradleUserHomeDir
    includedBuilds
    parent
    rootProject
    sharedServices
    startParameter
    taskGraph
    rootProject {  }
    includedBuild("")
    addBuildListener()
    addListener("")
    addProjectEvaluationListener()
    removeListener()
    removeProjectEvaluationListener()
    afterProject {  }
    beforeProject {  }
    allprojects {  }
    beforeSettings {  }
    buildFinished {  }
    projectsEvaluated(Action {  })
    projectsLoaded {  }
    settingsEvaluated {  }
    useLogger("")
}
allprojects
ant.run {
    properties
    lifecycleLogLevel
    references
    importBuild("")
    setLifecycleLogLevel("")
}
artifacts.run {
    add("","")
    annotationProcessor("")
    api("")
    apiDependenciesMetadata("")
    apiElements("")
    testApi("")
    testApiDependenciesMetadata("")
    archives("")
    compileClasspath("")
    compileOnly("")
    compileOnlyDependenciesMetadata("")
    default("")
    implementation("")
    implementationDependenciesMetadata("")
    testImplementation("")
    testImplementationDependenciesMetadata("")
    intransitiveDependenciesMetadata("")
    testIntransitiveDependenciesMetadata("")
    invoke {
    }
    kotlinCompilerClasspath("")
    kotlinCompilerPluginClasspath("")
    kotlinCompilerPluginClasspathMain("")
    kotlinCompilerPluginClasspathTest("")
    kotlinKlibCommonizerClasspath("")
    kotlinNativeCompilerPluginClasspath("")
    kotlinScriptDef("")
    kotlinScriptDefExtensions("")
    runtimeClasspath("")
    runtimeElements("")
    runtimeOnly("")
    runtimeOnlyDependenciesMetadata("")
    testRuntimeClasspath("")
    testRuntimeOnly("")
    testRuntimeOnlyDependenciesMetadata("")
    sourceArtifacts("")
    testKotlinScriptDef("")
    testKotlinScriptDefExtensions("")
}
buildDir.run {
}
buildFile.run {
}
buildscript.run {
    classLoader
    configurations
    dependencies
    dependencyLocking
    repositories
    sourceFile
    sourceURI
    repositories {
    }
    dependencyLocking {
    }
    dependencies {
    }
}
childProjects.run {
}
components
configurations
defaultTasks
dependencies.run {
    components
    artifactTypes
    attributesSchema
    constraints
    modules
    devNpm
    ext
    npm
    optionalNpm
    peerNpm
    modules {  }
    constraints {  }
    attributesSchema {  }
    artifactTypes {  }
    components {  }
    add("","")
    addProvider("",provider {  })
    create("")
    createArtifactResolutionQuery()
    enforcedPlatform("")
    platform("")
    gradleApi()
    gradleTestKit()
    localGroovy()
    testFixtures("")
    variantOf(){}
}
dependencyLocking.run {
    ignoredDependencies
    lockFile
    lockMode
    lockAllConfigurations()
    unlockAllConfigurations()
}
depth
description
displayName
extensions.run {
    extensionsSchema
    extraProperties
    add("","")
    get("")
    findByName("")
    getByName("")
    configure<Any> {  }
}
ext.run {
    properties
    get("")
    set("","")
    has("")
    invoke {}
    provideDelegate(null,this::javaClass)
}
extra.run {
}
group = "me.yon"
layout.run {
    buildDirectory
    projectDirectory
    dir(provider { null })
    file(provider { null })
    files(null)
}
logger.run {
    isLifecycleEnabled
    isQuietEnabled
    isEnabled(LogLevel.INFO)
    lifecycle("")
    log(LogLevel.INFO,"")
    quiet("")
}
logging.run {
    level
    standardErrorCaptureLevel
    standardOutputCaptureLevel
    captureStandardError(LogLevel.WARN)
    captureStandardOutput(LogLevel.INFO)
}
name
normalization.run {
    runtimeClasspath
    runtimeClasspath {  }
}
objects.run {
    directoryProperty()
    domainObjectContainer(this::class)
    domainObjectSet(this::class)
    fileCollection()
    fileProperty()
    fileTree()
    listProperty(this::class)
    mapProperty(this::class ,this::class)
    named()
    namedDomainObjectList(this::class)
    namedDomainObjectSet(this::class)
    polymorphicDomainObjectContainer(this::class)
    property()
    setProperty("","")
    sourceDirectorySet("","")
}
parent
path
pluginManager.run {
    apply("")
    findPlugin("")
    hasPlugin("")
    withPlugin(""){}
}
projectDir
properties
providers.run {
    credentials()
    fileContents(provider {null})
    environmentVariable("")
    fileContents(provider { null })
    gradleProperty(provider { "" })
    of()
    systemProperty("")
    zip()
}
repositories.run {
    exclusiveContent{}
    flatDir()
    google()
    gradlePluginPortal()
    ivy {}
    maven {}
    mavenCentral()
    mavenLocal()
}
resources.run {
    text
    gzip("")
    bzip2("")
}
rootDir
rootProject
state.run {
    executed
    failure
    rethrowFailure()
}
status
subprojects
tasks.run {
    test
    jar
    assemble
    build
    buildNeeded
    buildDependents
    buildEnvironment
    buildKotlinToolingMetadata
    check
    classes
    clean
    compileJava
    compileTestJava
    compileKotlin
    compileTestKotlin
    components
    dependencies
    dependencyInsight
    dependentComponents
    help
    init
    inspectClassesForKotlinIC
    javaToolchains
    javadoc
    kotlinDslAccessorsReport
    kotlinSourcesJar
    mainClasses
    model
    outgoingVariants
    prepareKotlinBuildScriptModel
    processResources
    processTestResources
    projects
    properties
    create()
    findByPath("")
    getByPath("")
    register("")
    replace("")
    invoke {  }
    withType<KotlinCompile>() {
        kotlinOptions.jvmTarget = "11"
    }
}
version = "1.0.1"
java.run {

}
javaToolchains
base.run {
    archivesName
    ext
    distsDirectory
    libsDirectory
}
defaultArtifacts.run {
    addCandidate(null)
}
reporting.run {
    ext
    baseDir
    apiDocTitle
    baseDirectory
    file("")
    setBaseDir("")
}
sourceSets
yarn.run {
    command
    download
    downloadBaseUrl
    ignoreScripts
    installationDir
    lockFileDirectory
    lockFileName
    project
    resolutions
    rootPackageJsonTaskProvider
    version
    yarnSetupTaskProvider
    resolution("","")
    disableGranularWorkspaces()
}

apply {
}
project("")

task("")

provider {

}

plugins {
    kotlin("jvm") version "1.6.10"
}

ext("...")

configurations(groovy.lang.Closure.IDENTITY)

absoluteProjectPath("")
afterEvaluate {

}
beforeEvaluate {

}
configure {}
container()
copy {

}
copySpec {

}
createAntBuilder()
delete {

}
depthCompare(project)
evaluationDependsOn("")
evaluationDependsOnChildren()
exec {}
file("")
fileTree()
files("")
findProject("")
findProperty("")
getAllTasks(true)
getTasksByName("",true)
mkdir("")
java {
}
javaexec {}
property("")
relativePath("")
relativeProjectPath("")
setBuildDir("")
setProperty("","")
sync {}
tarTree("")
uri("")
zipTree("")
sourceSets {
}
delegateClosureOf<Any> {  }
the()
gradleKotlinDsl()
withGroovyBuilder {
}
```