package com.vpcloud.shiro.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vpcloud.shiro.pojo.Resource;

public interface ResourceDao {

    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();
    List<Map<String,String>> findAllResourceName();
}
