package pl.borys.decide.common.viewModel

class Response<out T>(val status: Status, val data: T?, val error: Throwable?) {

    fun map(onLoading: () -> Unit, onSuccess: (T?) -> Unit, onError: (Throwable?) -> Unit, onFinish: ()-> Unit) {
        when (status) {
            Status.LOADING -> onLoading()
            Status.SUCCESS -> {
                onSuccess(data)
                onFinish()
            }
            Status.ERROR -> {
                onError(error)
                onFinish()
            }
        }
    }

    companion object {
        fun loading() = Response(Status.LOADING, null, null)
        fun <T> success(data: T) = Response(Status.SUCCESS, data, null)
        fun error(throwable: Throwable) = Response(Status.ERROR, null, throwable)
    }
}