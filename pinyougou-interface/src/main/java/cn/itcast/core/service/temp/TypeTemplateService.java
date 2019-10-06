package cn.itcast.core.service.temp;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.template.TypeTemplate;

public interface TypeTemplateService {
    /**
     * 模板分页查询
     * @param page
     * @param rows
     * @param typeTemplate
     * @return
     */
    PageResult search(Integer page, Integer rows, TypeTemplate typeTemplate);

    /**
     * 保存模板
     * @param typeTemplate
     */
    void add(TypeTemplate typeTemplate);

    /**
     * 批量删除
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 回显模板
     * @param id
     */
    TypeTemplate findOne(Long id);

    /**
     * 更新模板
     * @param typeTemplate
     */
    void update(TypeTemplate typeTemplate);


}
