package coroutines.selects

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.selects.selectUnbiased
import kotlinx.coroutines.selects.whileSelect

 @ExperimentalCoroutinesApi
 fun main()= runBlocking {
    select<Any> {  }
    selectUnbiased<Any> {  }
    whileSelect {  }
}