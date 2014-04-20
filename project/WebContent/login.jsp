<%@include file="/template/header.jsp"%>

<form class="form-signin" action="login" method="post">
	<h2 class="form-signin-heading">Login</h2>
	<input type="email" class="input-block-level" placeholder="Email"
		id="username" name="username"> <input type="password"
		class="input-block-level" placeholder="Mot de passe" id="password"
		name="password">


	<button class="btn btn-large btn-primary" type="submit">Submit</button>
</form>
<%@include file="/template/footer.jsp"%>
