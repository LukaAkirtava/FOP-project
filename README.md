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
✨ **More files coming soon to support the full interpreter!**


✨ **Stay tuned for exciting updates as we build this project together!**  
