package com.js.service.program;

import com.js.enums.program.ProgramDateEnum;
import com.js.vo.program.DefaultProgramDateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 姜爽
 * @Description: 节目日期范围获取
 * @Date: 2020/5/9 11:04
 */
@Service
@Slf4j
public class ProgramDateService {
    public List<DefaultProgramDateVo> getDefaultProgramDate() {
        List<DefaultProgramDateVo> defaultProgramDateVos = new ArrayList<>();
        ProgramDateEnum[] programDateEnums = ProgramDateEnum.values();
        for (ProgramDateEnum programDateEnum : programDateEnums) {
            DefaultProgramDateVo defaultProgramDateVo = DefaultProgramDateVo.builder().code(programDateEnum.getCode())
                    .msg(programDateEnum.getMsg()).dayOfWeek(programDateEnum.getDayOfWeek()).startTime(programDateEnum.getStartTime()).endTime(programDateEnum.getEndTime()).build();
            defaultProgramDateVos.add(defaultProgramDateVo);
        }
        return defaultProgramDateVos;
    }
    public DefaultProgramDateVo getDefaultProgramDateByCode(String code) {
        DefaultProgramDateVo defaultProgramDateVo = ProgramDateEnum.getEnumValues(code);
        if (defaultProgramDateVo == null){
            defaultProgramDateVo = ProgramDateEnum.getEnumValues("00");
        }
        return defaultProgramDateVo;
    }
}
