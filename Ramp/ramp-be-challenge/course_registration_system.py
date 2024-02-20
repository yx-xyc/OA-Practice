from typing import Dict

class Student:
    def __init__(self, studentId: str):
        self.studentId = studentId
        self.courses = set()  # Stores course IDs
    
    def register_course(self, courseId: str):
        self.courses.add(courseId)

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
    else:
        print("Cannot recognize command")
    

def test_level1():
    # Ensure creation of students first to prevent error during registration
    create_student("st001")
    create_student("st002")

    queries = [
        ["CREATE_COURSE", "CSE220", "System Programming", "3"],
        ["CREATE_COURSE", "CSE221", "Data Structures", "3"],
        ["CREATE_COURSE", "CSE220", "Data Structures", "3"],
        ["CREATE_COURSE", "CSE222", "Computer Architecture", "4"],
        ["CREATE_COURSE", "CSE300", "Introduction to Algorithms", "4"],
        ["REGISTER_FOR_COURSE", "st001", "CSE220"],
        ["REGISTER_FOR_COURSE", "st002", "CSE221"],
        ["REGISTER_FOR_COURSE", "st001", "CSE300"],
        ["REGISTER_FOR_COURSE", "st003", "CSE330"],  # st003 not created, should cause an error message
    ]
    
    for query in queries:
        parser(query)

    print(courses)
    print(students)
test_level1()