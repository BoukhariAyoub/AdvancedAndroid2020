package com.boukharist.domains.usecase

import com.boukharist.domains.entities.CallResult
import com.boukharist.domains.entities.UserNotFoundException
import com.boukharist.domains.entities.UserRepositoryDownException
import com.boukharist.domains.repositories.UserRepo
import com.boukharist.domains.usecase.MockDta.USER_MOCK
import com.boukharist.domains.usecase.MockDta.USER_MOCK_2
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class InfoUseCaseImplTest {

    @Mock
    private lateinit var userRepo: UserRepo

    @InjectMocks
    private lateinit var systemUnderTest: InfoUseCaseImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    internal fun `Test getUser Emit User when Repository emits user`() = runBlockingTest {
        //GIVEN
        whenever(userRepo.getUser()).thenReturn(flowOf(CallResult.success(USER_MOCK), CallResult.success(USER_MOCK_2)))

        //WHEN
        val result = systemUnderTest.getUser().toList()
        val expectedResult = listOf(CallResult.success(USER_MOCK), CallResult.success(USER_MOCK_2))

        //THEN
        Assertions.assertEquals(result, expectedResult)
    }

    @Test
    internal fun `Test getUser Emit User when Repository throws UserNotFoundException`() = runBlockingTest {
        //GIVEN
        whenever(userRepo.getUser()).thenReturn(flowOf(CallResult.failure(UserNotFoundException)))

        //WHEN
        val result = systemUnderTest.getUser().first()
        val expectedResult = CallResult.failure(UserNotFoundException)

        //THEN
        Assertions.assertEquals(result, expectedResult)
    }

    @Test
    internal fun `Test getUser Emit User when Repository throws UserRepositoryDownException`() = runBlockingTest {
        //GIVEN
        val reason = "because we want to"
        whenever(userRepo.getUser()).thenReturn(flowOf(CallResult.failure(UserRepositoryDownException(reason))))

        //WHEN
        val result = systemUnderTest.getUser().first()

        //THEN
        val resultException = result.exceptionOrNull()
        assert(result.isFailure)
        assert(resultException is UserRepositoryDownException && resultException.reason == reason)
    }
}