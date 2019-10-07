package cn.itcast.core.service.seller;

import cn.itcast.core.dao.seller.SellerDao;
import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.seller.Seller;
import cn.itcast.core.pojo.seller.SellerQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private SellerDao sellerDao;

    /**
     * 商家入驻
     *
     * @param seller
     */
    @Transactional
    @Override
    public void add(Seller seller) {
        seller.setStatus("0");
        seller.setCreateTime(new Date());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(seller.getPassword());
        seller.setPassword(encodePassword);
        sellerDao.insertSelective(seller);
    }

    /**
     * 商家审核分页
     *
     * @param page
     * @param rows
     * @param seller
     * @return
     */
    @Override
    public PageResult search(Integer page, Integer rows, Seller seller) {
        PageHelper.startPage(page, rows);
        SellerQuery sellerQuery = new SellerQuery();
        SellerQuery.Criteria criteria = sellerQuery.createCriteria();
        if (seller.getStatus() != null && !"".equals(seller.getStatus().trim())) {
            criteria.andStatusEqualTo(seller.getStatus().trim());
        }
        if (seller.getName() != null && !"".equals(seller.getName().trim())) {
            criteria.andNameLike("%" + seller.getName().trim() + "%");
        }
        if (seller.getNickName() != null && !"".equals(seller.getNickName().trim())) {
            criteria.andNickNameLike("%" + seller.getNickName().trim() + "%");
        }
        Page<Seller> sellerPage = (Page<Seller>) sellerDao.selectByExample(sellerQuery);
        return new PageResult(sellerPage.getTotal(), sellerPage.getResult());
    }

    /**
     * 商家详情
     *
     * @param id
     * @return
     */
    @Override
    public Seller findOne(String id) {
        return sellerDao.selectByPrimaryKey(id);
    }

    /**
     * 商家审核
     *
     * @param sellerId
     * @param status
     */
    @Transactional
    @Override
    public void updateStatus(String sellerId, String status) {
        Seller seller = new Seller();
        seller.setSellerId(sellerId);
        seller.setStatus(status);
        sellerDao.updateByPrimaryKeySelective(seller);
    }
}
