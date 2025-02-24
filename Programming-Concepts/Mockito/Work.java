public class Work {
    private String title;
    private String address;
    private double salary;

    public Work(){}

    public Work(String title, String address, double salary) {
        this.title = title;
        this.address = address;
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public double getSalary() {
        return salary;
    }

    public void doWork() {
        System.out.println("Doing work: " + title);
    }
}
