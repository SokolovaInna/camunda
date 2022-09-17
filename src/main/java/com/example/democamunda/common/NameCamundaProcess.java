package com.example.democamunda.common;
//перечисление имен процессов

public enum NameCamundaProcess {
    PROBA("Proba"),
    ADD_CARD("addCard");

    private final String name;

  NameCamundaProcess(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    }
