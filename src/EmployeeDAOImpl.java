import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    //set connection
    public static String driverName = "org.sqite.JDBC";
    public static String url = "jdbc:sqite:MIT_Company.sqite";
    public static Connection conn = null;

    //constan Operator
    //SQL CRUD
    public  static final String GET_ALL_EMP = "select * from Employee";
    public  static final String ADD_NEW_EMP = "insert into Employee" +
            "(emp_id,name,position,email,salary) valusa (?,?,?,?,?)";
    public static  final  String FIND_EMP_BY_ID = "select * from Employee" + "where emp_id= ?";

    //Create Instants
    private static EmployeeDAOImpl instance = new EmployeeDAOImpl();
    public static EmployeeDAOImpl getInstance(){
        return instance;
    }
    //const
    public EmployeeDAOImpl(){
        //load class driver
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Employee> getAllEmp() {
        List<Employee> emp = new ArrayList<Employee>();

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_EMP);

            while (rs.next()){
                emp.add(new Employee(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5)));

            }
            //close connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return emp;
    }

    @Override
    public void addEmp(Employee emp) {
            try {
                conn = DriverManager.getConnection(url);
                PreparedStatement ps = conn.prepareStatement(ADD_NEW_EMP);
                ps.setString(1,(emp.getEmp_id()));
                ps.setString(2, emp.getName());
                ps.setString(3, emp.getPosition());
                ps.setString(4, emp.getEmail());
                ps.setDouble(5,emp.getSalary());
                if (ps.execute()==false){
                    System.out.println("could not add new data to database.");
                }else{
                    System.out.println("Succesful add a new data to database.");
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }

    }

    @Override
    public Employee findByID(String id) {
        Employee myemp = new ArrayList<Employee>();
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps =conn.prepareStatement(FIND_EMP_BY_ID);
            //set paramitor
            ps.setString(1,id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 myemp = new Employee(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5));
                return myemp;
            }else {
                System.out.println(("Could not found employee id: "+id));

            }
            //close
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myemp;
    }

    @Override
    public void updateEmp(Employee employee) {

    }

    @Override
    public void deleteEmp(String id) {

    }
}
