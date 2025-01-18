#include <iostream>
#include "Characters.h"
#include "WeaponBehaviors.h"
using namespace std;

int main() {
  cout << "Welcome to Adventure Game!" << endl << endl;
  // TODO: 
  // create each character
  King king;
  SwordBehavior sword;
  king.setWeaponBehavior(&sword);
  king.fight();
  cout << endl;

  Queen queen;
  KnifeBehavior knife;
  queen.setWeaponBehavior(&knife);
  queen.fight();
  cout << endl;

  Knight knight;
  BowAndArrowBehavior bowArrow;
  knight.setWeaponBehavior(&bowArrow);
  knight.fight();
  cout << endl;

  Troll troll;
  AxeBehavior axe;
  troll.setWeaponBehavior(&axe);
  troll.fight();
  cout << endl;
  // set each character to use a different weapon
  return 0;
}