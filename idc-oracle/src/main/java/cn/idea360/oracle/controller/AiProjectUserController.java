package cn.idea360.oracle.controller;

import cn.idea360.oracle.dto.AiProjectUserQueryDTO;
import cn.idea360.oracle.service.AiProjectUserService;
import cn.idea360.oracle.vo.AiProjectGroupUserRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ProjectUser")
public class AiProjectUserController {

    @Autowired
    private AiProjectUserService aiProjectUserService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public Object filterAiProjectUser(@RequestBody AiProjectUserQueryDTO aiProjectUserQueryDTO) {

        AiProjectGroupUserRespVO data = null;
        try {
            data = aiProjectUserService.filterAiProjectUser(aiProjectUserQueryDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return R.ok(data);
    }
}
