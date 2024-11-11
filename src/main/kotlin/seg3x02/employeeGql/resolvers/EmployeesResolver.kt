package seg3x02.employeeGql.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.coxautodev.graphql.tools.GraphQLMutationResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput
import java.util.*

@Component
class EmployeesResolver : GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private lateinit var employeesRepository: EmployeesRepository

    // Query to get all employees
    fun getEmployees(): List<Employee> {
        return employeesRepository.findAll()
    }

    // Query to get a single employee by ID
    fun getEmployee(id: UUID): Employee? {
        return employeesRepository.findById(id).orElse(null)
    }

    // Mutation to create a new employee
    fun createEmployee(input: CreateEmployeeInput): Employee {
        val employee = Employee(
            id = UUID.randomUUID(),
            name = input.name,
            position = input.position ?: "Unspecified",
            department = input.department ?: "Unspecified",
            salary = input.salary ?: 0.0
        )
        return employeesRepository.save(employee)
    }
}
