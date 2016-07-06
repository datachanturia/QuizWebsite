<%@ page import="ULS.AccountManager" import="dataSrc.DataSource"
	import="Database.ChallengeDaoImpl" import="MRC.ChallengeManager"
	import="Model.Challenge" import="Database.PostDaoImpl"
	import="Model.Post" import="java.util.ArrayList"%>

</div>
<!-- /col-lg-9 END SECTION MIDDLE -->



<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<div class="col-lg-3 ds">
	<!-- Admin Posts Start -->
	<h3>ADMIN POSTS</h3>

	<%
		java.util.ArrayList<Model.Post> posts = new java.util.ArrayList<Model.Post>();

		try {
			conn = dataSrc.DataSource.getInstance().getConnection();
			Database.PostDaoImpl pdi = new Database.PostDaoImpl(conn);

			posts = pdi.getPosts();

		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (java.sql.SQLException e) {
					e.printStackTrace();
				}
		}
	%>
	<%
		for (int ii = 0; ii < 4 && ii < posts.size(); ii++) {
	%>
	<div class="desc">
		<div class="thumb">
			<span><i class="fa"></i></span>
		</div>
		<div class="details">
			<p>
				<br /> <a href="#"><%=posts.get(ii).getAdminName()%>: </a>
				<%=posts.get(ii).getPost()%><br />
			</p>
		</div>
	</div>
	<%
		}
	%>
	<!-- Admin Posts End -->

	<!-- Challenges Start -->
	<h3>
		NEW CHALLENGES <a href="./ServletChallengeList"> -- see all</a>
	</h3>

	<%
		ChallengeManager challmanager = null;
		am = (AccountManager) getServletContext().getAttribute("AccMan");

		int userId = am.getUser().getUserID();
		try {
			conn = DataSource.getInstance().getConnection();

			ChallengeDaoImpl cdi = new ChallengeDaoImpl(conn);
			challmanager = new ChallengeManager(conn);
			challmanager.setUserID(userId);
			challmanager.setChallenges(cdi.getUserChallenges(userId));
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (java.sql.SQLException e) {
					e.printStackTrace();
				}
		}

		ArrayList<Challenge> chall = challmanager.getChallenges();
		Challenge ch = null;
		String sender = "";
		String text = " wants to challenge you in quiz: ";
		String quizname = "";

		for (int ii = 0; ii < 4 && ii < chall.size(); ii++) {
			ch = chall.get(ii);
			sender = ch.getSenderName();
			quizname = ch.getQuizName();
			out.println(sender + text);
			out.println(quizname);
	%>

	<form name="challengeAccept<%=ii%>" method="Get"
		action="./ServletAcceptChallenge">
		<a href='#'
			onclick="document.forms['challengeAccept<%=ii%>'].submit()">
			Accept </a> <input type="hidden" id="challengerID" name="challengerID"
			value=<%=ch.getSenderID()%>> <input type="hidden" id="quizID"
			name="quizID" value=<%=ch.getQuizID()%>> <input type="hidden"
			id="challengeID" name="challengeID" value=<%=ch.getChallengeID()%>>
	</form>

	<form name="challengeReject<%=ii%>" method="Get"
		action="./ServletRejectChallenge">
		<a href='#'
			onclick="document.forms['challengeReject<%=ii%>'].submit()">
			Reject </a> <input type="hidden" id="challengeID" name="challengeID"
			value=<%=ch.getChallengeID()%>>
	</form>

	<div class="desc"></div>
	<%
		}
	%>
	<!-- Challenges End -->
</div>
<!-- RIGHT SLIDEBAR CONTENT END -->

</div>
<!--MAIN + RIGHT END -->
</section>
</section>
<!--main content end-->

<!--footer start
<footer class="site-footer">
	<div class="text-center">
		2016 - www.freeuni.edu.ge - QUIZ WEBSITE <a href="#" class="go-top">
			<i class="fa fa-angle-up"></i>
		</a>
	</div>
</footer>
footer end-->

</section>
</body>
</html>