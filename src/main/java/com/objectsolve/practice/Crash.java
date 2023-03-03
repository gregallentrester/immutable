package com.objectsolve.practice;

public final class Crash {

  public static void main(String... args) {

    Role joesRole =
      new Role.Build().
         title("king").
         dept("throne").
         salary(180000).
         build();

    System.err.println(
      "\n\n Crash.main(), joesRole: \n" + joesRole);


    ImmutableUser user =
      new ImmutableUser("Joe", "Trump", joesRole);

    System.err.println(
      "\n Crash.main(), ImmutableUser: \n" + user);
  }
}
