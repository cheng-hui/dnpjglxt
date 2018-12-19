package com.zhannicholas.cpwms.web.controller;

import com.zhannicholas.cpwms.domain.model.Supplier;
import com.zhannicholas.cpwms.service.SupplierService;
import com.zhannicholas.cpwms.util.Constants;
import com.zhannicholas.cpwms.util.ToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.zhannicholas.cpwms.util.Constants.*;

@Controller
@RequestMapping("/supplierManage")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }


    /**
     * 通用查询方法
     * @param searchType    查询类型
     * @param offset        分页偏移量
     * @param limit         分页大小
     * @param keyWord       查询关键词
     * @return  符合要求的分页对象
     */
    private Map<String, Object> query(String searchType, int offset, int limit, String keyWord){
        Pageable pageable = null;
        if(offset >= 0 && limit >= 0){
            pageable = PageRequest.of(offset, limit);
        }
        switch (searchType){
            case SEARCH_BY_ID:
                return supplierService.findOne(Integer.parseInt(keyWord));
            case SEARCH_ALL:
                return supplierService.findAll(pageable);
            case SEARCH_BY_NAME:
                return supplierService.findBySupplierNameContaining(keyWord, pageable);
            default:
                return supplierService.findAll(pageable);
        }
    }

    @RequestMapping(value = "getSupplierList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAllSupplier(@RequestParam("searchType") String searchType,
                                            @RequestParam(value = "offset", defaultValue = "0") int offset,
                                            @RequestParam(value = "limit", defaultValue = "5") int limit,
                                            @RequestParam("keyWord") String keyWord){
        System.out.println("searchType: " + searchType +
                "\noffset: " + offset +
                "\nlimit: " + limit +
                "\nkeyWord: " + keyWord);
//        *******************************************************************************************
//        注意：bootstrap table 中 offset 的计算方式为：pageSize * (pageNumber - 1);而 pageSize = limit;
//        *******************************************************************************************
        return query(searchType, offset < limit ? 0 : offset / limit, limit, keyWord);
    }

    /**
     *  更新或者插入供应商信息
     * @param supplier 修改后的供应商或者新的供应商
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "updateSupplier", method = RequestMethod.POST)
    @ResponseBody   /** 必须得返回json格式的数据，因为前端要的就是json数据 */
    public Map<String, Object> updateSupplier(@RequestBody Supplier supplier){

        String result =supplierService.save(supplier) ? Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        return ToMapUtil.fromString(result);
    }

    /**
     * 删除供应商
     * @param supplierId   供应商Id
     * @return  返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "deleteSupplier/{supplierId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteSupplier(@PathVariable("supplierId") int supplierId){
        System.out.println("删除：" + supplierId);
        String result =supplierService.delete(supplierId) ? Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        return ToMapUtil.fromString(result);
    }
}
