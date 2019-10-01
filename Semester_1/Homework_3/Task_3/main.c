#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void swap(int *array_a, int *array_b)
{
    int temp = *array_a;
    *array_a = *array_b;
    *array_b = temp;
}

int main()
{
    int size = 0;
    printf("Enter the size of array:");
    scanf("%d", &size);

    int *arrayOfNumbers = malloc(sizeof(int) * size);
    for (int i = 0; i < size; i++)
    {
        arrayOfNumbers[i] = 0;
    }
    printf("Enter the array:");
    for (int i = 0; i < size; i++)
    {
        scanf("%d", &arrayOfNumbers[i]);
    }


    int i = 0;
    int j = size - 1;
    while(true)
    {
        while(arrayOfNumbers[i] == 0)
        {
            i++;
        }

        while(arrayOfNumbers[j] != 0)
        {
            j--;
        }

        if (i >= j)
        {
            break;
        }

        swap(&arrayOfNumbers[i++], &arrayOfNumbers[j--]);
    }

    for (int i = 0; i < size; i++)
    {
        printf("%d ", arrayOfNumbers[i]);
    }
}