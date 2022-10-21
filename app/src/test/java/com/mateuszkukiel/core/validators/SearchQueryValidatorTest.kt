package com.mateuszkukiel.core.validators

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
        assertTrue(searchQueryValidator.validate("Warszawa "))
        assertTrue(searchQueryValidator.validate("new york"))
    }

    @Test
    fun `invalid cities returns false`() {
        assertFalse(searchQueryValidator.validate("Białystok"))
        assertFalse(searchQueryValidator.validate("he"))
        assertFalse(searchQueryValidator.validate("new--york"))
    }

    @Test
    fun `valid lat lng returns true`() {
        assertTrue(searchQueryValidator.validate("48.8567, 2.3508"))
        assertTrue(searchQueryValidator.validate("53.12083594592215, 23.093173061342494"))
    }

    @Test
    fun `invalid lat lng returns false`() {
        assertFalse(searchQueryValidator.validate("365.8567, 586.3508"))
        assertFalse(searchQueryValidator.validate("53,12083594592215, 23,093173061342494"))
    }

    @Test
    fun `valid zip codes returns true`() {
        assertTrue(searchQueryValidator.validate("10001"))
        assertTrue(searchQueryValidator.validate("12332-1232"))
    }

    @Test
    fun `invalid zip codes returns false`() {
        assertFalse(searchQueryValidator.validate("15-231"))
        assertFalse(searchQueryValidator.validate("1233212322312313"))
    }
}