package com.trash.collection.trash.address.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.trash.collection.trash.address.domain.UserAddress;
import com.trash.collection.trash.address.service.UserAddressService;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.service.ProductKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/selectByUser")
    public Response selectByUser(int userId){
        if(Objects.isNull(userId)){
            return this.productKindService.judgeParam();
        }
        Response response = new Response();
        response.setData(this.userAddressService.selectList(new EntityWrapper<UserAddress>().eq("user_id",userId)));
        return response;
    }
}

