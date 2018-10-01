package springsecurety.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by SOFTWARE02 on 01.09.2018.
 */

@Entity
@Table(name="manager")
public class Managers {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "position")
    private  String position;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @OneToMany(mappedBy="managers",fetch = FetchType.LAZY)
    private List<Employee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Managers(String name, String lastname, String position, List<Employee> employees) {
        this.name = name;
        this.lastname = lastname;
        this.position = position;
        this.employees =employees;
    }

    public Managers() {
    }
}
