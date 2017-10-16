package priv.roc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.roc.common.proxy.CrudService;
import priv.roc.dao.ProductCopyDao;
import priv.roc.entity.ProductCopy;

/**
 * .
 *
 * @author ZCP
 * @version 2017-10-12
 **/
@Service("productCopyService")
@Transactional(readOnly = true)
public class ProductCopyService extends CrudService<ProductCopyDao,ProductCopy> {

}
