package cn.ananyz.cp.service.service;


import java.util.List;

/**
 * Created by 王晶 on 2018/6/10.
 */
public interface BaseService<T> {
    public int insert(T t);
    public T queryById(Object t);
    public List<T> query(T t);
    public T queryByBean(T t) throws Exception;
}
