# 🚀 Fundamentals of Programming Project

Welcome to our **Fundamentals of Programming** project!  
In this capstone project, we will design and implement a simple interpreter 
for a Python. The project will focus on creating and supporting 
a minimal subset of the language, with an emphasis on basic constructs like 
variables, arithmetic, conditionals, and iterative control flow. The interpreter 
will be capable of executing simple algorithms, serving as a proof of its functionality.

---

## 👥 Team Members

| Name                        | Role            | GitHub Profile                                   |
|-----------------------------|-----------------|--------------------------------------------------|
| **Luka Akirtava**           | Project Leader  | [LukaAkirtava](https://github.com/LukaAkirtava)  |
| **Luka Khurtsidze**         | Developer       | [lukka01](https://github.com/lukka01)            |
| **Davit Meshvelashvili**    | Developer       | [Ak1ra777](https://github.com/Ak1ra777)          |
| **Levan Lekvinadze**        | Developer       | [levani2405](https://github.com/levani2405)      |


# How to Run and Test the Python Interpreter

This guide explains how to run, test, and modify the Python interpreter implemented in Java.

---

## Prerequisites

Before running the interpreter, ensure you have the following:

1. *Java Development Kit (JDK):*  
   Install JDK 8 or later. You can download it from:  
   - [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)  
   - [OpenJDK](https://openjdk.org/)

2. *Command-Line or IDE:*  
   Use a command-line tool like cmd`/bash` or an IDE like IntelliJ IDEA, Eclipse, or VS Code to compile and execute the Java code.

---

## Steps to Run the Interpreter

Follow these steps to run the interpreter and execute Python-like code snippets:

### Step 1: Clone the Repository
Download the project from GitHub to your local machine:  
git clone https://github.com/LukaAkirtava/FOP-project.git  
cd `<your-repository-name>`  
### Step 2: Run the Main class
after you got all the necessary files by cloning. In your InteliJ/Visual studio or any other java compiler run the `Main` class

---

## 🛠️ Project Goals
- Build an interpreter in Java for Python-like syntax.  
- Implement essential features:  
	- Variable assignment.  
	- Arithmetic operations (+, -, *, /, %).  
	- Conditional statements (if, else).  
	- Iterative control flow (while loops).  
	- Basic input and output.
- Ensure the interpreter is simple, correct, and detects syntax errors.  

---

### 🚚 Deliverables
1. **Language Subset Specification**:  
   A concise document defining the subset syntax and features.
   
2. **Interpreter Implementation**:  
   A working interpreter capable of parsing and executing the subset.
   
3. **Test Algorithms**:  
   Code examples for simple algorithms executed successfully by the interpreter.
   
4. **Documentation**:  
   A user guide describing how to use the interpreter, including sample programs.

---

# 🧩 TokenType.java – Token Definitions for Interpreter

This file defines the core `TokenType` enum, representing the various tokens used by the interpreter during lexical analysis. Tokens are the smallest building blocks of source code, such as operators, keywords, and literals.

## 📋 Token Categories
- **Operators** – `PLUS`, `MINUS`, `STAR`, `SLASH`, `MOD`
- **Delimiters** – `LPAREN`, `RPAREN`, `LBRACE`, `RBRACE`, `EQ`, `COLON`
- **Comparisons** – `GT`, `GTE`, `LT`, `LTE`, `EQEQ`, `NEQ`
- **Literals** – `IDENT`, `NUMBER`
- **Keywords** – `IF`, `ELSE`, `WHILE`, `PRINT`
- **Utility** – `EOF` (End of File marker)

## 🛠️ Purpose
The `TokenType` enum categorizes input from the source code, enabling the interpreter to classify and process each component accurately. This is a crucial step in parsing and executing code.

---

# 🧩 Token.java – Token Representation for Interpreter

The `Token` class represents a single token in the source code, consisting of a type and the text it corresponds to. Tokens are used by the lexer to categorize input during lexical analysis.

## 📋 Key Components
- **type**: The type of the token (e.g., `IDENT`, `NUMBER`).
- **text**: The actual text of the token as it appears in the source code.

## 🛠️ Purpose
The `Token` class holds the information for each token, enabling easy identification and manipulation during the lexing and parsing phases of the interpreter.

---

# 🧩 Lexer.java – Tokenizer for Interpreter

The `Lexer` class is responsible for converting a source string into a list of tokens. It scans the input character by character, grouping them into meaningful tokens (numbers, identifiers, operators, etc.), which are later processed by the interpreter.

## 📋 Key Functions
- **tokenize()**: Converts the input source code into a list of tokens by analyzing each character and categorizing it as an operator, number, identifier, or delimiter.
- **advance()**: Moves the current index forward and returns the current character.
- **addToken()**: Creates and adds a new token to the list of tokens.
- **match()**: Checks if the next character matches an expected one, consuming it if true.
- **isAlpha() / isDigit()**: Helper methods to check whether a character is alphabetic or a digit, respectively.
- **number()**: Handles parsing number tokens and continues reading digits until the number is complete.
- **identifier()**: Handles parsing identifiers or keywords, continuing to read characters until a full identifier is formed.
- **checkKeyword()**: Checks if an identifier corresponds to a keyword (like `if`, `while`, `print`).

## 🛠️ Purpose
The `Lexer` breaks down the source code into manageable tokens, categorizing the smallest units of the program so that they can be parsed and executed by the interpreter. This is a critical step in the compilation or interpretation process.

---

# 🧩 Interpreter.java – Updated with Attributes and Constructor

We’ve recently updated our `Interpreter` class to store the tokens and keep track of the current parsing position. In addition, we introduced a map to hold variables in memory. These changes allow the interpreter to manage program state more effectively and prepare it for upcoming language features like variable assignment and control flow.

---

## 🧩 Interpreter.java – Core Execution Engine

The `Interpreter` class parses and executes tokenized source code, supporting variable assignments, arithmetic operations, conditionals, loops, and print statements.

### 📋 Components
- **Tokens** – List of tokens representing the program.
- **Variables** – Stored in a `HashMap` with names and values.
- **Control Flow** – Supports `if` and `while` blocks.

### 🛠️ Purpose
Processes tokenized input to execute programs by evaluating expressions and managing flow control.

---

## 🧩 Key Features
- **Assignment** – `x = expr` stores values.
- **Print** – Outputs results with `print(expr)`.
- **Conditionals** – Executes `{}` blocks after `if`.
- **Loops** – Re-evaluates conditions for `while`.
- **Arithmetic** – `+`, `-`, `*`, `/`, `%` with precedence.
- **Comparisons** – `==`, `!=`, `<`, `>`, `<=`, `>=`.

---

## 🚦 Execution Flow
- **Blocks** – Executes or skips based on conditions.
- **Looping** – Resets pointer for re-evaluation.
- **Error Handling** – Division by zero and syntax checks (`=`, `:`).

---

## 📊 Efficiency and Readability
- **Modular** – Separated parsing and expression evaluation.
- **Extendable** – Easy to add new statements or expressions.

---

## 🚧 Improvements
- ... we will add things here later

---

### 📋 Key Changes
- **Tokens List**: A List to hold all tokens for the program.
- **Current Index**: An integer `current` that keeps track of where we are in the token list.
- **Variables Map**: A `Map<String, Integer>` that stores variable names and their values.
- **Constructor**: Accepts a list of tokens and initializes the interpreter’s state.

### 🛠️ Purpose
These new attributes and the constructor help us structure the interpreter more clearly, paving the way for the parsing logic that will support variable assignments, expressions, conditionals, and loops. They form the core of the interpreter’s state management, allowing each token to be processed in sequence while maintaining information about declared variables.



# 🚀 Main.java – Entry Point for Interpreter Testing

The Main class serves as the testing ground for the Python-like interpreter. It compiles various code snippets, feeds them into the lexer and interpreter, and observes the results, facilitating the validation of interpreter functionalities.

## 📋 Core Responsibilities
- *Code Snippet Testing* – Provides a suite of sample programs to evaluate the interpreter.
- *Lexer Integration* – Tokenizes input using the Lexer class.
- *Interpreter Execution* – Processes tokens and executes code via the Interpreter class.
- *Console Output* – Displays the source code, tokens, and results of interpretation.

## 🛠️ Key Components
- *runSnippet Method* –
  - Accepts a code snippet and its title.
  - Prints the source code.
  - Tokenizes and interprets the input.
  - Displays the output for verification.
- *Test Cases* –
  1. *Basic Arithmetic* – Simple addition and multiplication.
  2. *Sum of N* – Iterative summation of numbers up to N.
  3. *Factorial Calculation* – Computes factorial using a while loop.
  4. *GCD Calculation* – Euclidean algorithm for GCD.
  5. *Reverse Number* – Digit-by-digit reversal of an integer.
  6. *Prime Check* – Determines if a number is prime.
  7. *Palindrome Check* – Verifies if a number is palindromic.
  8. *Largest Digit* – Finds the highest digit in a number.
  9. *Sum of Digits* – Computes the sum of digits in a number.
  10. *Multiplication Table* – Prints multiplication results for a given integer.
  11. *Nth Fibonacci* – Iteratively calculates the Nth Fibonacci number.

## 🎯 Purpose
The Main class ensures comprehensive testing of the interpreter's capabilities, covering arithmetic, control flow, and algorithmic operations. It serves as the backbone for identifying bugs and refining interpreter logic.

---

