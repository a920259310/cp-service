package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.service.BaseService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    protected org.slf4j.Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }


    @Override
    public int insert(T t) {
        return mapper.insert(t);
    }

    @Override
    public T queryById(Object t) {
        return mapper.selectByPrimaryKey(t);
    }

    @Override
    public List<T> query(T t) {
        return mapper.select(t);
    }

    @Override
    public T queryByBean(T t) throws Exception {
        return mapper.selectOne(t);
    }

    @Override
    public int delete(T key) {
        return mapper.delete(key);
    }

    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {

        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int selectCount(T entity) {
        return mapper.selectCount(entity);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return null;
    }

    @Override
    public List<T> selectAll() {
        return null;
    }
}
