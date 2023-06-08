# Kotlin + Docker

Motivation[Permalink](https://www.malachid.com/blog/2020-01-04-docker/#motivation)

Over the last few years, I have been programming more in Kotlin than Java; both for mobile and for the CLI.

I decided I wanted to determine what would be required to run Kotlin in Docker. I decided on a CLI app, instead of an Android app in an emulator. To be honest, Hello World is fine for this, because it is more about the process than the actual code being executed.

I’m also going to focus on Kotlin JVM rather than Multiplatform, just to keep this a minimalist example.

There is always a ton of configuration and optimizations that can be done as well. I wanted this post to focus on the minimal steps necessary. There are many other resources out there to help tweak your app or your docker image or your cloud environment.

The question is: What is the minimal steps to run a Kotlin CLI app inside a docker image?

## The App[Permalink](https://www.malachid.com/blog/2020-01-04-docker/#the-app)

First, we need a basic app. Since Hello World is sufficient, let’s auto-generate it.

Create a directory, and change into it.

```
malachi@enki:~/work$ mkdir ktDocker
malachi@enki:~/work$ cd ktDocker/
```

Use gradle to auto-generate a Kotlin Application:

```
malachi@enki:~/work/ktDocker$ gradle init --dsl kotlin

Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2

Select implementation language:
  1: C++
  2: Groovy
  3: Java
  4: Kotlin
  5: Swift
Enter selection (default: Java) [1..5] 4

Project name (default: ktDocker): 

Source package (default: ktDocker): com.malachid.ktdocker


BUILD SUCCESSFUL in 20s
2 actionable tasks: 2 executed
```

And verify it works.

```
malachi@enki:~/work/ktDocker$ ./gradlew run

> Task :run
Hello world.

BUILD SUCCESSFUL in 2s
2 actionable tasks: 2 executed
```

## Create the distribution[Permalink](https://www.malachid.com/blog/2020-01-04-docker/#create-the-distribution)

We’re going to have Docker run the app, not build it (since we are talking about minimalist).

Create the distribution.

```
malachi@enki:~/work/ktDocker$ ./gradlew distTar

BUILD SUCCESSFUL in 661ms
5 actionable tasks: 4 executed, 1 up-to-date
```

## The Dockerfile[Permalink](https://www.malachid.com/blog/2020-01-04-docker/#the-dockerfile)

For this, we’ll just create a new `Dockerfile` image in the current directory.

Use nano/vi/emacs/gedit/whatever and create the `Dockerfile`:

```
# We are only running a pre-compiled app; so select a small JRE
FROM openjdk:8-jre-alpine

# Unpack from our `./gradlew distTar` into the docker image
RUN mkdir /app
ADD build/distributions/ktDocker.tar /app
WORKDIR /app/ktDocker

# Run the app
CMD ["bin/ktDocker"]
```

## Build the Docker image[Permalink](https://www.malachid.com/blog/2020-01-04-docker/#build-the-docker-image)

Now we run the docker command to build the image.

```
malachi@enki:~/work/ktDocker$ docker build -t ktdocker .

Sending build context to Docker daemon  2.118MB
Step 1/5 : FROM openjdk:8-jre-alpine
 ---> f7a292bbb70c
Step 2/5 : RUN mkdir /app
 ---> Running in e0a006eff828
 ---> cdf980f50d10
Removing intermediate container e0a006eff828
Step 3/5 : ADD build/distributions/ktDocker.tar /app
 ---> 542b2cc0036d
Removing intermediate container c6d235b58081
Step 4/5 : WORKDIR /app/ktDocker
 ---> 2ad29b79c278
Removing intermediate container 32362f112ae1
Step 5/5 : CMD bin/ktDocker
 ---> Running in 0854c39bed56
 ---> 6a17d8001851
Removing intermediate container 0854c39bed56
Successfully built 6a17d8001851
Successfully tagged ktdocker:latest
```

## Run your Kotlin Docker image[Permalink](https://www.malachid.com/blog/2020-01-04-docker/#run-your-kotlin-docker-image)

And now we run it.

```
malachi@enki:~/work/ktDocker$ docker run -it --rm ktdocker
Hello world.
```

## Summary[Permalink](https://www.malachid.com/blog/2020-01-04-docker/#summary)

And that’s it, you’ve now built and deployed a Kotlin app to Docker by adding the `Dockerfile` and using just these commands:

```
gradle init --dsl kotlin
./gradlew distTar
docker build -t ktdocker .
docker run -it --rm ktdocker
```

In case you are wondering about the directory structure of what is being deployed, you can run `./gradlew installDist` and then look inside `build/install/ktDocker/`.

Happy deployments!

## Gradle7 Update (12 August 2021)[Permalink](https://www.malachid.com/blog/2020-01-04-docker/#gradle7-update-12-august-2021)

I was trying these instructions again today with a newer version of Gradle, and the instructions are slightly different now.

The auto-generated directory is now called `app`, as well as the jar, shell files, etc.

It’s a pretty simple fix. We just need to update our Dockerfile.

```
FROM openjdk:15-jdk

# Unpack from our `./gradlew distTar` into the docker image
ADD app/build/distributions/app.tar /
WORKDIR /app

# Run the app
CMD ["bin/app"]
```

You’ll notice that we are no longer creating a directory. That’s because the tar now includes an `app` directory. As such we are also extracting it one level higher.

That’s it!