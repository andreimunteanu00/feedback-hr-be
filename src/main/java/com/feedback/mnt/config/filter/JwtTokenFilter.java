package com.feedback.mnt.config.filter;

import com.feedback.mnt.dto.mapper.UserMapper;
import com.feedback.mnt.dto.role.RoleDTO;
import com.feedback.mnt.dto.user.UserSecurityDTO;
import com.feedback.mnt.repository.UserRepository;
import com.feedback.mnt.util.JwtTokenUtil;
import com.feedback.mnt.util.exception.UserNotActiveException;
import com.feedback.mnt.util.exception.UserNotFoundException;
import com.feedback.mnt.util.message.Message;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Strings.isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        final String token = header.split(" ")[1].trim();
        if (Strings.isEmpty(token)) throw new Error("token not found!");
        if (!jwtTokenUtil.validateToken(token)) throw new Error("invalid token");
        if (jwtTokenUtil.isTokenExpired(token)) throw new Error("token expired");
        UserSecurityDTO user = userMapper.toUserSecurityDTO(Objects.requireNonNull(userRepository
                .findByEmail(jwtTokenUtil.getEmail(token)).orElse(null)));
        if (user == null) throw new UserNotFoundException(Message.USER_NOT_FOUND);
        if (!user.getActive()) {
            throw new UserNotActiveException(String.format(Message.USER_NOT_ACTIVE, user.getEmail()));
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (RoleDTO role : user.getRoleSet()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}