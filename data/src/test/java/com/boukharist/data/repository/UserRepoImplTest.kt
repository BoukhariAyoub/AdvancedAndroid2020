package com.boukharist.data.repository

import com.boukharist.data.datasource.inmemory.user.InMemoryUser
import com.boukharist.data.datasource.inmemory.user.InMemoryUserDataSource
import com.boukharist.domains.entities.User
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class UserRepoImplTest {

    @Mock
    private lateinit var inMemoryUserDataSource: InMemoryUserDataSource

    @InjectMocks
    private lateinit var systemUnderTest: UserRepoImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test should return CallResultSuccess when inMemoryUserDataSource emit inMemoryUser`() = runBlockingTest {
        //GIVEN
        val mockInMemoryUser = InMemoryUser(firstName = "ss", lastName = "ss", birthDate = 19272)
        whenever(inMemoryUserDataSource.getUserFormMemory()).thenReturn(flowOf(mockInMemoryUser))
        val expectedResult = User(firstName = "ss", lastName = "ss", birthDate = Date(19272))

        //WHEN
        val result = systemUnderTest.getUser().first()

        //THEN
        assert(result.isSuccess)
        assertEquals(result.getOrNull(), expectedResult)
    }
}