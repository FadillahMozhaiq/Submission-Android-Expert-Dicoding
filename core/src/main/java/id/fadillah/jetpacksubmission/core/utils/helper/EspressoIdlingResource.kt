package id.fadillah.jetpacksubmission.core.utils.helper

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "RESOURCE_GLOBAL"

    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = idlingResource.increment()

    fun decrement() = idlingResource.decrement()
}