#include <iostream>
#include "queue.h"
using namespace std;


int main() {
// Test ints
MyDeque<int> deq;
Queue<int> q(&deq);
q.add(91);
q.add(92);
// Swap implementation
MyList<int> lst;
lst.add(93);
q.changeImpl(&lst); // clear list/deque first
q.add(94);
q.add(95);
displayAndEmptyQueue(q);
// Test strings
MyDeque<string> deq2;
deq2.add ("Discard me"); // Contents will be discarded
Queue<string> q2(&deq2);
q2.add("91");
q2.add("92");
// Swap implementation
MyList<string> lst2;
lst2.add("93");
q2.changeImpl(&lst2);
q2.add("94");
q2.add("95");
displayAndEmptyQueue(q2);
}