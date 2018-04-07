import javax.persistence.*;
import java.util.List;

@Entity         // Аннотация говорит, что этот класс - сущность, которую можно хранить в БД
@Table(name = "departments") //в какой таблице хранятся сущности
public class Department {

    @Id // обозначение ключевого поля
    @GeneratedValue(strategy = GenerationType.IDENTITY) // способ генерации ключей
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 64) //название и длина столбца
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Workplace> workplaces;


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

    public List<Workplace> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<Workplace> workplaces) {
        this.workplaces = workplaces;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
