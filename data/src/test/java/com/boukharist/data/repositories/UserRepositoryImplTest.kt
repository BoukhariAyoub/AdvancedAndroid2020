package com.boukharist.data.repositories

import com.boukharist.data.local.datasources.UserLocalDataSource
import com.boukharist.data.local.mapper.UserDtoToDataMapper
import com.boukharist.data.local.mapper.UserDtoToDomainMapper
import com.boukharist.data.local.models.UserDto
import com.boukharist.data.remote.datasources.UserRemoteDataSource
import com.boukharist.data.remote.mapper.UserRequestMapper
import com.boukharist.data.remote.models.UserRequest
import com.boukharist.domain.model.*
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.NoMoreInteractions
import java.io.IOException
import java.util.*

internal class UserRepositoryImplTest {

    @InjectMocks
    private lateinit var systemUnderTest: UserRepositoryImpl

    @Mock
    private lateinit var remoteDataSource: UserRemoteDataSource

    @Mock
    private lateinit var localDataSource: UserLocalDataSource

    @Mock
    private lateinit var userRequestMapper: UserRequestMapper

    @Mock
    private lateinit var userDtoToDataMapper: UserDtoToDataMapper

    @Mock
    private lateinit var userDtoToDomainMapper: UserDtoToDomainMapper

    private val mockUser = User(
        gender = Gender.Male,
        birthDate = GregorianCalendar().time,
        height = 0.0f,
        weight = 0.0f,
        activityType = ActivityType.HIGH,
        goal = Goal.GAIN
    )

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test when registerUser Called should call Api and save user`() {
        //GIVEN
        whenever(remoteDataSource.registerUser(any())).then { }

        //WHEN
        val result = systemUnderTest.registerUser(mockUser)

        //THEN
        val userRequestCaptor = argumentCaptor<UserRequest>()
        val userDtoCaptor = argumentCaptor<UserDto>()
        verify(remoteDataSource).registerUser(userRequestCaptor.capture())
        verify(localDataSource).setLoggedInUser(userDtoCaptor.capture())
        assert(result.isSuccess)
        assert(result.getOrNull() == Unit)
    }

    @Test
    fun `test when registerUser called and remoteDataSource throws Exception must return failure`() {
        //GIVEN
        whenever(remoteDataSource.registerUser(anyOrNull())).thenThrow(IOException())
        whenever(localDataSource.setLoggedInUser(anyOrNull())).then { }

        //WHEN
        val result = systemUnderTest.registerUser(mockUser)

        //THEN
        val userRequestCaptor = argumentCaptor<UserRequest>()
        verify(remoteDataSource).registerUser(anyOrNull())
        verify(localDataSource, NoMoreInteractions()).setLoggedInUser(anyOrNull())
        assert(result.isFailure)
        assert(result.exceptionOrNull() is UserRegistrationException)
    }
}