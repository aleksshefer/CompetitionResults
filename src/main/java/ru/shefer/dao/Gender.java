package ru.shefer.dao;

/**
 * Даннное перечисление служит для обозначения пола спротсмена
 *
 * @author aleksandr shefer
 * @version 1
 * @since 1
 */
public enum Gender {
    M("male"), F("female");
    /**
     * Строковок представление пола спортсмена
     */
    final String value;

    Gender(String value) {
        this.value = value;
    }

    /**
     * Возвращает строковое представление пола спротсмена
     *
     * @return строковое представление пола спортсмена
     */
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "value='" + value + '\'' +
                '}';
    }
}
