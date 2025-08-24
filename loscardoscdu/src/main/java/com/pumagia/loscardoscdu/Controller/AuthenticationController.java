package com.pumagia.loscardoscdu.Controller;

import com.pumagia.loscardoscdu.DTO.AuthLoginRequestDTO;
import com.pumagia.loscardoscdu.DTO.AuthResponseDTO;
import com.pumagia.loscardoscdu.Service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController



@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    //Todas estas requests y responses vamos a tratarlas como dto
    @PostMapping("/login")

    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }



}
