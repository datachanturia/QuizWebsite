<%@page import="Database.PostDaoImpl"%>
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
		for (int ii = 0; ii < 4; ii++) {
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
		for (int ii = 0; ii < 4; ii++) {
	%>
	<div class="desc">
		<div class="thumb">
			<img class="img-circle" src="./loggedIn/assets/img/ui-divya.jpg"
				width="35px" height="35px">
		</div>
		<div class="details">
			<p>
				<a href="#">DIVYA MANIAN</a><br />
			</p>
		</div>
	</div>
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

<!--footer start-->
<footer class="site-footer">
	<div class="text-center">
		2016 - www.freeuni.edu.ge - QUIZ WEBSITE <a href="#" class="go-top">
			<i class="fa fa-angle-up"></i>
		</a>
	</div>
</footer>
<!--footer end-->

</section>
</body>
</html>