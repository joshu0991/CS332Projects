#include <iostream>
#include <cstring>

namespace copy_of
{

    class Exception : public std::exception
    {
        virtual const char* what() const throw()
        {
            return "Null pointer exception ";
        }
    }nPe;


    /*!
     *  \pre elements must have a copy constructor if they aren't POD
     *  \pre new size must be greater than zero
     *  \pre user must delete array
     *
     *  \brief Function to copy an array to a new array of a given size
     *  
     *  \param[in] p_array the old array to copy over
     *  \param[in] p_newSize the new size of the return array
     *  \param[in] p_oldSize the old arrays size
     *
     *  \return a new array of the given size with the old elements copied over
     *
     *  \throws an exception called null pointer exception (custom exception) when the given array is null
     *
     *  \note Size of the array being greater than 0 a warning will be issued  at compile time because the input is unsigned otherwise.
     *
     */
    template<typename T> static T* copyOf(const T* p_array, unsigned int p_newSize, unsigned int p_oldSize)
    {
        if (p_array == NULL)
            throw nPe;
 
        T* newArray = new T[p_newSize];
        // This memset doesn't need to happen it basically happens when the memory is newed
        // but valgrind will be upset unless we do it explicitly.
        std::memset(newArray, 0, sizeof(T)*p_newSize);
        std::copy(p_array, p_array + p_oldSize, newArray);
        return newArray;
    }
}
