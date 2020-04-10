package cn.idea360.oracle.controller;

import cn.idea360.oracle.dto.AiProjectUserQueryDTO;
import cn.idea360.oracle.service.AiProjectUserService;
import cn.idea360.oracle.vo.AiProjectGroupUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ProjectUser")
public class AiProjectUserController {

    @Autowired
    private AiProjectUserService aiProjectUserService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public Object filterAiProjectUser(@RequestBody(required = false) AiProjectUserQueryDTO aiProjectUserQueryDTO) {

        List<AiProjectGroupUserVO> aiProjectGroupUserVOS = aiProjectUserService.filterAiProjectUser(aiProjectUserQueryDTO);

        return R.ok(aiProjectGroupUserVOS);
    }
}
