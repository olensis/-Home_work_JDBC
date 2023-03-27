import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        final String user = "postgres";
        final String password = "2207ebs";


        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            City city = new City(1, "Moscow");


            Employee employee = new Employee(2, "sad", "kat", "woman", 23, city);
            employeeDAO.create(employee);


            List<Employee> list = new ArrayList<>(employeeDAO.readALl());
            list.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("что то пошло не так");
        }
    }
}
