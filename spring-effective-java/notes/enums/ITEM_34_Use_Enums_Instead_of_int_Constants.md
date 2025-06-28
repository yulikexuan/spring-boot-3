# Use Enums

### To associate data with enum constants
- Declare instance fields and write a constructor that takes the data and stores it in the fields
- Enums are by their nature immutable, so all fields should be final 

### Methods 
- Some behaviors associated with enum constants may need to be used 
  - only from within the class or package in which the enum is defined
  - Such behaviors are best implemented as private or package-private methods
  - Just as with other classes, unless you have a compelling reason to expose an enum method to its clients 
    - declare it private or, if need be, package-private

### If an enum is generally useful, it should be a top-level class 
- If its use is tied to a specific top-level class, it should be a member class of that top-level class

### Enum types have an automatically generated `valueOf(String)` method
- It translates a constant's name into the constant itself. 
- If you override the toString method in an enum type 
  - consider writing a fromString method 
    - to translate the custom string representation back to the corresponding enum.

### So when should you use enums? 
- Use enums any time you need a set of constants whose members are known at compile time
- Of course, this includes "natural enumerated types" such as 
  - the planets, the days of the week, and the chess pieces
- Also includes other sets for which you know all the possible values at compile time 
  - such as choices on a menu, operation codes, and command line flags