package priv.roc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.roc.common.proxy.Page;
import priv.roc.entity.ProductCopy;
import priv.roc.service.ProductCopyService;
import priv.roc.utils.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * .
 * product_copy
 * @author ZCP
 * @version 2017-10-12
 **/
@Controller
@RequestMapping(value = "/show")
public class ProductCopyController {
    @Autowired
    private ProductCopyService productCopyService;

    @ModelAttribute
    public ProductCopy get(@RequestParam(required=false) String id) {
        ProductCopy entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = productCopyService.get(id);
        }
        if (entity == null){
            entity = new ProductCopy();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    @ResponseBody
    public Page<ProductCopy> list(ProductCopy productCopy,HttpServletRequest request, HttpServletResponse response, Model model){
        Page<ProductCopy> page = productCopyService.findPage(new Page<ProductCopy>(request, response), productCopy);
        model.addAttribute("page", page);
//        return "index";
        return page;
    }

}
