package Dat.NguyenThaiThanh.SpringBootCRUD.Repository;

import Dat.NguyenThaiThanh.SpringBootCRUD.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
