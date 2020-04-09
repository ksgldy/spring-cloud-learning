package cn.idea360.oracle.controller;

import cn.idea360.oracle.dto.FilterProjectUserDTO;
import cn.idea360.oracle.service.AiProjectUserService;
import cn.idea360.oracle.vo.AiProjectGroupUserRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/ProjectUser")
public class AiProjectUserController {

    @Autowired
    private AiProjectUserService aiProjectUserService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public Object filterAiProjectUser(FilterProjectUserDTO filterProjectUserDTO) {

        AiProjectGroupUserRespVO data = null;
        try {
            data = aiProjectUserService.filterAiProjectUser(filterProjectUserDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<Object, Object> resp = new HashMap<>();
        resp.put("code", 0);
        resp.put("msg", "success");
        resp.put("data", data);
        return resp;
    }
}
