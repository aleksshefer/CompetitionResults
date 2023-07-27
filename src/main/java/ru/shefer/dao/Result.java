package ru.shefer.dao;


import java.time.Duration;
import java.util.Objects;

/**
 * Данная запись хранит в себе результат выступления спорстмена
 *
 * @param name     имя спортсмена
 * @param gender   пол спортсмена
 * @param distance дистанция соревнования
 * @param duration время результата
 * @author aleksandr shefer
 * @version 1
 * @since 1
 */
public record Result(String name, Gender gender, int distance, Duration duration) {

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Result) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.gender, that.gender) &&
                this.distance == that.distance &&
                Objects.equals(this.duration, that.duration);
    }

    @Override
    public String toString() {
        return "Result[" +
                "name = " + name + ", " +
                "gender = " + gender.getValue() + ", " +
                "distance = " + distance + ", " +
                "duration = " + duration.getSeconds() / 60 + ":" + duration.getSeconds() % 60 + ']' + "\n";
    }

}
