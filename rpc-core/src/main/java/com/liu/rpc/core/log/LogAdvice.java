package com.liu.rpc.core.log;

import java.lang.annotation.*;

/**
 * @author knuslus
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD})
public @interface LogAdvice {
}
