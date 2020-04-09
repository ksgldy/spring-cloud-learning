package cn.idea360.oracle.controller;

import cn.idea360.oracle.dto.AiProjectGroupReqDTO;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.ProjectGroupDTO;
import cn.idea360.oracle.vo.AiProjectGroupReqVo;
import cn.idea360.oracle.service.AiProjectGroupService;
import cn.idea360.oracle.vo.PageRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ProjectGroup")
public class AiProjectGroupController {

    @Autowired
    private AiProjectGroupService aiProjectGroupService;

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public Object addProjectGroup(@RequestBody AiProjectGroupReqVo aiProjectGroupReqVo) {

        if (StringUtils.isEmpty(aiProjectGroupReqVo.getGroupName()) || CollectionUtils.isEmpty(aiProjectGroupReqVo.getUserIds())) {
            return R.failed("参数错误");
        }


        AiProjectGroupReqDTO aiProjectGroupReqDTO = new AiProjectGroupReqDTO();
        BeanUtils.copyProperties(aiProjectGroupReqVo, aiProjectGroupReqDTO);

        // TODO session取数据
        aiProjectGroupReqDTO.setCompanyId(1);
        aiProjectGroupReqDTO.setCreater("blm");
        aiProjectGroupReqDTO.setUpdater("blm");

        boolean respState = Boolean.FALSE;
        try {
            if (aiProjectGroupReqVo.getId() == null) {
                respState = aiProjectGroupService.addProjectGroup(aiProjectGroupReqDTO);
            } else {
                respState = aiProjectGroupService.updateProjectGroup(aiProjectGroupReqDTO);
            }

            if (!respState) {
                return R.failed(R.Code.FAILED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                return R.failed("数据冲突");
            }
            if (e instanceof NullPointerException) {
                return R.failed("数据不存在");
            }
        }
        return R.ok();
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Object page(@RequestBody PageDTO pageDTO) {

        List<ProjectGroupDTO> data = new ArrayList<>();
        try {
            data = aiProjectGroupService.pageProjectGroup(pageDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageRespVO page = new PageRespVO();
        page.setPageNum(pageDTO.getPage());
        page.setSize(pageDTO.getSize());
        page.setRecords(data);
        page.setTotal(aiProjectGroupService.totalRecord());

        HashMap<Object, Object> resp = new HashMap<>();
        resp.put("code", 0);
        resp.put("msg", "success");
        resp.put("data", page);

        return resp;
    }


    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    public Object delProjectGroup(@PathVariable Long id) {
        HashMap<Object, Object> resp = new HashMap<>();
        resp.put("code", -1);
        try {
            boolean b = aiProjectGroupService.delProjectGroupById(id);
            if (!b) {
                resp.put("msg", "删除失败");
                return resp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.put("msg", "删除失败");
            return resp;
        }
        resp.put("msg", "删除成功");
        return resp;
    }


}
