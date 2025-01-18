#ifndef CHARACTERS_H
#define CHARACTERS_H


#include <iostream>
#include "WeaponBehaviors.h"
using namespace std;

class Character {
  protected:
    WeaponBehavior* weapon;

  public:
    virtual ~Character() = default;
    Character() : weapon(nullptr){}

    virtual void fight() = 0;

    void setWeaponBehavior(WeaponBehavior* weapon) {
      this->weapon = weapon;
    }
};

class King : public Character {
  public:
    King() {
      cout << "Creating king..." << endl;
    }

    void fight () {
      cout << "King attack..." << endl;
      weapon->useWeapon();
    }

};

class Queen : public Character {
  public:
    Queen() {
      cout << "Creating Queen..." << endl;
    }

    void fight () {
      cout << "Queen attack..." << endl;
      weapon->useWeapon();
    }

};

class Knight : public Character {
  public:
    Knight() {
      cout << "Creating knight..." << endl;
    }

    void fight () {
      cout << "Knight attack..." << endl;
      weapon->useWeapon();
    }

};

class Troll : public Character {
  public:
    Troll() {
      cout << "Creating troll..." << endl;
    }

    void fight () {
      cout << "Troll attack..." << endl;
      weapon->useWeapon();
    }

};

#endif