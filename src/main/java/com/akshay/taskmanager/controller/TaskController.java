package com.akshay.taskmanager.controller;

import com.akshay.taskmanager.model.Task;
import com.akshay.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
   private TaskRepository taskRepository;

    // CREATE

    @PostMapping
    public  Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // READ ALL

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // READ BY ID

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
       return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // UPDATE

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());

        return taskRepository.save(task);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "Deleted Task with id: " + id;
    }

}
