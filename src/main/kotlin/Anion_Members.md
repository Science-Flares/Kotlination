##Anion_Members:

###introduction...

###kotlin.*
`lazy {} ` see Blocks.kt

`lazyOf("")`  see Blocks.kt
 
`with("") {}` see Blocks.kt 

```kotlin
Comparator<Any> { a,b  -> a.compareTo(b) }
```

`TODO("")` mean it's not implemented.
`emptyArray<Any>()`

`arrayOf<Any>()`  and most others variable arrays.

`arrayOfNulls<Any>(0)`

`assert(true)`  assert verifies your function did its job:

`check(true)`  check if some condition is done for pass to next line.

`checkNotNull(true)`  like check(), Otherwise returns the not null value.

`require(true)`  like check().

`requireNotNull("")`  like check(), Otherwise returns the not null value.

`enumValues<MyEnum>()`  Returns an array containing enum T entries.

`enumValueOf<MyEnum>("")`  Returns an enum entry with specified name.

`error("")`  Throws a [IllegalStateException] with the given [message].

`repeat(0) { /*action something*/ }`  repeat any action n times.

`run { } ` see CodeHelper.kt

`runCatching { }`  see CodeHelper.kt
```kotlin
suspend {
          // For make suspend scope.
}
```


### kotlin.script.templates.*

`DEFAULT_SCRIPT_FILE_PATTERN` TODO

### kotlin.collections.*

`List(0) { 0 }`

` listOf<Any>()` This list accepts any type of values.

` arrayListOf<Any>()`  ...  ...    ...   ... ..   ...

`mutableListOf<Any>()`  ...  ...    ...   ... ..   ...

` mapOf<Any,Any>()`  ...  ...    ...   ... ..   ...

` hashMapOf<Any,Any>()`  ...  ...    ...   ... ..   ...

` linkedMapOf<Any,Any>()`  ...  ...    ...   ... ..   ...

` mutableMapOf<Any,Any>()`  ...  ...    ...   ... ..   ...

` hashSetOf<Any>()`  ...  ...    ...   ... ..   ...

` linkedSetOf<Any>()`  ...  ...    ...   ... ..   ...

`mutableSetOf<Any>()`  ...  ...    ...   ... ..   ...

` setOf<Any>()`  ...  ...    ...   ... ..   ...

` listOfNotNull("")` This list will Exception any null value can be existing in the list.

` buildList<Any> {  }` TODO

`buildMap<Any,Any> {  } ` TODO

`emptyList<Any>()`

` emptyMap<Any,Any>()`

 `sortedMapOf(Pair("","")) ` Returning sorted map sequence.

 `buildSet<Any> {  }` TODO

 `sortedSetOf<Nothing>()` Returning sorted set sequence.

```kotlin
Iterable{ 
    iterator {
        yield(0) // We can say it's the initializer of SequenceScope block, for one value.
        yieldAll(0..10) // this one for ranges values or Sequence.
        /*yield() and yieldAll(), we can return one or more elements (Iterable) and suspend the execution (wait until these elements are consumed).*/
     }
 }
 ```

###kotlin.coroutines.*
```kotlin
suspend {
     kotlin.coroutines.coroutineContext  // TODO
     suspendCoroutine<Nothing> {  } // TODO
     Continuation<String>(coroutineContext){} // TODO
}
```

###kotlin.comparisons.*

`compareBy<Nothing>{""}` see Appeana.c.Comparator.kt

`compareByDescending<String> { "" }`  ...    ...  .    ....    ..

`naturalOrder<String>()`  ...    ...  .    ....    ..

`reverseOrder<String>()`  ...    ...  .    ....    ..

`nullsFirst<String>()` ...    ...  .    ....    ..

`nullsLast<String>()`  ...    ...  .    ....    ..

`compareValues(1,2)` Comparative.

`compareValuesBy(1,2,{it>it })`  Comparative.

`maxOf(0,0)`  Comparative.

`minOf(0,0)`  Comparative.

###kotlin.concurrent.*

`thread {  }` see AtlasJ.JThread.kt

`timer()` TODO("internal").

`timerTask{}` TODO

`fixedRateTimer("",true,1L,1L) {}`  TODO

###kotlin.io.*

`DEFAULT_BUFFER_SIZE`

`print("")`

`println()`

`createTempDir()`  Creates an empty directory in the specified default temporary-file in your system, using the given [prefix] and [suffix] to generate its name.

`createTempFile()` like createTempDir() but file.

`readln()`  Reads a line of input.

`readlnOrNull()` Reads a line of input or null.

###kotlin.random.*

`Random(0)` see Appeana.r.Random.kt

###kotlin.reflect.*

`typeOf<Any>()` see Appeana.k.KType.kt

###kotlin.sequences.*

`Sequence{ iterator { yield("") }}`

`sequenceOf("")`  Creates a sequence that returns the specified values.

`emptySequence<Nothing>()`  Returns an empty sequence.

`generateSequence{}` Sequences represent lazily-evaluated collections, We can create a sequence using the "generateSequence" function.

 example:

```kotlin
val fooSequence = generateSequence(1) { it + 1 }
    .take(10)
    .toList()

println(fooSequence)  
```
in the console:

`[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`

`sequence { this.yield(10) }`

`iterator { this.yield(10) }`

###kotlin.system.*

`exitProcess(0)` Terminates the currently running process.

`measureNanoTime {}` returns elapsed time inside the block by nanoseconds.

`measureTimeMillis {}` returns elapsed time inside the block by milliseconds.

###kotlin.time.*

`measureTime {}` returns elapsed time inside the block.

`measureTimedValue { }`  -> `TimedValue(value=kotlin.Unit, duration='ms/s/m/h/d')`.

###kotlin.text.*

`String()` The String class represents character strings, by using ByteArray||CharArray||StringBuffer||StringBuilder in.

 `buildString { }`  see variables.String.kt

 `charset("")`  see AtlasJ.JCharset.kt

###kotlin.contracts.*

```kotlin
private fun contractOnce() {
    contract { 
        returns() 
    }
}
```





