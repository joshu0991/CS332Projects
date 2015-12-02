#include "copy_of.hpp"
#include <iostream>
#include <vector>

int main()
{
    int a[5] = {0, 1, 1, 2, 3}; 
    int* newArray = copy_of::copyOf<int>(a, 10, 5); 
    std::cout << "Old array is: " << std::endl;
    for (int i = 0; i < 5; ++i)
    {
        std::cout << a[i] << std::endl;
    }

    std::vector<int> v;
    v.push_back(1);
    v.push_back(2);
    v.push_back(3);
    // This causes a seg fault but compiles a-ok w/o warnings!
    //std::vector<int>* q = copy_of::copyOf<std::vector<int> >(&v, 3, 6); 

    std::cout << "New array is: " << std::endl;
    for (int i = 0; i < 10; ++i)
    {
        std::cout << newArray[i] << std::endl;
    }
    delete[] newArray;
    return 0;
}
