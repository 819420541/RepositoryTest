package d49.ssm.po;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import d49.ssm.validation.ValidGroupAdd;
import d49.ssm.validation.ValidGroupUpdate;

public class Items {
	
    private Integer id;

    @Size(min=1,max=30,message="{items.name.length.error}",groups= {ValidGroupAdd.class,ValidGroupUpdate.class})
    private String name;

    @Min(value=10,message="{items.price.min.error}",groups= {ValidGroupUpdate.class})
    private Float price;

    private String pic;

    @NotNull(message="{items.createtime.isNULL}",groups= {ValidGroupAdd.class})
    @Past(message="{items.createtime.past}",groups= {ValidGroupUpdate.class})
    private Date createtime;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}