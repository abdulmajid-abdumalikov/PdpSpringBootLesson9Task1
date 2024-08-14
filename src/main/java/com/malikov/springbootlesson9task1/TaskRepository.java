package com.malikov.springbootlesson9task1;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findUncompletedTasks();
}
