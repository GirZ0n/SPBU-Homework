#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

const int sizeOfInt = 4 * 8;

int* createArray(int size)
{
    int* array = malloc(sizeOfInt * size);
    for (int i = 0; i < size; i++)
    {
        array[i] = 0;
    }

    return array;
}

int* getBinaryForm(int number)
{
    int* binaryForm = createArray(sizeOfInt);
    for (int i = 0; i < sizeOfInt; i ++)
    {
        binaryForm[i] = number >> i & 1;
    }

    return binaryForm;
}

void printBinaryForm(int* binaryForm)
{
    for (int i = sizeOfInt - 1; i >= 0; i--)
    {
        printf("%d", binaryForm[i]);
        if (i % 8 == 0)
        {
            printf(" ");
        }
    }
    printf("\n");
}

int* getSumInBinaryForm(const int* binaryFormOfNumberA, const int* binaryFormOfNumberB)
{
    int* sumInBinaryForm = createArray(sizeOfInt);

    int carry = 0;
    int currentSum = 0;
    for (int i = 0; i < sizeOfInt; i++)
    {
        currentSum = binaryFormOfNumberA[i] + binaryFormOfNumberB[i] + carry;
        sumInBinaryForm[i] = currentSum % 2;
        carry = currentSum / 2;
    }

    return sumInBinaryForm;
}

int* getPowersOfTwo(int maxPower)
{
    int* powersOfTwo = createArray(maxPower + 1);
    powersOfTwo[0] = 1;
    for (int i = 1; i < maxPower + 1; i++)
    {
        powersOfTwo[i] = powersOfTwo[i - 1] * 2;
    }

    return powersOfTwo;
}

int getNumberFromBinaryForm(const int* binaryForm)
{
    int* powersOfTwo = getPowersOfTwo(sizeOfInt - 1);
    int number = 0;
    if (binaryForm[sizeOfInt - 1] == 0)
    {
        for (int i = sizeOfInt - 1; i >= 0; i--)
        {
            number += binaryForm[i] * powersOfTwo[i];
        }
    }
    else
    {
        for (int i = sizeOfInt - 1; i >= 0; i--)
        {
            number += !binaryForm[i] * powersOfTwo[i];
        }
        number++;
        number *= -1;
    }

    free(powersOfTwo);
    return number;
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

void getNumber(int* number)
{
    bool isCorrect = scanf("%d", number);
    while (isCorrect == false)
    {
        cleanStdin();
        printf("Input Error. Enter the correct number: ");
        isCorrect = scanf("%d", number);
    }
    cleanStdin();
}

int main()
{
    int numberA = 0;
    printf("Enter the number #1: ");
    getNumber(&numberA);

    int numberB = 0;
    printf("Enter the number #2: ");
    getNumber(&numberB);

    int* binaryFormOfNumberA = getBinaryForm(numberA);
    int* binaryFormOfNumberB = getBinaryForm(numberB);

    printf("Binary form of number #1: ");
    printBinaryForm(binaryFormOfNumberA);
    printf("Binary form of number #2: ");
    printBinaryForm(binaryFormOfNumberB);

    int* sum = getSumInBinaryForm(binaryFormOfNumberA, binaryFormOfNumberB);
    printf("%6cBinary form of sum: ", ' ');
    printBinaryForm(sum);
    printf("%5cDecimal form of sum: %d", ' ', getNumberFromBinaryForm(sum));

    free(binaryFormOfNumberA);
    free(binaryFormOfNumberB);
    free(sum);
    return 0;
}
