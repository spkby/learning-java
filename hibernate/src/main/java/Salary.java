import javax.persistence.*;

@Entity
@Table(name = "salaries")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne(mappedBy = "salary", fetch = FetchType.LAZY)
    //@ManyToMany
    //@OneToMany
    //@ManyToOne
    private Worker worker;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", worker=" + worker +
                '}';
    }
}
