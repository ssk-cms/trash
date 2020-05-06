package com.trash.collection.trash.address.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.trash.collection.trash.address.domain.UserAddress;
import com.trash.collection.trash.address.service.UserAddressService;
import com.trash.collection.trash.common.NotLoginedDotGo;
import com.trash.collection.trash.common.RRException;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.user.VO.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 *  控制器
 *
 * @author seth
 * @since 2020-01-19
 */
@RestController
@RequestMapping("/address/userAddress")
public class UserAddressController {

    @Autowired
    private ProductKindService productKindService;

    @Autowired
    private UserAddressService userAddressService;


    /**
     * 根据用户id查询用户地址信息
     * */
    @GetMapping("/selectByUser")
    public Response selectByUser(){
        UserInfo userInfo = NotLoginedDotGo.getUser();
        if (Objects.isNull(userInfo)){
            throw new RRException("请登录！");
        }
        if (Objects.isNull(userInfo.getId())){
            throw new RRException("请登录！");
        }
        Long userId = userInfo.getId();
        if(Objects.isNull(userId)){
            return this.productKindService.judgeParam();
        }
        Response response = new Response();
        response.setData(this.userAddressService.selectList(new EntityWrapper<UserAddress>().eq("user_id",userId)
                                        .eq("state",1)));
        return response;
    }

    /**
     * 用户新增自己地址
     * */
    @PostMapping("/addAddress")
    public Response addAddress(@RequestBody UserAddress userAddress){
        UserInfo userInfo = NotLoginedDotGo.getUser();
        if (Objects.isNull(userAddress)){
            return productKindService.judge("请添加相应的地址信息");
        }
        userAddress.setUserId(userInfo.getId());
        Response response = new Response();
        this.userAddressService.addAddress(userAddress);
        return response;
    }

    /**
     * 用户-修改自己的地址信息
     * */
    @PostMapping("/update")
    public Response update(@RequestBody UserAddress userAddress){
        if (Objects.isNull(userAddress.getId())){
            throw new RRException("请选择地址进行修改");
        }
        this.userAddressService.updateById(userAddress);
        return new Response();
    }
}

