#ifndef QUEUE_H
#define QUEUE_H

#include <iostream>
#include <deque>
#include <list>
#include <stdexcept>

using namespace std;

// Abstract Base Class
template <typename T>
class QImpl {
public:
    virtual void add(const T& item) = 0;  // Add an item
    virtual T get() = 0;                 // Get the front item
    virtual void remove() = 0;           // Remove the front item
    virtual size_t size() = 0;           // Get the size of the queue
    virtual void clear() = 0;            // Clear the queue
    virtual ~QImpl() = default;          // Virtual destructor
};


template <typename T>
class MyDeque : public QImpl<T> {
private:
    deque<T> deque;

public:
    void add(const T& item) override {
        deque.push_back(item);
    }

    T get() override {
        if (deque.empty()) throw runtime_error("Queue is empty.");
        return deque.front();
    }

    void remove() override {
        if (deque.empty()) throw runtime_error("Queue is empty.");
        deque.pop_front();
    }

    size_t size() override {
        return deque.size();
    }

    void clear() override {
        deque.clear();
    }
};

// Implementation Using std::list
template <typename T>
class MyList : public QImpl<T> {
private:
    list<T> list;

public:
    void add(const T& item) override {
        list.push_back(item);
    }

    T get() override {
        if (list.empty()) throw runtime_error("Queue is empty.");
        return list.front();
    }

    void remove() override {
        if (list.empty()) throw runtime_error("Queue is empty.");
        list.pop_front();
    }

    size_t size() override {
        return list.size();
    }

    void clear() override {
        list.clear();
    }
};

// Generic Queue Class
template <typename T>
class Queue {
private:
    QImpl<T>* impl;

public:
    explicit Queue(QImpl<T>* implementation) : impl(implementation) {
        if (!implementation) throw invalid_argument("Implementation cannot be null.");
    }

    void add(const T& item) {
        impl->add(item);
    }

    T get() {
        return impl->get();
    }

    void remove() {
        impl->remove();
    }

    size_t size() const {
        return impl->size();
    }

    void clear() {
        impl->clear();
    }

    void changeImpl(QImpl<T>* newImpl) {
        newImpl->clear();
        while (impl->size() > 0) {
            newImpl->add(impl->get());
            impl->remove();
        }
        impl = newImpl;
    }


    ~Queue() = default;
};

// Display and Empty Queue
template <typename T>
void displayAndEmptyQueue(Queue<T>& q) {
    while (q.size() > 0) {
        cout << q.get() << " ";
        q.remove();
    }
    cout << endl;
}

#endif
