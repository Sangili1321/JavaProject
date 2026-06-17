package utils;

import java.lang.reflect.Field;
import java.util.Objects;

public class ObjectComparator {

    public static void compareObjects(Object obj1, Object obj2) {
        compareObjects(obj1, obj2, "");
    }

    private static void compareObjects(Object obj1, Object obj2, String parentField) {

        if (obj1 == null || obj2 == null) {
            if (!Objects.equals(obj1, obj2)) {
                throw new AssertionError(
                        parentField + " -> One of the objects is null: " + obj1 + " vs " + obj2
                );
            }
            return;
        }

        Class<?> clazz = obj1.getClass();

        if (!clazz.equals(obj2.getClass())) {
            throw new AssertionError("Type mismatch: " + clazz + " vs " + obj2.getClass());
        }

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);

                String fieldPath = parentField.isEmpty()
                        ? field.getName()
                        : parentField + "." + field.getName();

                // ✅ If it's a primitive, wrapper, String → compare directly
                if (isPrimitiveOrWrapper(field.getType()) || field.getType().equals(String.class)) {

                    if (!Objects.equals(value1, value2)) {
                        throw new AssertionError(
                                "Mismatch at " + fieldPath + " -> " + value1 + " != " + value2
                        );
                    }
                }
                // ✅ If it's another object → recursive comparison
                else {
                    compareObjects(value1, value2, fieldPath);
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() ||
                type == Integer.class ||
                type == Long.class ||
                type == Double.class ||
                type == Float.class ||
                type == Boolean.class ||
                type == Character.class ||
                type == Byte.class ||
                type == Short.class;
    }
}
