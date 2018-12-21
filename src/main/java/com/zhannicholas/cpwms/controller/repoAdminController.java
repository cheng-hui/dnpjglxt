package com.zhannicholas.cpwms.controller;

import com.zhannicholas.cpwms.domain.model.RepoAdmin;
import com.zhannicholas.cpwms.domain.model.User;
import com.zhannicholas.cpwms.service.RepoAdminService;
import com.zhannicholas.cpwms.service.RespositoryService;
import com.zhannicholas.cpwms.service.UserService;
import com.zhannicholas.cpwms.util.Constants;
import com.zhannicholas.cpwms.util.EncryptUtil;
import com.zhannicholas.cpwms.util.ToMapUtil;
import com.zhannicholas.cpwms.domain.dto.RepoAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static com.zhannicholas.cpwms.util.Constants.*;

@Controller
@RequestMapping("/repositoryAdminManage")
public class repoAdminController {
    private final RepoAdminService repoAdminService;
    private final RespositoryService respositoryService;
    private final UserService userService;

    @Autowired
    public repoAdminController(RepoAdminService repoAdminService, UserService userService, RespositoryService respositoryService) {
        this.repoAdminService = repoAdminService;
        this.userService = userService;
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
        Pageable pageable = PageRequest.of(offset, limit);
        switch (searchType){
            case SEARCH_BY_ID:
                return repoAdminService.findByRepoAdminId(Integer.parseInt(keyWord));
            case SEARCH_ALL:
                return repoAdminService.findAll(pageable);
            case SEARCH_BY_NAME:
                return repoAdminService.findByRepoAdminNameContaining(keyWord, pageable);
            case SEARCH_BY_REPOSITORY_ID:
                return repoAdminService.findByRepoId(Integer.parseInt(keyWord));
            default:
                return repoAdminService.findAll(pageable);
        }
    }

    @RequestMapping(value = "getRepositoryAdminList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAllRepoAdmin(@RequestParam("searchType") String searchType,
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
     *  更新仓库管理员信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "updateRepositoryAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateRepoAdmin(@RequestBody RepoAdminDTO repoAdminDTO){
        RepoAdmin repoAdmin = new RepoAdmin();
        repoAdmin.setRepoAdminId(repoAdminDTO.getRepoAdminId());
        repoAdmin.setRepoAdminName(repoAdminDTO.getRepoAdminName());
        repoAdmin.setRepoAdminSex(repoAdminDTO.getRepoAdminSex());
        repoAdmin.setRepoAdminTel(repoAdminDTO.getRepoAdminTel());
        repoAdmin.setRepoAdminAddress(repoAdminDTO.getRepoAdminAddress());
        repoAdmin.setRepoAdminBirth(repoAdminDTO.getRepoAdminBirth());
        repoAdmin.setRepository(respositoryService.findById(repoAdminDTO.getRepoId()));

        System.out.println(repoAdmin);

        String result =repoAdminService.save(repoAdmin) ? Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        return ToMapUtil.fromString(result);
    }

    /**
     * 添加仓库管理员信息
     * @param repoAdmin 新的仓库管理员
     * @return  返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "insertRepositoryAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertRepoAdmin(@RequestBody RepoAdmin repoAdmin){

        System.out.println(repoAdmin);

        String result =repoAdminService.save(repoAdmin) ? Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        if(result.equals(RESULT_ERROR)){
            return ToMapUtil.fromString(result);
        }
        // 为添加的管理员创建账户
        User user = new User();
        user.setId(repoAdmin.getRepoAdminId());
        user.setUsername(repoAdmin.getRepoAdminName());
        try {
            String str1 = EncryptUtil.MD5(String.valueOf(repoAdmin.getRepoAdminId()));
            String password = EncryptUtil.MD5(str1 + repoAdmin.getRepoAdminId());
            user.setPassword(password);
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            result = RESULT_ERROR;
            return ToMapUtil.fromString(result);
        }
        userService.save(user);
        result = RESULT_SUCCESS;

        System.out.println("\n\n用户表插入成功");
        System.out.println(ToMapUtil.fromString(result));
        return ToMapUtil.fromString(result);
    }

    /**
     * 删除仓库管理员
     * @param repoAdminId   仓库管理员Id
     * @return  返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "deleteRepositoryAdmin/{repoAdminId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteRepoAdmin(@PathVariable("repoAdminId") int repoAdminId){
        String result =repoAdminService.delete(repoAdminId) ? Constants.RESULT_SUCCESS : Constants.RESULT_ERROR;
        if(result.equals(RESULT_ERROR)){
            return ToMapUtil.fromString(result);
        }
        System.out.println(result);
        result = userService.delete(repoAdminId) ? RESULT_SUCCESS : RESULT_ERROR;
        System.out.println("删除用户 " + repoAdminId + "：" + result);
        return ToMapUtil.fromString(result);
    }

}
