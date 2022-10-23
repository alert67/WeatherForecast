package com.mateuszkukiel.core.validators

import com.mateuszkukiel.core.exception.SearchQueryValidationException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class SearchQueryValidatorTest {

    private lateinit var searchQueryValidator: SearchQueryValidator

    @BeforeEach
    internal fun setUp() {
        searchQueryValidator = SearchQueryValidator()
    }

    @Test
    fun `valid cities returns true`() {
        assertTrue(searchQueryValidator.validate("bialystok"))
        assertTrue(searchQueryValidator.validate("WarszawA"))
        assertTrue(searchQueryValidator.validate("new york"))
    }

    @Test
    fun `invalid cities returns false`() {
        assertThrows(SearchQueryValidationException::class.java) {
            searchQueryValidator.validate("Białystok")
        }
        assertThrows(SearchQueryValidationException::class.java) {
            searchQueryValidator.validate("he")
        }
        assertThrows(SearchQueryValidationException::class.java) {
            searchQueryValidator.validate("new--york")
        }
    }

    @Test
    fun `valid lat lng returns true`() {
        assertTrue(searchQueryValidator.validate("48.8567, 2.3508"))
        assertTrue(searchQueryValidator.validate("53.12083594592215, 23.093173061342494"))
    }

    @Test
    fun `invalid lat lng returns false`() {
        assertThrows(SearchQueryValidationException::class.java) {
            searchQueryValidator.validate("365.8567, 586.3508")
        }
        assertThrows(SearchQueryValidationException::class.java) {
            searchQueryValidator.validate("53,12083594592215, 23,093173061342494")
        }
    }

    @Test
    fun `valid zip codes returns true`() {
        assertTrue(searchQueryValidator.validate("10001"))
        assertTrue(searchQueryValidator.validate("12332-1232"))
    }

    @Test
    fun `invalid zip codes returns false`() {
        assertThrows(SearchQueryValidationException::class.java) {
            searchQueryValidator.validate("15-231")
        }
        assertThrows(SearchQueryValidationException::class.java) {
            searchQueryValidator.validate("1233212322312313")
        }
    }
}