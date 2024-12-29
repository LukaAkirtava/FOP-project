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

🧩 Interpreter.java – Updated with Attributes and Constructor We’ve recently updated our Interpreter class to store the tokens and keep track of the current parsing position. In addition, we introduced a map to hold variables in memory. These changes allow the interpreter to manage program state more effectively and prepare it for upcoming language features like variable assignment and control flow.

📋 Key Changes Tokens List: A List to hold all tokens for the program. Current Index: An integer current that keeps track of where we are in the token list. Variables Map: A Map<String, Integer> that stores variable names and their values. Constructor: Accepts a list of tokens and initializes the interpreter’s state. 🛠️ Purpose These new attributes and the constructor help us structure the interpreter more clearly, paving the way for the parsing logic that will support variable assignments, expressions, conditionals, and loops. They form the core of the interpreter’s state management, allowing each token to be processed in sequence while maintaining information about declared variables.
