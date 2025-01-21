#ifndef QUEUE_H
#define QUEUE_H

#include <iostream>
#include <deque>
#include <vector>
#include <memory>

template <typename T>
// Create a super class so subclasses can implement same functions in different ways

class QImpl {
  public:
    // Add
    virtual void add(const T& item) = 0;
    // Get
    virtual T get() = 0;
    // Remove
    virtual void remove() = 0;
    // Size
    virtual size_t size() = 0;
    // Clear
    virtual void clear() = 0;
    virtual ~QImpl() = default;
};



template <typename T>
class MyDeque: public QImpl {
  private:
    std::deque<T> deque;
    
  public:
    void add(const T& item) override {
      deque.push_back(item);
    }

    void push_back(const T& item) override {
      deque.push_back(item);
    }

    T get() override {
      return deque.front();
    }

    void remove() override {
      deque.pop_front();
    }

    size_t size() override {
      return deque.size();
    }

    void clear() override {
      deque.clear();
    }
};



template <typename T>
class MyList: public QImpl {
  private:
    std::list<T> list;
    
  public:
    void add(const T& item) override {
      list.push_back(item);
    }

    void push_back(const T& item) override {
      list.push_back(item);
    }

    T get() override {
      return list.front();
    }

    void remove() override {
      list.pop_front();
    }

    size_t size() override {
      return list.size();
    }

    void clear() override {
      list.clear();
    }
};


template <typename T>
class Queue {
  private:
    QImpl<T>* impl;

    // Disable copy and assignment
    // Queue(const Queue&) = delete;
    // Queue& operator=(const Queue&) = delete;

    public:
      explicit Queue(QImpl<T>* implementation) : impl(implementation){}

      void add(const T& item){
        impl->add(item);
      }

      T get() {
        return impl->get();
      }

      void remove() {
        impl->remove();
      }

      size_t size() {
        return impl->size();
      }

      void clear() {
        impl->clear();
      }

      ~Queue() = default;
};

#endif