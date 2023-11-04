package Dat.NguyenThaiThanh.SpringBootCRUD.Controller;

import Dat.NguyenThaiThanh.SpringBootCRUD.Model.Task;
import Dat.NguyenThaiThanh.SpringBootCRUD.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @GetMapping
    public ResponseEntity<List<Task>> getListOfAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (!tasks.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            Task _task = taskRepository
                    .save(new Task(task.getTitle(), task.getDescription(), false));
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable long id, @RequestBody Task task) {
        Optional<Task> taskData = taskRepository.findById(id);
        if (taskData.isPresent()) {
            Task _task = taskData.get();
            _task.setTitle(task.getTitle());
            _task.setDescription(task.getDescription());
            _task.setCompleted(task.isCompleted());
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
