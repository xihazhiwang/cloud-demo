package com.base.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    public static Boolean canVisitDb = false;

    @RequestMapping(value = "/db/{canVisitDb}", method = RequestMethod.GET)
    public String setConnectState(@PathVariable("canVisitDb") Boolean canVisitDb) {
        canVisitDb = canVisitDb;
        return "当前数据库是否正常：" + this.canVisitDb;
    }

}
