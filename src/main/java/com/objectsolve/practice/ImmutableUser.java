package com.objectsolve.practice;

 public final class ImmutableUser {

  private final String firstName;
  private final String lastName;
  private final Role role;  // final does NOT confer immutablity!


  /*
   To confer true immutability to this [ImmutableUser] class,
   apply the same mechanisms as does the Role.java class.
  */
  public ImmutableUser(String fName, String lName, Role aRole) {
    firstName = fName;
    lastName = lName;
    role = aRole;
  }

  // scalar
  public String getFirstName() { return firstName; }
  public String getLastName() { return lastName; }

  // complex; thi accessor delegates to extant state
  public Role getRole() {

    Role temp =
      new Role.Build().
		    title(role.getTitle()).
		    dept(role.getDept()).
		    salary(role.getSalary()).
		    build();

    return role;
  }

  @Override
  public final String toString() {
    return
      "  ImmutableUser " +
      "\n    [firstName=" + firstName +
      ",\n    lastName=" + lastName +
      ",\n    Role=" + getRole() + "]";
  }
}
