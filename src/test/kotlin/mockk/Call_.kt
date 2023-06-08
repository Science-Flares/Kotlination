package mockk

import io.mockk.*

class Call_ {
    init {
        Call(retType = Nothing::class,
            invocation = Invocation(self = "",
                stub = "",
                method = methodDescription,
                args = emptyList(),
                timestamp = 1L,
                callStack = { emptyList() },
                originalCall = {},
                fieldValueProvider = { BackingFieldValue("", {}, {}) }
            ),
            matcher = InvocationMatcher(
                self = "",
                method = methodDescription,
                args = emptyList(),
                allAny = false
            )
        ) { BackingFieldValue("", {}, {}) }
    }

    companion object {
        val methodDescription = MethodDescription(
            name = "",
            returnType = Nothing::class,
            returnTypeNullable = true,
            returnsUnit = true,
            returnsNothing = true,
            isSuspend = true,
            isFnCall = true,
            declaringClass = Nothing::class,
            paramTypes = emptyList(),
            varArgsArg = 1,
            privateCall = true
        )
    }
}
