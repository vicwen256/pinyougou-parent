package cn.itcast.core.service.temp;

import cn.itcast.core.dao.template.TypeTemplateDao;
import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.template.TypeTemplate;
import cn.itcast.core.pojo.template.TypeTemplateQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Resource
    private TypeTemplateDao typeTemplateDao;

    /**
     * 模板分页查询
     *
     * @param page
     * @param rows
     * @param typeTemplate
     * @return
     */
    @Override
    public PageResult search(Integer page, Integer rows, TypeTemplate typeTemplate) {
        PageHelper.startPage(page, rows);
        TypeTemplateQuery query = new TypeTemplateQuery();
        TypeTemplateQuery.Criteria criteria = query.createCriteria();
        if (typeTemplate.getName() != null && !"".equals(typeTemplate.getName())) {
            criteria.andNameLike("%" + typeTemplate.getName().trim() + "%");
        }
        Page<TypeTemplate> templatePage = (Page<TypeTemplate>) typeTemplateDao.selectByExample(query);
        return new PageResult(templatePage.getTotal(), templatePage.getResult());
    }

    /**
     * 添加模板
     * @param typeTemplate
     */
    @Transactional
    @Override
    public void add(TypeTemplate typeTemplate) {
        typeTemplateDao.insertSelective(typeTemplate);
    }

    /**
     * 删除模板
     * @param ids
     */
    @Transactional
    @Override
    public void delete(Long[] ids) {
        typeTemplateDao.deleteByPrimaryKeys(ids);
    }

    /**
     * 回显模板
     *
     * @param id
     */
    @Override
    public TypeTemplate findOne(Long id) {
        return typeTemplateDao.selectByPrimaryKey(id);
    }

    /**
     * 更新模板
     *
     * @param typeTemplate
     */
    @Override
    public void update(TypeTemplate typeTemplate) {
        typeTemplateDao.updateByPrimaryKeySelective(typeTemplate);
    }

}
