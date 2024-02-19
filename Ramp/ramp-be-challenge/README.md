## University Course Registration System

### 4 Levels

1. Support basic operations to create a course in teh system and register a student for the course.
2. Support retrieving pairs of students who take at least one course together.
3. Support different types of courses, grading students and computing student Grade Point Averages.
4. Support retrieving the best student nominees for all university departments. 

### Level 1

- `CREATE_COURSE <courseId> <name> <credits>` 
  - A courseID consists of 5 symbols first 3 for department, the last three forming a number.
  - Return false for existed name or courseId
  - Otherwise, return true
- `REGISTER_FOR_COURSE <studentId> <courseId>`
  - A student cannot register for the same course twice.
  - A student cannot register for more than 24 credits. 
  - true for success registration, otherwise false.