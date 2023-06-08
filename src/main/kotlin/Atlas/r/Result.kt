package r

class Result {
    var result = Result
    init {
        result.failure<Nothing>(Throwable())
        result.success(this)
    }
}