public class Employee {

  public Employee(String name, double salary, int seniority) {
    this.name = name;
    this.salary = salary;
    this.seniority = seniority;
  }

  public boolean equals(Object rhs) {
    return rhs instanceof Employee && name.equals( ((Employee) rhs).name);
  }

  public int hashCode() {
    return name.hashCode();
  }

  private String name;
  private double salary;
  private int seniority;
}
