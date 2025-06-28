# Data Oriented Programming in Java

by Brian Goetz
- https://www.infoq.com/articles/data-oriented-programming-java/

> DOP - Data Oriented Programming

## Key Takeaways

### [Project Amber](https://openjdk.org/projects/amber/)
- [Introduction to Project Amber](https://www.baeldung.com/java-project-amber)
- Project Amber has brought a number of new features to Java in recent years
- While each of these features are self-contained, they are also designed to work together
    - Specifically, records, sealed classes, and pattern matching work together
        - to enable easier data-oriented programming in Java

### OOP 
- OOP encourages us to model complex entities and processes using objects
    - which combines state and behavior
- OOP is at its best when it is defining and defending boundaries

### Java's Static Typing and Class-Based Modeling
- Still be tremendously useful for smaller programs just in different ways

### Separates the Code that Embodies the Business Logic from Data  
- Data-oriented programming encourages us to model data as (immutable) data 
- and also keep the code that embodies the business logic of how we act on that data separately 
  - Records, sealed classes, and pattern-matching, make that easier

### DOP Offers us a Straighter Path 
- For modeling complex entities, OO techniques have a lot to offer us 
- For modeling simple services that process plain, ad-hoc data 
  - the techniques of DOP may offer us a straighter path

### The techniques of OOP and DOP are NOT at Odds
- They are different tools for different granularity and situations
- We can freely mix and match them as we see fit.

> be at odds (with something): to be different from something 
> - when the two things should be the same 
> - synonym conflict
>     - These findings are at odds with what is going on in the rest of the country


## Object-Oriented Programming
- A language could be well-suited to some things and less well-suited to others
- If we understand what OOP is better or worse at 
  - We can use it where it offers more value and use something else where it offers less
- OOP is at its best when it is defining and defending boundaries
  - Dividing a large program into smaller parts with clear boundaries helps us manage complexity 
    - because it enables modular reasoning 
      - the ability to analyze one part of the program at a time 
      - but still reason about the whole 
- Nowadays, rather than building monoliths 
  - We compose larger applications out of many smaller services
  - Within a small service, there is less need for internal boundaries 
  - Sufficiently, small services can be maintained by a single team, 
    - or even a single developer
  - Within such smaller services, we have less need for modeling long-running stateful processes


## Data-Oriented Programming
- Data-Oriented Programming encourages us to model data as immutable data 
  - and keep the code that embodies the business logic of how we act on that data separately

### Java's new Tools for Data-Oriented Programming
- Make it easier to model data as data: records 
- Directly model Alternatives: sealed classes) 
- Flexibly destructure polymorphic data: (pattern matching) patterns

### Data-Oriented Programming Encourages us to model Data as Data 
- Records, sealed classes, and pattern-matching work together to make that easier

### Programming with data as data doesn't mean giving up static typing
- Static typing in Java still has a lot to offer in terms of 
  - Safety 
  - Readability M
  - Maintainability 
  even when we are only modeling plain data


## Data Oriented Programming in Java

### Records, Sealed Classes, and Pattern Matching 
- They are designed to work together to support Data-Oriented Programming
  - Records allow us to simply model data using classes
  - Sealed Classes let us Model Choices
  - Pattern Matching provides us with an easy and type-safe way of acting on polymorphic data

### Records are Syntactically Concise
- They let us cleanly and simply model aggregates


## Example - Command-Line Options

### The Command Line Program
- A `cat`-like program that copies lines from one or more files to another 
- Can trim files to a certain line-count 
- Can optionally include line numbers

### Model Invocation Options in this Command Line Program
- Some options take arguments 
  - Some do not 
- Some arguments are arbitrary strings 
  - Whereas others are more structured, such as numbers or dates
- Should reject bad options and malformed arguments early in the execution of the program

### The Solution
- Create a single class representing a command line option 
- Parse the command line into a list of option objects

### Model 1

``` 
enum MyOptions { INPUT_FILE, OUTPUT_FILE, MAX_LINES, PRINT_LINE_NUMBERS }
record OptionValue(MyOptions option, String optionValue) { }
static List<OptionValue> parseOptions(String[] args) { ... }
```
- Some options have no parameter
  - We still model them with an `OptionValue` object that has an `optionValue` field
- And even for options that do have parameters, they are always string-typed

### Model 2 
- Model Each Option Directly
  - Historically, this might have been prohibitively verbose
  - However, this is no longer the case fortunately 
  - Used a sealed class to represent an Option
  - Have a record for each kind of option
  - The Option subclasses are pure data
  - The option values have nice clean names and types
    - Options that have parameters represent them with the appropriate type
  - Options without parameters do not have useless parameter variables that might be misinterpreted
  - It's straightforward to process the options with a pattern matching switch 
    - usually one line of code per kind of option
  - Because Option is sealed, the compiler can do type-check 
    - by using a switch handles all the option types
    - What if we add more option types later?  
      - the compiler will remind us which switches need to be extended
- What have we done here? 
  - Take messy, untyped data from across the invocation boundary 
    - command line arguments
  - Transformed it into data that is strongly typed, validated, easily acted upon 
    - through pattern matching
  - Makes many illegal states unrepresentable
    - Such as specifying `--input-file` but not providing a valid path 
  - The rest of the program can just use it with confidence

    ``` 
    sealed interface Option { 
        record InputFile(Path path) implements Option { }
        record OutputFile(Path path) implements Option { }
        record MaxLines(int maxLines) implements Option { }
        record PrintLineNumbers() implements Option { }
    }
    ```


## Algebraic Data Types - ADTs 
This combination of records and sealed types is an example of
> algebraic data types (ADTs)

### The Concept
- `Records` are a form of `Product Types` 
  - Their state space is the cartesian product of that of their components
- `Sealed Classes` are a form of `Sum Types` 
  - The set of possible values is the sum (union) of the value sets of the alternatives
- This Simple Combination of Mechanisms is  `Aggregation and Choice` 
  - It is deceptively powerful and shows up in many programming languages 
- One of the permitted subtypes of a `sealed interface` could be another `sealed interface` 
  - allowing modeling of complex structures
  - Restricting to one Level of Hierarchy does not need to be the case in general

### Java's interpretation of ADTs has a Number of Desirable Properties
- In Java, ADPs can be modeled precisely as sealed hierarchies whose leaves are `records`
- Being Nominal: the types and components have human-readable names
- Being Immutable: they are simpler, safer, and can be freely shared without a worry of interference 
- Being easily Testable: they contain nothing but their data 
  - possibly with behavior derived purely from the data
- Being easily serialized to disk or across the wire
- Being expressive: they can model a broad range of data domains

### Application: Complex Return Types
One of the simplest but most frequently used applications of algebraic data types

#### How do we normally and historically handle Return Types?
- Often tempting to overload the representation of the return value in questionable or complex ways
- Using `null` to mean _not found_
- Encoding multiple values into a `String`
- Using an overly abstract type, such as `Arrays`, `List` or `Map` 
  - to CRAM all the different kinds of information that a method could return 
    - into a single carrier object

### Applying Algebraic Data Types in Java is such a Tempting Offer

#### [Sealed Classes](https://www.infoq.com/articles/java-sealed-classes/)
- Scenario 1 Abstract over both success and failure conditions without using exceptions
  ```  
  sealed interface AsyncReturn<V> {
      record Success<V>(V result) implements AsyncReturn<V> { }
      record Failure<V>(Throwable cause) implements AsyncReturn<V> { }
      record Timeout<V>() implements AsyncReturn<V> { }
      record Interrupted<V>() implements AsyncReturn<V> { }
  }
  ```
  - The PRO
    - the client can handle success and failure uniformly by pattern matching over the result
    - Rather than having to handle success via the return value 
      - and the various failure modes via separate catch blocks
    - If switching over them without a default 
      - the compiler will remind you if you've forgotten a case 
        - Checked exceptions do this too, but in a more intrusive manner
    ``` 
    AsyncResult<V> result = future.get();
    switch (result) {
        case Success<V>(var result): ...
        case Failure<V>(Throwable cause): ...
        case Timeout<V>(): ...
        case Interrupted<V>(): ...
    }
    ```
    
- Scenario 2 A service that Looks Up Entities by name
  - users, documents, groups, etc
  - Different results which distinguish between 
    - no match found 
    - exact match found 
    - no exact match, but there were close matches
  - Mediocre Solutions
    - Cram all into a single List or array 
    - This may make the search API easy to write 
    - It makes it harder to understand, use, or test
  - Algebraic Data Types make both sides of this equation easy
    - the code virtually writes itself from the requirements
      ```  
      sealed interface MatchResult<T> { 
          record NoMatch<T>() implements MatchResult<T> { }
          record ExactMatch<T>(T entity) implements MatchResult<T> { }
          record FuzzyMatches<T>(Collection<FuzzyMatch<T>> entities) 
              implements MatchResult<T> { }
          record FuzzyMatch<T>(T entity, int distance) { }
      }
    
      MatchResult<User> findUser(String userName) { ... }
      ```
      ``` 
      Page userSearch(String user) { 
          return switch (findUser(user)) { 
              case NoMatch() -> noMatchPage(user);
              case ExactMatch(var u) -> userPage(u);
              case FuzzyMatches(var ms) -> 
                  disambiguationPage(ms.stream()
                      .sorted(FuzzyMatch::distance))
                      .limit(MAX_MATCHES)
                      .toList());
      ```
    - Algebraic Data Type for Binary Tree
      ``` 
      sealed interface Tree<T> { 
          record Nil<T>() implements Tree<T> { }
          record Node<T>(Tree<T> left, T val, Tree<T> right) implements Tree<T> { }
      }
      ```
      ``` 
      static<T> boolean contains(Tree<T> tree, T target) { 
          return switch (tree) {
              case Nil() -> false;
              case Node(var left, var val, var right) ->
                      target.equals(val) || left.contains(target) || right.contains(target);
         };
      }
      
      static<T> void inorder(Tree<T> t, Consumer<T> c) { 
          switch (tree) {
              case Nil(): break;
              case Node(var left, var val, var right):
                  inorder(left, c);
                  c.accept(val);
                  inorder(right, c);
          };
      }
      ```


## A JSON Value is also an ADT

``` 
sealed interface JsonValue { 
    record JsonString(String s) implements JsonValue { }
    record JsonNumber(double d) implements JsonValue { }
    record JsonNull() implements JsonValue { }
    record JsonBoolean(boolean b) implements JsonValue { }
    record JsonArray(List<JsonValue> values) implements JsonValue { }
    record JsonObject(Map<String, JsonValue> pairs) implements JsonValue { }
}
```

### To Map the JSON Blob `{ "name":"John", "age":30, "city":"New York" }`

``` 
if (j instanceof JsonObject(var pairs)
    && pairs.get("name") instanceof JsonString(String name)
    && pairs.get("age") instanceof JsonNumber(double age)
    && pairs.get("city") instanceof JsonString(String city)) { 
    // use name, age, city
}
```

- When modeling data as data 
  - Both creating aggregates and taking them apart to extract their contents 
    - (or repack them into another form) is straightforward
- Because pattern matching fails gracefully when something doesn't match 
  - the code to take apart this JSON blob is relatively free of complex control flow 
    - for enforcing structural constraints


## More Complex Domains
- to model an arithmetic expression

``` 
sealed interface Node { }

sealed interface BinaryNode extends Node { 
    Node left();
    Node right();
}

record AddNode(Node left, Node right) implements BinaryNode { }
record MulNode(Node left, Node right) implements BinaryNode { }
record ExpNode(Node left, int exp) implements Node { }
record NegNode(Node node) implements Node { }
record ConstNode(double val) implements Node { }
record VarNode(String name) implements Node { }
```

``` 
double eval(Node n, Function<String, Double> vars) { 
    return switch (n) { 
        case AddNode(var left, var right) -> eval(left, vars) + eval(right, vars);
        case MulNode(var left, var right) -> eval(left, vars) * eval(right, vars);
        case ExpNode(var node, int exp) -> Math.exp(eval(node, vars), exp);
        case NegNode(var node) -> -eval(node, vars);
        case ConstNode(double val) -> val;
        case VarNode(String name) -> vars.apply(name);
    }
}
```

``` 
String format(Node n) { 
    return switch (n) { 
        case AddNode(var left, var right) -> 
                String.format("("%s + %s)", format(left), format(right));
        case MulNode(var left, var right) -> 
                String.format("("%s * %s)", format(left), format(right));
        case ExpNode(var node, int exp) -> String.format("%s^%d", format(node), exp);
        case NegNode(var node) -> String.format("-%s", format(node));
        case ConstNode(double val) -> Double.toString(val);
        case VarNode(String name) -> name;
    }
}
```

## It's not Either / Or
- The techniques of OOP and data-oriented programming are not at odds
  - they are different tools for different granularity and situations 
  - We can freely mix and match them as we see fit.

### Following the Data
**Simple Principles that usually Lead Us to Simple, Reliable Data-Oriented Code**

#### 1. Model the Data, the Whole Data, and Nothing But the Data
- Records should Model Data 
  - Make each Record Model One Thing 
  - Make it clear what each record is modeling  
  - Choose clear names for its components
- When there are Multiple Choices to be Modeled, for example, a tax return is filed either 
  - by the taxpayer or by a legal representative
  - model these as a sealed class and model each alternative with a record
    ``` 
    sealed interface TaxReturn {
        record TaxReturnByTaxPayer implements TaxReturn { }
        record TaxReturnByRepresentative implements TaxReturn { }
    }
    ```
- Behavior in record classes should be limited to implementing derived quantities from the data itself
  - Such as formatting

#### 2. Data is Immutable
- Should not have to worry about the data will change out from under us
- Records are shallowly immutable 
  - Records still require some discipline to avoid letting mutability inject itself into data models

#### 3. Validate at the Boundary 
- Before injecting data into our system, we should ensure that it is valid 
- This might be done in the record compact constructor 
  - if the validation applies universally to all instances 
  - or by the code at the boundary that has received the data from another source

#### 4. Make illegal States Unrepresentable
- `Records` and `Sealed` types make it easy to model our domains in such a way 
  - that Erroneous States simply Can't be Represented



