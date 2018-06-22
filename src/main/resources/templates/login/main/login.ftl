<div class="container">
	
    <div class="card card-container">
    	<#if logout??>
			<div class="row">
				<center>
					<div class="alert alert-success">
						<span>${logout}</span>
					</div>
				</center>
			</div>
		</#if>
		<#if isError??>
			<span class="alert alert-warning">${isError}</span>
		</#if>
        
        <p id="profile-name" class="profile-name-card"></p>
        <form class="form-signin" role="form" action="/login/user" method="POST">
        	<input type="hidden" name="clientId" value="${clientId!''}" />
			<input type="hidden" name="redirectUri" value="${redirectUri!''}" />
        	<input type="hidden" name="state" value="${state!''}" />
        	<input type="hidden" name="responseType" value="${responseType!''}" />
        	        	        	
            <span id="reauth-email" class="reauth-email"></span>
            <input type="text" name="username" id="inputEmail" class="form-control" placeholder="Username" required autofocus>
            <input type="password" name="userpass" id="inputPassword" class="form-control" placeholder="Password" required>
            <div id="remember" class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
        </form><!-- /form -->
        <a href="#" class="forgot-password">
            Forgot the password?
        </a>
    </div><!-- /card-container -->
</div><!-- /container -->