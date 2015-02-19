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
void java6()
{
  long longNumber = 9876543210L;

  long _5 = 5;

  double pi = 3.1415926;
}
```
### ... improve readability
```
void java7()
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
int dayInWeek_java6(String dayOfWeek) {
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
int dayInWeek_java7(String dayOfWeek) {
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
private Map<IK, Map<IK, List<Mouvement>>> regroupMvtByPalAndFract(List<Mouvement> movs)
{
// MAP <IK palSV, <IK Fract, List<Mouvement>>>
  Map<IK, Map<IK, List<Mouvement>>> mapPalFractMvt = new HashMap<IK, Map<IK,List<Mouvement>>>();
  ...
```
### ... eliminates useless repetition in generic instance creation
```
private Map<IK, Map<IK, List<Mouvement>>> regroupMvtByPalAndFract(List<Mouvement> movs)
{
// MAP <IK palSV, <IK Fract, List<Mouvement>>>
  Map<IK, Map<IK, List<Mouvement>>> mapPalFractMvt = new HashMap<>();
  ...
```
---
name: 7try
# [The try-with-resources Statement](http://docs.oracle.com/javase/7/docs/technotes/guides/language/try-with-resources.html)

---
name: 7catch
# [Catching Multiple Exception Types](http://docs.oracle.com/javase/7/docs/technotes/guides/language/catch-multiple.html)

---
layout: true
.left-column[
.grey[
## [Java 7](#7_)
]
## Java 8
1. [Lambda Expressions](#8lambda)
1. [Method References](#8refs)
1. [Default Methods](#8default)
1. [Streams](#8streams)
1. [Date-Time Package](#8date)
1. Removal of PermGen
]

---
name: 8lambda
# [Lambda Expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)

---
name: 8refs
# [Method References](http://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html)

---
name: 8default
# [Default Methods](http://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)

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
