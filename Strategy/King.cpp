#include <iostream>
#include "character.h"
using namespace std;

class King : public Character {

  public:

    King() {
      cout << "I am the KING!!!" << endl;
    }

    void fight () {
      cout << "King, use your " + weapon.getName() << endl;
    }


};