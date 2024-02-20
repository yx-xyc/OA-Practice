from typing import Dict
from course import Course
from student import Student

courses: Dict[str, Course] = {}
students: Dict[str, Student] = {}

def create_course(courseId: str, name: str, credits: int):
    if courseId not in courses:
        new_course = Course(courseId, name, credits)  # Correct class name
        courses[courseId] = new_course  # Correct dict name
    else:
        print(f"Course with ID {courseId} already exists.")

def create_student(studentId: str):
    if studentId not in students:
        new_student = Student(studentId)  # Correct class name
        students[studentId] = new_student
    else:
        print(f"Student with ID {studentId} already exists.")

def register_for_course(studentId: str, courseId: str):
    if studentId in students and courseId in courses:
        students[studentId].register_course(courseId)
    else:
        print(f"Error registering {studentId} for {courseId}")

def get_paired_students():
    students_list = list(students.values())
    paired_students = set()
    for idx, student in enumerate(students_list):
        for another_student in students_list[idx+1:]:
            for course in student.courses:
                if course in another_student.courses:
                    paired_students.add((student.studentId, another_student.studentId))
    return sorted(list(paired_students))

def parser(input_args):
    command, *args = input_args
    if command == "CREATE_COURSE":
        # Ensure there are enough arguments for course creation
        if len(args) >= 3:
            create_course(*args)  # Unpack args correctly
        else:
            print("CREATE_COURSE command requires three arguments: courseId, name, credits.")
    elif command == "REGISTER_FOR_COURSE":
        # Ensure there are enough arguments for course registration
        if len(args) == 2:
            register_for_course(*args)  # Correct function name and unpack args
        else:
            print("REGISTER_FOR_COURSE command requires two arguments: studentId, courseId.")
    elif command == "GET_PAIRD_STUDENTS":
        paired_students = get_paired_students()
        print(paired_students)
    else:
        print("Cannot recognize command")
    

def test_level1():
    # Ensure creation of students first to prevent error during registration
    create_student("st001")
    create_student("st002")
    create_student("st003")
    queries = [
        ["CREATE_COURSE", "CSE220", "System Programming", "3"],
        ["CREATE_COURSE", "CSE221", "Data Structures", "3"],
        ["CREATE_COURSE", "CSE220", "Data Structures", "3"],
        ["CREATE_COURSE", "CSE222", "Computer Architecture", "4"],
        ["CREATE_COURSE", "CSE300", "Introduction to Algorithms", "4"],
        ["REGISTER_FOR_COURSE", "st001", "CSE220"],
        ["REGISTER_FOR_COURSE", "st002", "CSE221"],
        ["GET_PAIRD_STUDENTS"],
        ["REGISTER_FOR_COURSE", "st001", "CSE300"],
        ["REGISTER_FOR_COURSE", "st003", "CSE300"],  # st003 not created, should cause an error message
        ["GET_PAIRD_STUDENTS"]
    ]
    
    for query in queries:
        parser(query)

    for student in students:
        print(students[student])
    for course in courses:
        print(courses[course])
test_level1()