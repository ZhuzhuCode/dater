//package com.zjr.dater.business.service.Impl;
//
//import com.zjr.dater.business.model.User;
//import com.zjr.dater.business.service.EhcacheService;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * Created by zhujr on 2018/10/24.
// */
//@Service
//@CacheConfig(cacheNames = {"department"})
//public class EhCacheServiceImpl implements EhcacheService {
//    //缓存key
//    public static final String USER_ALL_KEY = "\"user_all\"";
//    //value属性标识使用哪个缓存策略，缓存策略在ehcache
//    public static final String CACHE_NAME = "department";
//
//    @CacheEvict(value = CACHE_NAME,key = USER_ALL_KEY)
//    @Override
//    public void create(User user) {
//
//    }
//
//    @Override
//    public User findById(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<User> findAll() {
//        return null;
//    }
//
//    @Override
//    public User update(User user) {
//        return null;
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
//}
