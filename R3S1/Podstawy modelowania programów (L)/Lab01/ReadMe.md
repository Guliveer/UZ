# Lab01 - Program Modeling Diagrams

This document presents comprehensive modeling diagrams for all four tasks in Lab01, showcasing different architectural patterns and design approaches in Java programming.

1. [Lab01 - Program Modeling Diagrams](#lab01---program-modeling-diagrams)
   1. [Task 1: Basic Fraction Class](#task-1-basic-fraction-class)
      1. [Architecture Overview](#architecture-overview)
      2. [UML Class Diagram](#uml-class-diagram)
      3. [Data Flow Diagram](#data-flow-diagram)
      4. [Design Patterns Used](#design-patterns-used)
   2. [Task 2: Extended Fraction Class](#task-2-extended-fraction-class)
      1. [Architecture Overview](#architecture-overview-1)
      2. [UML Class Diagram](#uml-class-diagram-1)
      3. [State Diagram for Fraction Forms](#state-diagram-for-fraction-forms)
      4. [Conversion Flow Diagram](#conversion-flow-diagram)
      5. [Design Patterns Used](#design-patterns-used-1)
   3. [Task 3: Rectangle and RectangleCollection](#task-3-rectangle-and-rectanglecollection)
      1. [Architecture Overview](#architecture-overview-2)
      2. [UML Class Diagram](#uml-class-diagram-2)
      3. [Composition Relationship Diagram](#composition-relationship-diagram)
      4. [CRUD Operations Flow](#crud-operations-flow)
      5. [Design Patterns Used](#design-patterns-used-2)
   4. [Task 4: Shape Hierarchy with Polymorphism](#task-4-shape-hierarchy-with-polymorphism)
      1. [Architecture Overview](#architecture-overview-3)
      2. [UML Class Diagram](#uml-class-diagram-3)
      3. [Interface Implementation Diagram](#interface-implementation-diagram)
      4. [Polymorphism in Action Diagram](#polymorphism-in-action-diagram)
      5. [Design Patterns Used](#design-patterns-used-3)
   5. [Summary of Architectural Patterns](#summary-of-architectural-patterns)

## Task 1: Basic Fraction Class

### Architecture Overview

Task 1 implements a simple immutable fraction class with basic mathematical operations. The design follows the **Value Object** pattern, ensuring immutability and proper encapsulation.

### UML Class Diagram

```mermaid
classDiagram
    class Fraction {
        -int numerator
        -int denominator
        +Fraction(int numerator, int denominator)
        +int getNumerator()
        +int getDenominator()
        +Fraction getReciprocal()
        +double doubleValue()
        +String toString()
        +boolean equals(Object obj)
        +int hashCode()
        -int gcd(int a, int b)
    }

    class Main {
        +main(String[] args)
    }

    Main --> Fraction : creates and uses
```

### Data Flow Diagram

```mermaid
flowchart TD
    A[User Input] --> B[Main.main]
    B --> C[Create Fraction]
    C --> D[Fraction Constructor]
    D --> E[Validate Denominator ≠ 0]
    E --> F[Normalize Sign]
    F --> G[Calculate GCD]
    G --> H[Reduce Fraction]
    H --> I[Store Final Values]
    I --> J[Display Fraction Info]
    J --> K[Calculate Reciprocal]
    K --> L[Display Results]
```

### Design Patterns Used

- **Value Object Pattern**: Immutable fraction representation
- **Factory Method Pattern**: Constructor acts as factory for creating valid fractions
- **Template Method Pattern**: Common validation and normalization steps

---

## Task 2: Extended Fraction Class

### Architecture Overview

Task 2 extends the fraction concept to support mixed fractions with multiple constructors and conversion methods. It demonstrates **Constructor Overloading** and **Static Factory Methods** patterns.

### UML Class Diagram

```mermaid
classDiagram
    class Fraction {
        -int wholePart
        -int numerator
        -int denominator
        +Fraction(int wholePart, int numerator, int denominator)
        +Fraction(int numerator, int denominator)
        +Fraction(double decimal)
        +Fraction(int wholeNumber)
        +int getWholePart()
        +int getNumerator()
        +int getDenominator()
        +boolean isZero()
        +boolean isInteger()
        +Fraction getReciprocal()
        +Fraction toMixed()
        +Fraction toImproper()
        +double doubleValue()
        +String toString()
        +boolean equals(Object obj)
        +int hashCode()
        +Fraction of(int numerator, int denominator)$
        +Fraction of(int wholePart, int numerator, int denominator)$
        +Fraction parse(String fractionStr)$
        -int gcd(int a, int b)
    }

    class Main {
        +main(String[] args)
    }

    Main --> Fraction : creates and manipulates
```

### State Diagram for Fraction Forms

```mermaid
stateDiagram-v2
    [*] --> Mixed : Constructor with wholePart
    [*] --> Improper : Constructor without wholePart
    [*] --> Integer : numerator = 0
    [*] --> Zero : wholePart = 0 && numerator = 0

    Mixed --> Improper : toImproper()
    Improper --> Mixed : toMixed()
    Integer --> Mixed : add fractional part
    Zero --> Mixed : modify values

    Mixed --> Reciprocal : getReciprocal()
    Improper --> Reciprocal : getReciprocal()
    Integer --> Reciprocal : getReciprocal()

    Reciprocal --> Mixed : toMixed()
    Reciprocal --> Improper : toImproper()
```

### Conversion Flow Diagram

```mermaid
flowchart LR
    A[Input Data] --> B{Constructor Type}
    B -->|wholePart, num, den| C[Mixed Form]
    B -->|num, den| D[Improper Form]
    B -->|decimal| E[Decimal Conversion]
    B -->|wholeNumber| F[Integer Form]

    C --> G[toImproper]
    D --> H[toMixed]
    G --> I[Improper Result]
    H --> J[Mixed Result]

    C --> K[getReciprocal]
    D --> K
    K --> L[Reciprocal Form]
```

### Design Patterns Used

- **Builder Pattern**: Multiple constructors for different initialization scenarios
- **Static Factory Methods**: `of()` and `parse()` methods
- **State Pattern**: Different fraction representations (mixed, improper, integer)
- **Strategy Pattern**: Different conversion strategies

---

## Task 3: Rectangle and RectangleCollection

### Architecture Overview

Task 3 implements a **Composition Pattern** where `Main` class uses `RectangleCollection` to manage multiple `Rectangle` objects. This demonstrates separation of concerns and encapsulation of collection operations.

### UML Class Diagram

```mermaid
classDiagram
    class Rectangle {
        -String name
        -double sideA
        -double sideB
        +Rectangle(String name, double sideA, double sideB)
        +String getName()
        +double getSideA()
        +double getSideB()
        +double getArea()
        +double getPerimeter()
        +boolean isSquare()
        +boolean equals(Object obj)
        +int hashCode()
        +String toString()
    }

    class RectangleCollection {
        -Map~String, Rectangle~ rectangles
        +RectangleCollection()
        +boolean addRectangle(Rectangle rectangle)
        +Rectangle removeRectangle(String name)
        +Rectangle getRectangle(String name)
        +boolean containsRectangle(String name)
        +Collection~Rectangle~ getAllRectangles()
        +Collection~String~ getAllNames()
        +int size()
        +boolean isEmpty()
        +double getTotalArea()
        +double getAverageArea()
        +Rectangle getLargestRectangle()
        +Rectangle getSmallestRectangle()
        +long countSquares()
        +void clear()
        +String toString()
    }

    class Main {
        -Scanner scanner$
        -RectangleCollection rectangleCollection$
        +main(String[] args)$
        -displayMenu()$
        -addRectangle()$
        -displayAllRectangles()$
        -removeRectangle()$
        -calculateTotalArea()$
        -displayStatistics()$
    }

    RectangleCollection *-- Rectangle : contains
    Main --> RectangleCollection : uses
    Main --> Rectangle : creates
```

### Composition Relationship Diagram

```mermaid
flowchart TD
    A[Main Class] --> B[RectangleCollection]
    B --> C[HashMap~String, Rectangle~]
    C --> D[Rectangle A]
    C --> E[Rectangle B]
    C --> F[Rectangle C]
    C --> G[Rectangle ...]

    A --> H[User Interface]
    A --> I[Menu System]
    A --> J[Input Validation]
```

### CRUD Operations Flow

```mermaid
flowchart TD
    A[User Menu] --> B{Operation Choice}

    B -->|Create| C[Add Rectangle]
    C --> D[Validate Input]
    D --> E[Create Rectangle Object]
    E --> F[Add to Collection]
    F --> G[Confirm Success]

    B -->|Read| H[Display All]
    H --> I[Get All Rectangles]
    I --> J[Format Display]
    J --> K[Show Statistics]

    B -->|Update| L[Not Implemented]

    B -->|Delete| M[Remove Rectangle]
    M --> N[Get Rectangle Name]
    N --> O[Remove from Collection]
    O --> P[Confirm Removal]

    G --> A
    K --> A
    P --> A
    L --> A
```

### Design Patterns Used

- **Composition Pattern**: Main uses RectangleCollection
- **Collection Pattern**: Encapsulated rectangle management
- **Command Pattern**: Menu-driven operations
- **Repository Pattern**: RectangleCollection acts as repository

---

## Task 4: Shape Hierarchy with Polymorphism

### Architecture Overview

Task 4 demonstrates **Interface-based Polymorphism** with the `Shape` interface implemented by `Rectangle` and `Square` classes. This showcases the **Strategy Pattern** and **Polymorphic Behavior**.

### UML Class Diagram

```mermaid
classDiagram
    class Shape {
        <<interface>>
        +String getName()
        +String getType()
        +double getArea()
        +double getPerimeter()
        +boolean isRegular()
    }

    class Rectangle {
        -String name
        -double sideA
        -double sideB
        +Rectangle(String name, double sideA, double sideB)
        +String getName()
        +String getType()
        +double getArea()
        +double getPerimeter()
        +boolean isRegular()
        +double getSideA()
        +double getSideB()
        +boolean isSquare()
        +boolean equals(Object obj)
        +int hashCode()
        +String toString()
    }

    class Square {
        -String name
        -double side
        +Square(String name, double side)
        +String getName()
        +String getType()
        +double getArea()
        +double getPerimeter()
        +boolean isRegular()
        +double getSide()
        +boolean equals(Object obj)
        +int hashCode()
        +String toString()
    }

    class Main {
        -Scanner scanner$
        -Map~String, Shape~ shapes$
        +main(String[] args)$
        -displayMenu()$
        -addRectangle()$
        -addSquare()$
        -displayAllShapes()$
        -removeShape()$
        -calculateTotalArea()$
        -getDimensionsString(Shape shape)$
        -calculateTotalAreaValue()$
    }

    Shape <|.. Rectangle : implements
    Shape <|.. Square : implements
    Main --> Shape : uses polymorphically
    Main --> Rectangle : creates
    Main --> Square : creates
```

### Interface Implementation Diagram

```mermaid
flowchart TD
    A[Shape Interface] --> B[Common Contract]
    B --> C[getName]
    B --> D[getType]
    B --> E[getArea]
    B --> F[getPerimeter]
    B --> G[isRegular]

    A --> H[Rectangle Implementation]
    A --> I[Square Implementation]

    H --> J[Rectangle-specific Logic]
    I --> K[Square-specific Logic]

    J --> L[sideA × sideB area]
    J --> M[2 × sideA + sideB perimeter]
    J --> N[sideA == sideB regular check]

    K --> O[side × side area]
    K --> P[4 × side perimeter]
    K --> Q[always regular]
```

### Polymorphism in Action Diagram

```mermaid
sequenceDiagram
    participant Main
    participant ShapeMap as Map<String, Shape>
    participant Rectangle
    participant Square

    Main->>Rectangle: new Rectangle("A", 3, 4)
    Rectangle-->>Main: Rectangle instance
    Main->>ShapeMap: put("A", rectangle)

    Main->>Square: new Square("B", 5)
    Square-->>Main: Square instance
    Main->>ShapeMap: put("B", square)

    Main->>ShapeMap: get all shapes
    ShapeMap-->>Main: Collection<Shape>

    loop For each Shape
        Main->>Shape: getArea() [polymorphic call]
        alt Rectangle
            Shape-->>Main: sideA × sideB
        else Square
            Shape-->>Main: side × side
        end

        Main->>Shape: getType() [polymorphic call]
        alt Rectangle
            Shape-->>Main: "Rectangle"
        else Square
            Shape-->>Main: "Square"
        end
    end
```

### Design Patterns Used

- **Strategy Pattern**: Different area/perimeter calculation strategies
- **Template Method Pattern**: Common interface with specific implementations
- **Polymorphism**: Runtime method resolution
- **Factory Pattern**: Object creation in Main class

---

## Summary of Architectural Patterns

| Task       | Primary Patterns                        | Key Concepts                                 |
| ---------- | --------------------------------------- | -------------------------------------------- |
| **Task 1** | Value Object, Factory Method            | Immutability, Encapsulation                  |
| **Task 2** | Builder, Static Factory, State          | Multiple constructors, State transitions     |
| **Task 3** | Composition, Repository, Command        | Object composition, Collection management    |
| **Task 4** | Strategy, Polymorphism, Template Method | Interface-based design, Runtime polymorphism |
