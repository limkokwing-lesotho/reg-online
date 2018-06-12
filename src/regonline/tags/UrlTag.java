package regonline.tags;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class UrlTag extends SimpleTagSupport {
	private String type;
	private Serializable id;

	public void setType(String msg) {
		this.type = msg;
	}
	
	public void setId(Serializable id) {
		this.id = id;
	}
	
	public void doTag() throws JspException, IOException {
		if (type != null) {
			JspWriter out = getJspContext().getOut();
			if(type.equalsIgnoreCase("Update")){
				out.println("<a href='./?e="+id+"'>"+type+"</a>");
			}
			else if(type.equalsIgnoreCase("Delete")){
				out.println("<a href='./?d="+id+"'>"+type+"</a>");
			}
			else{
				throw new JspException("Unknown type '"+type+"'");
			}
			
		} else {
			//TODO ??????
		}
	}
}