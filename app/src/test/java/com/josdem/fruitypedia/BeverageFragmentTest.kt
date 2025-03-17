/*
Copyright 2025 Jose Morales contact@josdem.io

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
 */

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
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test

class BeverageFragmentTest {
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
