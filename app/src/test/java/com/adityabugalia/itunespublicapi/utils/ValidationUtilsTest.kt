package com.adityabugalia.itunespublicapi.utils


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidationUtilsTest {

    @Test
    fun `check for minimum length`() {
        val result = ValidationUtils.validateSearchInput("asassc")
       assertThat(result).isTrue()

    }
}