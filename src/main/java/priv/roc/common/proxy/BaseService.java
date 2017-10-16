package priv.roc.common.proxy;

import org.springframework.transaction.annotation.Transactional;

/**
 * Service基类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class BaseService {

}