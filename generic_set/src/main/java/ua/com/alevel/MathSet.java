package ua.com.alevel;

import java.util.Objects;

public class MathSet<Numbers extends Number & Comparable<Numbers>> {

    private Numbers[] num;
    private int size;
    private int capacity = 10;

    public MathSet() {
        num = (Numbers[]) new Number[capacity];
        size = 0;
    }

    public MathSet(int capacity) {
        num = (Numbers[]) new Number[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public MathSet(Numbers[] numbers) {
        num = deleteDuplicate(numbers);
        size = numbers.length;
    }

    public MathSet(Numbers[]... numbers) {
        num = (Numbers[]) new Number[capacity];
        for (Numbers[] number : numbers) {
            add(number);
        }
    }

    public MathSet(MathSet numbers) {
        capacity = numbers.capacity;
        num = (Numbers[]) new Number[capacity];
        for (int i = 0; i < numbers.size; i++) {
            add((Numbers) numbers.num[i]);
        }
        size = numbers.size;
    }

    public MathSet(MathSet... numbers) {
        num = (Numbers[]) new Number[capacity];
        for (MathSet<Numbers> number : numbers) {
            for (int i = 0; i < number.size; i++) {
                add(number.num[i]);
            }
        }
    }

    public void add(Numbers n) {
        if (findElement(n) >= 0) {
            return;
        }
        if (size == capacity) {
            expansionCapacity();
        }
        num[size++] = n;
    }

    public void add(Numbers... n) {
        for (Numbers number : n) {
            add(number);
        }
    }

    public void join(MathSet ms) {
        for (int i = 0; i < ms.size; i++) {
            add((Numbers) ms.num[i]);
        }
        sortAsc();
    }

    public void join(MathSet... ms) {
        for (MathSet number : ms) {
            for (int i = 0; i < number.size; i++) {
                add((Numbers) number.num[i]);
            }
        }
        sortAsc();
    }

    public void intersection(MathSet ms) {
        MathSet<Numbers> res = new MathSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < ms.size; j++) {
                if (Objects.equals(this.num[i], ms.num[j])) {
                    res.add((Numbers) ms.num[j]);
                }
            }
        }
        clear();
        join(new MathSet(res));
    }

    public void intersection(MathSet... ms) {
        for (MathSet mathSet : ms) {
            intersection(mathSet);
        }
    }

    public void sortDesc() {
        sortDesc(0, size-1);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if ((lastIndex - firstIndex) > 0) {
            for (int i = firstIndex; i <= lastIndex; i++) {
                for (int k = i ; k <= lastIndex; k++) {
                    if ((num[i].compareTo(num[k]) < 0)) {
                        Numbers temp = num[i];
                        num[i] = num[k];
                        num[k] = temp;
                    }
                }
            }
        }
    }

    public void sortDesc(Numbers value) {
        if (getIndex(value) != -1) {
            sortDesc(getIndex(value), size-1);
        }
    }

    public int findElement(Numbers element) {
        if (num != null) {
            for (int i = 0; i < size; i++) {
                if (element.equals(num[i])) {
                    return i;
                }
            }
            return -1;
        }
        return 0;
    }

    public void sortAsc() {
        sortAsc(0, size-1);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if ((lastIndex - firstIndex) > 0) {
            for (int i = firstIndex; i <= lastIndex ; i++) {
                for (int k = i ; k <= lastIndex; k++) {
                    if (num[i].compareTo(num[k]) > 0) {
                        Numbers temp = num[i];
                        num[i] = num[k];
                        num[k] = temp;
                    }
                }
            }
        }
    }

    public void sortAsc(Numbers value) {
        if (getIndex(value) != -1) {
            sortAsc(getIndex(value), size-1);
        }
    }

    public Numbers get(int index) throws Exception {
        if (index < 0 || index > size) throw new Exception("Incorrect index");
        return num[index];
    }

    public Numbers getMax() {
        Numbers max = num[0];
        for (int i = 0; i < size; i++) {
            if (num[i].compareTo(max) > 0) {
                max = num[i];
            }
        }
        return max;
    }

    public Numbers getMin() {
        Numbers min = num[0];
        for (int i = 0; i < size; i++) {
            if (num[i].compareTo(min) < 0) {
                min = num[i];
            }
        }
        return min;
    }

    public Number getAverage() {
        double sum = 0d;
        for (int i = 0; i < size; i++) {
            sum += num[i].doubleValue();
        }
        return (Number) (sum / size);
    }

    public Number getMedian() {
        if (size != 0) {
            sortAsc();
            if (size % 2 != 0) {
                return num[size / 2];
            } else {
                Double firstValue = num[size / 2].doubleValue();
                Double secondValue = num[size / 2 - 1].doubleValue();
                return (Number) ((firstValue + secondValue) / 2);
            }
        } else {
            return null;
        }
    }

    public Numbers[] toArray() {
        return num;
    }

    public Numbers[] toArray(int firstIndex, int lastIndex) {
        Numbers[] newArray = (Numbers[]) new Number[(firstIndex - lastIndex) + 1];
        System.arraycopy(num, firstIndex, newArray, 0, firstIndex - lastIndex + 1);
        return newArray;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        MathSet<Numbers> mathSet = new MathSet<>();
        for (int i = 0; i < capacity; i++) {
            if (i >= firstIndex && i <= lastIndex) mathSet.add(num[i]);
        }
        return mathSet;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            num[i] = null;
        }
        size = 0;
    }

    public void clear(Numbers[] numbers) {
        for (Numbers number : numbers) {
            for (int i = 0; i < size; i++) {
                if (number.equals(num[i])) {
                    for (int k = i; k < size; k++) {
                        num[k] = num[k + 1];
                        num[k + 1] = null;
                    }
                    size--;
                }
            }
        }
    }

    private void expansionCapacity() {
        try {
            Numbers[] temp = (Numbers[]) new Number[capacity + 10];
            System.arraycopy(num, 0, temp, 0, capacity);
            capacity = capacity + 10;
            num = temp;
        } catch (Exception exception) {
            System.out.println("exception = " + exception.getMessage());
        }
    }

    public Numbers[] deleteDuplicate(Numbers[] numbers) {
        int n = numbers.length;
        for (int i = 0, m = 0; i != n; i++, n = m) {
            for (int j = m = i + 1; j != n; j++) {
                if (numbers[j] != numbers[i]) {
                    if (m != j) numbers[m] = numbers[j];
                    m++;
                }
            }
        }
        if (n != numbers.length) {
            Numbers[] b = (Numbers[]) new Number[n];
            System.arraycopy(numbers, 0, b, 0, n);
            numbers = b;
        }
        return numbers;
    }

    int getIndex(Numbers value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (num[i].equals(value)) {
                index = i;
            }
        }
        return index;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (num[i] != null)
                result.append(num[i]).append(",");
        }
        return "MathSet:" +
                "\nnumbers in set = " + result + "\b;" +
                "\nthe set size = " + size;
    }
}
