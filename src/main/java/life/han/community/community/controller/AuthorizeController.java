package life.han.community.community.controller;

import life.han.community.community.Provider.GitHubProvider;
import life.han.community.community.dto.AccesstokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @GetMapping("/callback")

    public  String callback(@RequestParam(name="code") String code,
                            @RequestParam(name="state") String state){
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri("http://localhost:8888/callback");
        accesstokenDTO.setState(state);
        accesstokenDTO.setClient_id("931c7ff5a6cef5c89d08");
        accesstokenDTO.setClient_secret(" f3a90ffaca84cb85e75db963e1bf0960b09d7f82");
        gitHubProvider.getAccessToken(accesstokenDTO);
        return "index";
    }
}
