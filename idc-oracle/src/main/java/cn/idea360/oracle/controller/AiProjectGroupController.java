package cn.idea360.oracle.controller;

import cn.idea360.oracle.dto.AiProjectGroupReqDTO;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.PageRespDTO;
import cn.idea360.oracle.service.AiProjectGroupService;
import cn.idea360.oracle.vo.AiProjectGroupDetailRespVO;
import cn.idea360.oracle.vo.AiProjectGroupReqVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

        PageRespDTO pageRespDTO = aiProjectGroupService.pageProjectGroup(pageDTO);

        return R.ok(pageRespDTO);

    }


    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public Object delProjectGroup(@PathVariable Long id) {
        aiProjectGroupService.delProjectGroupById(id);
        return R.ok();
    }

    /**
     * 当前分组已选客服
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/detail/{groupId}", method = RequestMethod.GET)
    public Object filterAiProjectUser(@PathVariable Long groupId) {

        AiProjectGroupDetailRespVO aiProjectGroupDetailRespVO = aiProjectGroupService.currGroupDataByGroupId(groupId);
        return R.ok(aiProjectGroupDetailRespVO);
    }


}
