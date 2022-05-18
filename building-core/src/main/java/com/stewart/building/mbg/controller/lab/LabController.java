package com.stewart.building.mbg.controller.lab;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import com.stewart.building.common.renum.ReturnEnum;
import com.stewart.building.common.renum.SuccessEnum;
import com.stewart.building.mbg.pojo.Lab;
import com.stewart.building.mbg.service.ILabService;
import com.stewart.building.param.lab.AddLabParam;
import com.stewart.building.param.lab.GetAllLabPageParam;
import com.stewart.building.param.lab.UpdateLabParam;
import com.stewart.building.util.ControllerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
@Api(tags = "实验室模块")
@RestController
@RequestMapping("/lab")
@CrossOrigin
public class LabController {

    @Autowired
    private ILabService labService;

    @ApiOperation(value = "分页查询实验室")
    @PostMapping("/getAll")
    public R getAll(@Valid @RequestBody GetAllLabPageParam labGetAllLabPage){
        Page<Lab> result = labService.getAll(labGetAllLabPage.getSize(), labGetAllLabPage.getCurrent(), labGetAllLabPage.getName());
        return R.ok(ResultStatus.SELECT_SUCCESS,result);
    }
    @ApiOperation(value = "添加实验室")
    @PostMapping("/add")
    public R addLab(@Valid @RequestBody AddLabParam addLabParam){
        int res = labService.addLab(addLabParam);
        if(res==0){
            return R.error(ResultStatus.ADD_ERROR);
        }
        return R.ok(ResultStatus.ADD_SUCCESS);
    }

    @ApiOperation(value = "修改实验室")
    @PostMapping("/update")
    public R updateLab(@Valid @RequestBody UpdateLabParam updateLabParam){
        int res = labService.updateLab(updateLabParam);
        if(res==0) {
            return R.error(ResultStatus.UPDATE_ERROR);
        }
        return R.ok(ResultStatus.UPDATE_SUCCESS);
    }

    @ApiOperation(value = "根据实验室id查询实验室信息")
    @GetMapping("/getLabById/{id}")
    public R getLabById(@PathVariable Integer id){

        if(StringUtils.isEmpty(id)){
            return R.error(ResultStatus.NOT_ID);
        }

        Lab lab = labService.getLabById(id);
        if(lab==null){
            return R.error(ResultStatus.ID_NOT_EXIST);
        }
        return R.ok(ResultStatus.SELECT_SUCCESS,lab);
    }

    @ApiOperation(value = "根据实验id删除实验室信息")
    @GetMapping("/deleteLabById/{id}")
    public R deleteLabById(@PathVariable Integer id){
        return labService.deleteLabById(id);
    }




}
