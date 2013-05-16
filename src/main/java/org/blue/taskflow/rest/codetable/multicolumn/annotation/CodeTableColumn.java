package org.blue.taskflow.rest.codetable.multicolumn.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-13
 * Time: 11:48:40
 */
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeTableColumn {

    String field();

    boolean isPk();
}
