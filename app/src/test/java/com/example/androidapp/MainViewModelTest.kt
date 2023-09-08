package com.example.androidapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.androidapp.data.models.UserData
import com.example.androidapp.data.remote.StudentRepository
import com.example.androidapp.utils.DataResult
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations


@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest : BaseTest() {
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var repository: StudentRepository

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataObserver: Observer<DataResult<List<UserData>>>

    private val testList  = listOf(UserData(10,"","","Dept","01","Name","123"))

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
     }

    @Test
    fun givenFetch_shouldReturnSuccess() {
        val testData  = DataResult.Success(testList)
        runTest {
            doReturn(flowOf(
                testData
            )).`when`(repository).fetchStudents()

            viewModel.fetchStudents()

            viewModel.responseLiveData.observeForever(dataObserver)
            Mockito.verify(repository).fetchStudents()
            assertEquals(viewModel.responseLiveData.getOrAwaitValue(), testData)
            Mockito.verify(dataObserver).onChanged(testData)
            viewModel.responseLiveData.removeObserver(dataObserver)
        }
    }


    @Test
    fun givenFetch_shouldReturnCorrectData() {
        val testData  = DataResult.Success(testList)
        runTest {
            doReturn(flowOf(
                testData
            )).`when`(repository).fetchStudents()

            viewModel.fetchStudents()
            Mockito.verify(repository).fetchStudents()
            assertEquals(viewModel.responseLiveData.getOrAwaitValue(), testData)
        }
    }

    @Test
    fun givenFetchFailed_shouldReturnError() {
        val errorData  = DataResult.Error("Error", null)
        runTest {
            doReturn(flowOf(
                errorData
            )).`when`(repository).fetchStudents()

            viewModel.fetchStudents()
            Mockito.verify(repository).fetchStudents()
            assertEquals(viewModel.responseLiveData.getOrAwaitValue(), errorData)
        }
    }


}