#include <iostream>
#include <cmath>

using namespace std;

class Rectangle {
    double width;
    double height;
    public:
        Rectangle(double width, double height) {
            this->width = width;
            this->height = height;
        }
        double area() {
            return ceil(this->width*this->height);
        }
};

class Square {
    double width;
    public:
        Square(double width) {
            this->width = width;
        }
        double area() {
            return ceil(this->width*this->width);
        }
};

class Circle {
    double radius;
    public:
        Circle(double radius) {
            this->radius = radius;
        }
        double area() {
            return ceil(M_PI*this->radius*this->radius);
        }
};

int main() {
    Rectangle rct(3.0, 4.0);
    cout << rct.area() << endl;
    Square sq(3.3);
    cout << sq.area() << endl;
    Circle c(5.0);
    cout << c.area() << endl;
    Circle c2(2.0);
    cout << c2.area() << endl;
    Rectangle rct2(5.0, 7.5);
    cout << rct2.area() << endl;
}