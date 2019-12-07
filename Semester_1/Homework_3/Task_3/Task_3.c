#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void swap(int *array_a, int *array_b)
{
    int temp = *array_a;
    *array_a = *array_b;
    *array_b = temp;
}

void cleanStdin()
{
    int c;
    do
    {
        c = getchar();
    }
    while (c != EOF && c != '\n');
}

bool checkSize(int size, int isCorrect)
{
    if (isCorrect != 1 || size < 1)
    {
        return false;
    }
    return true;
}

void getSize(int* size)
{
    int isCorrect = scanf("%d", size);
    while (checkSize(*size, isCorrect) == false)
    {
        cleanStdin();
        printf("Enter the correct size of array:\n");
        isCorrect = scanf("%d", size);
    }
    cleanStdin();
}

void getArray(int size, int* array)
{
    for (int i = 0; i < size; i++)
    {
        int isCorrect = scanf("%d", &array[i]);
        if (isCorrect != 1)
        {
            printf("Input Error. Re-enter the array:\n");
            cleanStdin();
            i = -1;
            continue;
        }
    }
}

int main()
{
    int size = 0;
    printf("Enter the size of array (number > 0):\n");
    getSize(&size);

    int *arrayOfNumbers = calloc(sizeof(int), size);
    printf("Enter the array (the element must be a number):\n");
    getArray(size, arrayOfNumbers);

    int i = 0;
    int j = size - 1;
    while (i < j)
    {
        while (i < size && arrayOfNumbers[i] == 0)
        {
            i++;
        }

        while (j >= 0 && arrayOfNumbers[j] != 0)
        {
            j--;
        }

        if (i < j)
        {
            swap(&arrayOfNumbers[i++], &arrayOfNumbers[j--]);
        }
    }

    printf("Desired array:\n");
    for (int k = 0; k < size; k++)
    {
        printf("%d ", arrayOfNumbers[k]);
    }

    free(arrayOfNumbers);
    return 0;
}
