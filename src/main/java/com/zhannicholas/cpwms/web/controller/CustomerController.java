package com.zhannicholas.cpwms.web.controller;

import com.zhannicholas.cpwms.domain.model.Customer;
import com.zhannicholas.cpwms.service.CustomerService;
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
@RequestMapping("/customerManage")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
        if(offset >= 0 && limit >= 0) {
            pageable = PageRequest.of(offset, limit);
        }
        switch (searchType){
            case SEARCH_BY_ID:
                return customerService.findOne(Integer.parseInt(keyWord));
            case SEARCH_ALL:
                return customerService.findAll(pageable);
            case SEARCH_BY_NAME:
                return customerService.findByCustomerNameContaining(keyWord, pageable);
            default:
                return customerService.findAll(pageable);
        }
    }

    @RequestMapping(value = "getCustomerList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAllCustomer(@RequestParam("searchType") String searchType,
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
     * @param customer 修改后的供应商或者新的供应商
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "updateCustomer", method = RequestMethod.POST)
    @ResponseBody   /** 必须得返回json格式的数据，因为前端要的就是json数据 */
    public Map<String, Object> updateCustomer(@RequestBody Customer customer){

        String result =customerService.save(customer) ? Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        return ToMapUtil.fromString(result);
    }

    /**
     * 删除供应商
     * @param supplierId   供应商Id
     * @return  返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "deleteCustomer/{customerId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCustomer(@PathVariable("customerId") int supplierId){
        System.out.println("删除：" + supplierId);
        String result =customerService.delete(supplierId) ? Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        return ToMapUtil.fromString(result);
    }
}
