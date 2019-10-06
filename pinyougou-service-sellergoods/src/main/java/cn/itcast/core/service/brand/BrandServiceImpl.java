package cn.itcast.core.service.brand;

import cn.itcast.core.dao.good.BrandDao;
import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.pojo.good.BrandQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandDao brandDao;

    /**
     * 查询所有品牌
     *
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandDao.selectByExample(null);
    }

    /**
     * 分页查询品牌
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Brand> page = (Page<Brand>) brandDao.selectByExample(null);
        //不直接返回Page，因为Page封装的属性太多
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 分页查询品牌
     *
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return
     */
    @Override
    public PageResult search(Integer pageNum, Integer pageSize, Brand brand) {
        PageHelper.startPage(pageNum, pageSize);
        BrandQuery query = new BrandQuery();
        BrandQuery.Criteria criteria = query.createCriteria();
        if (brand.getName() != null && !"".equals(brand.getName().trim())) {
            criteria.andNameLike("%" + brand.getName().trim() + "%");
        }
        if (brand.getFirstChar() != null && !"".equals(brand.getFirstChar().trim())) {
            criteria.andFirstCharLike("%" + brand.getFirstChar().trim() + "%");
        }
        query.setOrderByClause("id asc");
        Page<Brand> page = (Page<Brand>) brandDao.selectByExample(query);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 保存手机品牌
     *
     * @param brand
     */
    @Transactional
    @Override
    public void add(Brand brand) {
        //insertSelective非空判断
        brandDao.insertSelective(brand);
    }

    /**
     * 手机品牌回显
     *
     * @param id
     * @return
     */
    @Override
    public Brand findOne(Long id) {
        return brandDao.selectByPrimaryKey(id);
    }

    /**
     * 更新手机品牌
     *
     * @param brand
     */
    @Transactional
    @Override
    public void update(Brand brand) {
        brandDao.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除批量品牌
     *
     * @param ids
     */
    @Transactional
    @Override
    public void delete(Long[] ids) {
        if (ids != null && ids.length > 0) {
//            效率低
//            for (Long id : ids) {
//                brandDao.deleteByPrimaryKey(id);
//            }
            brandDao.deleteByPrimaryKeys(ids);
        }
    }

    /**
     * 品牌下拉框列表
     *
     * @return
     */
    @Override
    public List<Map<String, String>> selectOptionList() {
        return brandDao.selectOptionList();
    }
}
