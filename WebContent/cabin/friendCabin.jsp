<%@page import="Database.AchievementDaoImpl"%>
<%@page import="org.omg.CORBA.FREE_MEM"%>
<%@page import="Database.RequestDaoImpl"%>
<%@page import="Database.ChallengeDaoImpl"%>
<%@page import="Database.QuizDaoImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.User"%>
<%@page import="dataSrc.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="Database.UserDaoImpl"%>

<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>


<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->


<%
	int friendID = Integer.parseInt(request.getParameter("friendID"));
	Connection con;
	con = DataSource.getInstance().getConnection();
	UserDaoImpl user = new UserDaoImpl(con);
	User friend = user.getUserById(friendID);
	QuizDaoImpl qzImpl = new QuizDaoImpl(con);
	ChallengeDaoImpl chImpl = new ChallengeDaoImpl(con);
	RequestDaoImpl reqImpl = new RequestDaoImpl(con);
	MessageDaoImpl msgImpl = new MessageDaoImpl(con);
	AchievementDaoImpl achDaoImpl = new AchievementDaoImpl(con);
	friend.setQuizesCreated(qzImpl.userCreatedQuizes(friendID));
	friend.setQuizesTaken(qzImpl.userTakenQuizes(friendID));
	friend.setChallenges(chImpl.getUserChallenges(friendID));
	friend.setRequests(reqImpl.getUserRequests(friendID));
	friend.setMessages(msgImpl.getUserMessages(friendID));
	friend.setAchievements(achDaoImpl.getuserAchievements(friendID));
	
	ArrayList<Integer> frFrsIds = user.getUserFriends(friendID);
	ArrayList<User> friendFriends = new ArrayList<User>();
	friend.setFriends(friendFriends);
	for(int i=0; i<frFrsIds.size(); i++){
		friendFriends.add(user.getUserById(frFrsIds.get(i)));
	}
%>
<div align="center">
	<table>
		<tr>
			<td bgcolor="#444c57" height="200" width="175" valign="top"
				align="center"><img src="<%=friend.getPhoto()%>" vspace="10"
				width=80 height=80 class="img-circle"></img></a>
				<p align="center">
					<font size="+1.5" color="black"> <%=friend.getUsername()%></font>
				</p></td>
		</tr>
	</table>
</div>

<div align="center">E-Mail: <%=friend.getMail()%></div>
<br>

<div align="center">
		Friends:<%=friend.getFriends().size() %>
</div>
<div align="center">Quizzes Taken: <%=friend.getQuizesTaken().size() %></div>
<div align="center">Achievements: <%=friend.getAchievements().size() %></div>

<%if(friend.isAdmin() == true){
%>	<div align="center">Quizzes Created: <%=friend.getQuizesCreated().size() %></div>
<% }%>


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>