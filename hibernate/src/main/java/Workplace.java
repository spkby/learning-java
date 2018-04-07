import javax.persistence.*;

@Entity
@Table(name = "workplaces")
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "workplace")
    private Worker worker;

    @ManyToOne
    private Department department;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Workplace{" +
                "id=" + id +
                ", worker=" + worker +
                ", department=" + department +
                '}';
    }
}
