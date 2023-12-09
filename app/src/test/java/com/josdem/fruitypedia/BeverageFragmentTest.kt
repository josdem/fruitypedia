package com.josdem.fruitypedia

import android.os.Bundle
import android.util.Log
import android.view.View
import com.josdem.fruitypedia.state.ApplicationState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executors

class BeverageFragmentTest {

    private val mainThreadSurrogate = Executors.newSingleThreadExecutor()


    @MockK
    private lateinit var view: View

    @MockK
    private lateinit var bundle: Bundle

    @InjectMockKs
    private var beverageFragment = BeverageFragment()

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun shouldValidateBeverageFragment() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0

        ApplicationState.storeValue("currentCategory", 0)

        beverageFragment.onViewCreated(view, bundle)
        assertNull(ApplicationState.getValue("currentBeverage"))
    }
}