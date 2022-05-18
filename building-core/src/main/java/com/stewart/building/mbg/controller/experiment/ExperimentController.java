package com.stewart.building.mbg.controller.experiment;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import com.stewart.building.common.renum.ReturnEnum;
import com.stewart.building.common.renum.SuccessEnum;
import com.stewart.building.mbg.pojo.Experiment;
import com.stewart.building.mbg.service.IExperimentService;
import com.stewart.building.param.experiment.AddExperimentParam;
import com.stewart.building.param.experiment.GetAllExperimentParam;
import com.stewart.building.param.experiment.UpdateExperimentParam;
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
@Api(tags = "实验模块")
@RestController
@RequestMapping("/experiment")
@CrossOrigin
public class ExperimentController {

    @Autowired
    private IExperimentService experimentService;

    @ApiOperation(value = "分页/模糊 查询所有的实验")
    @PostMapping("/getAllExperimentByPage")
    public R getAll(@Valid @RequestBody GetAllExperimentParam getAllExperiment) {
        Page<Experiment> list = experimentService.getAll(getAllExperiment);
        return R.ok(ResultStatus.SELECT_SUCCESS, list);
    }

    @ApiOperation(value = "添加实验")
    @PostMapping("/addExperiment")
    public R addExperiment(@Valid @RequestBody AddExperimentParam addExperimentParam) {
        if (addExperimentParam.getMaxStuNum() < addExperimentParam.getMinStuNum()) {
            return R.error(ResultStatus.MIN_NOT_GREATER_MAX);
        }
        int res = experimentService.addExperiment(addExperimentParam);
        if (res == 0) {
            return R.error(ResultStatus.ADD_ERROR);
        }
        return R.ok(ResultStatus.ADD_SUCCESS);
    }


    @ApiOperation(value = "修改实验")
    @PostMapping("/updateExperiment")
    public R updateExperiment(@Valid @RequestBody UpdateExperimentParam updateExperimentParam) {
        if (updateExperimentParam.getMaxStuNum() < updateExperimentParam.getMinStuNum()) {
            return R.error(ResultStatus.MIN_NOT_GREATER_MAX);
        }
        int res = experimentService.updateExperiment(updateExperimentParam);
        if (res == SuccessEnum.ID_NOT_EXIST.ordinal()) {
            return R.error(ResultStatus.ID_NOT_EXIST);
        } else if (res == SuccessEnum.ERROR_CODE.ordinal()) {
            return R.error(ResultStatus.UPDATE_ERROR);
        }
        return R.ok(ResultStatus.UPDATE_SUCCESS);
    }

    @ApiOperation(value = "根据实验id删除实验")
    @GetMapping("/deleteExperimentById/{id}")
    public R deleteExperimentById(@PathVariable Integer id) {
        return experimentService.deleteExperimentById(id);
    }

    @ApiOperation(value = "根据实验id查询实验")
    @GetMapping("/getExperimentById/{id}")
    public R getExperimentById(@PathVariable Integer id){
        if (StringUtils.isEmpty(id)){
            return R.error(ResultStatus.NEED_ID);
        }
        Experiment result = experimentService.getExperimentById(id);
        return R.ok(ResultStatus.SELECT_SUCCESS,result);
    }
}
