package com.trash.collection.trash.product.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.VO.WorkerMessageVO;
import com.trash.collection.trash.product.domain.WorkerMessage;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.product.service.WorkerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 控制器
 *
 * @author seth
 * @since 2020-03-08
 */
@RestController
@RequestMapping("/product/workerMessage")
@CrossOrigin
public class WorkerMessageController {

    @Autowired
    ProductKindService productKindService;

    @Autowired
    WorkerMessageService messageService;

    /**
     * 新增上门取件工作人员信息
     */
    @PostMapping("/add")
    public Response add(@RequestBody WorkerMessage workerMessage){
        Response response = new Response();
        if (Objects.isNull(workerMessage)){
            return productKindService.judgeParam();
        }
        messageService.add(workerMessage);
        return response;
    }

    /**
     * 获取工作人员信息列表
     * */
    @GetMapping("/list")
    public Response list(WorkerMessageVO workerMessageVO){
        Response response = new Response();
        if (Objects.isNull(workerMessageVO)){
            return productKindService.judgeParam();
        }
        response.setData(messageService.getList(workerMessageVO));
        return response;
    }

    /**
     * 根据id查询某一个工作人员的信息
     * */
    @GetMapping("/getOne")
    public Response getOne(Integer workerMessageId){
        Response response = new Response();
        if (Objects.isNull(workerMessageId)){
            return productKindService.judgeParam();
        }
        response.setData(messageService.selectById(workerMessageId));
        return response;
    }
    /**
     * 编辑
     * */
    @PostMapping("/edit")
    public Response edit(@RequestBody WorkerMessage workerMessage){
        Response response = new Response();
        if (Objects.isNull(workerMessage)){
            return productKindService.judgeParam();
        }
        if (Objects.isNull(workerMessage.getId())){
            return productKindService.judgeParam();
        }
        messageService.edit(workerMessage);
        return response;
    }

    /**
     * 修改工作人员状态-离职
     * */
    @GetMapping("/editState")
    public Response editState(Long workerId){
        if (Objects.isNull(workerId)){
            return this.productKindService.judge("请选择工作人员");
        }
        Response response = new Response();
        this.messageService.editState(workerId);
        return response;
    }

}

