
<%@page import="java.util.ArrayList"%>
<%@page import="Model.User"%>
<%@page import="dataSrc.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="Database.UserDaoImpl"%>
<%int friendID = Integer.parseInt(request.getParameter("friendID"));
	Connection con;
	con = DataSource.getInstance().getConnection();
	UserDaoImpl user = new UserDaoImpl(con);
	User friend = user.getUserById(friendID);
%>
<div align="center">
<table>
<tr>
<td bgcolor="#444c57" height="200" width="175" valign="top"
					align="center">
					<a href =<%="\"./friendCabin.jsp?friendID=" + friendID + 
				 			"\""%>>
					<img src="<%=friend.getPhoto()%>" vspace="10" width=80 height=80
					class="img-circle"></img></a>
					<p align="center">
						<font size="+1.5" color="black"> <%=friend.getUsername()%></font>
					</p>
				</td>
				</tr>
				</table>
				</div>