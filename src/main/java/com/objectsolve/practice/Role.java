package com.objectsolve.practice;

import java.io.*;


public final class Role extends SecurityManager implements java.io.Serializable { // marker interface

  private static final long serialVersionUID =
    1716847171807399256L;

  private String title;
  public String getTitle() { return title; }

  private String dept;
  public String getDept() { return dept; }

  private int salary;
  public int getSalary() { return salary; }


  // Nested classes should generally have static scope
  public static class Build {

    private String title;
    private String dept;
    private int salary;

    public Build dept(String value) {
      dept = value;
      return this;
    }

    public Build title(String value) {
      title = value;
      return this;
    }

    public Build salary(int value) {
      salary = value;
      return this;
    }

    public Role build() {
      return new Role(this);
    }
  }

  private Role(Build Build) {
    title = Build.title;
    dept = Build.dept;
    salary = Build.salary;
  }

  @Override
  public final String toString() {
    return
      " Role [title=" + title +
      ", dept=" + dept +
      ", salary=" + salary + "]";
  }



  /*
    Programmatically close the Reflection backdoor (pre Java 17).

    Extending the SecurityManager class, overriding the checkPackageAccess() method
    restricts access to Reflection - this ability is deprecated in Java 17.

    Rationale:
      In Java 17, we will:
        - Deprecate most SecurityManager-related classes/methods.
        - Warn at startup when CLI eanbles a SecurityManager.
        - Warn at runtime when Java app/lib dynamically installs a SecurityManager.

    ref:  https://openjdk.org/jeps/411
  */
  @Override
  public void checkPackageAccess(String pkg) {

    // Disallow use of the canonical Reflection package (breaks the likes of Hibernate/Spring - "javabeans!!!")
    if (pkg.equals("java.lang.reflect")) {
      throw new SecurityException("Reflection is not allowed!");
    }
  }

  // Close the Serialization backdoor
  public void writeObject(java.io.ObjectOutputStream out) throws IOException { throw new IOException(); }
  public void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException { throw new IOException(); }
  public void readObjectNoData() throws ObjectStreamException { throw new RuntimeException(); }

  public void	readExternal(ObjectInput in) throws IOException { throw new IOException(); }
  public void	writeExternal(ObjectOutput out) throws IOException { throw new IOException(); }
}
