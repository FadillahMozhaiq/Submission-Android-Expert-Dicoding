package id.fadillah.jetpacksubmission.ui.activity.home

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import id.fadillah.jetpacksubmission.R
import id.fadillah.jetpacksubmission.utils.helper.EspressoIdlingResource
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {
    private val query = "Avenger"

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    /* Memastikan Seluruh data tampil pada fragment home
    * Menutup custom dialog
    * Mengecek imageSlider tampil
    * Mengecek rv_now_playing tampil
    * Mengecek rv_popular tampil
    * Mengecek rv_top_rated tampil
    */
    @Test
    fun loadAllDataFragmentHome() {
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.imageSlider)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_now_playing)).check(matches(isDisplayed()))
        onView(isRoot()).perform(swipeUp())
        onView(withId(R.id.rv_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_top_rated)).check(matches(isDisplayed()))
    }

    /* Memastikan Seluruh data tampil pada fragment Movies
    * Menutup custom dialog
    * Mengecek navigation_movies tampil
    * Memberi tindakan click pada navigation_movies
    * Mengecek rv_movies tampil
    */
    @Test
    fun loadAllDataFragmentMovie() {
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.navigation_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movies)).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
    }

    /* Memastikan Seluruh data tampil pada fragment Tv Show
    * Menutup custom dialog
    * Mengecek navigation_tv tampil
    * Memberi tindakan click pada navigation_tv
    * Mengecek rv_tv_show tampil
    */
    @Test
    fun loadAllDataFragmentTv() {
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.navigation_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
    }

    /* Memastikan Seluruh data tampil pada Detail Activity
    * Menutup custom dialog
    * Mengecek navigation_tv tampil
    * Memberi tindakan click pada navigation_tv
    * Mengecek rv_tv_show tampil
    * Memberi tindakan scroll ke posisi 20 pada rv_tv_show
    * Memberi tindakan click di posisi 18 pada rv_tv_show
    */
    @Test
    fun loadDetailDataDetail() {
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.navigation_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                20
            )
        )
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                18,
                click()
            )
        )
        onView(withId(R.id.detail_movie_cover)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_title)).check(matches(not(withText(""))))
        onView(withId(R.id.detail_movie_desc)).check(matches(not(withText(""))))
    }

    /* Memastikan Seluruh data pencarian pada fragment explore muncul
    * Menutup custom dialog
    * Berpindah navigasi ke fragment explore
    * Memastikan autoCompleteTextView tampil
    * Memberi tindakan click pada autoCompleteTextView
    * Memberi tindakan click pada pilihan "Movies"
    * Mengecek sv_explore tampil
    * Memberi tindakan click pada sv_explore
    * Memberi tindakan mengisi text pada sv_explore
    * Mengecek rv_explore tampil
    * Memberi tindakan click pada autoCompleteTextView
    * Memberi tindakan click pada pilihan "Tv Show"
    * Mengecek sv_explore tampil
    * Memberi tindakan click pada sv_explore
    * Memberi tindakan mengisi text pada sv_explore
    * Mengecek rv_explore tampil
    * Memberi tindakan click pada autoCompleteTextView
    * Memberi tindakan click pada pilihan "People"
    * Mengecek sv_explore tampil
    * Memberi tindakan click pada sv_explore
    * Memberi tindakan mengisi text pada sv_explore
    * Mengecek rv_explore tampil
    * Memberi tindakan click pada autoCompleteTextView
    * Memberi tindakan click pada pilihan "Company"
    * Mengecek sv_explore tampil
    * Memberi tindakan click pada sv_explore
    * Memberi tindakan mengisi text pada sv_explore
    * Mengecek rv_explore tampil
    * Memberi tindakan click pada autoCompleteTextView
    * Memberi tindakan click pada pilihan "Multi Search"
    * Mengecek sv_explore tampil
    * Memberi tindakan click pada sv_explore
    * Memberi tindakan mengisi text pada sv_explore
    * Mengecek rv_explore tampil
    */
    @Test
    fun loadAllDataFragmentExplore() {
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.navigation_explore)).perform(click())
        onView(withId(R.id.autoCompleteTextView)).check(matches(isDisplayed()))
//        Movies
        onView(withId(R.id.autoCompleteTextView)).perform(click())
        onView(withText("Movies")).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withId(R.id.sv_explore)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_explore)).perform(click())
        onView(withId(R.id.sv_explore)).perform(typeSearchViewText(query), closeSoftKeyboard())
        onView(withId(R.id.rv_explore)).check(matches(isDisplayed()))
//        Tv Show
        onView(withId(R.id.autoCompleteTextView)).perform(click())
        onView(withText("Tv Show")).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withId(R.id.sv_explore)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_explore)).perform(click())
        onView(withId(R.id.sv_explore)).perform(typeSearchViewText(query), closeSoftKeyboard())
        onView(withId(R.id.rv_explore)).check(matches(isDisplayed()))
//        People
        onView(withId(R.id.autoCompleteTextView)).perform(click())
        onView(withText("People")).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withId(R.id.sv_explore)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_explore)).perform(click())
        onView(withId(R.id.sv_explore)).perform(typeSearchViewText("Leo"), closeSoftKeyboard())
        onView(withId(R.id.rv_explore)).check(matches(isDisplayed()))
//        Company
        onView(withId(R.id.autoCompleteTextView)).perform(click())
        onView(withText("Company")).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withId(R.id.sv_explore)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_explore)).perform(click())
        onView(withId(R.id.sv_explore)).perform(typeSearchViewText("Marvel"), closeSoftKeyboard())
        onView(withId(R.id.rv_explore)).check(matches(isDisplayed()))
//        Multi Search
        onView(withId(R.id.autoCompleteTextView)).perform(click())
        onView(withText("Multi Search")).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withId(R.id.sv_explore)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_explore)).perform(click())
        onView(withId(R.id.sv_explore)).perform(typeSearchViewText(query), closeSoftKeyboard())
        onView(withId(R.id.rv_explore)).check(matches(isDisplayed()))
    }

    /* Memastikan Seluruh navigation bekerja dengan sempurna
    * Menutup custom dialog
    * Memastikan bottom navigation tampil
    * Melakukan klik pada navigation_explore
    * Memastikan fragment Explore dalam keadaan tampil
    * Melakukan klik pada navigation_movies
    * Memastikan fragment Movies dalam keadaan tampil
    * Melakukan klik pada navigation_tv
    * Memastikan fragment TV dalam keadaan tampil
    * Melakukan klik pada navigation_favorite
    * Memastikan fragment Favorite dalam keadaan tampil
    * Memastikan fragment Favorite Movie dalam keadaan tampil
    * Melakukan swipe left untuk berpindah fragment
    * Memastikan fragment Favorite TV Show dalam keadaan tampil
    *
   */
    @Test
    fun testAllNavigation() {
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.bubble_tab_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_explore)).perform(click())
        onView(withId(R.id.fragment_container_explore)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movies)).perform(click())
        onView(withId(R.id.fragment_container_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.fragment_container_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.fragment_container_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_container_favorite_movie)).check(matches(isDisplayed()))
        onView(isRoot()).perform(swipeLeft())
        onView(withId(R.id.fragment_container_favorite_tv)).check(matches(isDisplayed()))
    }

    private fun typeSearchViewText(text: String?): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                //Ensure that only apply if it is a SearchView and if it is visible.
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change view text"
            }

            override fun perform(uiController: UiController?, view: View) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }
}