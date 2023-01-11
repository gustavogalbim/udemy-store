package udemy.course.model;

import udemy.course.helper.Utils;

public class Store {

    private static int count = 1;
    private int code;
    private String name;
    private Double value;

    public Store(final String name, final Double value) {
        this.code = Store.count;
        this.name = name;
        this.value = value;

        Store.count += 1;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String toString() {
        return "Código: " +this.getCode() +
                "\nNome: " +this.getName() +
                "\nPreço: " + Utils.doubleToString(this.getValue());
    }
}
