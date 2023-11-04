package Dat.NguyenThaiThanh.SpringBootCRUD.Repository;

import Dat.NguyenThaiThanh.SpringBootCRUD.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
