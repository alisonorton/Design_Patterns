#include <string>
using namespace std;

class WeaponBehavior {
  private:
    string name;

  public:
    WeaponBehavior() {
      name = "";
    }

    string getName() {
      return name;
    }

    void useWeapon() {};
};