package cn.ananyz.cp.service.schedule;

import cn.ananyz.cp.service.controller.CpDataResultController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class ScheduleCpDataResult {

    @Autowired
    private CpDataResultController cpDataResultController;

    public void analyz() throws IOException, ParseException {
        cpDataResultController.selectCruNum();
        cpDataResultController.analyz();
    }
}
