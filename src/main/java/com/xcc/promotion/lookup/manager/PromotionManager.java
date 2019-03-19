package com.xcc.promotion.lookup.manager;

import com.xcc.promotion.lookup.controller.PromotionController;
import com.xcc.promotion.lookup.model.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PromotionManager {
    private static final Logger logger = LoggerFactory.getLogger(PromotionManager.class);
    private final DataSource dataSource;

    public PromotionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Promotion> getAllActivePromotion() {
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
