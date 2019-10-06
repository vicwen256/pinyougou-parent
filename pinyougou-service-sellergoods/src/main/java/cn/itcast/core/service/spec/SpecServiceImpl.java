package cn.itcast.core.service.spec;

import cn.itcast.core.dao.specification.SpecificationDao;
import cn.itcast.core.dao.specification.SpecificationOptionDao;
import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.pojo.specification.SpecificationOption;
import cn.itcast.core.pojo.specification.SpecificationOptionQuery;
import cn.itcast.core.pojo.specification.SpecificationQuery;
import cn.itcast.core.vo.SpecVo;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SpecServiceImpl implements SpecService {

    @Resource
    private SpecificationDao specificationDao;

    @Resource
    private SpecificationOptionDao specificationOptionDao;

    /**
     * 规格列表查询
     *
     * @param page
     * @param rows
     * @param specification
     * @return
     */
    @Override
    public PageResult search(Integer page, Integer rows, Specification specification) {
        PageHelper.startPage(page, rows);
        SpecificationQuery query = new SpecificationQuery();
        SpecificationQuery.Criteria criteria = query.createCriteria();
        if (specification.getSpecName() != null && !"".equals(specification.getSpecName())) {
            criteria.andSpecNameLike("%" + specification.getSpecName().trim() + "%");
        }
        query.setOrderByClause("id asc");
        Page<Specification> specPage = (Page<Specification>) specificationDao.selectByExample(query);
        return new PageResult(specPage.getTotal(), specPage.getResult());
    }

    /**
     * 保存规格
     *
     * @param specVo
     * @return
     */
    @Transactional
    @Override
    public void add(SpecVo specVo) {
        Specification specification = specVo.getSpecification();
        specificationDao.insertSelective(specification);
        List<SpecificationOption> specificationOptionList = specVo.getSpecificationOptionList();
        for (SpecificationOption specificationOption : specificationOptionList) {
            specificationOption.setSpecId(specification.getId());
//            specificationOptionDao.insertSelective(specificationOption);
        }
        //批量插入
        specificationOptionDao.insertSelectives(specificationOptionList);
    }

    /**
     * 回显规格
     *
     * @param id
     * @return
     */
    @Override
    public SpecVo findOne(Long id) {
        Specification specification = specificationDao.selectByPrimaryKey(id);
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = query.createCriteria();
        criteria.andSpecIdEqualTo(specification.getId());
        List<SpecificationOption> specificationOptionList = specificationOptionDao.selectByExample(query);
        SpecVo specVo = new SpecVo();
        specVo.setSpecification(specification);
        specVo.setSpecificationOptionList(specificationOptionList);
        return specVo;
    }

    /**
     * 更新规格
     *
     * @param specVo
     */
    @Override
    public void update(SpecVo specVo) {
        Specification specification = specVo.getSpecification();
        specificationDao.updateByPrimaryKeySelective(specification);
        //更新规格选项
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = query.createCriteria();
        //先删除根据外键删除
        criteria.andSpecIdEqualTo(specification.getId());
        specificationOptionDao.deleteByExample(query);
        //在插入
        List<SpecificationOption> specificationOptionList = specVo.getSpecificationOptionList();
        for (SpecificationOption specificationOption : specificationOptionList) {
            //设置外键
            specificationOption.setSpecId(specification.getId());
        }
        //批量插入
        specificationOptionDao.insertSelectives(specificationOptionList);
    }

    /**
     * 删除规格
     *
     * @param ids
     */
    @Transactional
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            //先删从表
            SpecificationOptionQuery query = new SpecificationOptionQuery();
            query.createCriteria().andSpecIdEqualTo(id);
            specificationOptionDao.deleteByExample(query);
        }
        //删主表
        specificationDao.deleteByPrimaryKeys(ids);
    }

    /**
     * 规格下拉列表
     *
     * @return
     */
    @Override
    public List<Map<String, String>> selectOptionList() {
        return specificationDao.selectOptionList();
    }
}
