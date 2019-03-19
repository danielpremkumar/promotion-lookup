package com.xcc.promotion.lookup.controller;

import com.xcc.promotion.lookup.model.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.ArrayList;
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
       // List<Promotion> promoList = new ArrayList<>();
        //JDBC Code - Start
        String query = "select id, name from Promotion";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Map<String,Object>> promoRows = jdbcTemplate.queryForList(query);
        List<Promotion> promoList = promoRows.stream()
                .map(record -> new Promotion(Integer.parseInt(String.valueOf(record.get("id"))),
                        String.valueOf(record.get("name"))))
                .collect(Collectors.toList());
        /*for(Map<String,Object> promoRow : promoRows){
            Promotion promotion = new Promotion();
            promotion.setId(Integer.parseInt(String.valueOf(promoRow.get("id"))));
            promotion.setName(String.valueOf(promoRow.get("name")));
            promoList.add(promotion);
        }*/

        return promoList;
    }
}
