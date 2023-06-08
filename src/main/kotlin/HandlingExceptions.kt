@file:Suppress("UNREACHABLE_CODE")

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletionHandlerException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ClosedSendChannelException
import java.io.File

@InternalCoroutinesApi
fun myException() {
    /**
    Nothing perfect,And the programming not exception,
    so when you have some code you'll be trying to control this issues,
    and don't let errors itself show for the user ,
    so for solve this problem we will be using inline function runCatching ,
    its can help you for hide the probable errors and show a message as alternative
     */
    runCatching {

    }


    // let's try divide some number of zero.
    runCatching {
        val div = Integer.divideUnsigned(readLine()!!.toInt(), readLine()!!.toInt())
        println(div)
    }
        .onFailure {
            when (it) { // Errors!!
                AssertionError() -> throw AssertionError("")
                NotImplementedError() -> throw NotImplementedError("")
                KotlinReflectionNotSupportedError() -> throw KotlinReflectionNotSupportedError("")
            }
        }
        .onFailure {
            when (it) { // Exceptions!!
                RuntimeException() -> throw RuntimeException("")
                TypeCastException() -> throw TypeCastException("")
                ClassCastException() -> throw ClassCastException("")
                FileSystemException(File("")) -> throw FileSystemException(File(""))
                NoSuchFileException(File("")) -> throw NoSuchFileException(File(""))
                ArithmeticException() -> throw ArithmeticException("")
                NullPointerException() -> throw NullPointerException("")
                IllegalStateException() -> throw IllegalStateException("")
                AccessDeniedException(File("")) -> throw AccessDeniedException(File(""))
                NumberFormatException() -> throw NumberFormatException("")
                NoSuchElementException() -> throw NoSuchElementException("")
                CharacterCodingException() -> throw CharacterCodingException()
                IllegalArgumentException() -> throw IllegalArgumentException("")
                IndexOutOfBoundsException() -> throw IndexOutOfBoundsException("")
                FileAlreadyExistsException(File("")) -> throw FileAlreadyExistsException(File(""))
                KotlinNullPointerException() -> throw KotlinNullPointerException("")
                NoWhenBranchMatchedException() -> throw NoWhenBranchMatchedException("")
                UnsupportedOperationException() -> throw UnsupportedOperationException("")
                ConcurrentModificationException() -> throw ConcurrentModificationException("")
                UninitializedPropertyAccessException() -> throw UninitializedPropertyAccessException("")
                CompletionHandlerException("", Throwable()) -> throw CompletionHandlerException("", Throwable())
                CoroutineExceptionHandler { coroutineContext, throwable ->
                    val s = "$coroutineContext $throwable"
                } -> throw  Throwable()
//            TimeoutCancellationException("") -> throw TimeoutCancellationException("") todo internal.
                CancellationException() -> throw CancellationException("")
                ClosedReceiveChannelException("") -> throw ClosedReceiveChannelException("")
                ClosedSendChannelException("") -> throw ClosedSendChannelException("")
//    DateTimeFormatException
//    DateTimeArithmeticException
//    IllegalTimeZoneException
            }
        }
        .getOrElse {
            println(
                "finally is not necessary ," +
                        "and this procedure runs out anyway like (init) option "
            )
        }
}