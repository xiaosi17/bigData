package com.example.ecommerce.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireLogin {
    // 可以添加其他属性，如需要的权限等
}
