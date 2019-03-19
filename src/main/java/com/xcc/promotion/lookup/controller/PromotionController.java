package com.xcc.promotion.lookup.controller;

import com.xcc.promotion.lookup.model.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class PromotionController {
    private static final Logger logger = LoggerFactory.getLogger(PromotionController.class);
    private final DataSource dataSource;

    public PromotionController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping(value = "/rest/promos", method = RequestMethod.GET)
    public @ResponseBody
    List<Promotion> getAllActivePromotion() {
        logger.info("Start getAllActivePromotion.");
        String query = "select PE.EVENTIDENTIFIER,PS.DESCRIPTION,PE.STARTDATETIME ,PE.ENDDATETIME" +
        " from PROMOTIONEVENT PE,PROMOTIONSET PS" +
        " where PE.EVENTIDENTIFIER=PS.EVENTIDENTIFIER" +
        " AND PE.STARTDATETIME >= sysdate -100" +
        " order by PE.STARTDATETIME";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Map<String,Object>> promoRows = jdbcTemplate.queryForList(query);
        List<Promotion> promoList = promoRows.stream()
                .map(record -> new Promotion(String.valueOf(record.get("EVENTIDENTIFIER")),
                        String.valueOf(record.get("DESCRIPTION")),
                        Date.valueOf(((Timestamp) record.get("STARTDATETIME")).toLocalDateTime().toLocalDate()),
                        Date.valueOf(((Timestamp) record.get("ENDDATETIME")).toLocalDateTime().toLocalDate())))
                .collect(Collectors.toList());
        return promoList;
    }
}
