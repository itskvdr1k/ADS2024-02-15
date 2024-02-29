package by.it.group310901.baradzin.lesson02;

import java.util.ArrayList;
import java.util.List;
/*
    Даны интервальные события events
    реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
    непересекающихся событий было максимально.
    Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    public static void main(String[] args) {
        var instance = new B_Sheduler();
        var events = new Event[]{
                new Event(0, 3), new Event(0, 1), new Event(1, 2),
                new Event(3, 5), new Event(1, 3), new Event(1, 3),
                new Event(1, 3), new Event(3, 6), new Event(2, 7),
                new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5), new Event(2, 4), new Event(2, 3),
                new Event(3, 7), new Event(4, 5), new Event(6, 7),
                new Event(6, 9), new Event(7, 9), new Event(8, 9),
                new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        // рассчитаем оптимальное заполнение аудитории
        List<Event> starts = instance.calcStartTimes(events, 0, 10);
        // покажем рассчитанный график занятий
        System.out.println(starts);
    }

    /**
     * @param events события которые нужно распределить в аудитории
     * @param from   начало периода (включительно)
     * @param to     начало периода (включительно)
     * @return итог
     * @implNote Оптимизация проводится по наибольшему числу непересекающихся событий, начало и конец событий могут
     * совпадать
     */
    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result;
        result = new ArrayList<>();

        // ваше решение

        return result;
    }

    static class Event {
        int start, stop;

        /**
         * Событие у аудитории
         *
         * @param start начало
         * @param stop  конец
         */
        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "(" + start + ":" + stop + ")";
        }
    }
}
