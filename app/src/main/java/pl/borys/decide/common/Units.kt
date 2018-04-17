package pl.borys.decide.common

import org.kodein.di.generic.instance

val doNothing: Unit = doNothing()
fun doNothing() {}

fun isTesting(): Boolean {
    val isTesting: Boolean by KodeinProvider.kodeinInstance.instance(tag = KodeinProvider.TEST_TAG)
    return isTesting
}