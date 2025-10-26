package com.cibertec.biblioteca.rest;

import com.cibertec.biblioteca.entidades.Usuario;
import com.cibertec.biblioteca.negocio.JwtUserDetailsService;
import com.cibertec.biblioteca.negocio.UsuarioNegocio;
import com.cibertec.biblioteca.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class JwtAuthenticationRest {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioNegocio usuarioNegocio;
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println("INGRESO authenticate!!!");
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final Usuario usuario = usuarioNegocio.findByEmail(authenticationRequest.getEmail()); // Obtener el usuario

        final String token = jwtTokenUtil.generateToken(userDetails, usuario.getId(), usuario.getRole());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody Usuario user) throws Exception {
        System.out.println("INGRESO!!!");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(usuarioNegocio.grabar(user));
    }

    private void authenticate(String email, String password) throws Exception {
        System.out.println("email!!!");
        System.out.println(email);
        System.out.println("password!!!");
        System.out.println(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
