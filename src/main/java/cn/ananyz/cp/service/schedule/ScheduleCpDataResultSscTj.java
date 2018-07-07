package cn.ananyz.cp.service.schedule;

import cn.ananyz.cp.service.controller.CpDataResultSscTjController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class ScheduleCpDataResultSscTj {

    @Autowired
    private CpDataResultSscTjController cpDataResultSscTjController;

    public void analyz() throws IOException, ParseException {
        cpDataResultSscTjController.selectCruNum();
        cpDataResultSscTjController.analyz();
    }
}
