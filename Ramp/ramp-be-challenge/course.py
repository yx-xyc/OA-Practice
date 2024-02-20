from enum import Enum

class GradingType(Enum):
    NA = 0
    STD = 1
    PF = 2

class Course:
    def __init__(self, courseId: str, name: str, credits: int, gradingType: GradingType = GradingType.NA):
        self.courseId = courseId
        self.name = name
        self.credits = credits
        self.gradingType = gradingType
    def __str__(self):
        return f"Course ID: {self.courseId} \
            Name: {self.name} \
            Credits: {self.credits}"