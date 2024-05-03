package com.example.taskapp.model

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class TaskRepositoryTest {

    @InjectMockKs
    private lateinit var repository: TaskRepository

    @MockK
    private lateinit var dao: TaskDAO

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when creating a Task, it should be success`() {
        val task = Task().apply { taskName = "Task 1" }

        coEvery { dao.insert(task) } returns Unit

        runBlocking {
            val response = repository.add(task)

            assertTrue(response.isSuccess)
        }
    }

    @Test
    fun `if creating a Task fails, should return error`() {

        coEvery { dao.insert(any()) } throws Exception("Error creating task")

        runBlocking {
            val response = repository.add(Task().apply { taskName = "Task 1" })

            assertTrue(response.isFailure)
        }

    }

    @Test
    fun `when a Task is delete, should return success`() {
        val task = Task().apply { taskName = "Task 1" }

        coEvery { dao.delete(task) } returns Unit

        runBlocking {
            val response = repository.delete(task)

            assertTrue(response.isSuccess)
        }
    }

    @Test
    fun `when a Task fails when delete, should return error`() {

        coEvery { dao.delete(any()) } throws Exception("Error deleting task")

        runBlocking {
            val response = repository.delete(Task())

            assertTrue(response.isFailure)
        }
    }

    @Test
    fun `when getting all Task, it should be success`() {
        val task = Task().apply { taskName = "Task 1" }
        val taskList = listOf(task)

        coEvery { dao.getAll() } returns taskList

        runBlocking {
            val response = repository.getAll()

            assertTrue(response.isSuccess)
            assertEquals(taskList, response.getOrNull())
        }
    }

    @Test
    fun `when getting all Task fails , should return error`() {

        coEvery { dao.getAll() } throws Exception("Error getting all tasks")

        runBlocking {
            val response = repository.getAll()

            assertTrue(response.isFailure)
        }
    }
}