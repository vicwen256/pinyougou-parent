package cn.itcast.core.service.spec;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.vo.SpecVo;

import java.util.List;
import java.util.Map;

public interface SpecService {
    /**
     * 规格列表查询
     * @param page
     * @param rows
     * @param specification
     * @return
     */
    PageResult search(Integer page, Integer rows, Specification specification);

    /**
     * 保存规格
     * @param specVo
     * @return
     */
    void add(SpecVo specVo);

    /**
     * 回显规格
     * @param id
     * @return
     */
    SpecVo findOne(Long id);

    /**
     * 更新规格
     * @param specVo
     */
    void update(SpecVo specVo);

    /**
     * 删除规格
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 规格下拉列表
     * @return
     */
    List<Map<String,String>> selectOptionList();
}
