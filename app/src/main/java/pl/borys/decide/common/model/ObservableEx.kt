package pl.borys.decide.common.model

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.emitAfter(delay: Long, unit:TimeUnit): Observable<T> =
        Observable.timer(delay, unit)
                .flatMap {
                    this
                }