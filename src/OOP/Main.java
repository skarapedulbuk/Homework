package OOP;

public class Main {
    public static void main(String[] args) {
        Employee[] empArray = new Employee[5];
        empArray[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        empArray[1] = new Employee("Эдуард Олигофренов", "Начальник отдела");
        empArray[2] = new Employee("Армен Затупян", "Армянский специалист");
        empArray[3] = new Employee("Игорь Туповатов", "Ведущий специалист", "i.tupovatov@oooip.org", "+7 903 (02)", 14000, 42);
        empArray[4] = new Employee("Джордж Слоу", "Курьер");
        empArray[1].age = 45;
        for (Employee employee : empArray) {
            if (employee.age >= 40)
                employee.info();
            System.out.println();
        }


        System.out.println("Напечатано объектов: " + empArray.length);
    }

    }

