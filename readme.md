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
# [Lambda Expressions ...](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
```
public void sortByLengthInJava8(String[] strings) {
* Arrays.sort(strings, (first, second) -> Integer.compare(first.length(), second.length()));
}
```
### ... are functional programming constructs
#### (blocks of code with parameters, aka methods, that may be executed at a later point in time)
### ... are code treated as data
---
# [λ ...](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
```
public void sortByLengthInJava8(String[] strings) {
  Arrays.sort(strings, (first, second) -> Integer.compare(first.length(), second.length()));
}

public class Arrays {
  public static <T> void sort(T[] a, Comparator<? super T> c) {
    ...
  }
  ...
}
```
### ... can be converted to functional interfaces
```
*@FunctionalInterface
public interface Comparator<T> {
  int compare(T o1, T o2);
  ...
}
```
### ... which have exactly one abstract method

???
- λ expressions can access effectively final variables from the enclosing scope.
---
name: 8default
# [Default Methods in Interfaces ...](http://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)
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
  ...
}
```
### ... enable you to add new functionality to the interfaces
???
- You can now add default and static methods to interfaces that provide concrete implementations.
- You must resolve any conflicts between default methods from multiple interfaces (`_8_Default`)
---
name: 8refs
# [Method References ...](http://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html)
```
public void sortByLengthInJava8(String[] strings) {
* Arrays.sort(strings, (first, second) -> Integer.compare(first.length(), second.length()));
}
```
### ... are compact, easy-to-read λ for methods that already have a name
```
public void sortByLengthInJava8(String[] strings) {
* Arrays.sort(strings, this::compareByLength);
}
private int compareByLength(String first, String second) {
  return Integer.compare(first.length(), second.length());
}
```
### ... can operate on λ's formal parameters
```
public void sortIgnoringCaseInJava8(String[] strings) {
* Arrays.sort(strings, String::compareToIgnoreCase);
}
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
  ...
* public int compareToIgnoreCase(String str) {
    return CASE_INSENSITIVE_ORDER.compare(this, str);
  }
  ...
}
```
???
- Method and constructor references refer to methods or constructors without invoking them.
---
name: 8streams
# [Streams ...](http://docs.oracle.com/javase/8/docs/technotes/guides/language/lambda_api_jdk8.html)
```
public void printAll_java7(Collection<Integer> collection) {
  for(Integer elt : collection) {
    System.out.println(elt);
  }
}
```
### ... are sequences of elements on which you can apply computations ...
```
public void printAll_java8(Collection<Integer> collection) {
  collection.stream().sorted().forEach(System.out::println);
}
```
### ... defined as sequences of steps ([monads](http://en.wikipedia.org/wiki/Monad_%28functional_programming%29))
```
public void printAllLessThanTenOrdered(Collection<?> collection) {
  collection.stream().filter(i -> i < 10).sorted().forEach(System.out::println);
}
```
---
# Streams are pipelines with ...
### ... a **source**
#### collection, array, generator function, I/O channel
```
public static IntStream range(int startInclusive, int endExclusive) {...}
```
### ... 0 or more **intermediate operations**
#### **stateless**: peek, filter, map, flatMap, skip, limit
#### **stateful**: sorted, distinct
```
public Stream<T> filter(Predicate<? super T> predicate);
```
### ... a **terminal operation** that triggers computation
#### forEach, collect, reduce
```
public Optional<T> min(Comparator<? super T> comparator);
```
---
# Mapping ...
```
static public IK[] getIKs(IIdentifiable[] identifiables) {
  IK[] res = new IK[identifiables.length];
  for (int i = 0; i < identifiables.length; i++) {
    res[i] = identifiables[i].getIk();
  }
  return res;
}
```
### ... with Streams
```
IK[] res = Arrays.stream(identifiables)
                 .map(IIdentifiable::getIk)
                 .toArray(n -> new IK[n]);

List<IK> res = Arrays.stream(identifiables)
                     .map(IIdentifiable::getIk)
                     .collect(Collectors.toList());

Map<IK, IIdentifiable> res = Arrays.stream(identifiables)
                                   .collect(Collectors.toMap(IIdentifiable::getIk,
                                                             Function.identity()));
```
---
# Parallel Streams ...
```
identifiables.parallelStream().map(IIdentifiable::getIk)
                              .collect(toList());
```
### ... use the default JVM’s fork/join pool: `ForkJoinPool.common()` ...
```
identifiables.parallelStream().map(IIdentifiable::getIk)
*                             .peek(ik -> Thread.sleep(999_999))
                              .collect(toList());
```
### ... which doesn’t compensate blocked workers
<br>
# Do not use *parallel streams* on server side
---
layout: true
---
class: center, middle, title
# Questions ?
