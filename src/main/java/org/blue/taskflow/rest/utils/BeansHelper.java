package org.blue.taskflow.rest.utils;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-13
 * Time: 11:07:02
 */
public class BeansHelper {

    public static Object deepClone(Object ob) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        Object newOb = null;
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(ob);
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            newOb = oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newOb;
    }
}
