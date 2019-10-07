package cn.itcast.core.service.seller;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.seller.Seller;

public interface SellerService {
    /**
     * 商家入驻
     * @param seller
     */
    void add(Seller seller);

    /**
     * 商家审核分页
     * @param page
     * @param rows
     * @param seller
     * @return
     */
    PageResult search(Integer page, Integer rows, Seller seller);

    /**
     * 商家详情
     * @param id
     * @return
     */
    Seller findOne(String id);

    /**
     * 商家审核
     * @param sellerId
     * @param status
     */
    void updateStatus(String sellerId, String status);
}
