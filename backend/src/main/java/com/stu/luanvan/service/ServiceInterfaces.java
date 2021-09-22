package com.stu.luanvan.service;


import java.util.Collection;
import java.util.Map;

public interface ServiceInterfaces<M,R> {
    Map<String,Object> findByAll(Integer page, Integer size, String nameSort);
    Collection<M> findByAll();
    M findById(Integer id);
    M saveNew(R r) throws Exception;
    M saveEdit(R r,int id) throws Exception;
    void delete(Integer id) throws Exception;
}