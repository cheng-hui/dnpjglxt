package com.zhannicholas.cpwms.web.controller;

import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.service.RespositoryService;
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
@RequestMapping("/repositoryManage")
public class RepoController {
    private final RespositoryService respositoryService;

    @Autowired
    public RepoController(RespositoryService respositoryService) {
        this.respositoryService = respositoryService;
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
                return respositoryService.findOne(Integer.parseInt(keyWord));
            case SEARCH_ALL:
                return respositoryService.findAll(pageable);
            case SEARCH_BY_ADDRESS:
                return respositoryService.findByRepoAddressContaining(keyWord, pageable);
            default:
                return respositoryService.findAll(pageable);
        }
    }

    @RequestMapping(value = "getRepositoryList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAllRepo(@RequestParam("searchType") String searchType,
                                            @RequestParam(value = "offset", defaultValue = "0") int offset,
                                            @RequestParam(value = "limit", defaultValue = "5") int limit,
                                            @RequestParam("keyWord") String keyWord){
//        *******************************************************************************************
//        注意：bootstrap table 中 offset 的计算方式为：pageSize * (pageNumber - 1);而 pageSize = limit;
//        *******************************************************************************************
        return query(searchType, offset < limit ? 0 : offset / limit, limit, keyWord);
    }

    /**
     *  更新或者插入仓库信息
     * @param respository 修改后的仓库或者新的仓库
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "updateRepository", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateRepo(@RequestBody Respository respository){

        String result =respositoryService.save(respository) ? Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        return ToMapUtil.fromString(result);
    }

    /**
     * 删除仓库
     * @param repoId   仓库Id
     * @return  返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "deleteRepository/{repoId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteRepo(@PathVariable("repoId") int repoId){
        System.out.println("删除：" + repoId);
        String result =respositoryService.delete(repoId) ? Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        return ToMapUtil.fromString(result);
    }

    /**
     * 查询所有未指派管理管理员的仓库
     * @return  返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "getUnassignRepository", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUnassignedRepository(){
        System.out.println(respositoryService.findAllUnassignedRepo());
        return respositoryService.findAllUnassignedRepo();
    }
}
