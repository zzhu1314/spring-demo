package com.zzhu.config;

import com.zzhu.cache.LocalCacheService;
import com.zzhu.distrbutecache.CacheService;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 兼容spring
 */
public class EasyCacheImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> localCacheclassNames = SpringFactoriesLoader.loadFactoryNames(LocalCacheService.class, ClassUtils.getDefaultClassLoader());
        List<String> cacheServiceclassNames = SpringFactoriesLoader.loadFactoryNames(CacheService.class, ClassUtils.getDefaultClassLoader());
        localCacheclassNames.addAll(cacheServiceclassNames);
        localCacheclassNames.add(ConfigBeans.class.getName());
        return StringUtils.toStringArray(localCacheclassNames);
    }
}
