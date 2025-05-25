# CLI Quiz Application

## Overview
This CLI-based Quiz Application helps users practice programming concepts across multiple topics with flexible quiz formats. Users can select their preferred topic, difficulty level, and quiz format to challenge themselves with 5 timed questions per session.

## Features
- **Topics:** Java, Python, C++, HTML, SQL
- **Difficulty Levels:** Beginner and Advanced
- **Quiz Formats:** 
  - Direct answer (type the answer)
  - Multiple Choice Questions (MCQ) with 4 options
- **Timer:** 15 seconds per question to answer
- **Immediate feedback:** Error handling for missing questions or files

## How to Use
1. Run the program.
2. Enter the topic (Java, Python, C++, HTML, SQL).
3. Enter the difficulty level (Beginner, Advanced).
4. Select the quiz format (Direct or MCQ).
5. Answer 5 questions within the time limit.
6. View your score at the end.

## File Format for Questions
Questions are stored in a structured text file `questions.txt` with the following format:

[Topic: Java][Mode: Beginner][Format: Direct]
Q: What is JVM?
A: Java Virtual Machine
...

[Topic: Python][Mode: Advanced][Format: MCQ]
Q: Lambda functions are?

Named functions

Anonymous functions

Loops

Classes
A: 2

## How to Run
1. Compile the Java program:
javac QuizApp.java

2. Run the program:

## Customization
- Modify `questions.txt` to add or update quiz questions.
- Adjust the timer in the source code if needed.
- Extend topics, difficulty levels, or formats by following the question file pattern.

## Requirements
- Java JDK 8 or above
- Command line interface

## Author
Kavita Sharma  
Geetanjali Institute of Technical Studies  
MCA 2nd Semester, 2025

## License
This project is open source and free to use for educational purposes.

