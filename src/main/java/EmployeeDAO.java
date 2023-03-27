import java.util.List;

public interface EmployeeDAO {
    void create(Employee employee);

    Employee readByID(int id);

    List<Employee> readALl();



    void update(int id, int age);

    void deleteById(int id);
}
