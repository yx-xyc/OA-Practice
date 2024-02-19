# import re

# sentence = "horses are fast"
# regex = re.compile("(?P<animal>[a-z]+) (?P<verb>[a-z]+) (?P<adjective>[a-z]+)")
# matched = re.search(regex, sentence)

# print(matched.groupdict())
class BaseClass1:
    def func1(self):
        print("BaseClass1.func1()")
 
    def func2(self):
        print("BaseClass1.func2()")
 
class BaseClass2:
    def func1(self):
        print("BaseClass2.func1()")
 
    def func2(self):
        print("BaseClass2.func2()")
 
class SubclassName(BaseClass1, BaseClass2):
    def func1(self):
        print("SubclassName.func1()")

sub = SubclassName()
sub.func2()
print(6%4)

a, b = (1, 2, 3, 4, 5)[1::3]

print(a)
print(b)

pairs = {'div': 3, 'num': 6}

# def division(div, num): 
#     if div != 0: 
#         return num/div
# print(division(pairs))
print(1//3)