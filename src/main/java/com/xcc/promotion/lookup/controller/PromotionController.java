package com.xcc.promotion.lookup.controller;

import com.xcc.promotion.lookup.manager.PromotionManager;
import com.xcc.promotion.lookup.model.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class PromotionController {
    private static final Logger logger = LoggerFactory.getLogger(PromotionController.class);

    private final PromotionManager promotionManager;

    public PromotionController(PromotionManager promotionManager) {
        this.promotionManager = promotionManager;
    }

    @RequestMapping(value = "/rest/promos", method = RequestMethod.GET)
    public @ResponseBody
    List<Promotion> getAllActivePromotion() {
        logger.info("Inside getAllActivePromotion method");
        return promotionManager.getAllActivePromotion();
    }
}
