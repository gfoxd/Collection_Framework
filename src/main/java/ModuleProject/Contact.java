package ModuleProject;

import java.util.Objects;
import java.util.Scanner;

public class Contact {
    private String name;
    private String phone;
    private String email;
    private String group;

    public Contact(String name, String phone, String email, String group) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public Contact(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(phone, contact.phone) && Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }

    @Override
    public String toString() {
        return  "name - '" + name + '\'' +
                ", phone - '" + phone + '\'' +
                ", email - '" + email + '\'' +
                ", группа - '" + group + '\'';
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }
}
