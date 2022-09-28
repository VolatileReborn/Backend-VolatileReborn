package com.example.BackendVolatile.Controller;

import com.example.BackendVolatile.dto.squareDTO.EmployeeTaskDetailDTO;
import com.example.BackendVolatile.dto.squareDTO.TaskDetailDTO;
import com.example.BackendVolatile.service.SquareService;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.PassToken;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.vo.squareVO.BrowserTasksVO;
import com.example.BackendVolatile.vo.squareVO.VisitorBrowserTasksVO;
import com.example.BackendVolatile.vo.squareVO.EmployeeTaskDetailVO;
import com.example.BackendVolatile.vo.squareVO.TaskDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
//@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping(path = "/api/square")
public class SquareController {

    @Resource
    SquareService squareService;

    @GetMapping(value ="/visitorBrowserTasks" )
    @PassToken
    public VisitorBrowserTasksVO visitorBrowserTasks(){
        return squareService.visitorBrowserTasks();
    }

    @GetMapping(value ="/browserTasks" )
    @UserLoginToken
    public BrowserTasksVO browserTasks(){
        return squareService.browserTasks();
    }

    @GetMapping(value ="/employeeTaskDetail" )
    @UserLoginToken
    public EmployeeTaskDetailVO employeeTaskDetail(@Valid EmployeeTaskDetailDTO employeeTaskDetailDTO){
        return squareService.employeeTaskDetail(employeeTaskDetailDTO);
    }

    @GetMapping(value ="/taskDetail" )
    @PassToken
    public TaskDetailVO taskDetail(@Valid TaskDetailDTO taskDetailDTO){
        return squareService.taskDetail(taskDetailDTO);
    }


}
