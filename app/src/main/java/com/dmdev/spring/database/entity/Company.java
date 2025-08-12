package com.dmdev.spring.database.entity;

public record Company(Integer id) {

    // Дополнительный конструктор, если нужно создать Company из другого Company
    public Company(Company other) {
        this(other.id());
    }

}
