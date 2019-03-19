package com.xcc.promotion.lookup.controller;

import com.xcc.promotion.lookup.manager.PromotionManager;
import com.xcc.promotion.lookup.model.Promotion;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "Promotions", description = "REST API for Promotions", tags = { "Promotions" })
@RestController
public class PromotionController {
    private static final Logger logger = LoggerFactory.getLogger(PromotionController.class);

    private final PromotionManager promotionManager;

    public PromotionController(PromotionManager promotionManager) {
        this.promotionManager = promotionManager;
    }

    @ApiOperation(value = "List of all Active Promotion", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/rest/promos", method = RequestMethod.GET)
    public @ResponseBody
    List<Promotion> getAllActivePromotion(@ApiParam(
            value = "Possible values are JU,CA & DB, defaulted to CA if param is null" ,
            example = "JU") @RequestParam String brand) {
        logger.info("Inside getAllActivePromotion method");
        return promotionManager.getAllActivePromotion(brand);
    }
}
