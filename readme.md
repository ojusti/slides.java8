class: center, middle, title
# New Features in<br>Java 7 & Java 8

[jop@infologic.fr](mailto:jop@infologic.fr)<br>2015

---
layout: true
.left-column[
## Java 7
1. [Underscores in Numeric Literals](#7_)
1. [Strings in switch](#7switch)
1. [Diamond operator](#7diamond)
1. [try-with-resources](#7try)
1. [Catching Multiple Exception Types](#7catch)

.grey[
## [Java 8](#8lambda)
]]

---
name: 7_
# [Underscores in Numeric Literals ...](http://docs.oracle.com/javase/7/docs/technotes/guides/language/underscores-literals.html)
```
public void java6()
{
  long longNumber = 9876543210L;

  long _5 = 5;

  double pi = 3.1415926;
}
```
### ... improve readability
```
public void java7()
{
  long longNumber = 9_876_543_210L;
  long longNumberOddFormat = 987__65______43__210L;

  long _5 = 5;
  long invalidUnderscoreAtTheEnd = 98_;

  double pi = 3.14_15_92_6;
  double invalidPi = 3_.1415926;
  double anotherInvalidPi = 3._1415926;
}
```
---
name: 7switch
# [Strings in switch Statements ...](http://docs.oracle.com/javase/7/docs/technotes/guides/language/strings-switch.html)
```
public int dayInWeek_java6(String dayOfWeek) {
  if(dayOfWeek.equalsIgnoreCase("Lundi")) return 1;
  if(dayOfWeek.equalsIgnoreCase("Mardi")) return 2;
  if(dayOfWeek.equalsIgnoreCase("Mercredi")) return 3;
  if(dayOfWeek.equalsIgnoreCase("Jeudi")) return 4;
  if(dayOfWeek.equalsIgnoreCase("Vendredi")) return 5;
  if(dayOfWeek.equalsIgnoreCase("Samedi")) return 6;
  if(dayOfWeek.equalsIgnoreCase("Dimanche")) return 7;
  throw new IllegalArgumentException("Jour inconnu : " + dayOfWeek);
}
```
### ... generate more efficient bytecode, but are case sensitive
```
public int dayInWeek_java7(String dayOfWeek) {
* switch(dayOfWeek.toLowerCase()) {
    case "lundi": return 1;
    case "mardi": return 2;
    case "mercredi": return 3;
    case "jeudi": return 4;
    case "vendredi": return 5;
    case "samedi": return 6;
    case "dimanche": return 7;
    default: throw new IllegalArgumentException("Jour inconnu : " + dayOfWeek);
  }
}
```
---
name: 7diamond
# [<> (diamond operator) ...](http://docs.oracle.com/javase/7/docs/technotes/guides/language/type-inference-generic-instance-creation.html)
```
private Map<IK, Map<IK, List<Mouvement>>> regroupMvtByPalAndFract(List<Mouvement> movs) {
// MAP <IK palSV, <IK Fract, List<Mouvement>>>
  Map<IK, Map<IK, List<Mouvement>>> mapPalFractMvt = new HashMap<IK, Map<IK,List<Mouvement>>>();
```
### ... eliminates useless repetition in generic instance creation
```
  Map<IK, Map<IK, List<Mouvement>>> mapPalFractMvt = new HashMap<>();
```
### ... but think about primitive obsession
```
private Palettes regroupMvtByPalAndFract(List<Mouvement> movs) {...}
public class Palettes {
  private Map<IK, Fractions> fractions = new HashMap<>();
  public Fractions getFractionsPalette(IK palette) {...}
}
public class Fractions {
  private Map<IK, Mouvements> mouvements = new HashMap<>();
  public Mouvements getMouvementsFraction(IK fraction) {...}
}
public class Mouvements {
  private List<Mouvement> mouvements = new ArrayList<>();
}
```
---
name: 7try
# [The try-with-resources Statement ...](http://docs.oracle.com/javase/7/docs/technotes/guides/language/try-with-resources.html)
```
public static int executeCount(Connection conn, String sqlQuery) throws SQLException {
  Statement st = conn.createStatement();
  try {
    ResultSet resultSet = st.executeQuery(sqlQuery);
    resultSet.next();
    return resultSet.getInt(1);
  } finally {
*   st.close();
  }
}
```
### ... ensures that each resource java.lang.AutoCloseable is closed at the end
```
public static int executeCount(Connection conn, String sqlQuery) throws SQLException {
* try(Statement st = conn.createStatement())
  {
    ResultSet resultSet = st.executeQuery(sqlQuery);
    resultSet.next();
    return resultSet.getInt(1);
  }
}
```
???
See execution order in `_7_Try.java`
---
name: 7catch
# [Catching Multiple Exception Types ...](http://docs.oracle.com/javase/7/docs/technotes/guides/language/catch-multiple.html)
```
public ISuiviVersion newInstance(Class<? extends ISuiviVersion versionClass) {
  try {
    return versionClass.newInstance();
  } catch (InstantiationException e) {
    throw new InfologicRuntimeException(/* TODO ERR */ null, e);
  } catch (IllegalAccessException e) {
    throw new InfologicRuntimeException(/* TODO ERR */ null, e);
  }
}
```
### ... reduces duplication and lessen the temptation to catch an overly broad exception
```
public ISuiviVersion newInstance(Class<? extends ISuiviVersion versionClass) {
  try {
    return versionClass.newInstance();
  } catch (InstantiationException | IllegalAccessException e) {
    throw new InfologicRuntimeException(/* TODO ERR */ null, e);
  }
}
```
---
layout: true
.left-column[
.grey[
## [Java 7](#7_)
]
## Java 8
1. [Lambda Expressions](#8lambda)
1. [Default Methods](#8default)
1. [Method References](#8refs)
1. [Streams](#8streams)
1. [Date-Time Package](#8date)
1. Removal of PermGen
1. Nashorn (js engine)
]

---
name: 8lambda
# [Lambda Expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
### Java is an object-oriented programming language
```
public void sortByLengthInJava7(String[] strings) {
  Arrays.sort(strings, new Comparator<String> {
    @Override
    public int compare(String first, String second) {
*     Integer.compare(first.length(), second.length());
    }
  });
}
```
### λ are functional programming constructs
#### - blocks of code with parameters
#### - that may be executed at a later point in time
```
public void sortByLengthInJava8(String[] strings) {
* Arrays.sort(strings, (first, second) -> Integer.compare(first.length(), second.length()));
}
```
---
# [λ](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
```
public void sortByLengthInJava8(String[] strings) {
  Arrays.sort(strings, (first, second) -> Integer.compare(first.length(), second.length()));
}

public class Arrays {
  public static <T> void sort(T[] a, Comparator<? super T> c) { }
}
```
### λ can be converted to functional interfaces
```
*@FunctionalInterface
public interface Comparator<T> {
  int compare(T o1, T o2);
  ...
}
```
### a functional interface has exactly one abstract method

???
- λ expressions can access effectively final variables from the enclosing scope.
- Method and constructor references refer to methods or constructors without invoking them.
- You can now add default and static methods to interfaces that provide concrete implementations.
- You must resolve any conflicts between default methods from multiple interfaces.

---
name: 8default
# [Default Methods in Interfaces](http://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)
```
@FunctionalInterface
public interface Comparator<T> {
  public int compare(T o1, T o2);
  public boolean equals(Object obj);
* public default Comparator<T> reversed() {
      return Collections.reverseOrder(this);
  }
* public default Comparator<T> thenComparing(Comparator<? super T> other) {
      Objects.requireNonNull(other);
      return (Comparator<T> & Serializable) (c1, c2) -> {
          int res = compare(c1, c2);
          return (res != 0) ? res : other.compare(c1, c2);
      };
  }
  ...

* public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
      return Collections.reverseOrder();
  }
* public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
      return (Comparator<T>) Comparators.NaturalOrderComparator.INSTANCE;
  }
* public static <T> Comparator<T> nullsFirst(Comparator<? super T> comparator) {
      return new Comparators.NullComparator<>(true, comparator);
  }
* public static <T> Comparator<T> nullsLast(Comparator<? super T> comparator) {
      return new Comparators.NullComparator<>(false, comparator);
  }
  ...

}
```
---
name: 8refs
# [Method References](http://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html)

---
name: 8streams
# [Streams](http://docs.oracle.com/javase/8/docs/technotes/guides/language/lambda_api_jdk8.html)

---
name: 8date
# [Date-Time Package](http://docs.oracle.com/javase/8/docs/technotes/guides/datetime/index.html)
un text

---

# Code
```
public static void main(String...args)
{
  int i;
* i++;
}
```

???
Ce naiba trebuie sa spun
