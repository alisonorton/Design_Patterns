#ifndef QUEUE_H
#define QUEUE_H

#include <iostream>
#include <deque>
#include <list>
#include <stdexcept>

using namespace std;



template <typename T>
// Create a super class so subclasses can implement same functions in different ways

class QImpl {
  public:
    virtual void add(const T& item) = 0;
    virtual T get() = 0;
    virtual void remove() = 0;
    virtual size_t size() = 0;
    virtual void clear() = 0;
    virtual ~QImpl() noexcept = default;
};



template <typename T>
class MyDeque: public QImpl<T> {
  private:
    std::deque<T> deque;
    
  public:
    void add(const T& item) {
      deque.push_back(item);
    }

    void push_back(const T& item) {
      deque.push_back(item);
    }

    T get() {
      return deque.front();
    }

    void remove() {
      deque.pop_front();
    }

    size_t size() {
      return deque.size();
    }

    void clear() {
      deque.clear();
    }

};



template <typename T>
class MyList: public QImpl<T> {
  private:
    std::list<T> list;
    
  public:
    void add(const T& item) {
      list.push_back(item);
    }

    void push_back(const T& item) {
      list.push_back(item);
    }

    T get() {
      return list.front();
    }

    void remove() {
      list.pop_front();
    }

    size_t size() {
      return list.size();
    }

    void clear() {
      list.clear();
    }
};


template <typename T>
class Queue {
  private:
    QImpl<T>* impl;

    public:
      Queue(QImpl<T>* implementation) : impl(implementation){}

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

      void changeImpl(QImpl<T>* newImpl) {
        while (impl->size() > 0) {
          newImpl->add(impl->get());
          impl->remove();
        }
        impl = newImpl;
      }

      virtual ~Queue() = default;
};

template <typename T>
    void displayAndEmptyQueue(Queue<T>& q) {
      for(int i = 0; i < q.size(); i++){
        cout << q.get() << endl;
      }

      q.clear();
    }

#endif