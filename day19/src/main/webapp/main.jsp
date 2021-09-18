<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>

<!DOCTYPE HTML>
<!--
	Dimension by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Dimension by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<style>
ul {
	list-style: none;
}
.ulist {
	float: left;
}

.ad img{
float: center;
 width :100px;
 height :100px;
}
</style>
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<header id="header">
			<div class="logo">
				<span class="icon fa-gem"></span>
			</div>
			<div class = "ulist">
			<h3>새 맴버</h3>
			<c:forEach var="v" items="${ulist}">	
			<a href="main.do?qid=${v.id}">${v.id}</a>
			<br>
			</c:forEach>
			<br>
			</div>
			
			<div class="content">
				<div class="inner">
					<a href="main.do">전체목록보기</a>
					<c:forEach var="v" items="${datas}">
						<c:set var="m" value="${v.m}" />
						<h3>
							[${m.id}] ${m.msg} &gt;&gt;
							<mytag:fupdate mid = "${m.mid}" favcount =" ${m.favcount}"></mytag:fupdate>
							 | 댓글	${m.replycount} | ${m.mdate}]
							<span><mytag:delete type = "mdel" id ="${m.id}" mid ="${m.mid}"></mytag:delete></span>
						</h3>
						
						<ul>
							<c:forEach var="r" items="${v.rlist}">
								<li>${r.id}>>${r.rmsg}[${r.rdate}]
								<mytag:delete type = "rdel" id = "${r.id}"  mid = "${m.mid}" rid ="${r.rid}"></mytag:delete>
								</li>
							</c:forEach>

							<li>
							
							
							<a href="main.do?msgmid=${m.mid}&mcnt=${mcnt+3}&qid=${param.qid}">댓글 더보기  "${param.msgmid}"</a> &nbsp;&nbsp;
							<mytag:insert mid = "${m.mid}" rmid = "${param.rmid}" />
						
							</li>
						</ul>
					</c:forEach>
					<p>
						A fully responsive site template designed by <a
							href="https://html5up.net">HTML5 UP</a> and released<br /> for
						free under the <a href="https://html5up.net/license">Creative
							Commons</a> license.
					</p>
					<form action="control.jsp" method="post" name="form1">
						<input type="hidden" name="action" value="signup">

					</form>
				</div>
			</div>
			<nav>
				<mytag:login></mytag:login>
				<!-- <ul>
					<li><a href="#intro">로그인</a></li>
					<li><a href="#work">내정보</a></li>
					<li><a href="#contact">회원가입</a></li>
					<li><a href="#elements">Elements</a></li>
				</ul> -->
				<br>
				
			<div class = "ad">
			<a href="https://ko-kr.facebook.com/">
			<img alt="페이스북" src="images/facebook.png">
			</a>
			&nbsp;
			<a href="https://www.youtube.com/">
			<img alt="유튜브" src="images/youtube.png">
			</a>
			
			</div>
			</nav>
		</header>

		<!-- Main -->
		<div id="main">

			<!-- Intro -->
			<article id="login">
				<h2 class="major">로그인</h2>

				<form action="login.do" method="post" name="form1">
					<input type="hidden" name = "mcnt" value = "${mcnt}">
					<input type="hidden" name = "msgmid" value = "${param.msgmid}">
					<input type="hidden" name = "qid" value = "${param.qid}">
					<table border="1">
						<tr>
							<td>id</td>
							<td><input type="text" name="id" required></td>
						</tr>
						<tr>
							<td>password</td>
							<td><input type="password" name="passwd" required></td>
						</tr>
						<tr>
							<td colspan='2'><input type="submit" value="로그인"> <input
								type="button" value="회원가입"
								onClick="location.href='main.jsp#contact'"></td>
						</tr>
					</table>
				</form>
			</article>




			<article id="edit">							<!-- 초기화 되는걸로 -->
				<h2 class="major">정보수정</h2>
				<form action="uupdate.do" method="post" name="form2">
					<table border="1">
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" value="${mem.name}"
								readonly></td>
						</tr>
						<tr>
							<td>id</td>
							<td><input type="text" name="id" required value="${mem.id}"
								readonly></td>
						</tr>
						<tr>
							<td>password</td>
							<td><input type="password" name="passwd" required
								value="${mem.passwd}"></td>
						</tr>
						<tr>
							<td colspan='2'><input type="submit" value="정보수정"></td>
							<td><input type="button" value="회원탈퇴"
								onClick="location.href='signout.do'"></td>
						</tr>
					</table>
				</form>


				<ul class="icons">
					<li><a href="#" class="icon brands fa-twitter"><span
							class="label">Twitter</span></a></li>
					<li><a href="#" class="icon brands fa-facebook-f"><span
							class="label">Facebook</span></a></li>
					<li><a href="#" class="icon brands fa-instagram"><span
							class="label">Instagram</span></a></li>
					<li><a href="#" class="icon brands fa-github"><span
							class="label">GitHub</span></a></li>
				</ul>
			</article>





			<!-- Contact -->
			<article id="signup">
				<h2 class="major">회원가입</h2>			<!-- 회원가입도 초기화 되야지 -->
				<form action="signup.do" method="post" name="form3">
					<table border="1">
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" required></td>
						</tr>
						<tr>
							<td>id</td>
							<td><input type="text" name="id" required></td>
						</tr>
						<tr>
							<td>password</td>
							<td><input type="password" name="passwd" required></td>
						</tr>
						<tr>
							<td colspan='2'><input type="submit" value="회원가입"></td>
						</tr>
					</table>
				</form>
			</article>

			<article id="minsert">
				<h2 class="major">새글 쓰기</h2> 			<!-- 새글쓰기는 초기화가 되야지? -->
				<form action="minsert.do" method="post" name="form4">				
					<table border="1">
						<tr>
							<td>글쓴이</td>
							<td><input type="text" name="id" required value="${mem.id}"
								readonly></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><input type="text" name="msg" required></td>
						</tr>
						<tr>
							<td colspan='2'><input type="submit" value="새글 등록"></td>
						</tr>
					</table>
				</form>
			</article>



			<!-- Elements -->
			<article id="elements">
				<h2 class="major">Elements</h2>

				<section>
					<h3 class="major">Text</h3>
					<p>
						This is <b>bold</b> and this is <strong>strong</strong>. This is <i>italic</i>
						and this is <em>emphasized</em>. This is <sup>superscript</sup>
						text and this is <sub>subscript</sub> text. This is <u>underlined</u>
						and this is code:
						<code>for (;;) { ... }</code>
						. Finally, <a href="#">this is a link</a>.
					</p>
					<hr />
					<h2>Heading Level 2</h2>
					<h3>Heading Level 3</h3>
					<h4>Heading Level 4</h4>
					<h5>Heading Level 5</h5>
					<h6>Heading Level 6</h6>
					<hr />
					<h4>Blockquote</h4>
					<blockquote>Fringilla nisl. Donec accumsan interdum
						nisi, quis tincidunt felis sagittis eget tempus euismod.
						Vestibulum ante ipsum primis in faucibus vestibulum. Blandit
						adipiscing eu felis iaculis volutpat ac adipiscing accumsan
						faucibus. Vestibulum ante ipsum primis in faucibus lorem ipsum
						dolor sit amet nullam adipiscing eu felis.</blockquote>
					<h4>Preformatted</h4>
					<pre>
						<code>i = 0;

while (!deck.isInOrder()) {
    print 'Iteration ' + i;
    deck.shuffle();
    i++;
}

print 'It took ' + i + ' iterations to sort the deck.';</code>
					</pre>
				</section>

				<section>
					<h3 class="major">Lists</h3>

					<h4>Unordered</h4>
					<ul>
						<li>Dolor pulvinar etiam.</li>
						<li>Sagittis adipiscing.</li>
						<li>Felis enim feugiat.</li>
					</ul>

					<h4>Alternate</h4>
					<ul class="alt">
						<li>Dolor pulvinar etiam.</li>
						<li>Sagittis adipiscing.</li>
						<li>Felis enim feugiat.</li>
					</ul>

					<h4>Ordered</h4>
					<ol>
						<li>Dolor pulvinar etiam.</li>
						<li>Etiam vel felis viverra.</li>
						<li>Felis enim feugiat.</li>
						<li>Dolor pulvinar etiam.</li>
						<li>Etiam vel felis lorem.</li>
						<li>Felis enim et feugiat.</li>
					</ol>
					<h4>Icons</h4>
					<ul class="icons">
						<li><a href="#" class="icon brands fa-twitter"><span
								class="label">Twitter</span></a></li>
						<li><a href="#" class="icon brands fa-facebook-f"><span
								class="label">Facebook</span></a></li>
						<li><a href="#" class="icon brands fa-instagram"><span
								class="label">Instagram</span></a></li>
						<li><a href="#" class="icon brands fa-github"><span
								class="label">Github</span></a></li>
					</ul>

					<h4>Actions</h4>
					<ul class="actions">
						<li><a href="#" class="button primary">Default</a></li>
						<li><a href="#" class="button">Default</a></li>
					</ul>
					<ul class="actions stacked">
						<li><a href="#" class="button primary">Default</a></li>
						<li><a href="#" class="button">Default</a></li>
					</ul>
				</section>

				<section>
					<h3 class="major">Table</h3>
					<h4>Default</h4>
					<div class="table-wrapper">
						<table>
							<thead>
								<tr>
									<th>Name</th>
									<th>Description</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Item One</td>
									<td>Ante turpis integer aliquet porttitor.</td>
									<td>29.99</td>
								</tr>
								<tr>
									<td>Item Two</td>
									<td>Vis ac commodo adipiscing arcu aliquet.</td>
									<td>19.99</td>
								</tr>
								<tr>
									<td>Item Three</td>
									<td>Morbi faucibus arcu accumsan lorem.</td>
									<td>29.99</td>
								</tr>
								<tr>
									<td>Item Four</td>
									<td>Vitae integer tempus condimentum.</td>
									<td>19.99</td>
								</tr>
								<tr>
									<td>Item Five</td>
									<td>Ante turpis integer aliquet porttitor.</td>
									<td>29.99</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2"></td>
									<td>100.00</td>
								</tr>
							</tfoot>
						</table>
					</div>

					<h4>Alternate</h4>
					<div class="table-wrapper">
						<table class="alt">
							<thead>
								<tr>
									<th>Name</th>
									<th>Description</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Item One</td>
									<td>Ante turpis integer aliquet porttitor.</td>
									<td>29.99</td>
								</tr>
								<tr>
									<td>Item Two</td>
									<td>Vis ac commodo adipiscing arcu aliquet.</td>
									<td>19.99</td>
								</tr>
								<tr>
									<td>Item Three</td>
									<td>Morbi faucibus arcu accumsan lorem.</td>
									<td>29.99</td>
								</tr>
								<tr>
									<td>Item Four</td>
									<td>Vitae integer tempus condimentum.</td>
									<td>19.99</td>
								</tr>
								<tr>
									<td>Item Five</td>
									<td>Ante turpis integer aliquet porttitor.</td>
									<td>29.99</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2"></td>
									<td>100.00</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</section>

				<section>
					<h3 class="major">Buttons</h3>
					<ul class="actions">
						<li><a href="#" class="button primary">Primary</a></li>
						<li><a href="#" class="button">Default</a></li>
					</ul>
					<ul class="actions">
						<li><a href="#" class="button">Default</a></li>
						<li><a href="#" class="button small">Small</a></li>
					</ul>
					<ul class="actions">
						<li><a href="#" class="button primary icon solid fa-download">Icon</a></li>
						<li><a href="#" class="button icon solid fa-download">Icon</a></li>
					</ul>
					<ul class="actions">
						<li><span class="button primary disabled">Disabled</span></li>
						<li><span class="button disabled">Disabled</span></li>
					</ul>
				</section>

				<section>
					<h3 class="major">Form</h3>
					<form method="post" action="#">
						<div class="fields">
							<div class="field half">
								<label for="demo-name">Name</label> <input type="text"
									name="demo-name" id="demo-name" value="" placeholder="Jane Doe" />
							</div>
							<div class="field half">
								<label for="demo-email">Email</label> <input type="email"
									name="demo-email" id="demo-email" value=""
									placeholder="jane@untitled.tld" />
							</div>
							<div class="field">
								<label for="demo-category">Category</label> <select
									name="demo-category" id="demo-category">
									<option value="">-</option>
									<option value="1">Manufacturing</option>
									<option value="1">Shipping</option>
									<option value="1">Administration</option>
									<option value="1">Human Resources</option>
								</select>
							</div>
							<div class="field half">
								<input type="radio" id="demo-priority-low" name="demo-priority"
									checked> <label for="demo-priority-low">Low</label>
							</div>
							<div class="field half">
								<input type="radio" id="demo-priority-high" name="demo-priority">
								<label for="demo-priority-high">High</label>
							</div>
							<div class="field half">
								<input type="checkbox" id="demo-copy" name="demo-copy">
								<label for="demo-copy">Email me a copy</label>
							</div>
							<div class="field half">
								<input type="checkbox" id="demo-human" name="demo-human" checked>
								<label for="demo-human">Not a robot</label>
							</div>
							<div class="field">
								<label for="demo-message">Message</label>
								<textarea name="demo-message" id="demo-message"
									placeholder="Enter your message" rows="6"></textarea>
							</div>
						</div>
						<ul class="actions">
							<li><input type="submit" value="Send Message"
								class="primary" /></li>
							<li><input type="reset" value="Reset" /></li>
						</ul>
					</form>
				</section>

			</article>

		</div>

		<!-- Footer -->
		<footer id="footer">
			<p class="copyright">
				&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.
			</p>
		</footer>

	</div>

	<!-- BG -->
	<div id="bg"></div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>
