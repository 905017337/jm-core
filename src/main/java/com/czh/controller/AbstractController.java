package com.czh.controller;

import com.czh.service.AbstractService;
import com.czh.util.Message;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

public abstract class AbstractController<T, Service extends AbstractService<T>> {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    protected Service service;

    @ResponseBody
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @ApiOperation(value = "保存方法", httpMethod = "POST", notes = "Abstract method,save Object")
    public Message save(@Valid T t) {
        logger.debug("Start execute save operation[{}]", t.toString());
        Integer res = service.save(t);
        return res == 1 ? new Message("保存成功", true) : new Message("保存失败", false);
    }

    @ResponseBody
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @ApiOperation(value = "删除方法", httpMethod = "POST", notes = "Abstract method,delete By ID")
    public Message delete(@RequestParam String ids) {
        logger.debug("Start execute delete operation[{}]", ids);
        if (ids != null) {
            Integer count = service.deleteByIds(ids);
            if (count != null && count.intValue() > 0) {
                logger.debug("it successed to delete");
                return new Message("删除" + count + "条数据", true);
            }
        }
        logger.debug("删除数据失败,请检查数据信息[{}]", ids);
        return new Message("删除数据失败,请检查数据信息", true);
    }

    @ResponseBody
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @ApiOperation(value = "更新方法", httpMethod = "POST", notes = "Abstract method, updateColumnsAll")
    public Message update(@Valid T t, Errors errors) {
        logger.debug("Start execute update operation[{}]", t.toString());
        Integer res = service.updateColumnsAll(t);
        return res == 1 ? new Message("修改成功", true) : new Message("修改失败", false);
    }

    @ResponseBody
    @RequestMapping("/findOneById")
    @ApiOperation(value = "findOneById", httpMethod = "POST", notes = "Abstract method, findOneById")
    public T findOne(@RequestParam Long id) {
        logger.debug("Start execute findOne operation with id[{}]", id);
        return service.findOneById(id);
    }

    @ResponseBody
    @RequestMapping("/findAll")
    @ApiOperation(value = "findAll", httpMethod = "POST", notes = "Abstract method, findAll")
    public List<T> findAll() {
        logger.debug("Start execute findAll operation");
        return service.findAll();
    }

}
