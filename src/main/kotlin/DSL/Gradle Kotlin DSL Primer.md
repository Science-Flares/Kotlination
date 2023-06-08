# Gradle Kotlin DSL Primer

Contents

- [Prerequisites](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:prerequisites)
- [IDE support](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:ide_support)
- [Kotlin DSL scripts](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:scripts)
- [Type-safe model accessors](https://docs.gradle.org/current/userguide/kotlin_dsl.html#type-safe-accessors)
- [Multi-project builds](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:multi_project_builds)
- [When you can’t use the `plugins {}` block](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:plugins_resolution_strategy)
- [Working with container objects](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:containers)
- [Working with runtime properties](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:properties)
- [The Kotlin DSL Plugin](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin-dsl_plugin)
- [The embedded Kotlin](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin)
- [Interoperability](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:interoperability)
- [Limitations](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:limitations)

Gradle’s Kotlin DSL provides an alternative syntax to the traditional Groovy DSL with an enhanced editing experience in supported IDEs, with superior content assist, refactoring, documentation, and more. This chapter provides details of the main Kotlin DSL constructs and how to use it to interact with the Gradle API.

|      | If you are interested in migrating an existing Gradle build to the Kotlin DSL, please also check out the dedicated [migration section](https://docs.gradle.org/current/userguide/migrating_from_groovy_to_kotlin_dsl.html#migrating_groovy_kotlin). |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

## [Prerequisites](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:prerequisites)

- The embedded Kotlin compiler is known to work on Linux, macOS, Windows, Cygwin, FreeBSD and Solaris on x86-64 architectures.
- Knowledge of Kotlin syntax and basic language features is very helpful. The [Kotlin reference documentation](https://kotlinlang.org/docs/reference/) and [Kotlin Koans](https://kotlinlang.org/docs/tutorials/koans.html) will help you to learn the basics.
- Use of the [plugins {}](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block) block to declare Gradle plugins significantly improves the editing experience and is highly recommended.

## [IDE support](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:ide_support)

The Kotlin DSL is fully supported by IntelliJ IDEA and Android Studio. Other IDEs do not yet provide helpful tools for editing Kotlin DSL files, but you can still import Kotlin-DSL-based builds and work with them as usual.

|                          | Build import | Syntax highlighting 1 | Semantic editor 2 |
| -----------------------: | :----------: | :-------------------: | :---------------: |
|            IntelliJ IDEA |    **✓**     |         **✓**         |       **✓**       |
|           Android Studio |    **✓**     |         **✓**         |       **✓**       |
|              Eclipse IDE |    **✓**     |         **✓**         |         ✖         |
|                    CLion |    **✓**     |         **✓**         |         ✖         |
|          Apache NetBeans |    **✓**     |         **✓**         |         ✖         |
| Visual Studio Code (LSP) |    **✓**     |         **✓**         |         ✖         |
|            Visual Studio |    **✓**     |           ✖           |         ✖         |

1 Kotlin syntax highlighting in Gradle Kotlin DSL scripts
2 code completion, navigation to sources, documentation, refactorings etc…​ in Gradle Kotlin DSL scripts

As mentioned in the limitations, you must [import your project from the Gradle model](https://www.jetbrains.com/help/idea/gradle.html#gradle_import) to get content-assist and refactoring tools for Kotlin DSL scripts in IntelliJ IDEA.

Builds with slow configuration time might affect the IDE responsiveness, so please check out the [performance section](https://docs.gradle.org/current/userguide/performance.html#performance_gradle) to help resolve such issues.

### [Automatic build import vs. automatic reloading of script dependencies](https://docs.gradle.org/current/userguide/kotlin_dsl.html#automatic_build_import_vs_automatic_reloading_of_script_dependencies)

Both IntelliJ IDEA and Android Studio — which is derived from IntelliJ IDEA — will detect when you make changes to your build logic and offer two suggestions:

1. Import the whole build again

   ![IntelliJ IDEA](https://docs.gradle.org/current/userguide/img/intellij-build-import-popup.png)

   ![IntelliJ IDEA](https://docs.gradle.org/current/userguide/img/android-studio-build-sync-popup.png)

2. Reload script dependencies when editing a build script

   ![Reload script dependencies](https://docs.gradle.org/current/userguide/img/intellij-script-dependencies-reload.png)

We recommend that you *disable automatic build import*, but *enable automatic reloading of script dependencies*. That way you get early feedback while editing Gradle scripts and control over when the whole build setup gets synchronized with your IDE.

### [Troubleshooting](https://docs.gradle.org/current/userguide/kotlin_dsl.html#troubleshooting)

The IDE support is provided by two components:

- The Kotlin Plugin used by IntelliJ IDEA/Android Studio
- Gradle

The level of support varies based on the versions of each.

If you run into trouble, the first thing you should try is running `./gradlew tasks` from the command line to see whether your issue is limited to the IDE. If you encounter the same problem from the command line, then the issue is with the build rather than the IDE integration.

If you can run the build successfully from the command line but your script editor is complaining, then you should try restarting your IDE and invalidating its caches.

If the above doesn’t work and you suspect an issue with the Kotlin DSL script editor, you can:

- Run `./gradle tasks` to get more details
- Check the logs in one of these locations:
  - `$HOME/Library/Logs/gradle-kotlin-dsl` on Mac OS X
  - `$HOME/.gradle-kotlin-dsl/log` on Linux
  - `$HOME/AppData/Local/gradle-kotlin-dsl/log` on Windows
- Open an issue on the [Gradle issue tracker](https://github.com/gradle/gradle/issues/), including as much detail as you can.

From version 5.1 onwards, the log directory is cleaned up automatically. It is checked periodically (at most every 24 hours) and log files are deleted if they haven’t been used for 7 days.

If the above isn’t enough to pinpoint the problem, you can enable the `org.gradle.kotlin.dsl.logging.tapi` system property in your IDE. This will cause the Gradle Daemon to log extra information in its log file located in `$HOME/.gradle/daemon`. In IntelliJ IDEA this can be done by opening `Help > Edit Custom VM Options…` and adding `-Dorg.gradle.kotlin.dsl.logging.tapi=true`.

For IDE problems outside of the Kotlin DSL script editor, please open issues in the corresponding IDE’s issue tracker:

- [JetBrains’s IDEA issue tracker](https://docs.gradle.org/current/userguide/kotlin_dsl.html),
- [Google’s Android Studio issue tracker](https://docs.gradle.org/current/userguide/kotlin_dsl.html).

Lastly, if you face problems with Gradle itself or with the Kotlin DSL, please open issues on the [Gradle issue tracker](https://github.com/gradle/gradle/issues/).

## [Kotlin DSL scripts](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:scripts)

Just like the Groovy-based equivalent, the Kotlin DSL is implemented on top of Gradle’s Java API. Everything you can read in a Kotlin DSL script is Kotlin code compiled and executed by Gradle. Many of the objects, functions and properties you use in your build scripts come from the Gradle API and the APIs of the applied plugins.

### [Script file names](https://docs.gradle.org/current/userguide/kotlin_dsl.html#script_file_names)

|      | Groovy DSL script files use the `.gradle` file name extension.Kotlin DSL script files use the `.gradle.kts` file name extension. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

To activate the Kotlin DSL, simply use the `.gradle.kts` extension for your build scripts in place of `.gradle`. That also applies to the [settings file](https://docs.gradle.org/current/userguide/build_lifecycle.html#sec:settings_file) — for example `settings.gradle.kts` — and [initialization scripts](https://docs.gradle.org/current/userguide/init_scripts.html#init_scripts).

Note that you can mix Groovy DSL build scripts with Kotlin DSL ones, i.e. a Kotlin DSL build script can apply a Groovy DSL one and each project in a multi-project build can use either one.

We recommend that you apply the following conventions to get better IDE support:

- Name settings scripts (or any script that is backed by a Gradle `Settings` object) according to the pattern `*.settings.gradle.kts` — this includes script plugins that are applied from settings scripts
- Name [initialization scripts](https://docs.gradle.org/current/userguide/init_scripts.html#init_scripts) according to the pattern `*.init.gradle.kts` or simply `init.gradle.kts`.

This is so that the IDE knows what type of object "backs" the script, be it [Project](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html), [Settings](https://docs.gradle.org/current/dsl/org.gradle.api.initialization.Settings.html) or [Gradle](https://docs.gradle.org/current/dsl/org.gradle.api.invocation.Gradle.html).

### [Implicit imports](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:implicit_imports)

All Kotlin DSL build scripts have implicit imports consisting of:

- The [default Gradle API imports](https://docs.gradle.org/current/userguide/writing_build_scripts.html#script-default-imports)
- The Kotlin DSL API, which is all types within the `org.gradle.kotlin.dsl` and `org.gradle.kotlin.dsl.plugins.dsl` packages currently

|      | Avoid using internal Kotlin DSL APIsUse of internal Kotlin DSL APIs in plugins and build scripts has the potential to break builds when either Gradle or plugins change. The [Kotlin DSL API](https://gradle.github.io/kotlin-dsl-docs/api/) extends the [Gradle public API](https://docs.gradle.org/current/userguide/authoring_maintainable_build_scripts.html#sec:avoiding_gradle_internal_apis) with the types listed in the [corresponding API docs](https://gradle.github.io/kotlin-dsl-docs/api/) that are in the `org.gradle.kotlin.dsl` or `org.gradle.kotlin.dsl.plugins.dsl` packages (but not subpackages of those). |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

## [Type-safe model accessors](https://docs.gradle.org/current/userguide/kotlin_dsl.html#type-safe-accessors)

The Groovy DSL allows you to reference many elements of the build model by name, even when they are defined at runtime. Think named configurations, named source sets, and so on. For example, you can get hold of the `implementation` configuration via `configurations.implementation`.

The Kotlin DSL replaces such dynamic resolution with type-safe model accessors that work with model elements contributed by plugins.

### [Understanding when type-safe model accessors are available](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:accessor_applicability)

The Kotlin DSL currently supports type-safe model accessors for any of the following that are contributed by plugins:

- Dependency and artifact configurations (such as `implementation` and `runtimeOnly` contributed by the Java Plugin)
- Project extensions and conventions (such as `sourceSets`)
- Elements in the `tasks` and `configurations` containers
- Elements in [project-extension containers](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:containers) (for example the source sets contributed by the Java Plugin that are added to the `sourceSets` container)
- Extensions on each of the above

|      | Only the main project build scripts and precompiled project script plugins have type-safe model accessors. Initialization scripts, settings scripts, script plugins do not. These limitations will be removed in a future Gradle release. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

The set of type-safe model accessors available is calculated right before evaluating the script body, immediately after the `plugins {}` block. Any model elements contributed after that point do not work with type-safe model accessors. For example, this includes any configurations you might define in your own build script. However, this approach does mean that you can use type-safe accessors for any model elements that are contributed by plugins that are *applied by parent projects*.

The following project build script demonstrates how you can access various configurations, extensions and other elements using type-safe accessors:

Example 1. Using type-safe model accessors

build.gradle.kts

```kotlin
plugins {
    `java-library`
}

dependencies {                              
    api("junit:junit:4.13")
    implementation("junit:junit:4.13")
    testImplementation("junit:junit:4.13")
}

configurations {                            
    implementation {
        resolutionStrategy.failOnVersionConflict()
    }
}

sourceSets {                                
    main {                                  
        java.srcDir("src/core/java")
    }
}

java {                                      
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    test {                                  
        testLogging.showExceptions = true
    }
}
```

|      | Uses type-safe accessors for the `api`, `implementation` and `testImplementation` dependency configurations contributed by the [Java Library Plugin](https://docs.gradle.org/current/userguide/java_library_plugin.html#java_library_plugin) |
| ---- | ------------------------------------------------------------ |
|      | Uses an accessor to configure the `sourceSets` project extension |
|      | Uses an accessor to configure the `main` source set          |
|      | Uses an accessor to configure the `java` source for the `main` source set |
|      | Uses an accessor to configure the `test` task                |

|      | Your IDE knows about the type-safe accessors, so it will include them in its suggestions. This will happen both at the top level of your build scripts — most plugin extensions are added to the `Project` object — and within the blocks that configure an extension. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

Note that accessors for elements of containers such as `configurations`, `tasks` and `sourceSets` leverage Gradle’s [configuration avoidance APIs](https://docs.gradle.org/current/userguide/lazy_configuration.html#lazy_configuration). For example, on `tasks` they are of type `TaskProvider<T>` and provide a lazy reference and lazy configuration of the underlying task. Here are some examples that illustrate the situations in which configuration avoidance applies:

```
tasks.test {
    // lazy configuration
}

// Lazy reference
val testProvider: TaskProvider<Test> = tasks.test

testProvider {
    // lazy configuration
}

// Eagerly realized Test task, defeat configuration avoidance if done out of a lazy context
val test: Test = tasks.test.get()
```

For all other containers than `tasks`, accessors for elements are of type `NamedDomainObjectProvider<T>` and provide the same behavior.

### [Understanding what to do when type-safe model accessors are not available](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin_using_standard_api)

Consider the sample build script shown above that demonstrates the use of type-safe accessors. The following sample is exactly the same except that is uses the `apply()` method to apply the plugin. The build script can not use type-safe accessors in this case because the `apply()` call happens in the body of the build script. You have to use other techniques instead, as demonstrated here:

Example 2. Configuring plugins without type-safe accessors

build.gradle.kts

```kotlin
apply(plugin = "java-library")

dependencies {
    "api"("junit:junit:4.13")
    "implementation"("junit:junit:4.13")
    "testImplementation"("junit:junit:4.13")
}

configurations {
    "implementation" {
        resolutionStrategy.failOnVersionConflict()
    }
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("src/core/java")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    named<Test>("test") {
        testLogging.showExceptions = true
    }
}
```

Type-safe accessors are unavailable for model elements contributed by the following:

- Plugins applied via the `apply(plugin = "id")` method
- The project build script
- Script plugins, via `apply(from = "script-plugin.gradle.kts")`
- Plugins applied via [cross-project configuration](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin_cross_project_configuration)

You also can not use type-safe accessors in Binary Gradle plugins implemented in Kotlin.

If you can’t find a type-safe accessor, *fall back to using the normal API* for the corresponding types. To do that, you need to know the names and/or types of the configured model elements. We’ll now show you how those can be discovered by looking at the above script in detail.

#### [Artifact configurations](https://docs.gradle.org/current/userguide/kotlin_dsl.html#artifact_configurations)

The following sample demonstrates how to reference and configure artifact configurations without type accessors:

Example 3. Artifact configurations

build.gradle.kts

```kotlin
apply(plugin = "java-library")

dependencies {
    "api"("junit:junit:4.13")
    "implementation"("junit:junit:4.13")
    "testImplementation"("junit:junit:4.13")
}

configurations {
    "implementation" {
        resolutionStrategy.failOnVersionConflict()
    }
}
```

The code looks similar to that for the type-safe accessors, except that the configuration names are string literals in this case. You can use string literals for configuration names in dependency declarations and within the `configurations {}` block.

The IDE won’t be able to help you discover the available configurations in this situation, but you can look them up either in the corresponding plugin’s documentation or by running `gradle dependencies`.

#### [Project extensions and conventions](https://docs.gradle.org/current/userguide/kotlin_dsl.html#project_extensions_and_conventions)

Project extensions and [conventions](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin_dsl_about_conventions) have both a name and a unique type, but the Kotlin DSL only needs to know the type in order to configure them. As the following sample shows for the `sourceSets {}` and `java {}` blocks from the original example build script, you can use the [`configure()`](https://gradle.github.io/kotlin-dsl-docs/api/org.gradle.kotlin.dsl/org.gradle.api.-project/configure.html) function with the corresponding type to do that:

Example 4. Project extensions and conventions

build.gradle.kts

```kotlin
apply(plugin = "java-library")

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("src/core/java")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
```

Note that `sourceSets` is a Gradle extension on `Project` of type `SourceSetContainer` and `java` is an extension on `Project` of type `JavaPluginExtension`.

You can discover what extensions and conventions are available either by looking at the documentation for the applied plugins or by running `gradle kotlinDslAccessorsReport`, which prints the Kotlin code necessary to access the model elements contributed by all the applied plugins. The report provides both names and types. As a last resort, you can also check a plugin’s source code, but that shouldn’t be necessary in the majority of cases.

Note that you can also use the [`the()`](https://gradle.github.io/kotlin-dsl-docs/api/org.gradle.kotlin.dsl/org.gradle.api.-project/the.html) function if you only need a reference to the extension or convention without configuring it, or if you want to perform a one-line configuration, like so:

```
the<SourceSetContainer>()["main"].srcDir("src/core/java")
```

The snippet above also demonstrates one way of configuring the elements of a project extension that is a container.

#### [Elements in project-extension containers](https://docs.gradle.org/current/userguide/kotlin_dsl.html#elements_in_project_extension_containers)

Container-based project extensions, such as `SourceSetContainer`, also allow you to configure the elements held by them. In our sample build script, we want to configure a source set named `main` within the source set container, which we can do by using the [named()](https://docs.gradle.org/current/javadoc/org/gradle/api/NamedDomainObjectCollection.html#named-java.lang.String-) method in place of an accessor, like so:

Example 5. Elements of project extensions that are containers

build.gradle.kts

```kotlin
apply(plugin = "java-library")

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("src/core/java")
    }
}
```

All elements within a container-based project extension have a name, so you can use this technique in all such cases.

As for project extensions and conventions themselves, you can discover what elements are present in any container by either looking at the documentation of the applied plugins or by running `gradle kotlinDslAccessorsReport`. And as a last resort, you may be able to view the plugin’s source code to find out what it does, but that shouldn’t be necessary in the majority of cases.

#### [Tasks](https://docs.gradle.org/current/userguide/kotlin_dsl.html#tasks)

Tasks are not managed through a container-based project extension, but they are part of a container that behaves in a similar way. This means that you can configure tasks in the same way as you do for source sets, as you can see in this example:

Example 6. Tasks

build.gradle.kts

```kotlin
apply(plugin = "java-library")

tasks {
    named<Test>("test") {
        testLogging.showExceptions = true
    }
}
```

We are using the Gradle API to refer to the tasks by name and type, rather than using accessors. Note that it’s necessary to specify the type of the task explicitly, otherwise the script won’t compile because the inferred type will be `Task`, not `Test`, and the `testLogging` property is specific to the `Test` task type. You can, however, omit the type if you only need to configure properties or to call methods that are common to all tasks, i.e. they are declared on the `Task` interface.

One can discover what tasks are available by running `gradle tasks`. You can then find out the type of a given task by running `gradle help --task <taskName>`, as demonstrated here:

```
❯ ./gradlew help --task test
...
Type
     Test (org.gradle.api.tasks.testing.Test)
```

Note that the IDE can assist you with the required imports, so you only need the simple names of the types, i.e. without the package name part. In this case, there’s no need to import the `Test` task type as it is part of the Gradle API and is therefore [imported implicitly](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:implicit_imports).

### [About conventions](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin_dsl_about_conventions)

Some of the Gradle core plugins expose configurability with the help of a so-called *convention* object. These serve a similar purpose to — and have now been superseded by — *extensions*. Please avoid using convention objects when writing new plugins. The long term plan is to migrate all Gradle core plugins to use extensions and remove the convention objects altogether.

As seen above, the Kotlin DSL provides accessors only for convention objects on `Project`. There are situations that require you to interact with a Gradle plugin that uses convention objects on other types. The Kotlin DSL provides the `withConvention(T::class) {}` extension function to do this:

Example 7. Configuring source set conventions

build.gradle.kts

```kotlin
plugins {
    groovy
}

sourceSets {
    main {
        withConvention(GroovySourceSet::class) {
            groovy.srcDir("src/core/groovy")
        }
    }
}
```

This technique is most commonly required for source sets that are added by language plugins other than the Java Plugin, e.g. the Groovy Plugin and the Scala Plugin. You can see which plugins add which properties to source sets in the [SourceSet](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceSet.html) reference documentation.

## [Multi-project builds](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:multi_project_builds)

As with single-project builds, you should try to use the `plugins {}` block in your multi-project builds so that you can use the type-safe accessors. Another consideration with multi-project builds is that you won’t be able to use type-safe accessors when configuring subprojects within the root build script or with other forms of cross configuration between projects. We discuss both topics in more detail in the following sections.

### [Applying plugins](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:multi_project_builds_applying_plugins)

You can declare your plugins within the subprojects to which they apply, but we recommend that you also declare them within the root project build script. This makes it easier to keep plugin versions consistent across projects within a build. The approach also improves the performance of the build.

The [Using Gradle plugins](https://docs.gradle.org/current/userguide/plugins.html#sec:subprojects_plugins_dsl) chapter explains how you can declare plugins in the root project build script with a version and then apply them to the appropriate subprojects' build scripts. What follows is an example of this approach using three subprojects and three plugins. Note how the root build script only declares the community plugins as the Java Library Plugin is tied to the version of Gradle you are using:

Example 8. Declare plugin dependencies in the root build script using the `plugins {}` block

settings.gradle.kts

```
rootProject.name = "multi-project-build"
include("domain", "infra", "http")
```

build.gradle.kts

```
plugins {
    id("com.github.johnrengelman.shadow") version "4.0.1" apply false
    id("io.ratpack.ratpack-java") version "1.8.2" apply false
}
```

domain/build.gradle.kts

```
plugins {
    `java-library`
}

dependencies {
    api("javax.measure:unit-api:1.0")
    implementation("tec.units:unit-ri:1.0.3")
}
```

infra/build.gradle.kts

```
plugins {
    `java-library`
    id("com.github.johnrengelman.shadow")
}

shadow {
    applicationDistribution.from("src/dist")
}

tasks.shadowJar {
    minimize()
}
```

http/build.gradle.kts

```
plugins {
    java
    id("io.ratpack.ratpack-java")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":infra"))
    implementation(ratpack.dependency("dropwizard-metrics"))
}

application {
    mainClass.set("example.App")
}

ratpack.baseDir = file("src/ratpack/baseDir")
```

If your build requires additional plugin repositories on top of the Gradle Plugin Portal, you should declare them in the `pluginManagement {}` block in your `settings.gradle.kts` file, like so:

Example 9. Declare additional plugin repositories

settings.gradle.kts

```
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
```

Plugins fetched from a source other than the [Gradle Plugin Portal](https://plugins.gradle.org/) can only be declared via the `plugins {}` block if they are published with their [plugin marker artifacts](https://docs.gradle.org/current/userguide/plugins.html#sec:plugin_markers).

|      | At the time of writing, all versions of the Android Plugin for Gradle up to 3.2.0 present in the `google()` repository lack plugin marker artifacts. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

If those artifacts are missing, then you can’t use the `plugins {}` block. You must instead fall back to declaring your plugin dependencies using the `buildscript {}` block in the root project build script. Here’s an example of doing that for the Android Plugin:

Example 10. Declare plugin dependencies in the root build script using the `buildscript {}` block

settings.gradle.kts

```
include("lib", "app")
```

build.gradle.kts

```
buildscript {
    repositories {
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
    }
}
```

lib/build.gradle.kts

```
plugins {
    id("com.android.library")
}

android {
    // ...
}
```

app/build.gradle.kts

```
plugins {
    id("com.android.application")
}

android {
    // ...
}
```

This technique is not that different from what Android Studio produces when creating a new build. The main difference is that the subprojects' build scripts in the above sample declare their plugins using the `plugins {}` block. This means that you can use type-safe accessors for the model elements that they contribute.

Note that you can’t use this technique if you want to apply such a plugin either to the root project build script of a multi-project build (rather than solely to its subprojects) or to a single-project build. You’ll need to use a different approach in those cases that we detail in [another section](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:plugins_resolution_strategy).

### [Cross-configuring projects](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin_cross_project_configuration)

[Cross project configuration](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html#sec:convention_plugins_vs_cross_configuration) is a mechanism by which you can configure a project from another project’s build script. A common example is when you configure subprojects in the root project build script.

Taking this approach means that you won’t be able to use type-safe accessors for model elements contributed by the plugins. You will instead have to rely on string literals and the standard Gradle APIs.

As an example, let’s modify the [Java/Ratpack sample build](https://docs.gradle.org/current/userguide/kotlin_dsl.html#ex:multi_project_ratpack) to fully configure its subprojects from the root project build script:

Example 11. Cross-configuring projects

settings.gradle.kts

```
rootProject.name = "multi-project-build"
include("domain", "infra", "http")
```

build.gradle.kts

```
import com.github.jengelman.gradle.plugins.shadow.ShadowExtension
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import ratpack.gradle.RatpackExtension

plugins {
    id("com.github.johnrengelman.shadow") version "4.0.1" apply false
    id("io.ratpack.ratpack-java") version "1.8.2" apply false
}

project(":domain") {
    apply(plugin = "java-library")
    dependencies {
        "api"("javax.measure:unit-api:1.0")
        "implementation"("tec.units:unit-ri:1.0.3")
    }
}

project(":infra") {
    apply(plugin = "java-library")
    apply(plugin = "com.github.johnrengelman.shadow")
    configure<ShadowExtension> {
        applicationDistribution.from("src/dist")
    }
    tasks.named<ShadowJar>("shadowJar") {
        minimize()
    }
}

project(":http") {
    apply(plugin = "java")
    apply(plugin = "io.ratpack.ratpack-java")
    repositories { mavenCentral() }
    val ratpack = the<RatpackExtension>()
    dependencies {
        "implementation"(project(":domain"))
        "implementation"(project(":infra"))
        "implementation"(ratpack.dependency("dropwizard-metrics"))
        "runtimeOnly"("org.slf4j:slf4j-simple:1.7.25")
    }
    configure<JavaApplication> {
        mainClass.set("example.App")
    }
    ratpack.baseDir = file("src/ratpack/baseDir")
}
```

Note how we’re using the `apply()` method to apply the plugins since the `plugins {}` block doesn’t work in this context. We are also using standard APIs instead of type-safe accessors to configure tasks, extensions and conventions — an approach that we discussed in [more detail elsewhere](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin_using_standard_api).

## [When you can’t use the `plugins {}` block](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:plugins_resolution_strategy)

Plugins fetched from a source other than the [Gradle Plugin Portal](https://plugins.gradle.org/) may or may not be usable with the `plugins {}` block. It depends on how they have been published and, specifically, whether they have been published with the necessary [plugin marker artifacts](https://docs.gradle.org/current/userguide/plugins.html#sec:plugin_markers).

For example, the Android Plugin for Gradle is not published to the Gradle Plugin Portal and — at least up to version 3.2.0 of the plugin — the metadata required to resolve the artifacts for a given plugin identifier is not published to the Google repository.

If your build is a multi-project build and you don’t need to apply such a plugin to your *root* project, then you can get round this issue using the technique [described above](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:multi_project_builds_applying_plugins). For any other situation, keep reading.

|      | When publishing plugins, please use Gradle’s built-in [Gradle Plugin Development Plugin](https://docs.gradle.org/current/userguide/java_gradle_plugin.html#java_gradle_plugin). It automates the publication of the metadata necessary to make your plugins usable with the `plugins {}` block. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

We will show you in this section how to apply the Android Plugin to a single-project build or the root project of a multi-project build. The goal is to instruct your build on how to map the `com.android.application` plugin identifier to a resolvable artifact. This is done in two steps:

- Add a plugin repository to the build’s settings script
- Map the plugin ID to the corresponding artifact coordinates

You accomplish both steps by configuring a `pluginManagement {}` block in the build’s settings script. To demonstrate, the following sample adds the `google()` repository — where the Android plugin is published — to the repository search list, and uses a `resolutionStrategy {}` block to map the `com.android.application` plugin ID to the `com.android.tools.build:gradle:<version>` artifact available in the `google()` repository:

Example 12. Mapping plugin IDs to dependency coordinates

settings.gradle.kts

```
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if(requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}
```

build.gradle.kts

```
plugins {
    id("com.android.application") version "4.1.2"
}

android {
    // ...
}
```

In fact, the above sample will work for all `com.android.*` plugins that are provided by the specified module. That’s because the packaged module contains the details of which plugin ID maps to which plugin implementation class, using the properties-file mechanism described in the [Writing Custom Plugins](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:custom_plugins_standalone_project) chapter.

See the [Plugin Management](https://docs.gradle.org/current/userguide/plugins.html#sec:plugin_management) section of the Gradle user manual for more information on the `pluginManagement {}` block and what it can be used for.

## [Working with container objects](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:containers)

The Gradle build model makes heavy use of container objects (or just "containers"). For example, both `configurations` and `tasks` are container objects that contain `Configuration` and `Task` objects respectively. Community plugins also contribute containers, like the `android.buildTypes` container contributed by the Android Plugin.

The Kotlin DSL provides several ways for build authors to interact with containers. We look at each of those ways next, using the `tasks` container as an example.

|      | Note that you can leverage the type-safe accessors described in [another section](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:accessor_applicability) if you are configuring existing elements on supported containers. That section also describes which containers support type-safe accessors. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

### [Using the container API](https://docs.gradle.org/current/userguide/kotlin_dsl.html#using_the_container_api)

All containers in Gradle implement [NamedDomainObjectContainer](https://docs.gradle.org/current/dsl/org.gradle.api.NamedDomainObjectContainer.html#org.gradle.api.NamedDomainObjectContainer). Some of them can contain objects of different types and implement [PolymorphicDomainObjectContainer](https://docs.gradle.org/current/dsl/org.gradle.api.PolymorphicDomainObjectContainer.html#org.gradle.api.PolymorphicDomainObjectContainer). The simplest way to interact with containers is through these interfaces.

The following sample demonstrates how you can use the [named()](https://docs.gradle.org/current/dsl/org.gradle.api.NamedDomainObjectContainer.html#org.gradle.api.NamedDomainObjectContainer:named(java.lang.String)) method to configure existing tasks and the [register()](https://docs.gradle.org/current/dsl/org.gradle.api.NamedDomainObjectContainer.html#org.gradle.api.NamedDomainObjectContainer:register(java.lang.String)) method to create new ones.

Example 13. Using the container API

build.gradle.kts

```
tasks.named("check")                    
tasks.register("myTask1")               

tasks.named<JavaCompile>("compileJava") 
tasks.register<Copy>("myCopy1")         

tasks.named("assemble") {               
    dependsOn(":myTask1")
}
tasks.register("myTask2") {             
    description = "Some meaningful words"
}

tasks.named<Test>("test") {             
    testLogging.showStackTraces = true
}
tasks.register<Copy>("myCopy2") {       
    from("source")
    into("destination")
}
```

|      | Gets a reference of type `Task` to the existing task named `check` |
| ---- | ------------------------------------------------------------ |
|      | Registers a new untyped task named `myTask1`                 |
|      | Gets a reference to the existing task named `compileJava` of type `JavaCompile` |
|      | Registers a new task named `myCopy1` of type `Copy`          |
|      | Gets a reference to the existing (untyped) task named `assemble` and configures it — you can only configure properties and methods that are available on `Task` with this syntax |
|      | Registers a new untyped task named `myTask2` and configures it — you can only configure properties and methods that are available on `Task` in this case |
|      | Gets a reference to the existing task named `test` of type `Test` and configures it — in this case you have access to the properties and methods of the specified type |
|      | Registers a new task named `myCopy2` of type `Copy` and configures it |

|      | The above sample relies on the configuration avoidance APIs. If you need or want to eagerly configure or register container elements, simply replace `named()` with `getByName()` and `register()` with `create()`. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

### [Using Kotlin delegated properties](https://docs.gradle.org/current/userguide/kotlin_dsl.html#using_kotlin_delegated_properties)

Another way to interact with containers is via Kotlin delegated properties. These are particularly useful if you need a reference to a container element that you can use elsewhere in the build. In addition, Kotlin delegated properties can easily be renamed via IDE refactoring.

The following sample does the exact same things as the one in the previous section, but it uses delegated properties and reuses those references in place of string-literal task paths:

Example 14. Using Kotlin delegated properties

build.gradle.kts

```
val check by tasks.existing
val myTask1 by tasks.registering

val compileJava by tasks.existing(JavaCompile::class)
val myCopy1 by tasks.registering(Copy::class)

val assemble by tasks.existing {
    dependsOn(myTask1)  
}
val myTask2 by tasks.registering {
    description = "Some meaningful words"
}

val test by tasks.existing(Test::class) {
    testLogging.showStackTraces = true
}
val myCopy2 by tasks.registering(Copy::class) {
    from("source")
    into("destination")
}
```

|      | Uses the reference to the `myTask1` task rather than a task path |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

|      | The above rely on configuration avoidance APIs. If you need to eagerly configure or register container elements simply replace [`existing()`](https://gradle.github.io/kotlin-dsl-docs/api/org.gradle.kotlin.dsl/org.gradle.api.-named-domain-object-container/existing.html) with [`getting()`](https://gradle.github.io/kotlin-dsl-docs/api/org.gradle.kotlin.dsl/org.gradle.api.-named-domain-object-container/getting.html) and [`registering()`](https://gradle.github.io/kotlin-dsl-docs/api/org.gradle.kotlin.dsl/org.gradle.api.-named-domain-object-container/registering.html) with [`creating()`](https://gradle.github.io/kotlin-dsl-docs/api/org.gradle.kotlin.dsl/org.gradle.api.-named-domain-object-container/creating.html). |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

### [Configuring multiple container elements together](https://docs.gradle.org/current/userguide/kotlin_dsl.html#configuring_multiple_container_elements_together)

When configuring several elements of a container one can group interactions in a block in order to avoid repeating the container’s name on each interaction. The following example uses a combination of type-safe accessors, the container API and Kotlin delegated properties:

Example 15. Container scope

build.gradle.kts

```
tasks {
    test {
        testLogging.showStackTraces = true
    }
    val myCheck by registering {
        doLast { /* assert on something meaningful */ }
    }
    check {
        dependsOn(myCheck)
    }
    register("myHelp") {
        doLast { /* do something helpful */ }
    }
}
```

## [Working with runtime properties](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:properties)

Gradle has two main sources of properties that are defined at runtime: [*project properties*](https://docs.gradle.org/current/userguide/build_environment.html#sec:project_properties) and [*extra properties*](https://docs.gradle.org/current/userguide/writing_build_scripts.html#sec:extra_properties). The Kotlin DSL provides specific syntax for working with these types of properties, which we look at in the following sections.

### [Project properties](https://docs.gradle.org/current/userguide/kotlin_dsl.html#project_properties)

The Kotlin DSL allows you to access project properties by binding them via Kotlin delegated properties. Here’s a sample snippet that demonstrates the technique for a couple of project properties, one of which *must* be defined:

build.gradle.kts

```
val myProperty: String by project  
val myNullableProperty: String? by project 
```

|      | Makes the `myProperty` project property available via a `myProperty` delegated property — the project property must exist in this case, otherwise the build will fail when the build script attempts to use the `myProperty` value |
| ---- | ------------------------------------------------------------ |
|      | Does the same for the `myNullableProperty` project property, but the build won’t fail on using the `myNullableProperty` value as long as you check for null (standard [Kotlin rules for null safety](https://kotlinlang.org/docs/reference/null-safety.html) apply) |

The same approach works in both settings and initialization scripts, except you use `by settings` and `by gradle` respectively in place of `by project`.

### [Extra properties](https://docs.gradle.org/current/userguide/kotlin_dsl.html#extra_properties)

Extra properties are available on any object that implements the [ExtensionAware](https://docs.gradle.org/current/dsl/org.gradle.api.plugins.ExtensionAware.html#org.gradle.api.plugins.ExtensionAware) interface. Kotlin DSL allows you to access extra properties and create new ones via delegated properties, using any of the `by extra` forms demonstrated in the following sample:

build.gradle.kts

```
val myNewProperty by extra("initial value")  
val myOtherNewProperty by extra { "calculated initial value" }  

val myProperty: String by extra  
val myNullableProperty: String? by extra  
```

|      | Creates a new extra property called `myNewProperty` in the current context (the project in this case) and initializes it with the value `"initial value"`, which also determines the property’s *type* |
| ---- | ------------------------------------------------------------ |
|      | Create a new extra property whose initial value is calculated by the provided lambda |
|      | Binds an existing extra property from the current context (the project in this case) to a `myProperty` reference |
|      | Does the same as the previous line but allows the property to have a null value |

This approach works for all Gradle scripts: project build scripts, script plugins, settings scripts and initialization scripts.

You can also access extra properties on a root project from a subproject using the following syntax:

my-sub-project/build.gradle.kts

```
val myNewProperty: String by rootProject.extra  
```

|      | Binds the root project’s `myNewProperty` extra property to a reference of the same name |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

Extra properties aren’t just limited to projects. For example, `Task` extends `ExtensionAware`, so you can attach extra properties to tasks as well. Here’s an example that defines a new `myNewTaskProperty` on the `test` task and then uses that property to initialize another task:

build.gradle.kts

```
tasks {
    test {
        val reportType by extra("dev")  
        doLast {
            // Use 'suffix' for post processing of reports
        }
    }

    register<Zip>("archiveTestReports") {
        val reportType: String by test.get().extra  
        archiveAppendix.set(reportType)
        from(test.get().reports.html.destination)
    }
}
```

|      | Creates a new `reportType` extra property on the `test` task |
| ---- | ------------------------------------------------------------ |
|      | Makes the `test` task’s `reportType` extra property available to configure the `archiveTestReports` task |

If you’re happy to use eager configuration rather than the configuration avoidance APIs, you could use a single, "global" property for the report type, like this:

build.gradle.kts

```
tasks.test.doLast { ... }

val testReportType by tasks.test.get().extra("dev")  

tasks.create<Zip>("archiveTestReports") {
    archiveAppendix.set(testReportType)  
    from(test.get().reports.html.destination)
}
```

|      | Creates and initializes an extra property on the `test` task, binding it to a "global" property |
| ---- | ------------------------------------------------------------ |
|      | Uses the "global" property to initialize the `archiveTestReports` task |

There is one last syntax for extra properties that we should cover, one that treats `extra` as a map. We recommend against using this in general as you lose the benefits of Kotlin’s type checking and it prevents IDEs from providing as much support as they could. However, it is more succinct than the delegated properties syntax and can reasonably be used if you only need to set the value of an extra property without referencing it later.

Here’s a simple example demonstrating how to set and read extra properties using the map syntax:

build.gradle.kts

```
extra["myNewProperty"] = "initial value"  

tasks.create("myTask") {
    doLast {
        println("Property: ${project.extra["myNewProperty"]}")  
    }
}
```

|      | Creates a new project extra property called `myNewProperty` and sets its value |
| ---- | ------------------------------------------------------------ |
|      | Reads the value from the project extra property we created — note the `project.` qualifier on `extra[…]`, otherwise Gradle will assume we want to read an extra property from the *task* |

## [The Kotlin DSL Plugin](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin-dsl_plugin)

The Kotlin DSL Plugin provides a convenient way to develop Kotlin-based projects that contribute build logic. That includes [buildSrc projects](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources), [included builds](https://docs.gradle.org/current/userguide/composite_builds.html#composite_builds) and [Gradle plugins](https://docs.gradle.org/current/userguide/custom_plugins.html#custom_plugins).

The plugin achieves this by doing the following:

- Applies the [Kotlin Plugin](https://kotlinlang.org/docs/reference/using-gradle.html#targeting-the-jvm), which adds support for compiling Kotlin source files.
- Adds the `kotlin-stdlib-jdk8`, `kotlin-reflect` and `gradleKotlinDsl()` dependencies to the `compileOnly` and `testImplementation` configurations, which allows you to make use of those Kotlin libraries and the Gradle API in your Kotlin code.
- Configures the Kotlin compiler with the same settings that are used for Kotlin DSL scripts, ensuring consistency between your build logic and those scripts.
- Enables support for [precompiled script plugins](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:precompiled_plugins).

|      | Avoid specifying a version for the `kotlin-dsl` pluginEach Gradle release is meant to be used with a specific version of the `kotlin-dsl` plugin and compatibility between arbitrary Gradle releases and `kotlin-dsl` plugin versions is not guaranteed. Using an unexpected version of the `kotlin-dsl` plugin in a build will emit a warning and can cause hard to diagnose problems. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

This is the basic configuration you need to use the plugin:

Example 16. Applying the Kotlin DSL Plugin to a `buildSrc` project

buildSrc/build.gradle.kts

```
plugins {
    `kotlin-dsl`
}

repositories {
    // The org.jetbrains.kotlin.jvm plugin requires a repository
    // where to download the Kotlin compiler dependencies from.
    mavenCentral()
}
```

## [The embedded Kotlin](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin)

Gradle embeds Kotlin in order to provide support for Kotlin-based scripts.

### [Kotlin versions](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotlin_versions)

Gradle ships with `kotlin-compiler-embeddable` plus matching versions of `kotlin-stdlib` and `kotlin-reflect` libraries. For example, Gradle 4.3 ships with the Kotlin DSL v0.12.1 that includes Kotlin 1.1.51 versions of these modules. The `kotlin` package from those modules is visible through the Gradle classpath.

The [compatibility guarantees](https://kotlinlang.org/docs/reference/compatibility.html) provided by Kotlin apply for both backward and forward compatibility.

#### [Backward compatibility](https://docs.gradle.org/current/userguide/kotlin_dsl.html#backward_compatibility)

Our approach is to only do backwards-breaking Kotlin upgrades on a major Gradle release. We will always clearly document which Kotlin version we ship and announce upgrade plans before a major release.

Plugin authors who want to stay compatible with older Gradle versions need to limit their API usage to a subset that is compatible with these old versions. It’s not really different from any other new API in Gradle. E.g. if we introduce a new API for dependency resolution and a plugin wants to use that API, then they either need to drop support for older Gradle versions or they need to do some clever organization of their code to only execute the new code path on newer versions.

#### [Forward compatibility](https://docs.gradle.org/current/userguide/kotlin_dsl.html#forward_compatibility)

The biggest issue is the compatibility between the external `kotlin-gradle-plugin` version and the `kotlin-stdlib` version shipped with Gradle. More generally, between any plugin that transitively depends on `kotlin-stdlib` and its version shipped with Gradle. As long as the combination is compatible everything should work. This will become less of an issue as the language matures.

### [Kotlin compiler arguments](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin_compiler_arguments)

These are the Kotlin compiler arguments used for compiling Kotlin DSL scripts and Kotlin sources and scripts in a project that has the `kotlin-dsl` plugin applied:

- `-jvm-target=1.8`

  Sets the target version of the generated JVM bytecode to `1.8`.

- `-Xjsr305=strict`

  Sets up Kotlin’s Java interoperability to strictly follow JSR-305 annotations for increased null safety. See [Calling Java code from Kotlin](https://kotlinlang.org/docs/reference/java-interop.html#compiler-configuration) in the Kotlin documentation for more information.

## [Interoperability](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:interoperability)

When mixing languages in your build logic, you may have to cross language boundaries. An extreme example would be a build that uses tasks and plugins that are implemented in Java, Groovy and Kotlin, while also using both Kotlin DSL and Groovy DSL build scripts.

Quoting the Kotlin reference documentation:

> Kotlin is designed with Java Interoperability in mind. Existing Java code can be called from Kotlin in a natural way, and Kotlin code can be used from Java rather smoothly as well.

Both [calling Java from Kotlin](https://kotlinlang.org/docs/reference/java-interop.html) and [calling Kotlin from Java](https://kotlinlang.org/docs/reference/java-to-kotlin-interop.html) are very well covered in the Kotlin reference documentation.

The same mostly applies to interoperability with Groovy code. In addition, the Kotlin DSL provides several ways to opt into Groovy semantics, which we look at next.

### [Static extensions](https://docs.gradle.org/current/userguide/kotlin_dsl.html#static_extensions)

Both the Groovy and Kotlin languages support extending existing classes via [Groovy Extension modules](https://groovy-lang.org/metaprogramming.html#_extension_modules) and [Kotlin extensions](https://kotlinlang.org/docs/reference/extensions.html).

To call a Kotlin extension function from Groovy, call it as a static function, passing the receiver as the first parameter:

Example 17. Calling a Kotlin extension from Groovy

build.gradle

```
TheTargetTypeKt.kotlinExtensionFunction(receiver, "parameters", 42, aReference)
```

Kotlin extension functions are package-level functions and you can learn how to locate the name of the type declaring a given Kotlin extension in the [Package-Level Functions](https://kotlinlang.org/docs/reference/java-to-kotlin-interop.html#package-level-functions) section of the Kotlin reference documentation.

To call a Groovy extension method from Kotlin, the same approach applies: call it as a static function passing the receiver as the first parameter. Here’s an example:

Example 18. Calling a Groovy extension from Kotlin

build.gradle.kts

```
TheTargetTypeGroovyExtension.groovyExtensionMethod(receiver, "parameters", 42, aReference)
```

### [Named parameters and default arguments](https://docs.gradle.org/current/userguide/kotlin_dsl.html#named_parameters_and_default_arguments)

Both the Groovy and Kotlin languages support named function parameters and default arguments, although they are implemented very differently. Kotlin has fully-fledged support for both, as described in the Kotlin language reference under [named arguments](https://kotlinlang.org/docs/reference/functions.html#named-arguments) and [default arguments](https://kotlinlang.org/docs/reference/functions.html#default-arguments). Groovy implements [named arguments](https://groovy-lang.org/objectorientation.html#_named_arguments) in a non-type-safe way based on a `Map<String, ?>` parameter, which means they cannot be combined with [default arguments](https://groovy-lang.org/objectorientation.html#_default_arguments). In other words, you can only use one or the other in Groovy for any given method.

#### [Calling Kotlin from Groovy](https://docs.gradle.org/current/userguide/kotlin_dsl.html#calling_kotlin_from_groovy)

To call a Kotlin function that has named arguments from Groovy, just use a normal method call with positional parameters. There is no way to provide values by argument name.

To call a Kotlin function that has default arguments from Groovy, always pass values for all the function parameters.

#### [Calling Groovy from Kotlin](https://docs.gradle.org/current/userguide/kotlin_dsl.html#calling_groovy_from_kotlin)

To call a Groovy function with named arguments from Kotlin, you need to pass a `Map<String, ?>`, as shown in this example:

Example 19. Call Groovy function with named arguments from Kotlin

build.gradle.kts

```
groovyNamedArgumentTakingMethod(mapOf(
    "parameterName" to "value",
    "other" to 42,
    "and" to aReference))
```

To call a Groovy function with default arguments from Kotlin, always pass values for all the parameters.

### [Groovy closures from Kotlin](https://docs.gradle.org/current/userguide/kotlin_dsl.html#groovy_closures_from_kotlin)

You may sometimes have to call Groovy methods that take [Closure](https://groovy-lang.org/closures.html) arguments from Kotlin code. For example, some third-party plugins written in Groovy expect closure arguments.

|      | Gradle plugins written in any language should prefer the type `Action<T>` type in place of closures. Groovy closures and Kotlin lambdas are automatically mapped to arguments of that type. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

In order to provide a way to construct closures while preserving Kotlin’s strong typing, two helper methods exist:

- `closureOf<T> {}`
- `delegateClosureOf<T> {}`

Both methods are useful in different circumstances and depend upon the method you are passing the `Closure` instance into.

Some plugins expect simple closures, as with the [Bintray](https://plugins.gradle.org/plugin/com.jfrog.bintray) plugin:

Example 20. Use `closureOf<T> {}`

build.gradle.kts

```
bintray {
    pkg(closureOf<PackageConfig> {
        // Config for the package here
    })
}
```

In other cases, like with the [Gretty Plugin](https://plugins.gradle.org/plugin/org.gretty) when configuring farms, the plugin expects a delegate closure:

Example 21. Use `delegateClosureOf<T> {}`

build.gradle.kts

```
dependencies {
    implementation("group:artifact:1.2.3") {
        artifact(delegateClosureOf<DependencyArtifact> {
            // configuration for the artifact
            name = "artifact-name"
        })
    }
}
```

There sometimes isn’t a good way to tell, from looking at the source code, which version to use. Usually, if you get a `NullPointerException` with `closureOf<T> {}`, using `delegateClosureOf<T> {}` will resolve the problem.

These two utility functions are useful for *configuration closures*, but some plugins might expect Groovy closures for other purposes. The `KotlinClosure0` to `KotlinClosure2` types allows adapting Kotlin functions to Groovy closures with more flexibility.

Example 22. Use `KotlinClosureX` types

build.gradle.kts

```
somePlugin {

    // Adapt parameter-less function
    takingParameterLessClosure(KotlinClosure0({
        "result"
    }))

    // Adapt unary function
    takingUnaryClosure(KotlinClosure1<String, String>({
        "result from single parameter $this"
    }))

    // Adapt binary function
    takingBinaryClosure(KotlinClosure2<String, String, String>({ a, b ->
        "result from parameters $a and $b"
    }))
}
```

### [The Kotlin DSL Groovy Builder](https://docs.gradle.org/current/userguide/kotlin_dsl.html#the_kotlin_dsl_groovy_builder)

If some plugin makes heavy use of [Groovy metaprogramming](https://groovy-lang.org/metaprogramming.html), then using it from Kotlin or Java or any statically-compiled language can be very cumbersome.

The Kotlin DSL provides a `withGroovyBuilder {}` utility extension that attaches the Groovy metaprogramming semantics to objects of type `Any`. The following example demonstrates several features of the method on the object `target`:

Example 23. Use `withGroovyBuilder {}`

build.gradle.kts

```
target.withGroovyBuilder {                                          

    // GroovyObject methods available                               
    val foo = getProperty("foo")
    setProperty("foo", "bar")
    invokeMethod("name", arrayOf("parameters", 42, aReference))

    // Kotlin DSL utilities
    "name"("parameters", 42, aReference)                            
        "blockName" {                                               
            // Same Groovy Builder semantics on `blockName`
        }
    "another"("name" to "example", "url" to "https://example.com/") 
}
```

|      | The receiver is a [GroovyObject](https://docs.groovy-lang.org/latest/html/api/groovy/lang/GroovyObject.html) and provides Kotlin helpers |
| ---- | ------------------------------------------------------------ |
|      | The `GroovyObject` API is available                          |
|      | Invoke the `methodName` method, passing some parameters      |
|      | Configure the `blockName` property, maps to a `Closure` taking method invocation |
|      | Invoke `another` method taking named arguments, maps to a Groovy named arguments `Map<String, ?>` taking method invocation |

### [Using a Groovy script](https://docs.gradle.org/current/userguide/kotlin_dsl.html#using_a_groovy_script)

Another option when dealing with problematic plugins that assume a Groovy DSL build script is to configure them in a Groovy DSL build script that is applied from the main Kotlin DSL build script:

Example 24. Using a Groovy script

build.gradle.kts

```
plugins {
    id("dynamic-groovy-plugin") version "1.0"               
}
apply(from = "dynamic-groovy-plugin-configuration.gradle")  
```

dynamic-groovy-plugin-configuration.gradle

```
native {                                                    
    dynamic {
        groovy as Usual
    }
}
```

|      | The Kotlin build script requests and applies the plugin   |
| ---- | --------------------------------------------------------- |
|      | The Kotlin build script applies the Groovy script         |
|      | The Groovy script uses dynamic Groovy to configure plugin |

## [Limitations](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotdsl:limitations)

- The Kotlin DSL is [known to be slower than the Groovy DSL](https://github.com/gradle/gradle/issues/15886) on first use, for example with clean checkouts or on ephemeral continuous integration agents. Changing something in the *buildSrc* directory also has an impact as it invalidates build-script caching. The main reason for this is the slower script compilation for Kotlin DSL.
- In IntelliJ IDEA, you must [import your project from the Gradle model](https://www.jetbrains.com/help/idea/gradle.html#gradle_import) in order to get content assist and refactoring support for your Kotlin DSL build scripts.
- The Kotlin DSL will not support the `model {}` block, which is part of the [discontinued Gradle Software Model](https://blog.gradle.org/state-and-future-of-the-gradle-software-model).
- We recommend against enabling the incubating [configuration on demand](https://docs.gradle.org/current/userguide/multi_project_configuration_and_execution.html#sec:configuration_on_demand) feature as it can lead to very hard-to-diagnose problems.

If you run into trouble or discover a suspected bug, please report the issue in the [Gradle issue tracker](https://github.com/gradle/gradle/issues/).