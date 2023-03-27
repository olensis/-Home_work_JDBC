import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements  EmployeeDAO{
    private Connection connection;


    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) {
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employee_1 (first_name,last_name ,gender,age ) VALUES ((?), (?), (?),(?))")) {


            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(2, employee.getGender());
            statement.setInt(3, employee.getAge());


            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readByID(int id) {
        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee_1 INNER JOIN city c on c.city_id = employee_1.city_id AND employee_1.id=(?)"))
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFirst_name(resultSet.getString("first_name"));
                employee.setLast_name(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCity(
                        new City(Integer.parseInt(resultSet.getString("city_id")), resultSet.getString("city_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readALl() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee_1 INNER JOIN city c on c.city_id = employee_1.city_id"))
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFirst_name(resultSet.getString("first_name"));
                employee.setLast_name(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCity(
                        new City(Integer.parseInt(resultSet.getString("city_id")), resultSet.getString("city_name")));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void update(int id,int age) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE employee_1 SET age =(?) WHERE id=(2)")) {

            statement.setInt(1, age);
            statement.setInt(2, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM employee_1 WHERE id=(?)")) {

            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
