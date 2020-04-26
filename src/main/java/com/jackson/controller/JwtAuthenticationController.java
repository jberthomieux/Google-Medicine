package com.jackson.controller;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.jackson.config.JwtTokenUtil;
//import com.jackson.config.JwtUserDetailService;
import com.jackson.config.TokenProvider;
import com.jackson.model.AuthToken;
import com.jackson.model.JwtRequest;
import com.jackson.model.JwtResponse;
import com.jackson.model.LoginUser;
import com.jackson.service.UserService;

@CrossOrigin(origins ="*", maxAge =3600)
@RestController
@RequestMapping("/token")
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationmanager;
	@Autowired
	private TokenProvider jwtTokenUtil;
	@Autowired
	private UserService userService;
	@RequestMapping(value ="/generate-token",method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException{
		final Authentication authentication = authenticationmanager.authenticate( new UsernamePasswordAuthenticationToken(
						loginUser.getUsername(),
						loginUser.getPassword()
						)
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		return ResponseEntity.ok(new AuthToken(token));
	}
}




//
//@RestController
//@CrossOrigin
//public class JwtAuthenticationController {
//@Autowired
//private AuthenticationManager authenticationManager;
//@Autowired
//private TokenProvider jwtTokenUtil;
////private JwtTokenUtil jwtTokenUtil;
////@Autowired
////private JwtUserDetailService userDetailsService;
//
//@RequestMapping(value ="/authenticate", method = RequestMethod.POST)
//public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
//authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//final UserDetails userDetails = userDetailsService
//.loadUserByUsername(authenticationRequest.getUsername());
//final String token = jwtTokenUtil.generateToken(userDetails);
//return ResponseEntity.ok(new JwtResponse(token));
//}
//
//private void authenticate(String username, String password) throws Exception {
//try {
//authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//} catch (DisabledException e) {
//throw new Exception("USER_DISABLED", e);
//} catch (BadCredentialsException e) {
//throw new Exception("INVALID_CREDENTIALS", e);
//}
//}
//}