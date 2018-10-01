package springsecurety.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by SOFTWARE02 on 01.09.2018.
 */
@Table(name = "employee")
@Entity
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name")
    private String name;



    @Column(name = "lastname")
    private String lastname;




    public Employee(String name, String lastname,  Managers managers) {
        this.name = name;
        this.lastname = lastname;

        this.managers =managers;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Managers managers;


    public Managers getManagers() {
        return managers;
    }

    public void setManagers(Managers managers) {
        this.managers = managers;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
