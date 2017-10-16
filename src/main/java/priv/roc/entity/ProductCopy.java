package priv.roc.entity;

import priv.roc.common.proxy.DataEntity;

/**
 * .
 *
 * @author ZCP
 * @version 2017-10-12
 **/
public class ProductCopy extends DataEntity<ProductCopy> {
    private static final long serialVersionUID = 1L;

    private String name;
    private String validFlag; //0暂存中 1待审核 2已上架 3已下架 8 审核不通过 9已冻结
    private String displayOnCRMFlag; //是否在crm可以被搜索到 1是 0否

    private Integer crmBigTypeId;//crm一级商品分类
    private Integer crmSmallTypeId;//crm二级商品分类

    public ProductCopy() {
        super();
    }

    public ProductCopy(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getDisplayOnCRMFlag() {
        return displayOnCRMFlag;
    }

    public void setDisplayOnCRMFlag(String displayOnCRMFlag) {
        this.displayOnCRMFlag = displayOnCRMFlag;
    }

    public Integer getCrmBigTypeId() {
        return crmBigTypeId;
    }

    public void setCrmBigTypeId(Integer crmBigTypeId) {
        this.crmBigTypeId = crmBigTypeId;
    }

    public Integer getCrmSmallTypeId() {
        return crmSmallTypeId;
    }

    public void setCrmSmallTypeId(Integer crmSmallTypeId) {
        this.crmSmallTypeId = crmSmallTypeId;
    }
}
