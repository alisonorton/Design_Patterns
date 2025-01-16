#include "WeaponBehavior.h"

class Character {
  public:
    WeaponBehavior weapon;

    void fight(){};
    void setWeaponBehavior(WeaponBehavior weapon) {
      this->weapon = weapon;
    }
};