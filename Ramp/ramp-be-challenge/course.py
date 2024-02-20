class Course:
    def __init__(self, courseId: str, name: str, credits: int):
        self.courseId = courseId
        self.name = name
        self.credits = credits
    def __str__(self):
        return f"Course ID: {self.courseId} \
            Name: {self.name} \
            Credits: {self.credits}"