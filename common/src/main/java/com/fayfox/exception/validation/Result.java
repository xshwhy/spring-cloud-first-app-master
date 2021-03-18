package com.fayfox.exception.validation;

import java.util.LinkedList;
import java.util.List;

/**
 * 用于返回验证器报错
 */
public class Result {
    List<Field> fields = new LinkedList<>();

    public void addField(Field field) {
        fields.add(field);
    }

    public List<Field> getFields() {
        return fields;
    }
}
