package de.daver.buun.core.math;

import java.lang.reflect.Array;

public class Vector<N extends Number> {

    private final N[] values;

    @SuppressWarnings("unchecked")
    public Vector(Class<N> clazz, int rows){
        this.values = (N[]) Array.newInstance(clazz, rows);
    }

}
