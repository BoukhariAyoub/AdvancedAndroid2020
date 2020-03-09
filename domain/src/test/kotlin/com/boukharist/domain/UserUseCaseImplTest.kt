package com.boukharist.domain

import com.boukharist.domain.model.*
import com.boukharist.domain.repository.BmrRepository
import com.boukharist.domain.repository.UserRepository
import com.boukharist.domain.usecase.UserUseCaseImpl
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.time.LocalDate

class UserUseCaseImplTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var bmrRepository: BmrRepository

    @InjectMocks
    private lateinit var useCase: UserUseCaseImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when registerUser called and repository returns Success should return Success`() {
        val mockUser = User(
            gender = Gender.Male,
            birthDate = LocalDate.now(),
            height = 0.0f,
            weight = 0.0f,
            activityType = ActivityType.HIGH,
            goal = Goal.GAIN
        )
        //GIVEN
        whenever(userRepository.registerUser(any())).thenReturn(CallResult.success(Unit))

        //WHEN
        val result = useCase.registerUser(mockUser)

        //THEN
        val argCaptor = argumentCaptor<User>()
        verify(userRepository).registerUser(argCaptor.capture())
        assert(argCaptor.firstValue == mockUser)
        assert(result.isSuccess)
    }

    @Test
    fun `when registerUser called and repository returns exception should return Failure`() {
        val mockUser = User(
            gender = Gender.Male,
            birthDate = LocalDate.now(),
            height = 0.0f,
            weight = 0.0f,
            activityType = ActivityType.HIGH,
            goal = Goal.GAIN
        )
        //GIVEN
        whenever(userRepository.registerUser(any())).thenReturn(CallResult.failure(UserRegistrationException("")))

        //WHEN
        val result = useCase.registerUser(mockUser)

        //THEN
        val argCaptor = argumentCaptor<User>()
        verify(userRepository).registerUser(argCaptor.capture())
        assert(argCaptor.firstValue == mockUser)
        assert(result.isFailure)
        assert(result.exceptionOrNull() is UserRegistrationException)
    }

    @Test
    fun `when getUser called and repository returns Success should return Success`() {
        val mockUser = User(
            gender = Gender.Male,
            birthDate = LocalDate.now(),
            height = 0.0f,
            weight = 0.0f,
            activityType = ActivityType.HIGH,
            goal = Goal.GAIN
        )
        //GIVEN
        whenever(userRepository.getUser()).thenReturn(CallResult.success(mockUser))

        //WHEN
        val result = useCase.getUser()

        //THEN
        verify(userRepository).getUser()
        assert(result.isSuccess)
        assert(result.getOrNull() == mockUser)
    }

    @Test
    fun `when getUser called and repository returns exception should return Failure`() {
        //GIVEN
        whenever(userRepository.getUser()).thenReturn(CallResult.failure(UserNotFoundException))

        //WHEN
        val result = useCase.getUser()

        //THEN
        verify(userRepository).getUser()
        assert(result.isFailure)
        assert(result.exceptionOrNull() is UserNotFoundException)
    }

    @Test
    fun `when getBmr called and repository returns exception should return Success`() {
        val mockUser = User(
            gender = Gender.Male,
            birthDate = LocalDate.now(),
            height = 0.0f,
            weight = 0.0f,
            activityType = ActivityType.HIGH,
            goal = Goal.GAIN
        )
        val mockBmr = Bmr(bmr = "", tdee = "")
        //GIVEN
        whenever(bmrRepository.computeBmr(any())).thenReturn(Success(mockBmr))

        //WHEN
        val result = useCase.getBmr(mockUser)

        //THEN
        val argCaptor = argumentCaptor<User>()
        verify(bmrRepository).computeBmr(argCaptor.capture())
        assert(argCaptor.firstValue == mockUser)
        assert(result.isSuccess)
        assert(result.getOrNull() == mockBmr)
    }

    @Test
    fun `when getBmr called and repository returns exception should return Failure`() {
        val mockUser = User(
            gender = Gender.Male,
            birthDate = LocalDate.now(),
            height = 0.0f,
            weight = 0.0f,
            activityType = ActivityType.HIGH,
            goal = Goal.GAIN
        )
        //GIVEN
        whenever(bmrRepository.computeBmr(any())).thenReturn(CallResult.failure(BmrComputationException("")))

        //WHEN
        val result = useCase.getBmr(mockUser)

        //THEN
        val argCaptor = argumentCaptor<User>()
        verify(bmrRepository).computeBmr(argCaptor.capture())
        assert(argCaptor.firstValue == mockUser)
        assert(result.isFailure)
        assert(result.exceptionOrNull() is BmrComputationException)
    }
}