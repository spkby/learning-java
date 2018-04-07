package enums;

import entities.Client;
import entities.Employee;
import entities.Human;
import entities.Student;

public enum HumanType {
    NUN(-1, "nun", Human.class),
    EMPLOYEE(1, "employee", Employee.class),
    CLIENT(2, "client", Client.class),
    STUDENT(3, "student", Student.class);

    private int id;
    private String type;
    private Class aClass;

    HumanType(int id, String value, Class aClass) {
        this.id = id;
        this.type = value;
        this.aClass = aClass;
    }

    public String getType() {
        return type;
    }

    public static HumanType parse(int id) {
        HumanType[] values = HumanType.values();
        for (HumanType value : values) {
            if (value.id == id)
                return value;
        }
        return NUN;
    }

    public static HumanType parse(String value) {
        HumanType[] values = HumanType.values();
        for (HumanType item : values) {
            if (item.type.compareTo(value) == 0)
                return item;
        }
        return NUN;
    }

    public static HumanType parse(Human human) {
        HumanType[] values = HumanType.values();
        for (HumanType item : values) {
            if (human.getClass() == item.aClass)
                return item;
        }
        return NUN;
    }
}
