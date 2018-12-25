package com.zhannicholas.cpwms.controller;

import com.zhannicholas.cpwms.service.RecordService;
import com.zhannicholas.cpwms.service.RespositoryService;
import com.zhannicholas.cpwms.service.StorageService;
import com.zhannicholas.cpwms.util.Constants;
import com.zhannicholas.cpwms.util.ToMapUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.zhannicholas.cpwms.util.Constants.*;

@Controller
@RequestMapping("storageManage")
public class StorageController {
    private final StorageService storageService;
    private final RespositoryService respositoryService;
    private final RecordService recordService;

    @Autowired
    public StorageController(StorageService storageService, RecordService recordService, RespositoryService respositoryService) {
        this.storageService = storageService;
        this.recordService = recordService;
        this.respositoryService = respositoryService;
    }

    // 查询配件库存
    @RequestMapping("getStorageNumberById")
    @ResponseBody
    public Map<String, Object> getStorageNumberById(@RequestParam("partsId") int partsId,
                                                    @RequestParam("repoId") int repoId){
        System.out.println(storageService.findNumberById(partsId, repoId));
        return storageService.findNumberById(partsId, repoId);
    }

    // 配件入库
    @ResponseBody
    @RequestMapping("increaseStorage")
    public Map<String, Object> increaseStorage(@RequestParam("partsId") int partsId,
                                               @RequestParam("repoId") int repoId,
                                               @RequestParam("supplierId") int supplierId,
                                               @RequestParam("number") int number,
                                               HttpServletRequest request){
        System.out.println("partsId: " + partsId +
                "\nrepoId: " + repoId +
                "\nsupplierId: " + supplierId +
                "\nnumber: " + number);
        HttpSession session =request.getSession();
        String person = (String) session.getAttribute("userName");


        // *************************测试使用*******************************
        //person = "詹伟伟";
        // ***************************************************************

        String result = recordService.saveInRecord(supplierId, partsId, number, person, repoId) ?
                Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        System.out.println("入库结果：" + result);
        return ToMapUtil.fromString(result);
    }

    // 配件出库
    @ResponseBody
    @RequestMapping("decreaseStorage")
    public Map<String, Object> decreaseStorage(@RequestParam("partsId") int partsId,
                                               @RequestParam("repoId") int repoId,
                                               @RequestParam("customerId") int customerId,
                                               @RequestParam("number") int number,
                                               HttpServletRequest request){
        System.out.println("partsId: " + partsId +
                "\nrepoId: " + repoId +
                "\ncustomerId: " + customerId +
                "\nnumber: " + number);
        HttpSession session =request.getSession();
        String person = (String) session.getAttribute("userName");


        // *************************测试使用*******************************
        //person = "詹伟伟";
        // ***************************************************************

        String result = recordService.saveOutRecord(customerId, partsId, number, person, repoId) ?
                Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        System.out.println("出库结果：" + result);
        return ToMapUtil.fromString(result);
    }

    /**
     * 通用查询方法
     * @param searchType    查询类型
     * @param offset        分页偏移量
     * @param limit         分页大小
     * @param keyWord       查询关键词
     * @return  符合要求的分页对象
     */
    private Map<String, Object> query(String searchType, int offset, int limit, int repoId, String keyWord){
        Pageable pageable = null;
        if(offset >= 0 && limit >= 0) {
            pageable = PageRequest.of(offset, limit);
        }
        switch (searchType){
            case SEARCH_BY_PARTS_ID:
                System.out.println("Search by partsId: ");
                if(StringUtils.isNumeric(keyWord)){
                    int partsId = Integer.parseInt(keyWord);
                    if(respositoryService.isValidRepoId(repoId)){
                        return storageService.findByPartsIdAndRepoId(partsId, repoId);
                    }
                    else{
                        return storageService.findByPartsId(partsId, pageable);
                    }
                }
            case SEARCH_BY_PARTS_NAME:
                System.out.println("Search by parts name: ");
                if(respositoryService.isValidRepoId(repoId)) {
                    return storageService.findByPartsName(repoId, keyWord, pageable);
                }
                return storageService.findByPartsName(keyWord, pageable);
            case SEARCH_BY_PARTS_TYPE:
                System.out.println("Search by parts type: ");
                if(respositoryService.isValidRepoId(repoId)) {
                    return storageService.findByPartsType(repoId, keyWord, pageable);
                }
                return storageService.findByPartsType(keyWord, pageable);
            default:    // search all
                System.out.println("Search all: ");
                if(respositoryService.isValidRepoId(repoId)){
                    return storageService.findAll(repoId, pageable);
                }
                return storageService.findAll(pageable);
        }
    }


    // 查询配件库存信息
    @ResponseBody
    @RequestMapping(value = "getStorageListWithRepository", method = RequestMethod.GET)
    public Map<String, Object> getStorageListWithRepository(@RequestParam(value = "searchType", defaultValue = "SearchAll") String searchType,
                                                            @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                            @RequestParam(value = "limit", defaultValue = "5") int limit,
                                                            @RequestParam(value = "repoId", defaultValue = "-1") int repoId,
                                                            @RequestParam(value = "keyWord") String keyWord){
        System.out.println("searchType: " + searchType +
                "\noffset: " + offset +
                "\nlimit: " + limit +
                "\nkeyWord: " + keyWord +
                "\nrepoId: " + repoId);
//        *******************************************************************************************
//        注意：bootstrap table 中 offset 的计算方式为：pageSize * (pageNumber - 1);而 pageSize = limit;
//        *******************************************************************************************
        return query(searchType, offset, limit, repoId, keyWord);
    }

    // 删除库存信息
    @ResponseBody
    @RequestMapping(value = "deleteStorageRecord", method = RequestMethod.POST)
    public Map<String, Object> deleteStorageRecord(@RequestParam("partsId") int partsId,
                                                   @RequestParam("repoId") int repoId){
        System.out.println("删除库存：partsId=" + partsId + ", repoId=" + repoId);
        String result = storageService.delete(partsId, repoId) ? RESULT_SUCCESS : RESULT_ERROR;
        return ToMapUtil.fromString(result);
    }

    // 添加一条库存记录
    @ResponseBody
    @RequestMapping(value = "insertStorageRecord", method = RequestMethod.POST)
    public Map<String, Object> insertStorageRecord(@RequestParam("partsId") int partsId,
                                                   @RequestParam("repoId") int repoId,
                                                   @RequestParam("number") int number){
        String result = storageService.saveStorage(partsId, repoId, number) ? RESULT_SUCCESS : RESULT_ERROR;
        return ToMapUtil.fromString(result);
    }

    // 更新一条库存记录
    @ResponseBody
    @RequestMapping(value = "updateStorageRecord", method = RequestMethod.POST)
    public Map<String, Object> updateStorageRecord(@RequestParam("partsId") int partsId,
                                                   @RequestParam("repoId") int repoId,
                                                   @RequestParam("number") int number){
        String result = storageService.updateStorage(partsId, repoId, number) ? RESULT_SUCCESS : RESULT_ERROR;
        return ToMapUtil.fromString(result);
    }
}
