class Student:
    def __init__(self, studentId: str):
        self.studentId = studentId
        self.courses = set()  # Stores course IDs

    def __str__(self):
        courses_str = ', '.join(courseId for courseId in self.courses)
        return f"Student ID: {self.studentId} and attending {courses_str}"

    def register_course(self, courseId: str):
        self.courses.add(courseId)
