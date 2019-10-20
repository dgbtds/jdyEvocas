package cn.ihep.jdy.release.service.security;

import cn.ihep.jdy.release.dao.model.UserPrincipal;
import cn.ihep.jdy.release.dao.pojoRepository.UserRepository;
import cn.ihep.jdy.release.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByMobileAndStatus(username,1);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SALER");
        if ((user.getRoleId()==1007L)){

            GrantedAuthority grantedAuthority2 = new SimpleGrantedAuthority("ROLE_Manager");
            authorities.add(grantedAuthority2);
        }

        authorities.add(grantedAuthority);

        return new UserPrincipal(user.getMobile(),user.getPassWord(),user.getId(),authorities);//这是数据库里面的用户名密码构成的pringciple
    }

}
