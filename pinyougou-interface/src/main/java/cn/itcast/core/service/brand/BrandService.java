package cn.itcast.core.service.brand;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.good.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 分页查询品牌
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 分页查询品牌
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return
     */
    PageResult search(Integer pageNum, Integer pageSize, Brand brand);

    /**
     * 保存手机品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     * 手机品牌回显
     * @param id
     * @return
     */
    Brand findOne(Long id);

    /**
     * 更新手机品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 删除批量品牌
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 品牌下拉框列表
     * @return
     */
    List<Map<String,String>> selectOptionList();
}
