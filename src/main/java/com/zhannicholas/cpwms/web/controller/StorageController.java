package com.zhannicholas.cpwms.web.controller;

import com.zhannicholas.cpwms.domain.model.StoragePK;
import com.zhannicholas.cpwms.service.RecordInService;
import com.zhannicholas.cpwms.service.RecordOutService;
import com.zhannicholas.cpwms.service.StorageService;
import com.zhannicholas.cpwms.util.Constants;
import com.zhannicholas.cpwms.util.ToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("storageManage")
public class StorageController {
    private final StorageService storageService;
    private final RecordInService recordInService;
    private final RecordOutService recordOutService;

    @Autowired
    public StorageController(StorageService storageService, RecordInService recordInService, RecordOutService recordOutService) {
        this.storageService = storageService;
        this.recordInService = recordInService;
        this.recordOutService = recordOutService;
    }

    // 查询配件库存
    @RequestMapping("getStorageNumberById")
    @ResponseBody
    public Map<String, Object> getStorageNumberById(@RequestBody StoragePK storagePK){
        System.out.println(storageService.findNumberById(storagePK.getPartsId(), storagePK.getRepoId()));
        return storageService.findNumberById(storagePK.getPartsId(), storagePK.getRepoId());
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
        person = "詹伟伟";
        // ***************************************************************

        String result = recordInService.saveRecord(supplierId, partsId, number, person, repoId) ?
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
        person = "詹伟伟";
        // ***************************************************************

        String result = recordOutService.saveRecord(customerId, partsId, number, person, repoId) ?
                Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        System.out.println("出库结果：" + result);
        return ToMapUtil.fromString(result);
    }
}
