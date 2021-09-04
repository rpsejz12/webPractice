package tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
public class MemberTagHandler extends SimpleTagSupport{
	private String size;
	private String color;
	
	
	
	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public void doTag() throws IOException, JspException {
		JspContext context = getJspContext();
		JspWriter out = context.getOut();
		JspFragment body = getJspBody();
		StringBuffer sb=new StringBuffer();
		System.out.println("1");
	/*	MemberVO mem = (MemberVO) context.getAttribute("mem");*/

			sb.append("<p style = 'font-size:").append(size).append("; color:").append(color).append(";'>");
			out.println(sb.toString());
			if(body!=null) {
				body.invoke(null);
			}	
			out.println("</p>");
		System.out.println("4");
	}
	

}
