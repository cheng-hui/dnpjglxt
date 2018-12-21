package com.zhannicholas.cpwms.controller;

import com.zhannicholas.cpwms.service.RecordService;
import com.zhannicholas.cpwms.service.RespositoryService;
import com.zhannicholas.cpwms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.Map;

import static com.zhannicholas.cpwms.util.Constants.SEARCH_STOCK_IN_ONLY;
import static com.zhannicholas.cpwms.util.Constants.SEARCH_STOCK_OUT_ONLY;

@Controller
@RequestMapping("recordManage")
public class RecordController {
    private final RecordService recordService;
    private final RespositoryService respositoryService;

    @Autowired
    public RecordController(RecordService recordService, RespositoryService respositoryService) {
        this.recordService = recordService;
        this.respositoryService = respositoryService;
    }

    /**
     * 通用查询方法
     * @param searchType    查询类型
     * @param repoId    仓库Id
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param limit 分页大小
     * @param offset    分页偏移量
     * @return  符合要求的出入库记录
     */
    private Map<String, Object> query(String searchType, int repoId, Date startDate, Date endDate, int offset, int limit){
        Pageable pageable = PageRequest.of(offset, limit);
        switch (searchType){
            case SEARCH_STOCK_IN_ONLY:  // 只查询入库记录
                if(respositoryService.isValidRepoId(repoId)){
                    // 指定仓库
                    return recordService.findInRecordUsingRepoIdAndDate(repoId, startDate, endDate, pageable);
                }
                // 所有仓库
                return recordService.findInRecordUsingDate(startDate, endDate, pageable);
            case SEARCH_STOCK_OUT_ONLY: // 只查询出库记录
                if(respositoryService.isValidRepoId(repoId)){
                    // 指定仓库
                    return recordService.findOutRecordUsingRepoIdAndDate(repoId, startDate, endDate, pageable);
                }
                // 所有仓库
                return recordService.findOutRecordUsingDate(startDate, endDate, pageable);
            default:   // 查询所有
                int recordInOffset = offset / 2;
                int recordOutOffset = recordInOffset * 2 < offset ? recordInOffset + 1 : recordInOffset;
                int recordInLimit = limit / 2;
                int recordOutLimit = recordInLimit * 2 < limit ? recordInLimit + 1 : recordInLimit;
                Pageable recordInPageable = PageRequest.of(recordInOffset, recordInLimit);
                Pageable recordOutPageable = PageRequest.of(recordOutOffset, recordOutLimit);
                if(respositoryService.isValidRepoId(repoId)){
                    // 指定仓库
                    return recordService.findUsingRepoIdAndDate(repoId, startDate, endDate, recordInPageable, recordOutPageable);
                }
                // 不指定仓库
                return recordService.findUsingDate(startDate, endDate, recordInPageable, recordOutPageable);
        }
    }

    /**
     * 查询出入库记录
     *
     * @param searchType      查询类型（查询所有或仅查询入库记录或仅查询出库记录）
     * @param repoId 查询记录所对应的仓库ID
     * @param endDateStr      查询的记录起始日期
     * @param startDateStr    查询的记录结束日期
     * @param limit           分页大小
     * @param offset          分页偏移值
     * @return 返回一个Map，其中：Key为rows的值代表所有记录数据，Key为total的值代表记录的总条数
     */
    @ResponseBody
    @RequestMapping("searchRecord")
    public Map<String, Object> searchStockRecord(@RequestParam("searchType") String searchType,
                                  @RequestParam(value = "repoId", defaultValue = "-1") int repoId,
                                  @RequestParam("startDate") String startDateStr,
                                  @RequestParam("endDate") String endDateStr,
                                  @RequestParam(value = "limit", defaultValue = "5") int limit,
                                  @RequestParam(value = "offset", defaultValue = "0") int offset){
        System.out.println("searchType: " + searchType +
                "\nrepoId: " + repoId +
                "\nstartDate: " + startDateStr +
                "\nendDate: " + endDateStr +
                "\noffset: " + offset +
                "\nlimit: " + limit);
        return query(searchType, repoId, DateUtil.fromDateStr(startDateStr, false), DateUtil.fromDateStr(endDateStr, true), offset < limit ? 0 : offset / limit, limit);
    }
}
