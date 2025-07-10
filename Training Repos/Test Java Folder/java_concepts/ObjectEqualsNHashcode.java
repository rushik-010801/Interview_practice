package java_concepts;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Emp {
    int i;
    String name;
    String des;

    public Emp(int i, String name, String des) {
        this.i = i;
        this.name = name;
        this.des = des;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return i == emp.i && Objects.equals(name, emp.name) && Objects.equals(des, emp.des);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, name, des);
    }
}

public class ObjectEqualsNHashcode {
    public static void main(String args[]) {
        Emp e = new Emp(1, "a", "d");
        Emp e1 = new Emp(1, "a", "d");
        Emp e2 = new Emp(1, "b", "d");

        Map<Emp, Boolean> map = new HashMap<>();
        map.put(e, true);
        map.put(e1, true);
        map.put(e2, true);
        System.out.println(map);
    }
}
