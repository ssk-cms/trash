package com.trash.collection.trash.score.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.common.NotLoginedDotGo;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.domain.Product;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.product.service.ProductService;
import com.trash.collection.trash.score.VO.ProductOrderVO;
import com.trash.collection.trash.score.domain.ProductOrder;
import com.trash.collection.trash.score.domain.ScoreUser;
import com.trash.collection.trash.score.service.ProductOrderService;
import com.trash.collection.trash.score.service.ScoreUserService;
import com.trash.collection.trash.user.VO.UserInfo;
import com.trash.collection.trash.user.domain.User;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 控制器
 *
 * @author seth
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/score/productOrder")
@CrossOrigin
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private ProductKindService productKindService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ScoreUserService scoreUserService;

    /**
     * 查看积分捐赠订单列表
     * */
    @GetMapping("/list")
    public Response list(ProductOrderVO orderVO) {
        Response response = new Response();
        response.setData(productOrderService.getOrderList(orderVO));
        return response;
    }

    /**
     * a填写快递单号
     */
    @PostMapping("/updateTrackingNumber")
    public Response updateTrackingNumber(@RequestBody ProductOrder productOrder) {
        Response response = new Response();
        if (Objects.isNull(productOrder.getId()) || Objects.isNull(productOrder.getTrackingNumber())) {
            return productKindService.judgeParam();
        }
        productOrderService.updateTrackingNumber(productOrder);
        return response;
    }

    /**
     * 下单积分捐赠商品
     */
    @PostMapping("/addOrder")
    public Response addOrder(@RequestBody ProductOrder productOrder) {
        UserInfo userInfo = NotLoginedDotGo.getUser();
        //判断传入参数是否为空
        if (Objects.isNull(productOrder)) {
            return this.productKindService.judgeParam();
        }
        if (Objects.isNull(productOrder.getProductId())) {
            return this.productKindService.judgeParam();
        }
        productOrder.setUserId(userInfo.getId());
        Response response = new Response();
        //根据商品id查询商品兑换所需积分
        Product product = this.productService.selectById(productOrder.getProductId());
        //根据用户下单数量计算积分
        product.setNeedPoints(product.getNeedPoints().multiply(BigDecimal.valueOf(productOrder.getProductCount())));
        //根绝用户id查询用户当前拥有的积分
        ScoreUser scoreUser = this.scoreUserService.selectOne(new EntityWrapper<ScoreUser>().eq("user_id", productOrder.getUserId()));
        if (Objects.isNull(scoreUser)){
            return this.productKindService.judge("您当前没有积分记录，无法兑换商品，请及时捐赠物品获取积分！");
        }
        if (Objects.equals(product.getNeedPoints().compareTo(scoreUser.getResiduceScore()), 1)) {
            return this.productKindService.judge("您当前的积分不够兑换当前商品，请继续努力！");
        }
        //用户下单
        this.productOrderService.setOrder(productOrder, product.getNeedPoints());
        return response;
    }

    /**
     * 设置发货时间/快递单号
     */
    @PostMapping("/updateTime")
    public Response updateTime(@RequestBody ProductOrder productOrder) {
        if (Objects.isNull(productOrder.getId()) || Objects.isNull(productOrder.getDeliveryTime()) || Objects.isNull(productOrder.getTrackingNumber())) {
            return this.productKindService.judge("请填写必要信息!");
        }
        Response response = new Response();
        this.productOrderService.updateTime(productOrder);
        return response;
    }

    /**
     * 用户确认收货，上传收获时间，修改订单状态
     */
    @PostMapping("/gainGoods")
    public Response gainGoods(@RequestBody ProductOrder productOrder) {
        if (Objects.isNull(productOrder.getId())) {
            return this.productKindService.judge("请填写必要信息!");
        }
        Response response = new Response();
        this.productOrderService.gainGoods(productOrder);
        return response;
    }

    /**
     * 用户查看自己的积分兑换订单信息
     */
    @GetMapping("/getOrderListByUser")
    public Response gertListByUser(Integer state, Integer pageSize, Integer pageIndex) {
        UserInfo userInfo = NotLoginedDotGo.getUser();
        if (Objects.isNull(state)) {
            return productKindService.judge("请选择用户或选择订单状态！");
        }
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return productKindService.judge("请选择页码和每页数量！");
        }
        Long id = userInfo.getId();
        Integer userId = Integer.valueOf(Math.toIntExact(id));
        Response response = new Response();
        Page<ProductOrder> page = new Page<>(pageIndex, pageSize);
        response.setData(this.productOrderService.getListByUser(page, userId, state));
        return response;
    }
}

