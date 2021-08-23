package OOP;

public class Employee {
    private String fio;
    private String position;
    private String email;
    private   String phoneNumber;
    private int salary;
    public int age;

    public Employee(String fio) {
        this.fio = fio;
        this.salary = 1;
    }
    public Employee(String fio, String position) {
        this.fio = fio;
        this.position = position;
        this.salary = 88000;
        System.out.println("Шаблон на фио должность отработал");
    }

    public Employee(String fio, String position, String email, String phoneNumber, int salary, int age) {
        System.out.println("Полный шаблон (конструктор) щас отработает..");
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public Employee() {

    }

    public void info() {
        System.out.printf(" fio: %s%n position: %s%n email: %s%n phoneNumber: %s%n salary: %s%n age: %s%n", fio, position, email, phoneNumber, salary, age);
    }
}
