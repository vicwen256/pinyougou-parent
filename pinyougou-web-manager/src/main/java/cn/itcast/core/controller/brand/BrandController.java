package cn.itcast.core.controller.brand;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.entity.Result;
import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.service.brand.BrandService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll.do")
    public List<Brand> findAll() {
        List<Brand> brandList = brandService.findAll();
        return brandList;
    }

    @RequestMapping("/findPage.do")
    public PageResult findBrandPage(Integer pageNum, Integer pageSize) {
        PageResult brandPageResult = brandService.findPage(pageNum, pageSize);
        return brandPageResult;
    }

    @RequestMapping("/search.do")
    public PageResult search(Integer pageNum, Integer pageSize, @RequestBody Brand brand) {
        PageResult brandSearchPageResult = brandService.search(pageNum, pageSize, brand);
        return brandSearchPageResult;
    }

    @RequestMapping("/add.do")
    public Result add(@RequestBody Brand brand) {
        try {
            brandService.add(brand);
            return new Result(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "保存失败");
        }
    }

    @RequestMapping("/findOne.do")
    public Brand findOne(Long id) {
        Brand brand = brandService.findOne(id);
        return brand;
    }

    @RequestMapping("/update.do")
    public Result update(@RequestBody Brand brand) {
        try {
            brandService.update(brand);
            return new Result(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更新失败");
        }
    }

    @RequestMapping("/delete.do")
    public Result delete(Long[] ids) {
        try {
            brandService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/selectOptionList.do")
    public List<Map<String,String>> selectOptionList() {
      return brandService.selectOptionList();
    }
}
