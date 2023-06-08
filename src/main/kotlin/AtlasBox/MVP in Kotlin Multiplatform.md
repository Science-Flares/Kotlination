![img](https://miro.medium.com/max/4350/1*TnqLgmMECjqJmf8plwSTWg.png)

# MVP in Kotlin Multiplatform



My colleagues and I have been experimenting with creating a cross-platform application with **Kotlin Multiplatform**. I’m excited to share with you on how to write a mobile application with Kotlin Multiplatform using the MVP pattern.

# Introduction

## Kotlin and Kotlin Multiplatform

Kotlin is designed to interoperate fully with Java and it is the only other official language for Android besides Java. Because Kotlin runs on an LLVM backend, Kotlin can basically be compiled into native/machine code which has compatibility with C and Objective C in various platforms such as Linux, iOS, Android and more. On top of that, it can even be transpiled into javascript/web assembly to drive web applications! With that said, we can basically create applications on any platforms in Kotlin!

Starting from version 1.2. JetBrains is actively developing a feature called **Kotlin Multiplatform**. It allows code sharing between the aforementioned platforms in a single project. The shared code will be compiled to either *Android, JVM, JavaScript,* and *Native*.

Kotlin Multiplatform is currently an experimental feature of Kotlin and thus you can expect it to be rough around the edges. Do not worry though, because The updates and bug fixes are active and frequent, and many third-party libraries are popping up like mushrooms after the rain. Here is a list of useful Kotlin Multiplatform libraries that can be used across platforms:

1. [**SQLDelight**](https://github.com/square/sqldelight) — Database
2. [**Ktor**](https://ktor.io/) — Networking
3. [**Kissme**](https://github.com/netguru/Kissme) — Secure Key-Value store
4. [**Multiplatform Settings**](https://github.com/russhwolf/multiplatform-settings) — Key-Value store
5. [**Klock**](https://github.com/korlibs/klock) — Time/Date
6. [**kotlinx.serialization** ](https://github.com/Kotlin/kotlinx.serialization)— Serialization

And many more! I’m looking forward to an upcoming feature of [Kroto-FP.plus](https://github.com/marcoferrer/kroto-FP.plus) which allows the generation and usage of Protocol Buffers and gRPC on Kotlin Multiplatform.

There are limitations that we need to take note, however.

1. As mentioned, it is still an experimental feature of Kotlin. Things are not expected to be smooth, yet. The setting up of the project with IntelliJ and Gradle is still quite buggy and not set in stone yet.
2. IDE support works great [IntelliJ](https://www.jetbrains.com/idea/). But debug support of Kotlin Native code will have to be done on [CLion](https://www.jetbrains.com/clion/), another IDE developed by [JetBrains](https://www.jetbrains.com/).
3. The code sharing part of Kotlin Multiplatform is called the “common” in the project. It cannot contain platform specific code. But it can be easily circumvented by using the [actual/expect](https://kotlinlang.org/docs/reference/platform-specific-declarations.html) feature of Kotlin Multiplatform which allows the common code to use platform-specific APIs.
4. Kotlin Multiplatform is not for sharing UI code. But [**there are attempts that do exactly this**](https://github.com/lightningkite/koolui).

## Getting Started

![img](https://miro.medium.com/max/326/1*I5WrHK-ohDzcMU9ECS_31Q.png)

The project architecture that I am going to implement with Kotlin Multiplatform is the [**Model-View-Presenter (MVP)**](https://stackoverflow.com/questions/1317693/what-is-model-view-presenter). The Model class, View and Presenter interfaces will reside in common directory while the code that implements the View interfaces will reside in the platform-specific directories.

1. Create a Kotlin Multiplatform project with IntelliJ IDE. ➕ Create New Project → Kotlin → Kotlin (Mobile Android/iOS)

![img](https://miro.medium.com/max/875/1*nUI68K6dP3iqhdxdWEbELA.png)

\2. Use the default Gradle wrapper settings.![img](https://miro.medium.com/max/875/1*endBG5g_XgXjBHW0MlEQIw.png)

\3. Choose a suitable project name. In our case we choose **kotlin_multi_mvp_tutorial**. Click NEXT and the Gradle will proceed to setup the project and download all the necessary dependencies. It will take quite a while depending on your internet connection speed.

![img](https://miro.medium.com/max/875/1*4WioDhqeqZQb-9TdHJRGuA.png)

Error! You might encounter this error message similar to below.

> *Please fix the ‘sdk.dir’ property in the local.properties file.*

Normally Android Studio and IntelliJ will populate this **sdk.dir** variable in the local.properties file during the creation of Android project. But it seems like Kotlin Multiplatform is not doing this for us yet. If we look at the local.properties file in the project. This will be shown.

<iframe src="https://liewjuntung.medium.com/media/39d296ffaba8ec6b714797e39abf8838" allowfullscreen="" frameborder="0" height="191" width="680" title="kotlin multiplatform init" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 191px;"></iframe>

You will need to specify the Android SDK directory in the sdk.dir variable

In my case, it is **/Users/my_user/Library/Android/sdk**. Refresh Gradle again and wait for it to run all the required steps to setup the project.

In the common code, define the interface. In this example, We will create a **LoginView** and **LoginPresenter** Interface in the **commonMain** directory as shown below.

<iframe src="https://liewjuntung.medium.com/media/9a1269a4e733c2d01fb4263b35a05b16" allowfullscreen="" frameborder="0" height="258" width="680" title="login_interfaces.kt" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 258px;"></iframe>

\4. We will create a **User** data class as our Model.

<iframe src="https://liewjuntung.medium.com/media/9d0731777fa67089d8ed6911993b2bab" allowfullscreen="" frameborder="0" height="103" width="680" title="login_model.kt" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 103px;"></iframe>

\5. Implement the **LoginPresenter** interface in **LoginPresenterImpl.kt**.

<iframe src="https://liewjuntung.medium.com/media/bfe68555cdfa1fb61d76286ee0195829" allowfullscreen="" frameborder="0" height="346" width="680" title="LoginPresenterImpl.kt" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 346px;"></iframe>

And that’s it, we implemented a Presenter that can be used by both Android and iOS. Now we have to implement the view in each individual platform.

## Android

Since IntelliJ supports Android projects, we create our applications without leaving our IDE. First we’ll create an Android **LoginActivity.kt** in our **main** folder that will implement our **LoginView** interface defined in the **common**. We will declare and initialize the **LoginPresenterImpl** that we created in **common**.

![img](https://miro.medium.com/max/500/1*gy4Bodsnmx6256jrwtCphQ.png)

<iframe src="https://liewjuntung.medium.com/media/e2c837f94609532ed309b22d2aae0d22" allowfullscreen="" frameborder="0" height="631" width="680" title="LoginActivity.kt" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 631px;"></iframe>

Implementation of LoginActivity.kt

<iframe src="https://liewjuntung.medium.com/media/68bf68eb7afb9c1a00aea5a4a836f68d" allowfullscreen="" frameborder="0" height="1194" width="680" title="activity_login.xml" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 1194px;"></iframe>

<iframe src="https://liewjuntung.medium.com/media/6fa2669c94a06e517f4632eccebb6085" allowfullscreen="" frameborder="0" height="390" width="680" title="AndroidManifest.xml" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 390px;"></iframe>

Remember to update your AndroidManifest.xml to launch LoginActivity

Now that we are done on the Android side. Now let’s move on to implement this in iOS.

## iOS

It will be a little rough to get started with the iOS project (Hence the reason Kotlin Multiplatform is still not production ready yet as the time that I’m writing this article). We will need to do the following in order to get started.

1. Create a Gradle Wrapper with **./gradlew wrapper**. The reason for this is for XCode to run the **copyFramework** task during *Building Phase* of the framework.
2. Modify **build.gradle** so that **copyFramework** task will work. The code is shown below.

<iframe src="https://liewjuntung.medium.com/media/aeb36fa1d0aca2340011df4037771941" allowfullscreen="" frameborder="0" height="2206" width="680" title="build.gradle" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 2206px;"></iframe>

Afterward, implement **LoginView** on the **ViewController** class in **ViewController.swift**. declare and initialize the **LoginPresenterImpl** that we created in **common** like what we did for Android. Next, we will write our code as shown below.

<iframe src="https://liewjuntung.medium.com/media/1f6060969e45d35f85d9858836f76605" allowfullscreen="" frameborder="0" height="719" width="680" title="ViewController.swift" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 719px;"></iframe>

Update the storyboard

![img](https://miro.medium.com/max/875/1*lEpX7pVj8BPzZJepqwMHtw.png)

Now we implemented the View and the Presenter. Let's run them on the simulators.

![img](https://miro.medium.com/max/750/1*Wob7rtKYgK_T3nNHcLttBQ.gif)

Thanks for reading! You can find the code to the repository [here](https://github.com/LiewJunTung/Kotlin-Multiplatform-MVP-Tutorial). And a huge thanks to Ho, Jon, and Toru for helping me out with the drafting of this post.

[LiewJunTung/Kotlin-Multiplatform-MVP-TutorialContribute to LiewJunTung/Kotlin-Multiplatform-MVP-Tutorial development by creating an account on GitHub.github.com](https://github.com/LiewJunTung/Kotlin-Multiplatform-MVP-Tutorial/tree/master

## [More from JT Liew](https://liewjuntung.medium.com/?source=follow_footer-----4b828f18ed63--------------------------------)

