#ifndef WEAPON_BEHAVIORS_H
#define WEAPON_BEHAVIORS_H


#include <iostream>
using namespace std;

class WeaponBehavior {
  public:
    virtual ~WeaponBehavior() = default;
    virtual void useWeapon() = 0;
};

class AxeBehavior : public WeaponBehavior {
  public:
    void useWeapon() {
      cout << "Chop your enemy" << endl;
    }
};

class KnifeBehavior : public WeaponBehavior {
  public:
    void useWeapon() {
      cout << "Cut your enemy" << endl;
    }
};

class SwordBehavior : public WeaponBehavior {
  public:
    void useWeapon() {
      cout << "Swing at your enemy" << endl;
    }
};

class BowAndArrowBehavior : public WeaponBehavior {
  public:
    void useWeapon() {
      cout << "Shoot your enemy" << endl;
    }
};

#endif