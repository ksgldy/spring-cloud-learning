package cn.idea360.oracle.controller;

import cn.idea360.oracle.dto.AiProjectGroupReqDTO;
import cn.idea360.oracle.vo.AiProjectGroupReqVo;
import cn.idea360.oracle.service.AiProjectGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AiProjectGroupController {

    @Autowired
    private AiProjectGroupService aiProjectGroupService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object addProjectGroup(@RequestBody AiProjectGroupReqVo aiProjectGroupReqVo) {


        if (StringUtils.isEmpty(aiProjectGroupReqVo.getGroupName()) || CollectionUtils.isEmpty(aiProjectGroupReqVo.getUserIds())) {
            HashMap<Object, Object> resp = new HashMap<>();
            resp.put("code", -1);
            resp.put("msg", "参数错误");
            return resp;
        }

        AiProjectGroupReqDTO aiProjectGroupReqDTO = new AiProjectGroupReqDTO();
        BeanUtils.copyProperties(aiProjectGroupReqVo, aiProjectGroupReqDTO);
        try {
            aiProjectGroupService.addProjectGroup(aiProjectGroupReqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<Object, Object> resp = new HashMap<>();
            resp.put("code", -1);
            resp.put("msg", "插入失败");
            return resp;

        }

        HashMap<Object, Object> resp = new HashMap<>();
        resp.put("code", 0);
        resp.put("msg", "success");
        return resp;
    }


}
