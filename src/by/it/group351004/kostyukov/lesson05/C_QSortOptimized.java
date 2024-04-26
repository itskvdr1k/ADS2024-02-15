package by.it.group351004.kostyukov.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quickSort(segments, 0, segments.length-1);
        for (int i = 0; i < m; i++) {
            int camersCount = 0;
            int j = binaryFindOfFirstSegment(segments, points[i], 0, segments.length-1);

            while (j < n && points[i] > segments[j].start) {
                if (points[i] < segments[j].stop) {
                    camersCount++;
                }
                j++;
            }
            result[i] = camersCount;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    void quickSort (Segment[] segm, int left, int right) {
        while (left < right) {               //элиминация хвостовой рекурсии (глубина стека не будет привышать log n)
            int[] m = partition(segm, left, right);
            quickSort(segm, left, m[0] - 1);
            left = m[1] + 1;
        }
    }


    //для нахождения первого отрезка - бинарный поиск
    int binaryFindOfFirstSegment (Segment[] segm, int point, int left, int right) {
        int m = left + (right - left) / 2;
        if (point > segm[m].start && (m > 1) && point > segm[m-1].start) {
            return binaryFindOfFirstSegment(segm, point, m+1, segm.length-1);
        }
        else if (point < segm[m].start) {
            return binaryFindOfFirstSegment(segm, point, 0, m-1);
        }
        else return m;
    }


    //используется разбиение массива на 3 части (< опорного, = опорному, > опорного)
    int[] partition (Segment[] segm, int left, int right) {

        //считаем опорным первый элемент
        Segment x = segm[left];
        int mLeft = left;
        int mRight = right;

        //переброска меньших элементов влево от опорного и больших опорного - вправо
        int i = left + 1;
        while (i <= mRight) {
            if (segm[i].compareTo(x) < 0) {
                //переставляем меньший элемент влево
                Segment temp;
                temp = segm[i];
                segm[i] = segm[mLeft];
                segm[mLeft] = temp;

                mLeft++;
                i++;
            }
            else if (segm[i].compareTo(x) > 0) {
                //переставляем больший элемент вправо
                Segment temp;
                temp = segm[i];
                segm[i] = segm[mRight];
                segm[mRight] = temp;

                mRight--;
            } else {
                i++;
            }
        }
        int[] res = {mLeft, mRight};
        return res;
    }

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return this.start - o.start;
        }
    }

}