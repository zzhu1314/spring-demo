package com.zzhu.annotation;

import com.zzhu.config.EasyCacheImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EasyCacheImportSelector.class)
public @interface EnableAutoEasyCache {
}
