package com.valentinesgarage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.valentinesgarage.data.model.ConditionRating
import com.valentinesgarage.data.model.Truck
import com.valentinesgarage.data.repository.TruckRepository
import com.valentinesgarage.data.repository.UserRepository
import com.valentinesgarage.ui.checkin.CheckInViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CheckInViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var truckRepository: TruckRepository
    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: CheckInViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        truckRepository = mock()
        userRepository = mock()
        viewModel = CheckInViewModel(truckRepository, userRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `checkInTruck with blank registration emits error`() = runTest {
        viewModel.checkInTruck(
            registration = "",
            make = "Mercedes",
            model = "Actros",
            kilometers = "50000",
            condition = ConditionRating.GOOD,
            conditionNotes = ""
        )
        val state = viewModel.state.value
        assertTrue(state is CheckInViewModel.CheckInState.Error)
    }

    @Test
    fun `checkInTruck with invalid km emits error`() = runTest {
        viewModel.checkInTruck(
            registration = "N 123-456",
            make = "Mercedes",
            model = "Actros",
            kilometers = "abc",
            condition = ConditionRating.GOOD,
            conditionNotes = ""
        )
        val state = viewModel.state.value
        assertTrue(state is CheckInViewModel.CheckInState.Error)
    }

    @Test
    fun `checkInTruck with no logged in user emits error`() = runTest {
        whenever(userRepository.currentFirebaseUser).thenReturn(null)
        viewModel.checkInTruck(
            registration = "N 123-456",
            make = "Mercedes",
            model = "Actros",
            kilometers = "50000",
            condition = ConditionRating.GOOD,
            conditionNotes = "Minor dent on left door"
        )
        val state = viewModel.state.value
        assertTrue(state is CheckInViewModel.CheckInState.Error)
    }
}
