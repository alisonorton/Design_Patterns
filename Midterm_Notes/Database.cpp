#include <iostream>
#include <string>
using namespace std;

// Base interface for a Person
class PersonInterface {
public:
    virtual string name() = 0;
    virtual string wearing() = 0;
    virtual ~PersonInterface() {}
};

// Concrete Person implementation
class Person : public PersonInterface {
    string myname;
public:
    Person(const string& n) : myname(n) {}
    string name() { return myname; }
    string wearing() { return ""; }
};

class PersonDecorator : public PersonInterface {
    PersonInterface* decoratedPerson;
    string clothing;
public:
    PersonDecorator(PersonInterface* p, const string& cloth)
      : decoratedPerson(p), clothing(cloth) {}

    string name() {
        return decoratedPerson->name();
    }
    
    string wearing() {
        string wear = decoratedPerson->wearing();
        if (!wear.empty()) {
            wear += " ";
        }
        return wear + clothing;
    }
};

int main () {
    // Create the base person
    PersonInterface* p = new Person("Herman");
    // Decorate the person by wrapping with PersonDecorator instances
    p = new PersonDecorator(p, "underwear");
    p = new PersonDecorator(p, "pants");
    p = new PersonDecorator(p, "shirt");

    // Print out the result
    cout << p->name() << " is wearing: " << p->wearing() << endl;

    // Note: In a production system, proper memory management (like smart pointers) should be used.
    return 0;
}



