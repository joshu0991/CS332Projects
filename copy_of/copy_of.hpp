#include <iostream>
#include <cstring>

namespace copy_of
{

    class Exception : public std::exception
    {
        virtual const char* what()const throw()
        {
            return "Null pointer exception ";
        }
    }nPe;


    /*!
     *  \brief Function to copy an array to a new array of a given size
     *  \param[in] p_array the old array to copy over
     *  \param[in] p_newSize the new size of the return array
     *  \param[in] p_oldSize the old arrays size
     *
     *  \return a new array of the given size with the old elements copied over
     *
     *  \throws an exception called null pointer exception (custom exception) when the given array is null
     */
    template<typename T> static T* copyOf(const T* p_array, unsigned int p_newSize, unsigned int p_oldSize)
    {
        if (p_array == NULL)
            throw nPe;
 
        T* newArray = new T[p_newSize];
        std::copy(p_array, p_array + p_oldSize, newArray);
        return newArray;
    }
}
