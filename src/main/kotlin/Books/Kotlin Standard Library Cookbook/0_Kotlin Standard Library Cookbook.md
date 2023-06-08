# Kotlin Standard Library Cookbook

### Master the powerful Kotlin standard library through practical code examples

Samuel Urbanowicz

BIRMINGHAM - MUMBAI

Kotlin Standard Library Cookbook
Copyright © 2018 Packt Publishing
All rights reserved. 
No part of this book may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, without the prior written permission of the publisher, except in the case of brief quotations embedded in critical articles or reviews.
Every effort has been made in the preparation of this book to ensure the accuracy of the information presented. 
However, the information contained in this book is sold without warranty, either express or implied. 
Neither the author, nor Packt Publishing or its dealers and distributors, will be held liable for any damages caused or alleged to have been caused directly or indirectly by this book.
Packt Publishing has endeavored to provide trademark information about all of the companies and products mentioned in this book by the appropriate use of capitals.
However, Packt Publishing cannot guarantee the accuracy of this information.
Commissioning Editor: Richa Tripathi
 Acquisition Editor: Chaitanya Nair
 Content Development Editor: Akshada Iyer
 Technical Editor: Abhishek Sharma
 Copy Editor: Safis Editing
 Project Coordinator: Prajakta Naik
 Proofreader: Safis Editing
 Indexer: Tejal Daruwale Soni
 Graphics: Jisha Chirayil
 Production Coordinator: Aparna Bhagat
First published: July 2018
Production reference: 1260718
Published by Packt Publishing Ltd.
 Livery Place
 35 Livery Street
 Birmingham
 B3 2PB, UK.
ISBN 978-1-78883-766-8
www.packtpub.com 


 "I have no special talent.
 I am only passionately curious." 
 - A. Einstein

 I dedicate the book to all curious souls… most of all to you, Agat.


mapt.io
Mapt is an online digital library that gives you full access to over 5,000 books and videos, as well as industry leading tools to help you plan your personal development and advance your career. For more information, please visit our website.

_Why subscribe?_

• Spend less time learning and more time coding with practical eBooks and Videos from over 4,000 industry professionals

• Improve your learning with Skill Plans built especially for you

• Get a free eBook or video every month

• Mapt is fully searchable

• Copy and paste, print, and bookmark content PacktPub.com

Did you know that Packt offers eBook versions of every book published, with PDF and ePub files available? You can upgrade to the eBook version at www.PacktPub.com and as a print book customer, you are entitled to a discount on the eBook copy. 
Get in touch with us at service@packtpub.com for more details.
At www.PacktPub.com, you can also read a collection of free technical articles, sign up for a range of free newsletters, and receive exclusive discounts and offers on Packt books and eBooks.

### Contributors

**About the author:**
Samuel Urbanowicz is an experienced software engineer skilled in mobile applications and backend development. 
A fan of modern programming languages, he has been using Kotlin since its beginning.
He's always curious to dive into technologies.
He is especially passionate about machine learning. 
Samuel believes that the Kotlin language has great potential for multiplatform development. 
He has work experience in both big corps and small start-ups.
He is an active contributor to open source projects.
I would like to thank the mentors who inspired me along my programming adventures. 
I wouldn't have the same passion for programming if not for the wisdom I learned from you:
"Coding must be fun, otherwise we are doing it wrong!". 
I am also grateful to the reviewers who made the book better. This book could not have been written without you.

**About the reviewer:**
Miłosz Pacholczyk is a software developer with 5 years of commercial experience in Java.
He worked on B2B software used by leading manufacturers of automotive and construction industries.
He graduated from the University of Warsaw.





Packt is searching for authors like you
If you're interested in becoming an author for Packt, please visit authors packtpub.com and apply today.
We have worked with thousands of developers and tech professionals, just like you, to help them share their insight with the global tech community. 
You can make a FP.general application, apply for a specific hot topic that we are recruiting an author for, or submit your own idea.

Preface
The primary aim of Kotlin Standard Library Cookbook is to help you dive into advanced language concepts and features as fast as possible in a friendly way. It covers a wide range of FP.general programming problems at varying difficulty levels, including design patterns, functional programming, data processing, and more. The book consists of recipes that present a specific problem and give a step-by-step explanation of how to approach it effectively. All the presented features of the standard library are well explained, allowing you to discover them with ease.
This book will help software developers switch to Kotlin with ease and integrate it seamlessly into the existing JVM and JavaScript projects. Examples included in the book can easily be implemented in your own projects. You can also get the ready-made solutions from the book's GitHub repository if you prefer to follow and test the recipes in your favorite IDE. Once you have completed the book, you should have expert knowledge and insight into the language's advanced concepts, allowing you to address your daily programming challenges efficiently.

Who this book is for
The book is ideal for those who are already familiar with the basics of Kotlin and want to discover how to effectively solve day-to-day programming problems with state-of-the-art solutions, utilizing advanced language and standard library features. Experienced programmers who are willing to switch to Kotlin from other languages, especially Java, Scala, and JavaScript, will find it helpful as well.

_What this book covers_

Chapter 1, Ranges, Progressions, and Sequences, presents the concept of Kotlin ranges and sequences. It shows how to approach common algorithmic problems by defining custom sequences and how to define ranges for custom classes.

Chapter 2, Expressive Functions and Adjustable Interfaces, shows how to approach designing functions and interfaces using the language's built-in features. The chapter explains how to implement clean, reusable functions, and scalable interfaces containing default implementations. The recipes also cover other features of the language, such as inlining closures, destructuring variables, reified type parameters, and other useful tips that help design more flexible and natural code.

Chapter 3, Shaping Code with Kotlin Functional Programming Features, shows how to solve real-life programming challenges by adopting state-of-the-art functional programming patterns. The chapter helps readers get familiar with Kotlin support for functional programming concepts provided by the standard library and the built-in language features.

Chapter 4, Powerful Data Processing, focuses on presenting standard library support for declarative style operations on collections. The included recipes present solutions to varying programming problems related to dataset transformations, reducing, or filtering. The chapter shows how to approach data processing operations practicing a functional programming style with the use of powerful functionalities built into the standard library.

Chapter 5, Tasteful Design Patterns Adopting Kotlin Concepts, presents the Kotlin-specific approach to implementing popular design patterns, including the Observer and Lazy delegates, Builder, Strategy, and more. Design patterns presented in the following chapter are backed by real-life examples that emphasize the benefits of the Delegation pattern.

Chapter 6, Friendly I/O Operations, presents useful extension functions available in the standard library that simplify the work with I/O operations. The chapter focuses on common use cases of read-write operations on files, working with streams and buffers, and the Kotlin approach to traversing files available in a specific directory.

Chapter 7, Making Asynchronous Programming Great Again, is an in-depth guide to asynchronous programming focusing on the Kotlin coroutines framework and its application in real-life situations. The chapter presents how to optimize and advance previous examples by executing parts of their code in the background in a non-blocking way. Here, you will also find a practical example of implementing an asynchronous REST client with the Retrofit library and the coroutines framework.

Chapter 8, Best Practices for the Android, JUnit, and JVM UI Frameworks, covers practical problems specific to the popular frameworks, among which Kotlin is the one used most often. In FP.general, it will focus on Android platform-specific aspects and asynchronous UI programming with coroutines, both on Android and JVM frameworks such as JavaFX and Swing. It will also help you to write effective unit tests for the JVM platform using the JUnit framework. The recipes related to unit testing will also include more advanced topics, such as mocking dependencies with the Mockito Kotlin library and testing asynchronous code based on the coroutines framework.

Chapter 9, Miscellaneous, presents handy solutions to various problems and issues that Kotlin developers deal with on a daily basis.

To get the most out of this book
In order to learn from the book efficiently, you should follow the included recipes step by step and try to implement the solutions on your own. You can download the sample code from the book's GitHub repository at https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook and import it into IntelliJ IDEA and Android Studio. If you have any trouble, you can run and test each recipe instantly in the IDE.

_Download the example code files:_

You can download the example code files for this book from your account at www.packtpub.com. If you purchased this book elsewhere, you can visit www.packtpub.com/support and register to have the files emailed directly to you.
You can download the code files by following these steps:

• Log in or register at www.packtpub.com.

• Select the SUPPORT tab.

• Click on Code Downloads & Errata.

• Enter the name of the book in the Search box and follow the onscreen instructions.

Once the file is downloaded, please make sure that you unzip or extract the folder using the latest version of:

• WinRAR/7-Zip for Windows

• Zipeg/iZip/UnRarX for Mac

• 7-Zip/PeaZip for Linux

The code bundle for the book is also hosted on GitHub at https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook. In case there's an update to the code, it will be updated on the existing GitHub repository.
We also have other code bundles from our rich catalog of books and videos available at https://github.com/PacktPublishing/. Check them out!

**Conventions used:**

There are a number of text conventions used throughout this book.
CodeInText: Indicates code words in text, database table names, folder names, filenames, file extensions, pathnames, dummy URLs, user input, and Twitter handles. Here is an example: "We can do so with the extension function provided for the IntProgression, LongProgression, and CharProgression types, which is called reversed()."
A block of code is set as follows:
```kotlin
val daysOfYear: IntRange = 1..365
for(day in daysOfYear.reversed()) {
 println("Remaining days: $day")
}
```
When we wish to draw your attention to a particular part of a code block, the relevant lines or items are set in bold:
```kotlin
val sequence = sequenceOf("a", "b", "c", "d", "e", "f", "g", "h")
val transformedSequence = sequence.map {
 println("Applying map function for $it")
 it
}
```
Any command-line input or output is written as follows:

`[10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]`

**Bold**: Indicates a new term, an important word, or words that you see onscreen.
Warnings or important notes appear like this.
Tips and tricks appear like this.

**Sections:**

In this book, you will find several headings that appear frequently (Getting ready, How to do it..., How it works..., There's more..., and See also).
To give clear instructions on how to complete a recipe, use these sections as follows:

**Getting ready:**

This section tells you what to expect in the recipe and describes how to set up any software or any preliminary settings required for the recipe.

_How to do it…_

This section contains the steps required to follow the recipe.

_How it works…_

This section usually consists of a detailed explanation of what happened in the previous section.

**There's more…**

This section consists of additional information about the recipe in order to make you more knowledgeable about the recipe.

**See also:**

This section provides helpful links to other useful information for the recipe.

**Get in touch:**

Feedback from our readers is always welcome.
General feedback: Email feedback@packtpub.com and mention the book title in the subject of your message. If you have questions about any aspect of this book, please email us at questions@packtpub.com.
Errata: Although we have taken every care to ensure the accuracy of our content, mistakes do happen.
If you have found a mistake in this book, we would be grateful if you would report this to us. 
Please visit www.packtpub.com/submit-errata, selecting your book, clicking on the Errata Submission Form link, and entering the details.
Piracy: If you come across any illegal copies of our works in any form on the Internet, we would be grateful if you would provide us with the location address or website name.
Please contact us at copyright@packtpub.com with a link to the material.
If you are interested in becoming an author: If there is a topic that you have expertise in, and you are interested in either writing or contributing to a book, please visit www.packtpub/authors.com.

**Reviews:**

Please leave a review. Once you have read and used this book, why not leave a review on the site that you purchased it from? Potential readers can then see and use your unbiased opinion to make purchase decisions, we at Packt can understand what you think about our products, and our authors can see your feedback on their book. Thank you!
For more information about Packet, please visit www.packtpub.com.
