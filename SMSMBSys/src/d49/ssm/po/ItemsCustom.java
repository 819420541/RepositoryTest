package d49.ssm.po;

import java.util.Date;

public class ItemsCustom  extends Items{

    //可以方便进行属性的扩展
    private Float priceMin;
    
    private Float priceMax;
    
    private Date createtimeMin;
    
    private Date createtimeMax;

	public Float getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(Float priceMin) {
		this.priceMin = priceMin;
	}

	public Float getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(Float priceMax) {
		this.priceMax = priceMax;
	}

	public Date getCreatetimeMin() {
		return createtimeMin;
	}

	public void setCreatetimeMin(Date createtimeMin) {
		this.createtimeMin = createtimeMin;
	}

	public Date getCreatetimeMax() {
		return createtimeMax;
	}

	public void setCreatetimeMax(Date createtimeMax) {
		this.createtimeMax = createtimeMax;
	}
    
}


