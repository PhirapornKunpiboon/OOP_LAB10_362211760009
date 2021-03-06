import java.util.List;

public class CompanyManagement {
    public static void main(String[] args) {
        //create instance
        EmployeeDAOImpl dao = EmployeeDAOImpl.getInstance();

        //display all emp;oyee data
        displayAllEmp(dao);
        //add new employee
        addNewEmp(dao);

        //find employee my id
        FindEmpByID(dao);

    }

    private static void FindEmpByID(EmployeeDAOImpl dao) {
        Employee emp = dao.findByID("EMP001");
        if (emp=null){
            System.out.println("emp.toString()");
        }
    }

    private static void addNewEmp(EmployeeDAOImpl dao) {
        Employee myEmp = new Employee();
        dao.addEmp(myEmp);
    }

    private static void displayAllEmp(EmployeeDAOImpl dao) {
        List<Employee> emp = dao.getAllEmp();
        System.out.println("Employee Data : ");
        for (Employee e:emp) {
            System.out.println(e.toString());

        }
    }
}
